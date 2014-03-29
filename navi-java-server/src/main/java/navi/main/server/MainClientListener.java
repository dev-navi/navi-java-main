package navi.main.server;

import com.google.protobuf.InvalidProtocolBufferException;
import navi.common.connector.client.ClientListener;
import navi.common.proto.CommonProto;
import navi.main.agent.DriveAgent;

class MainClientListener implements ClientListener {

    private final DriveAgent driveAgent;

    MainClientListener(DriveAgent driveAgent) {
        this.driveAgent = driveAgent;
    }

    @Override
    public void notifyReceived(byte[] object) {
        try {
            CommonProto.ControlMessage msg = CommonProto.ControlMessage.parseFrom(object);
            switch (msg.getType()) {
                case CHANGE:
                    driveAgent.change(msg.getLeft(), msg.getRight());
                    break;
                case SET:
                    driveAgent.set(msg.getLeft(), msg.getRight());
                    break;
            }
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyDisconnected() {
        driveAgent.set(0, 0);
    }
}
