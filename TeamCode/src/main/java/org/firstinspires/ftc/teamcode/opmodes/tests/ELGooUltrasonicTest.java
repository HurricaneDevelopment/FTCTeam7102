package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;

/**
 * Created by drjekyll on 1/30/17.
 */

@Autonomous(name="ELGooUltrasonicTest",group="Tests")
public class ELGooUltrasonicTest extends LinearOpMode {

    //private static final int VCC = 2;
    //private static final int GND = 3;
    private static final int TRIG = 0;
    private static final int ECHO = 1;

    @Override
    public void runOpMode() {
        DeviceInterfaceModule dim = hardwareMap.deviceInterfaceModule.get("dim");

        //dim.setDigitalChannelMode(VCC, DigitalChannelController.Mode.OUTPUT);
        //dim.setDigitalChannelMode(GND, DigitalChannelController.Mode.OUTPUT);
        dim.setDigitalChannelMode(TRIG, DigitalChannelController.Mode.OUTPUT);
        dim.setDigitalChannelMode(ECHO, DigitalChannelController.Mode.INPUT);

        waitForStart();

        //dim.setDigitalChannelState(VCC,true);
        //dim.setDigitalChannelState(GND,false);

        long duration, inches, centimeters;
        double volt;

        while (opModeIsActive()) {
            dim.setDigitalChannelState(TRIG,false);
            sleep(2);
            dim.setDigitalChannelState(TRIG,true);
            sleep(5);
            dim.setDigitalChannelState(TRIG,false);

            volt = dim.getAnalogInputVoltage(0);

            while (volt == 0) {
                volt = dim.getAnalogInputVoltage(0);
                System.out.println("Volt >> " + volt);
                telemetry.addData("Volt",volt);
                telemetry.update();
            }

            while (volt != 0) {
                volt = dim.getAnalogInputVoltage(0);
                System.out.println("Volt >> " + volt);
                telemetry.addData("Volt",volt);
                telemetry.update();
            }


            //duration = dim.getPulseWidthOutputTime(ECHO);
            //System.out.println("Duration >> " + duration);
            //telemetry.addData("Voltage",volt);
            /*inches = microsecondsToInches(duration);
            centimeters = microsecondsToCentimeters(duration);
            System.out.println("Distances >> Inches: " + inches + " | Centimeters: " + centimeters);

            telemetry.addData("Distances","Inches >> " + inches + " | Centimeters >> " + centimeters);*/
            //telemetry.update();

            sleep(1000);

            idle();
        }



        sleep(5000);
    }

    private static long pulseIn(DeviceInterfaceModule dim,int channel) {
        long start = System.nanoTime();

        while (dim.getDigitalChannelState(channel)) {

        }

        return (System.nanoTime() - start)/ 1000;
    }

    private static long microsecondsToInches(long microseconds)
    {
        return microseconds / 74 / 2;
    }

    private static long microsecondsToCentimeters(long microseconds)
    {
        return microseconds / 29 / 2;
    }
}
