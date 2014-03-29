package navi.main;

import navi.common.PropertiesHelper;
import navi.main.server.Main;

import java.util.Properties;

public class NaviConfig {

    static {
        Properties prop = PropertiesHelper.loadProperties(null, Main.class, NaviConst.MAIN_PROPERTIES);
        prop.putAll(System.getProperties());
        properties = prop;
    }

    private static final Properties properties;

    public static String getAmberIp() {
        return PropertiesHelper.getProperty(properties, NaviConst.AMBER_IP, "127.0.0.1");
    }

    public static int getAmberPort() {
        return Integer.parseInt(PropertiesHelper.getProperty(properties, NaviConst.AMBER_PORT, "26233"));
    }

    public static String getConnectorIp() {
        return PropertiesHelper.getProperty(properties, NaviConst.CONNECTOR_IP, "0.0.0.0");
    }

    public static int getConnectorPort() {
        return Integer.parseInt(PropertiesHelper.getProperty(properties, NaviConst.CONNECTOR_PORT, "1234"));
    }

    public static boolean isEyeEnabled() {
        return Boolean.parseBoolean(PropertiesHelper.getProperty(properties, NaviConst.EYE_ENABLE, "true"));
    }

    public static int getHokuyoHelperSleep() {
        return Integer.parseInt(PropertiesHelper.getProperty(properties, NaviConst.HOKUYO_HELPER_SLEEP, "100"));
    }

    public static boolean getHokuyoHelperHighSensitive() {
        return Boolean.parseBoolean(PropertiesHelper.getProperty(properties, NaviConst.HOKUYO_HELPER_HIGH_SENSITIVE, "false"));
    }

    public static int getRoboclawHelperSleep() {
        return Integer.parseInt(PropertiesHelper.getProperty(properties, NaviConst.ROBOCLAW_HELPER_SLEEP, "100"));
    }

    public static int getDriveAgentSleep() {
        return Integer.parseInt(PropertiesHelper.getProperty(properties, NaviConst.DRIVE_AGENT_SLEEP, "25"));
    }

    public static int getVisibilityAgentSleep() {
        return Integer.parseInt(PropertiesHelper.getProperty(properties, NaviConst.VISIBILITY_AGENT_SLEEP, "50"));
    }
}
