package navi.droid.helper;

import navi.common.connector.client.ClientConnector;
import navi.common.connector.client.ClientListener;
import navi.common.proto.CommonProto;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class NaviDroidNetwork {

    private Socket socket;

    private ClientConnector connectorClient;

    public boolean isConnected() {
        return (socket != null);
    }

    public void connect(String hostname, int port, ClientListener listener) throws IOException {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(hostname, port));

            connectorClient = new ClientConnector(socket);
            connectorClient.setListener(listener);
            connectorClient.start();
        } catch (IOException e) {
            socket = null;
            throw e;
        }
    }

    public void disconnect() throws IOException {
        try {
            if (connectorClient != null) {
                connectorClient.interrupt();
            }
            if (socket != null) {
                socket.close();
            }
        } finally {
            connectorClient = null;
            socket = null;
        }
    }

    public void send(int leftSpeed, int rightSpeed) {
        if (connectorClient != null) {
            connectorClient.send(getMessage(leftSpeed, rightSpeed));
        }
    }

    private static byte[] getMessage(int left, int right) {
        return CommonProto.ControlMessage.newBuilder().setType(CommonProto.Type.SET)
                .setLeft(left).setRight(right).build().toByteArray();
    }
}
