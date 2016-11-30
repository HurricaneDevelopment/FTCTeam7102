package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.robots.RobotDec2;

public class Dec2 extends OpMode {

    private RobotDec2 robot;

    @Override
    public void init() {
        robot = new RobotDec2(hardwareMap);
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() {
        telemetry.addData("Status", "Preparatory");
    }

    @Override
    public void start() {
        telemetry.addData("Status", "Starting");
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running");
    }

    @Override
    public void stop() {
        telemetry.addData("Status", "Stopped");
    }
}
