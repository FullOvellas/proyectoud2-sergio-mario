package com.sergiomario.countryapi.dao;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cliente {

    public static Cliente instance;
    public static final int SERVER_PORT = 8090;

    private InetAddress adr; // server
    private DatagramSocket socket;

    static {

        instance = new Cliente();

    }

    private Cliente() {}

    public boolean configurarConexion(String ip) {

        boolean out = true;

        try {

            socket = new DatagramSocket();
            socket.setSoTimeout(3000);
            adr = InetAddress.getByName(ip);

        } catch (UnknownHostException | SocketException e) {

            out = false;

        }

        return out;
    }

    /**
     * Método para iniciar sesión contra el servidor
     * @param user el login de usuario
     * @param passwd la contraseña del usuario
     * @return un token de acceso o null si no se pudo hacer login
     */
    public String enviarCredenciales(String user, String passwd) {

        String ipAddress = getLocalIpAddress();
        String token = "ERROR";

        if(ipAddress != null ) {

            System.out.println(ipAddress);

            String hashedCredentialData = hashString(passwd);
            String hashedIPData = hashString(ipAddress);

            // Para separar los hashes se tiene en cuenta que
            // independientemente de la entrada, la longitud del
            // string del hash es 128 caracteres

                // "tipo mensaje" - longitud del login - login    -     hash contraseña    - hash IP
            enviar( "CRED-" + user.length() + "-" + user + "" + hashedCredentialData + hashedIPData);
            token = recibir();

        }

        return token;
    }

    public String hashString(String rawData ) {

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

    private String getLocalIpAddress() {

        String ipAddress = null;

        try {

            InetAddress address = InetAddress.getLocalHost();

            ipAddress = address.getHostAddress();

        } catch (UnknownHostException ex) {

        }

        return ipAddress;
    }

    private boolean enviar(String datos ) {

        boolean out = true;

        byte[] buffer = datos.getBytes();
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, adr, SERVER_PORT);

        try {

            socket.send(paquete);

        } catch (IOException e) {

            out = false;

        }

        return out;
    }

    private String recibir() {

        String data = null;

        try {

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            socket.receive(packet);
            data = new String(packet.getData(), packet.getOffset(), packet.getLength(), "UTF-8");

            System.out.println("Recibido   " + data);

        } catch (IOException ex ) {



        }

        return data;
    }

}
