public class Motors {

    private HashMap<String,DcMotorW> Motors;

    public  Motors() {
        Motors = new HashMap<String,DcMotorW>();
    }

    public void loadHardware(HardwareMap hwmap) {
        List<HardwareDevice> devices = hardwareMap.getAll(HardwareDevice);
        for(HardwareDevice device : devices)
            if (device instanceof DcMotor)
                Motors.put(hardwareMap.getNamesof(device).get(0),new DcMotorW(device));
    }

    public DcMotorW get(String str) throws UnfoundHardwareException {
        if (!Motors.containsKey(str))
            throw new UnfoundHardwareException("DcMotor",str);
        return Motors.get(str);
    }

    public String toString() {
        return Motors;
    }
}