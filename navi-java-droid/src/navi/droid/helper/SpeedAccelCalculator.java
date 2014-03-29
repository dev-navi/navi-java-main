package navi.droid.helper;

import navi.droid.NaviDroidPreference;

/**
 * @author Piotr Borowiec
 * @author Dawid Kala
 * @author Pawel Suder
 */
class SpeedAccelCalculator {

    private static final float MIN_LOCAL_X = -1.0f;
    private static final float MAX_LOCAL_X = 1.0f;

    private static final float MIN_LOCAL_Y = -1.0f;
    private static final float MAX_LOCAL_Y = 1.0f;

    private float convertToLocalX(float globalX) {
        float globalRangeX = NaviDroidPreference.getMaxGlobalX() - NaviDroidPreference.getMinGlobalX();
        float localRangeX = MAX_LOCAL_X - MIN_LOCAL_X;
        return ((globalX - NaviDroidPreference.getMinGlobalX()) * localRangeX) / globalRangeX + MIN_LOCAL_X;
    }

    private float convertToLocalY(float globalY) {
        float globalRangeY = NaviDroidPreference.getMaxGlobalY() - NaviDroidPreference.getMinGlobalY();
        float localRangeY = MAX_LOCAL_Y - MIN_LOCAL_Y;
        return ((globalY - NaviDroidPreference.getMinGlobalY()) * localRangeY) / globalRangeY + MIN_LOCAL_Y;
    }

    private int calculateForwardSpeed(float val) {
        return (int) (convertToLocalX(val) * NaviDroidPreference.getMaxSpeed());
    }

    private int calculateTurningSpeed(float val) {
        return (int) (convertToLocalY(val) * NaviDroidPreference.getMaxTurningSpeed());
    }

    private int transformTurningSpeed(int turningSpeed, int forwardSpeed) {
        float maxSpeed = NaviDroidPreference.getMaxSpeed();
        return (int) (turningSpeed * ((maxSpeed - forwardSpeed) / maxSpeed));
    }

    public int calculateRightWheelSpeed(float x, float y) {
        int forwardSpeed, turningSpeed;
        forwardSpeed = calculateForwardSpeed(x);
        turningSpeed = calculateTurningSpeed(y);
        return forwardSpeed - transformTurningSpeed(turningSpeed, forwardSpeed);
    }

    public int calculateLeftWheelSpeed(float x, float y) {
        int forwardSpeed, turningSpeed;
        forwardSpeed = calculateForwardSpeed(x);
        turningSpeed = calculateTurningSpeed(y);
        return forwardSpeed + transformTurningSpeed(turningSpeed, forwardSpeed);
    }
}
