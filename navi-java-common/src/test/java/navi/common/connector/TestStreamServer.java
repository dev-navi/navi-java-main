package navi.common.connector;

import navi.common.connector.server.ServerConnector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * Created by paoolo on 27.03.14.
 */
public class TestStreamServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        InetAddress address = InetAddress.getByName("0.0.0.0");
        int port = 1234;
        ServerSocket socket = new ServerSocket();

        socket.bind(new InetSocketAddress(address, port));
        ServerConnector server = new ServerConnector(socket);

        server.setListener(new StreamServerListener());
        server.start();
    }
}
