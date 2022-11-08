package com.sergiomario.countryapi.dao;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase para gestionar la conexión con el servidor y para enviar y recibir datos del servidor
 */
public class Cliente {

    public static Cliente instance;
    public static final int SERVER_PORT = 8090;

    private InetAddress adr;
    private DatagramSocket socket;
    private String token;

    static {

        instance = new Cliente();

    }

    private Cliente() {

        token = "";

    }

    /**
     * Getter del token de sesión
     * @return string del token
     */
    public String getToken() {
        return token;
    }

    /**
     * A partir de una IP intentará conectarse con el servidor
     * @param ip la IP del servidor
     * @return true si se puede realizar la conexión
     */
    public boolean configurarConexion(String ip) {

        boolean out;

        try {

            socket = new DatagramSocket();
            socket.setSoTimeout(3000);
            adr = InetAddress.getByName(ip);

            out = enviarPing(ip);

        } catch (UnknownHostException | SocketException e) {

            out = false;

        }

        return out;
    }

    /**
     * Envía un ping a una IP para comprobar si es un servidor
     * @param ip la IP para comprobar
     * @return true si el ping volvio
     */
    public boolean enviarPing(String ip ) {

        boolean out = false;

        try {

            enviar("PING");
            String r = recibir();

            if(r != null && !r.equals("ERROR") ) {

                out = true;

            }

        } catch (SocketException e) {



        }

        return out;
    }

    /**
     * Método para iniciar sesión contra el servidor
     * @param user el login de usuario
     * @param passwd la contraseña del usuario
     * @return un token de acceso o null si no se pudo hacer login. Si las credenciales no son
     * correctas devolverá la cadena "ERROR"
     */
    public String enviarCredenciales(String user, String passwd) throws SocketException {

        String ipAddress = getLocalIpAddress();
        String newToken = "ERROR";

        if(ipAddress != null ) {

            String hashedCredentialData = hashString(passwd);
            String hashedIPData = hashString(ipAddress);

            // Para separar los hashes se tiene en cuenta que
            // independientemente de la entrada, la longitud del
            // string del hash es 128 caracteres

                // "tipo mensaje" - longitud del login - login    -     hash contraseña    - hash IP
            enviar( "CRED-" + user.length() + "-" + user + "" + hashedCredentialData + hashedIPData);

            newToken = recibir();

            if(newToken != null && !newToken.equals("ERROR") ) {

                this.token = newToken;

            }

        }

        return newToken;
    }

    /**
     * Obtiene un hash de una cadena
     * @param rawData la cadena para hacer el hash
     * @return el hash de la cadena
     */
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

    /**
     * Obtiene la IP conectada a internet de la máquina del cliente
     * @return null si no se puede encontrar la IP o una cadena con la IP
     */
    private String getLocalIpAddress() {

        String ipAddress = null;

        if(adr.getHostAddress().equals("127.0.0.1") ) {

            ipAddress = "127.0.1.1";

        } else {

            try( DatagramSocket socket = new DatagramSocket()){

                socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                ipAddress = socket.getLocalAddress().getHostAddress();

            } catch (SocketException | UnknownHostException e) {



            }

        }

        return ipAddress;
    }

    /**
     * Envía una cadena al servidor configurado
     * @param datos los datos a enviar
     * @throws SocketException si no se pudieron enviar los datos
     */
    public void enviar(String datos ) throws SocketException {

        byte[] buffer = datos.getBytes();
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, adr, SERVER_PORT);

        if(socket == null ) {

            throw new SocketException();

        }

        try {

            socket.send(paquete);

        } catch (IOException e) {

            throw new SocketException();

        }

    }

    /**
     * Método para escuchar una respuesta del servidor
     * @return una cadena con la respuesta del servidor
     * @throws SocketException si no se pudo conectar con el servidor
     */
    public String recibir() throws SocketException{

        String data;

        try {

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            socket.receive(packet);
            data = new String(packet.getData(), packet.getOffset(), packet.getLength(), "UTF-8");

        } catch (IOException ex ) {

            throw new SocketException();

        }

        return data;
    }

}
