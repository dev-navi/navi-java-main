package navi.main.helpers;

import navi.main.NaviConfig;
import navi.main.NaviConst;
import navi.main.NaviObject;
import pl.edu.agh.amber.roboclaw.MotorsCurrentSpeed;
import pl.edu.agh.amber.roboclaw.RoboclawProxy;

import java.io.IOException;

/* Use it as thread */
public class RoboclawHelper extends NaviObject<RoboclawListener>
        implements Runnable {

    private static final int SLEEP = NaviConfig.getRoboclawHelperSleep();

    private final RoboclawProxy roboclawProxy;

    public RoboclawHelper(RoboclawProxy roboclawProxy) throws IOException {
        this.roboclawProxy = roboclawProxy;
        roboclawProxy.sendMotorsCommand(0, 0, 0, 0);
    }

    public void drive(double leftSpeed, double rightSpeed) {
        drive((int) leftSpeed, (int) rightSpeed);
    }

    public void drive(int leftSpeed, int rightSpeed) {
        leftSpeed = (leftSpeed > NaviConst.MAXIMUM_SPEED ? NaviConst.MAXIMUM_SPEED : leftSpeed);
        rightSpeed = (rightSpeed > NaviConst.MAXIMUM_SPEED ? NaviConst.MAXIMUM_SPEED : rightSpeed);

        leftSpeed = (leftSpeed < -NaviConst.MAXIMUM_SPEED ? -NaviConst.MAXIMUM_SPEED : leftSpeed);
        rightSpeed = (rightSpeed < -NaviConst.MAXIMUM_SPEED ? -NaviConst.MAXIMUM_SPEED : rightSpeed);

        try {
            roboclawProxy.sendMotorsCommand(leftSpeed, rightSpeed, leftSpeed, rightSpeed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                MotorsCurrentSpeed roboclawData = roboclawProxy.getCurrentMotorsSpeed();
                roboclawData.waitAvailable();
                if (roboclawData.isAvailable() && hasListener()) {
                    getListener().notifyReceivedSpeedData(roboclawData);
                }
                Thread.sleep(SLEEP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
