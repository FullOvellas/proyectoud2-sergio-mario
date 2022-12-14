package com.sergiomario.countryapi;

import com.sergiomario.countryapi.dao.ServerDao;
import com.sergiomario.countryapi.model.country.Pais;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;

public class Server {

    private static final int SERVER_PORT = 8090;
    private static InetAddress serverAddress;
    private static DatagramSocket socket;
    private static DatagramPacket paquete;
    private static byte[] buffer;


    public static void main(String[] args) {

        try {

            socket = new DatagramSocket(SERVER_PORT);

            System.out.println(" ### Server escuchando en puerto " + SERVER_PORT + " ### ");

            escuchar();

        } catch (SocketException e) {

            System.out.println("Error al crear el servidor");
            System.exit(-1);

        }

    }

    private static void escuchar() {

        while(true) {

            buffer = new byte[1024];
            paquete = new DatagramPacket(buffer, buffer.length);

            try {

                socket.receive(paquete);
                String data = new String(paquete.getData(), paquete.getOffset(), paquete.getLength(), "UTF-8");

                if(data.startsWith("PING")) {

                    System.out.println("PING de " + paquete.getAddress());

                    enviar("PING", paquete.getAddress(), paquete.getPort());

                } else if(data.startsWith("CRED-") ) {

                    loginUser(data, paquete.getAddress(), paquete.getPort());

                } else if(data.startsWith("SEARCH-") ) {

                    String userToken = data.substring(data.indexOf("TOKEN-") + 6);

                    if(checkToken(userToken, paquete.getAddress().getHostAddress())) {

                        ArrayList<Pais> result = search(data);
                        enviarPaises(result, paquete.getAddress(), paquete.getPort());

                    } else {

                        System.out.println("Usuario con token incorrecto");

                    }

                } else if(data.startsWith("RAND") ) {

                    String userToken = data.substring(data.indexOf("TOKEN-") + 6);

                    if(checkToken(userToken, paquete.getAddress().getHostAddress())) {

                        String rawString = data.substring(data.indexOf("-") + 1);

                        rawString = rawString.substring(0, rawString.indexOf("-"));

                        int quantity = Integer.parseInt(rawString);

                        System.out.println("Paises aleatorios a " + paquete.getAddress().getHostAddress());
                        enviarPaises(ServerDao.instance.getRandomCountries(quantity), paquete.getAddress(), paquete.getPort());

                    } else {

                        System.out.println("Usuario con token incorrecto");

                    }

                }

            } catch (Exception ex ) {

                System.out.println("Error al escuchar");
                ex.printStackTrace();

            }

        }

    }

    private static boolean checkToken(String userToken, String ip) {

        if(ip.equals("127.0.0.1") ) {

            ip = "127.0.1.1";

        }

        ArrayList<String> tokens = ServerDao.instance.getIpTokens(ip);

        return tokens.contains(userToken);
    }

    private static ArrayList<Pais> search(String data ) {

        String countryName = data.substring(data.indexOf("-") + 1);
        ArrayList<Pais> result = new ArrayList<>();

        // Quitar "prefijo" SEARCH-
        data = data.substring(7);

        if(data.startsWith("NAME") ) {

            data = data.substring("NAME-".length());

            int searchLength = Integer.parseInt(data.substring(0, data.indexOf("-")));
            data = data.substring(data.indexOf("-") + 1);

            String searchName = data.substring(0, searchLength);

            System.out.println("B??squeda por nombre: " + searchName);

            result = ServerDao.instance.searchByName(searchName);

        } else if(data.startsWith("CURRENCY") ) {

            data = data.substring("CURRENCY-".length());

            int searchLength = Integer.parseInt(data.substring(0, data.indexOf("-")));
            data = data.substring(data.indexOf("-") + 1);

            String searchName = data.substring(0, searchLength);

            System.out.println("B??squeda por moneda:" + searchName);

            result = ServerDao.instance.searchByCurrency(searchName);

        } else if(data.startsWith("CAPITAL") ) {

            data = data.substring("CAPITAL-".length());

            int searchLength = Integer.parseInt(data.substring(0, data.indexOf("-")));
            data = data.substring(data.indexOf("-") + 1);

            String searchName = data.substring(0, searchLength);

            System.out.println("B??squeda por capital:" + searchName);

            result = ServerDao.instance.searchByCapital(searchName);

        } else if (data.startsWith("LANG") ) {

            data = data.substring("LANG-".length());

            int searchLength = Integer.parseInt(data.substring(0, data.indexOf("-")));
            data = data.substring(data.indexOf("-") + 1);

            String searchName = data.substring(0, searchLength);

            System.out.println("B??squeda por idioma:" + searchName);

            result = ServerDao.instance.searchByLanguage(searchName);

        }

        return result;
    }
    private static void enviar(String data, InetAddress address, int userPort ) {

        try {

            buffer = data.getBytes();
            paquete = new DatagramPacket(buffer, buffer.length, address, userPort);

            socket.send(paquete);

        } catch (IOException ex ) {

            System.out.println("Error al enviar a " + address.getHostAddress());

        }

    }

    private static void enviarPaises(ArrayList<Pais> paises, InetAddress address, int userPort) {

        try {

            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(byteOutput);


            paises.forEach( pais -> {

                try {

                    outputStream.writeObject(pais);

                } catch ( IOException e ) {

                }

            });

            outputStream.close();

            String data = Base64.getEncoder().encodeToString(byteOutput.toByteArray());

            enviar(data, address, userPort);

        } catch (IOException  ex ) {


        }


    }

    private static void loginUser(String rawCredentials, InetAddress address, int userPort) {

        String login, userToken = "ERROR";
        String packetIp = address.getHostAddress();
        String hashedIP, hashedCredentials;
        int loginLength;
        boolean correcto = false;

        rawCredentials = rawCredentials.substring(5);
        loginLength = Integer.parseInt(rawCredentials.substring(0, rawCredentials.indexOf("-")));

        rawCredentials = rawCredentials.substring(rawCredentials.indexOf("-") + 1);
        login = rawCredentials.substring(0, loginLength);

        rawCredentials = rawCredentials.substring(loginLength);
        hashedCredentials = rawCredentials.substring(0, 128);
        hashedIP = rawCredentials.substring(128);

        if(address.getHostAddress().equals("127.0.0.1") ) {

            // 127.0.1.1 es loopback de 127.0.0.1

            packetIp = "127.0.1.1";

        }

        if(hashString(packetIp).equals(hashedIP)) {

            String bbddCredentials = ServerDao.instance.getCredentials(login);

            if(bbddCredentials != null && bbddCredentials.equals(hashedCredentials)) {

                userToken = generarToken(login);

                ServerDao.instance.registrarToken(userToken, packetIp, login);

                System.out.println("Login: " + login + " -- Token: " + userToken);

            }

        }

        // Independientemente de si las credenciales son correctas o no se env??a un mensaje
        enviar(userToken, address, userPort);

    }

    private static String generarToken(String userLogin) {

        SecureRandom secureRandom = new SecureRandom();
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();
        byte[] randomBytes = new byte[24];

        secureRandom.nextBytes(randomBytes);

        return base64Encoder.encodeToString(randomBytes);
    }

    private static String hashString(String rawData ) {

        String out = null;

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            String salt = "renaido";

            md.update(salt.getBytes());
            byte[] hashBytes = md.digest(rawData.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();

            for (byte hashByte : hashBytes) {

                sb.append(Integer.toString((hashByte & 0xff) + 0x100, 16).substring(1));

            }

            out = sb.toString();

        } catch (NoSuchAlgorithmException e) {


        }

        return out;
    }

}
