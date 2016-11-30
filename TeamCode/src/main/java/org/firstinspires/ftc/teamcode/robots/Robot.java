package org.firstinspires.ftc.teamcode.robots;

public abstract class Robot {

    public HardwareMap hwMap;

    public Motors motors;

    public Robot() {
        motors = new Motors();
    }

    public loadHardware(HardwareMap aHwMap) {
        hwMap = aHwMap;

        motors.loadHardware(hwMap);
    }

    public List<String> toStringList() {
        List<String> lines = new List<String>();
        lines.add("Motors");
        lines.add(motors);
    }

    public abstract void start() throws UnfoundHardwareException;

    public void stop() {
        for (DcMotorW motor : motors.Motors)
            motor.setPower(0);
    }

    public String toString() {
        op += "";
        for (String line : toStringList())
            op += line + "\n";
    }
}