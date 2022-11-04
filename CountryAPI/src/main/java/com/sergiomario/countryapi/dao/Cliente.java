package com.sergiomario.countryapi.dao;

import java.io.IOException;
import java.net.*;

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

            adr = InetAddress.getByName(ip);
            socket = new DatagramSocket();

        } catch (UnknownHostException | SocketException e) {

            out = false;

        }

        return out;
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
