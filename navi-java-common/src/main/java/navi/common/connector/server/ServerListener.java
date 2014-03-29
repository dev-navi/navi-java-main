package navi.common.connector.server;

import navi.common.connector.client.ClientConnector;

public interface ServerListener {

    void notifyNewClient(ClientConnector client);
}
