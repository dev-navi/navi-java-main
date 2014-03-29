package navi.droid.helper;

import navi.droid.NaviDroidHandler;

public class NaviDroidDriver {

    private volatile boolean running = false;

    private volatile boolean allowLeft = false, allowRight = false;

    private volatile float correction = 0;

    public void sendSpeed(int leftSpeed, int rightSpeed) {
        if (running) {

            if (correction > 0) {
                leftSpeed = (int) (leftSpeed - leftSpeed * correction * 0.1);
                rightSpeed = (int) (rightSpeed + rightSpeed * correction * 0.1);
            } else {
                leftSpeed = (int) (leftSpeed + leftSpeed * correction * 0.1);
                rightSpeed = (int) (rightSpeed - rightSpeed * correction * 0.1);
            }

            NaviDroidHandler.getNetwork().send(
                    (allowLeft ? leftSpeed : 0),
                    (allowRight ? rightSpeed : 0));
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setCorrection(float correction) {
        this.correction = correction;
    }

    public void setAllowLeft(boolean allow) {
        allowLeft = allow;
    }

    public void setAllowRight(boolean allow) {
        allowRight = allow;
    }
}
