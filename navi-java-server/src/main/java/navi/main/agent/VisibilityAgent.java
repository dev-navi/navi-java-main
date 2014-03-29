package navi.main.agent;

import navi.main.NaviConfig;
import navi.main.NaviObject;
import navi.main.dto.Visibility;
import navi.main.helpers.HokuyoHelper;
import navi.main.helpers.HokuyoListener;
import pl.edu.agh.amber.hokuyo.MapPoint;

import java.util.List;

/* Do not use it as a thread. */
public class VisibilityAgent extends NaviObject<VisibilityListener>
        implements Runnable, HokuyoListener {

    private static final int SLEEP = NaviConfig.getVisibilityAgentSleep();

    private final HokuyoHelper eyeHelper;

    public VisibilityAgent(HokuyoHelper eyeHelper) {
        this.eyeHelper = eyeHelper;
        eyeHelper.setListener(this);
    }

    @Override
    public void notifyReceivedScan(List<MapPoint> scan) {
        if (hasListener()) {
            Visibility visibility = new Visibility(scan);
            getListener().notifyVisibilityReceived(visibility);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                List<MapPoint> scan = eyeHelper.getScan();
                if (scan != null) {
                    Visibility visibility = new Visibility(scan);
                    getListener().notifyVisibilityReceived(visibility);
                }
                Thread.sleep(SLEEP);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
