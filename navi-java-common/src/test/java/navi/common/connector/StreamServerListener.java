package navi.common.connector;

import navi.common.connector.client.ClientConnector;
import navi.common.connector.server.ServerListener;

/**
 * Created by paoolo on 27.03.14.
 */
public class StreamServerListener implements ServerListener {

    @Override
    public void notifyNewClient(ClientConnector client) {
        client.setListener(new StreamClientListener());
        client.start();
    }
}
