package com.sergiomario.countryapi;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Server {

    private static final int SERVER_PORT = 8090;
    private static InetAddress address;
    private static DatagramSocket socket;
    private static DatagramPacket paquete;
    private static byte[] buffer;


    public static void main(String[] args) {

        try {

            socket = new DatagramSocket(SERVER_PORT);

            System.out.println(" ### Server escuchando en puerto " + SERVER_PORT + " ### ");

            escuchar();

        } catch (SocketException e) {

            System.out.println("**Error al crear el servidor");
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

                if(data.startsWith("CRED-") ) {

                    System.out.println(data);
                    loginUser(data, paquete.getAddress(), paquete.getPort());

                }

            } catch (IOException ex ) {

                System.out.println("Error al escuchar a " + address.getHostAddress());

            }

        }

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

    private static void loginUser(String rawCredentials, InetAddress address, int userPort) {

        String login, userToken = "ERROR";
        String packetIp = address.getHostAddress();
        String hashedIP, hashedCredentials;
        int loginLength;
        boolean correcto = false;

        rawCredentials = rawCredentials.substring(5);
        loginLength = Integer.parseInt(rawCredentials.substring(0, rawCredentials.indexOf("-")));

        rawCredentials = rawCredentials.substring(2);
        login = rawCredentials.substring(0, loginLength);

        rawCredentials = rawCredentials.substring(loginLength);
        hashedCredentials = rawCredentials.substring(0, 128);
        hashedIP = rawCredentials.substring(128);

        if(address.getHostAddress().equals("127.0.0.1") ) {

            // Debido a que 127.0.1.1 es loopback de 127.0.0.1

            // TODO: es un parche para funcionar en localhost
            packetIp = "127.0.1.1";

        }

        if(hashString(packetIp).equals(hashedIP)) {

            String tempUser = "user";
            String tempPassword = "1234";

            if(tempUser.equals(login) && hashString(tempPassword).equals(hashedCredentials)) {

                userToken = generarToken(login);

            }

        }

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
