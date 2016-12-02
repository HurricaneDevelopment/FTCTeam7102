package org.firstinspires.ftc.teamcode.robots;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;
import org.firstinspires.ftc.teamcode.hardware.ServoW;

import java.util.LinkedList;
import java.util.List;

public abstract class Robot {

    public HardwareMap hwMap;

    public Motors motors;
    public Servos servos;

    public Robot() {
        motors = new Motors();
        servos = new Servos();
    }

    public void loadHardware(HardwareMap aHwMap) {
        hwMap = aHwMap;

        motors.loadHardware(hwMap);
        servos.loadHardware(hwMap);
    }

    public List<String> toStringList() {
        List<String> lines = new LinkedList<String>();
        lines.add("Motors");
        lines.add(motors.toString());
        lines.add("Servos");
        lines.add(servos.toString());
        return lines;
    }

    public abstract void start() throws UnfoundHardwareException;

    public void stop() {
        for (String motorName : motors.Motors.keySet())
            motors.Motors.get(motorName).motor.setPower(0);
        for (String servoName : servos.servos.keySet()) {
            ServoW serv = servos.servos.get(servoName);
            serv.servo.setPosition(serv.home);
        }
    }

    public String toString() {
        String op = "";
        for (String line : toStringList())
            op += line + "\n";
        return op;
    }

    public void debugTelem(Telemetry telem) {

    }
}