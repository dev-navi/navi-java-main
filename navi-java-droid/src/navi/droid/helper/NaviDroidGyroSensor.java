package navi.droid.helper;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import navi.droid.NaviDroidHandler;
import navi.droid.NaviDroidPreference;

public class NaviDroidGyroSensor implements SensorEventListener {

    private final boolean swapSensorOrientation;

    private final SpeedGyroCalculator speedGyroCalculator = new SpeedGyroCalculator();

    private float[] oldValues;

    public NaviDroidGyroSensor(boolean swapSensorOrientation) {
        this.swapSensorOrientation = swapSensorOrientation;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] values = lowPass(sensorEvent.values, oldValues);
        oldValues = values;

        float z = values[2];

        // w prawo na minusie
        // w lewo na plusie

        if (swapSensorOrientation) {
            z = -z;
        }

        NaviDroidHandler.getDriver().setCorrection(z);
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
