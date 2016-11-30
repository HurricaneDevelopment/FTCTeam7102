package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created by Vivek Sreenivasan on 11/5/2016.
 */



public class Hardware7102
{
    // Drive Base Motors
    public DcMotor  leftMotor   = null;
    public DcMotor  rightMotor  = null;


    //Speed Shooter System Motors
    //public DcMotor collect = null;
    //public DcMotor shooter = null;

    //CapBall System Motors

    //Checking for distance sensors
    //public OpticalDistanceSensor ods1 = null;
    //public OpticalDistanceSensor ods2 = null;
    //public UltrasonicSensor us1 = null;
    //public UltrasonicSensor us2 = null;
    //public ColorSensor cs1 = null;
    //public ColorSensor cs2 = null;
    //public ColorSensor cs3 = null;
    //public LightSensor ls1 = null;
    //public LightSensor ls2 = null;

    //public CRServo drawBridge = null;
    public Servo switcher = null;

   // public ServoController

    public static final double SWITCHER_HOME = 0.5;

    HardwareMap hwMap  = null;
    private ElapsedTime period  = new ElapsedTime();


    public Hardware7102() {
    }


    public void init(HardwareMap ahwMap) {

        hwMap = ahwMap;

        // Define and Initialize Motors
        leftMotor  = hwMap.dcMotor.get("leftMotor");
        rightMotor  = hwMap.dcMotor.get("rightMotor");


        //collect = hwMap.dcMotor.get("collect");
        //shooter = hwMap.dcMotor.get("shooter");
/*
        ods1 = hwMap.opticalDistanceSensor.get("ods1");
        ods2 = hwMap.opticalDistanceSensor.get("ods2");




        us1 = hwMap.ultrasonicSensor.get("us1");
        us2 = hwMap.ultrasonicSensor.get("us2");

        ls1 = hwMap.lightSensor.get("ls1");
        ls2 = hwMap.lightSensor.get("ls2");

        cs1 = hwMap.colorSensor.get("cs1");*/
        //cs2 = hwMap.colorSensor.get("cs2");
        //cs3 = hwMap.colorSensor.get("cs3");


//        drawBridge = hwMap.crservo.get("DrawBridge");
        switcher.getController().pwmDisable();
        switcher = hwMap.servo.get("switcher");



        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);



        // Set all motors to zero power
        leftMotor.setPower(0);
        rightMotor.setPower(0);

       // collect.setPower(0);


//        ls1.enableLed(true);
  //      ls2.enableLed(true);
    //    leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      //  rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


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
