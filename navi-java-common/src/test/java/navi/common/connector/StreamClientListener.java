package navi.common.connector;

import navi.common.connector.client.ClientListener;

import java.util.Arrays;

/**
 * Created by paoolo on 27.03.14.
 */
class StreamClientListener implements ClientListener {
    @Override
    public void notifyReceived(byte[] object) {
        System.err.println("Received: " + Arrays.toString(object));
    }

    @Override
    public void notifyDisconnected() {
        System.err.println("Disconnected");
    }
}
