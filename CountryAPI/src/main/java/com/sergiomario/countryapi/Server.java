package com.sergiomario.countryapi;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {

    private static final int SERVER_PORT = 8090;
    private static InetAddress addressr;
    private static DatagramSocket socket;
    private static DatagramPacket paquete;
    private byte[] buffer;


    public static void main(String[] args) {

        try {

            socket = new DatagramSocket(SERVER_PORT);

        } catch (SocketException e) {

            System.out.println("**Error al crear el servidor");
            System.exit(-1);

        }

    }

}
