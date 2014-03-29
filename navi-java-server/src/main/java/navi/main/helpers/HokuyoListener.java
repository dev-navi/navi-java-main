package navi.main.helpers;

import navi.main.NaviListener;
import pl.edu.agh.amber.hokuyo.MapPoint;

import java.util.List;

public interface HokuyoListener extends NaviListener {

    public void notifyReceivedScan(List<MapPoint> scan);
}
