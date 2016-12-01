package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by drjekyll on 12/1/16.
 */
@Autonomous(name="Ultrasonic", group="Tools")
@Disabled
public class UltrasonicTest extends LinearOpMode{
    public UltrasonicSensor us;
    public void runOpMode() throws InterruptedException {
        us = hardwareMap.ultrasonicSensor.get("ultrasonic");
        waitForStart();
        while(opModeIsActive())
        {
            telemetry.addData("Ultrasonic Level", us.getUltrasonicLevel());
            telemetry.update();
            idle();
        }
    }
}
