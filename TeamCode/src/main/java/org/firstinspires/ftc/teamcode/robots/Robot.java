package org.firstinspires.ftc.teamcode.robots;


import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;

import java.util.LinkedList;
import java.util.List;

public abstract class Robot {

    public HardwareMap hwMap;

    public Motors motors;

    public Robot() {
        motors = new Motors();
    }

    public void loadHardware(HardwareMap aHwMap) {
        hwMap = aHwMap;

        motors.loadHardware(hwMap);
    }

    public List<String> toStringList() {
        List<String> lines = new LinkedList<String>();
        lines.add("Motors");
        lines.add(motors.toString());
        return lines;
    }

    public abstract void start() throws UnfoundHardwareException;

    public void stop() {
        for (String motorName : motors.Motors.keySet())
            motors.Motors.get(motorName).motor.setPower(0);
    }

    public String toString() {
        String op = "";
        for (String line : toStringList())
            op += line + "\n";
        return op;
    }
}