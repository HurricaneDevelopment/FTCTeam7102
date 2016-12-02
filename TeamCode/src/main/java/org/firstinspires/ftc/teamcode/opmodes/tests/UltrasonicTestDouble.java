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
public class UltrasonicTestDouble extends LinearOpMode{
    public UltrasonicSensor us1;
    public UltrasonicSensor us2;
    public void runOpMode() throws InterruptedException {
        us1 = hardwareMap.ultrasonicSensor.get("ultrasonic1");
        us2 = hardwareMap.ultrasonicSensor.get("ultrasonic2");
        waitForStart();
        while(opModeIsActive())
        {
            telemetry.addData("Ultrasonic Level 1", us1.getUltrasonicLevel());
            telemetry.addData("Ultrasonic Level 2", us2.getUltrasonicLevel());
            telemetry.update();
            idle();
        }
    }
}
