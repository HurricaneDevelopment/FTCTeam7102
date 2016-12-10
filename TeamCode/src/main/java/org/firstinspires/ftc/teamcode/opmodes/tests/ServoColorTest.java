package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robots.configurations.RobotTankDriveBeacon;

/**
 * Created by vivek on 11/17/2016.
 */

@TeleOp(name="ServoTool", group="Tools")
@Disabled
public class ServoColorTest extends LinearOpMode {

    private static final double SERVO_DEGREES = 200.0;
    private static final double DEFAULT_POSITION = 100;
    private static final double INCREMENTAL = 1.0;

    public RobotTankDriveBeacon robot;

    public void runOpMode() throws InterruptedException {

        waitForStart();
        while(opModeIsActive())
        {
            if (robot.colorSensor.isBeaconRed())
                robot.beaconSwitcher.increment(-0.25);
            else if (robot.colorSensor.isBeaconBlue())
                robot.beaconSwitcher.increment(0.25);
        }
    }

}
