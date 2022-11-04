package com.sergiomario.countryapi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {

    private static final int SERVER_PORT = 8090;
    private static InetAddress addressr;
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
                System.out.println("Recibido " + new String(paquete.getData(), paquete.getOffset(), paquete.getLength(), "UTF-8"));


            } catch (IOException ex ) {

                System.out.println("Error al enviar");

            }

        }

    }


}
