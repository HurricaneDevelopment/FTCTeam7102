package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by vivek on 11/26/2016.
 */
@Autonomous(name="ODS Tool", group="Tools")
@Disabled
public class ODSTest extends LinearOpMode{
    private OpticalDistanceSensor ods;
    public void runOpMode() throws InterruptedException {
        ods = hardwareMap.opticalDistanceSensor.get("ods");
        ods.enableLed(true);
        waitForStart();
        while(opModeIsActive()) {
            telemetry.addData("Light Sensor read out: ", ods.getLightDetected());
            telemetry.update();
            idle();
        }
    }
}
