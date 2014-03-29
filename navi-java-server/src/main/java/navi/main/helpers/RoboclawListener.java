package navi.main.helpers;

import navi.main.NaviListener;
import pl.edu.agh.amber.roboclaw.MotorsCurrentSpeed;

public interface RoboclawListener extends NaviListener {

    public void notifyReceivedSpeedData(MotorsCurrentSpeed speed);
}
