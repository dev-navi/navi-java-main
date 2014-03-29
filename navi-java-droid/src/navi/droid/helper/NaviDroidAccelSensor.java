package navi.droid.helper;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import navi.droid.NaviDroidHandler;
import navi.droid.NaviDroidPreference;

public class NaviDroidAccelSensor implements SensorEventListener {

    private final boolean swapSensorOrientation;

    private final SpeedAccelCalculator speedAccelCalculator = new SpeedAccelCalculator();

    private float[] oldValues;

    public NaviDroidAccelSensor(boolean swapSensorOrientation) {
        this.swapSensorOrientation = swapSensorOrientation;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] values = lowPass(sensorEvent.values, oldValues);
        oldValues = values;

        float x = values[0];
        float y = values[1];

        if (swapSensorOrientation) {
            float tmp = x;
            x = y;
            y = tmp;
        }

        int rightSpeed = speedAccelCalculator.calculateRightWheelSpeed(x, y);
        int leftSpeed = speedAccelCalculator.calculateLeftWheelSpeed(x, y);

        NaviDroidHandler.getDriver().sendSpeed(leftSpeed, rightSpeed);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    private static float[] lowPass(float[] input, float[] output) {
        if (output == null) {
            return input;
        }

        for (int i = 0; i < input.length; i++) {
            output[i] = output[i] + NaviDroidPreference.getAlpha() * (input[i] - output[i]);
        }
        return output;
    }
}
