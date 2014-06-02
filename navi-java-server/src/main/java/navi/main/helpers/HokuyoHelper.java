package navi.main.helpers;

import navi.main.NaviConfig;
import navi.main.NaviObject;
import pl.edu.agh.amber.hokuyo.HokuyoProxy;
import pl.edu.agh.amber.hokuyo.MapPoint;
import pl.edu.agh.amber.hokuyo.Scan;

import java.io.IOException;
import java.util.List;

/* Use it as thread */
public class HokuyoHelper extends NaviObject<HokuyoListener>
        implements Runnable {

    private static final int SLEEP = NaviConfig.getHokuyoHelperSleep();

    private static final boolean HIGH_SENSITIVE = NaviConfig.getHokuyoHelperHighSensitive();

    private final HokuyoProxy hokuyoProxy;

    public HokuyoHelper(HokuyoProxy hokuyoProxy) throws IOException {
        this.hokuyoProxy = hokuyoProxy;
    }

    public List<MapPoint> getScan() throws Exception {
        Scan scan = hokuyoProxy.getSingleScan();
        return scan.getPoints();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Scan _scan = hokuyoProxy.getSingleScan();
                List<MapPoint> scan = _scan.getPoints();
                if (scan != null && hasListener()) {
                    getListener().notifyReceivedScan(scan);
                }
                Thread.sleep(SLEEP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
