package navi.main.server;

import navi.common.connector.server.ServerConnector;
import navi.main.NaviConfig;
import navi.main.agent.DriveAgent;
import navi.main.agent.VisibilityAgent;
import navi.main.helpers.HokuyoHelper;
import navi.main.helpers.RoboclawHelper;
import pl.edu.agh.amber.common.AmberClient;
import pl.edu.agh.amber.hokuyo.HokuyoProxy;
import pl.edu.agh.amber.roboclaw.RoboclawProxy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * Contains heart of automated agent, to driving robo, using information
 * from tracking system and digital eye.
 */
public class Main {

    private final static String AMBER_IP = NaviConfig.getAmberIp();
    private final static int AMBER_PORT = NaviConfig.getAmberPort();

    private final static String CONNECTOR_IP = NaviConfig.getConnectorIp();
    private final static int CONNECTOR_PORT = NaviConfig.getConnectorPort();

    private final static boolean EYE_ENABLED = NaviConfig.isEyeEnabled();

    private static RoboclawHelper createRoboclawHelper(AmberClient amberClient) throws IOException {
        RoboclawProxy roboclawProxy = new RoboclawProxy(amberClient, 0);
        return new RoboclawHelper(roboclawProxy);
    }

    private static HokuyoHelper createHokuyoHelper(AmberClient amberClient) throws IOException {
        HokuyoProxy hokuyoProxy = new HokuyoProxy(amberClient, 0);
        return new HokuyoHelper(hokuyoProxy);
    }

    private static ServerConnector createServer(String ip, int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(ip, port));
        return new ServerConnector(serverSocket);
    }

    public static void main(String[] args) throws Exception {

        AmberClient amberClient = new AmberClient(AMBER_IP, AMBER_PORT);

        RoboclawHelper roboclawHelper = createRoboclawHelper(amberClient);
        DriveAgent driveAgent = new DriveAgent(roboclawHelper);

        if (EYE_ENABLED) {
            HokuyoHelper hokuyoHelper = createHokuyoHelper(amberClient);
            VisibilityAgent visibilityAgent = new VisibilityAgent(hokuyoHelper);

            visibilityAgent.setListener(driveAgent);
            new Thread(hokuyoHelper).start();
        }

        new Thread(driveAgent).start();

        ServerConnector server = createServer(CONNECTOR_IP, CONNECTOR_PORT);
        server.setListener(new MainServerListener(driveAgent));
        server.start();

        System.err.println("Ready.");
    }
}
