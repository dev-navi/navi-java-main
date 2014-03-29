package navi.main.server;

import navi.common.connector.client.ClientConnector;
import navi.common.connector.server.ServerListener;
import navi.main.agent.DriveAgent;

class MainServerListener implements ServerListener {

    private final DriveAgent driveAgent;

    MainServerListener(DriveAgent driveAgent) {
        this.driveAgent = driveAgent;
    }

    @Override
    public void notifyNewClient(ClientConnector client) {
        client.setListener(new MainClientListener(driveAgent));
        client.start();
    }
}
