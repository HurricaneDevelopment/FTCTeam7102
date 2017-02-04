package org.firstinspires.ftc.teamcode.robots.configurations;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;
import org.firstinspires.ftc.teamcode.hardware.DcMotorW;
import org.firstinspires.ftc.teamcode.hardware.ServoW;
import org.firstinspires.ftc.teamcode.robots.Robot;



/**
 * Created by drjekyll on 12/11/16.
 */

public class Hardware7102 extends Robot{
    // Drive Base Motors
    public DcMotorW left = null;
    public DcMotorW right = null;

    public int rightDriveRelativePosition;
    public int rightDriveBasePosition;
    public int leftDriveRelativePosition;
    public int leftDriveBasePosition;


    //public CRServo drawBridge = null;
    public ServoW beaconSwitcher = null;

    // public ServoController

    public static final double SWITCHER_HOME = 0.5;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();


    public Hardware7102() {
    }


    public void start() throws UnfoundHardwareException{


        left = motors.get("left");
        right = motors.get("right");

        left.motor.setDirection(DcMotor.Direction.REVERSE);
        right.motor.setDirection(DcMotor.Direction.REVERSE);

        left.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftDriveBasePosition = left.motor.getCurrentPosition();
        rightDriveBasePosition = right.motor.getCurrentPosition();

        leftDriveRelativePosition = 0;
        rightDriveRelativePosition = 0;


        beaconSwitcher = servos.get("beacon");

        beaconSwitcher.home = 0.43;
        beaconSwitcher.goHome();
/*
        bsLightSensor.enableLed(true);
        nbsLightSensor.enableLed(true);

        colorSensor.ledOff();
*/
    }







    public void waitForTick(long periodMs)  throws InterruptedException {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();

    }
}
