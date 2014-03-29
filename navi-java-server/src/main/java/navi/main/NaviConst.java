package navi.main;

public final class NaviConst {

    // spacing between wheels in [mm]
    public static final double SPACING = 220.0;
    // minimal distance [mm]
    public static final double MIN_DISTANCE = 260.0;
    // warn distance [mm]
    public static final double WARN_DISTANCE = 520.0;
    // tempo range
    public static final double TEMPO_RANGE = 40.0;
    // maximum speed
    public static final int MAXIMUM_SPEED = 600;

    public static final String MAIN_PROPERTIES = "main.properties";

    public static final String PREFIX = "info.suder.navi.";


    public static final String AMBER_IP = PREFIX + "amber.ip";
    public static final String AMBER_PORT = PREFIX + "amber.port";

    public static final String CONNECTOR_IP = "connector.ip";
    public static final String CONNECTOR_PORT = "connector.port";

    public static final String EYE_PORT = PREFIX + "eye.port";
    public static final String EYE_ENABLE = PREFIX + "eye.enable";

    public static final String HOKUYO_HELPER_SLEEP = PREFIX + "hokuyo_helper.sleep";
    public static final String HOKUYO_HELPER_HIGH_SENSITIVE = PREFIX + "hokuyo_helper.high_sensitive";

    public static final String NINEDOF_HELPER_SLEEP = PREFIX + "ninedof_helper.sleep";
    public static final String NINEDOF_HELPER_FREQ = PREFIX + "ninedof_helper.freq";
    public static final String NINEDOF_HELPER_ACCEL = PREFIX + "ninedof_helper.accel";
    public static final String NINEDOF_HELPER_GYRO = PREFIX + "ninedof_helper.gyro";
    public static final String NINEDOF_HELPER_MAGNET = PREFIX + "ninedof_helper.magnet";

    public static final String ROBOCLAW_HELPER_SLEEP = PREFIX + "roboclaw_helper.sleep";

    public static final String DRIVE_AGENT_SLEEP = PREFIX + "drive_agent.sleep";
    public static final String VISIBILITY_AGENT_SLEEP = PREFIX + "visibility_agent.sleep";


    private NaviConst() {
    }
}
