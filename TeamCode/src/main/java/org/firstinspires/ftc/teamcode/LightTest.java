package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.LightSensor;

/**
 * Created by vivek on 11/22/2016.
 */
@Autonomous(name="LightTest", group="7102 Tests")

public class LightTest extends LinearOpMode{
    private LightSensor ls;


    public void runOpMode() throws InterruptedException {
        ls = hardwareMap.lightSensor.get("ls");
        ls.enableLed(true);
        waitForStart();
        while(opModeIsActive()) {
            telemetry.addData("Light Sensor read out: ", ls.getLightDetected());
            telemetry.update();
            idle();
        }
    }
}
