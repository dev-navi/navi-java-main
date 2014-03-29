package navi.main.dto;

import navi.main.tool.ContinuumMap;
import navi.main.tool.Pair;
import pl.edu.agh.amber.hokuyo.MapPoint;

import java.util.List;

public final class Visibility {

    private final ContinuumMap visibility;

    public Visibility(List<MapPoint> points) {
        this.visibility = new ContinuumMap();
        for (MapPoint point : points) {
            visibility.put(point.getAngle(), point.getDistance());
        }
    }

    public Iterable<Pair<Double, Double>> iterateInRange(double start, double stop) {
        return visibility.iterateInRange(start, stop);
    }
}
