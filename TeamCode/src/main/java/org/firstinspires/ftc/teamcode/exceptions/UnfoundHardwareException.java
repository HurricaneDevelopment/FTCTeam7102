public class UnfoundHardwareException extends Exception {
    
    private String deviceType;
    private String deviceName;

    public UnfoundHardwareException(String type,String name) {
        deviceType = type;
        deviceName = name;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getMessage() {
        return "Could not find a '" + deviceType + "' named '" + deviceName + "'";
    }
}