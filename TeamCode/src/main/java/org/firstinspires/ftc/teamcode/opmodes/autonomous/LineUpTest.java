package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robots.configurations.RobotTankDriveBeacon;

/**
 * Created by drjekyll on 1/17/17.
 */

public class LineUpTest extends LinearOpMode {
    public RobotTankDriveBeacon robot;

    public void runOpMode() throws InterruptedException
    {
        waitForStart();
        while(opModeIsActive()) {
            while (robot.bsLightSensor.getLightDetected() > robot.nbsLightSensor.getLightDetected()) {
                robot.leftDrive.motor.setPower(0.5);
            }
            while (robot.bsLightSensor.getLightDetected() < robot.nbsLightSensor.getLightDetected()) {
                robot.rightDrive.motor.setPower(-0.5);
            }
        }
        robot.rightDrive.motor.setPower(0);
        robot.leftDrive.motor.setPower(0);
    }
}
