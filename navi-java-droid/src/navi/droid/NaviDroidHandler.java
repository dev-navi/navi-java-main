package navi.droid;

import navi.droid.helper.NaviDroidDriver;
import navi.droid.helper.NaviDroidNetwork;

public class NaviDroidHandler {

    private static final NaviDroidDriver driver = new NaviDroidDriver();

    private static final NaviDroidNetwork network = new NaviDroidNetwork();

    private static final Object mainLock = new Object();

    private static NaviDroidMain main;

    public static void setMain(NaviDroidMain main) {
        synchronized (mainLock) {
            NaviDroidHandler.main = main;
        }
    }

    public static NaviDroidMain getMain() {
        synchronized (mainLock) {
            return main;
        }
    }

    public static NaviDroidDriver getDriver() {
        return driver;
    }

    public static NaviDroidNetwork getNetwork() {
        return network;
    }
}
