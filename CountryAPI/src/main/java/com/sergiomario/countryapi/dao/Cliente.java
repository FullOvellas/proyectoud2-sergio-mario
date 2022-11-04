package com.sergiomario.countryapi.dao;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

public class Cliente {

    public static Cliente instance;
    public static final int SERVER_PORT = 8090;

    private InetAddress adr; // server
    private DatagramSocket socket;
    private DatagramPacket paquete;
    private byte[] buffer;

    static {

        instance = new Cliente();

    }

    private Cliente() {

    }

    public boolean configurarConexion(String ip) {

        boolean out = true;

        try {

            socket = new DatagramSocket();
            adr = InetAddress.getByName(ip);







        } catch (UnknownHostException | SocketException e) {

            out = false;

        }

        return out;
    }

    public String enviarCredenciales(String user, String passwd) {

        String macAddress = getLocalMACAddress();
        String token = null;

        if(macAddress != null ) {

            String rawData = "USER?:" + user + "||PASSWORD?:" + passwd + "||MAC?:" + macAddress;
            String hashedData = hashCredenciales(rawData);

            enviar( "CRED-" + hashCredenciales(rawData));

        }

        return token;
    }

    public String hashCredenciales(String rawData ) {

        String out = null;

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            String salt = "renaido";

            md.update(salt.getBytes());
            byte[] hashBytes = md.digest(rawData.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();

            for(int i=0; i< hashBytes.length ;i++){

                sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));

            }

            out = sb.toString();

        } catch (NoSuchAlgorithmException e) {


        }

        return out;
    }

    private String getLocalMACAddress() {

        String macAddress = null;

        try(final DatagramSocket socketTemp = new DatagramSocket()){

            socketTemp.connect(InetAddress.getByName("8.8.8.8"), 10002);

            NetworkInterface ni = NetworkInterface.getByInetAddress(socketTemp.getLocalAddress());
            byte[] hardwareAddress = ni.getHardwareAddress();
            String[] hexadecimalFormat = new String[hardwareAddress.length];

            for (int i = 0; i < hardwareAddress.length; i++) {

                hexadecimalFormat[i] = String.format("%02X", hardwareAddress[i]);

            }

            macAddress = String.join("-", hexadecimalFormat);

        } catch (SocketException | UnknownHostException ex) {


        }

        return macAddress;
    }

    private boolean enviar(String datos ) {

        boolean out = true;

        buffer = datos.getBytes();
        paquete = new DatagramPacket(buffer, buffer.length, adr, SERVER_PORT);

        try {

            socket.send(paquete);

        } catch (IOException e) {

            out = false;

        }

        return out;
    }



}
