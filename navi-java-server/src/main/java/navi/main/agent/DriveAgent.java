package navi.main.agent;

import navi.main.NaviConfig;
import navi.main.NaviObject;
import navi.main.dto.Visibility;
import navi.main.helpers.RoboclawHelper;

/* Use it as a thread */
public class DriveAgent extends NaviObject<DriveListener>
        implements Runnable, VisibilityListener {

    private static final double ALPHA = 0.25;

    private static final int SLEEP = NaviConfig.getDriveAgentSleep();

    private final RoboclawHelper driveHelper;

    private volatile double left = 0, right = 0;

    public DriveAgent(RoboclawHelper driveHelper) {
        this.driveHelper = driveHelper;
    }

    public void set(int l, int r) {
        left = l;
        right = r;
    }

    public void change(int l, int r) {
        left += l;
        right += r;
    }

    @Override
    public void notifyVisibilityReceived(Visibility visibility) {
        System.err.println(visibility);
    }

    @Override
    public void run() {
        double currentLeft = 0, currentRight = 0, oldLeft = 0, oldRight = 0;
        try {
            while (true) {
                try {
                    currentLeft = lowPass(left, oldLeft);
                    currentRight = lowPass(right, oldRight);

                    driveHelper.drive(currentLeft, currentRight);
                } finally {
                    oldLeft = currentLeft;
                    oldRight = currentRight;
                }
                Thread.sleep(SLEEP);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static double lowPass(double currentValue, double oldValue) {
        return (oldValue == 0 ? currentValue : (oldValue + ALPHA * (currentValue - oldValue)));
    }
}
