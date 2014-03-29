package navi.main.tool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class ContinuumMap extends HashMap<Double, Double> {

    private static final double MIN = 50.0;

    private final TreeSet<Double> angles = new TreeSet<Double>();

    @Override
    public Double put(Double angle, Double length) {
        angles.add(angle);
        return super.put(angle, length);
    }

    public Double get(double angle, double range) {
        double min = 5000.0;

        for (Pair<Double, Double> value : iterateInRange(angle - range, angle + range)) {
            if (min > value.second && value.second > MIN) {
                min = value.second;
            }
        }

        return min;
    }

    @Override
    public Double remove(Object key) {
        try {
            if (key instanceof Double) {
                angles.remove(key);
            }
            return super.remove(key);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Iterable<Pair<Double, Double>> iterateInRange(final double start, final double stop) {
        return new Iterable<Pair<Double, Double>>() {

            @Override
            public Iterator<Pair<Double, Double>> iterator() {
                return new Iterator<Pair<Double, Double>>() {
                    double index = angles.floor(start);
                    double end = angles.ceiling(stop);

                    @Override
                    public boolean hasNext() {
                        return index < end;
                    }

                    @Override
                    public Pair<Double, Double> next() {
                        try {
                            return new Pair<Double, Double>(index, ContinuumMap.this.get(index));
                        } finally {
                            index = angles.higher(index);
                        }
                    }

                    @Override
                    public void remove() {
                    }
                };
            }
        };
    }
}
