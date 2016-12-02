package org.firstinspires.ftc.teamcode.robots.configurations;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

import org.firstinspires.ftc.teamcode.control.EncoderInstructionSet;
import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;
import org.firstinspires.ftc.teamcode.hardware.DcMotorW;
import org.firstinspires.ftc.teamcode.hardware.ServoW;

import org.firstinspires.ftc.teamcode.robots.Robot;
import org.firstinspires.ftc.teamcode.hardware.AdaFruitColorSensor;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Spencer on 11/30/2016.
 */
public class RobotTankDriveBeacon extends Robot {
    public DcMotorW leftDrive;
    public DcMotorW rightDrive;

    public ServoW beaconSwitcher;

    public AdaFruitColorSensor colorSensor;
    public UltrasonicSensor ultraOmni;
    public UltrasonicSensor ultraStealth;

    public LightSensor bsLightSensor;
    public LightSensor nbsLightSensor;

    public int rightDriveRelativePosition;
    public int rightDriveBasePosition;
    public int leftDriveRelativePosition;
    public int leftDriveBasePosition;

    public boolean reversed;

    public RobotTankDriveBeacon() {
        super();
        reversed = false;
    }

    public void loadHardware(HardwareMap hwmap) {
        super.loadHardware(hwmap);
        colorSensor = new AdaFruitColorSensor("color",5);
        try {
            colorSensor.loadHardware(hwmap);
        } catch (UnfoundHardwareException ex) {
            colorSensor = null;
        }
        ultraOmni = hwmap.ultrasonicSensor.get("uOmni");
        ultraStealth = hwmap.ultrasonicSensor.get("uStealth");
        bsLightSensor = hwmap.lightSensor.get("bsLS");
        nbsLightSensor =  hwmap.lightSensor.get("nbsLS");
    }

    @Override
    public void start() throws UnfoundHardwareException {
        leftDrive = motors.get("left");
        rightDrive = motors.get("right");

        leftDrive.motor.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.motor.setDirection(DcMotor.Direction.REVERSE);

        leftDrive.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftDriveBasePosition = leftDrive.motor.getCurrentPosition();
        rightDriveBasePosition = rightDrive.motor.getCurrentPosition();

        leftDriveRelativePosition = 0;
        rightDriveRelativePosition = 0;


        beaconSwitcher = servos.get("beacon");

        beaconSwitcher.home = 0.43;
        beaconSwitcher.goHome();

        bsLightSensor.enableLed(true);
        nbsLightSensor.enableLed(true);

        colorSensor.ledOff();
    }

    public void reverseDrive() {
        DcMotorW temp = leftDrive;
        leftDrive = rightDrive;
        rightDrive = temp;

        leftDrive.motor.setDirection((leftDrive.motor.getDirection() == DcMotor.Direction.FORWARD ? (DcMotor.Direction.REVERSE) : (DcMotor.Direction.FORWARD)));
        rightDrive.motor.setDirection((rightDrive.motor.getDirection() == DcMotor.Direction.FORWARD ? (DcMotor.Direction.REVERSE) : (DcMotor.Direction.FORWARD)));

        reversed = !reversed;
    }

    public double ultrasonicToInches(double ultraVal) {
        //double inches = 0.4301 * ultraVal - 2.1553; // Experimental
        double inches = 0.4166666666 * ultraVal - 2; // Theoretical
        return (double) (((int) (inches * 100.0)))/100.0;
    }

    public void ultraParallelOne() {
            double stealthInch = ultrasonicToInches(ultraStealth.getUltrasonicLevel());
            double omniInch = ultrasonicToInches(ultraOmni.getUltrasonicLevel());

            while (stealthInch != omniInch) {
                if (omniInch > stealthInch)
                    if (reversed)
                        leftDrive.motor.setPower(-0.2);
                    else
                        rightDrive.motor.setPower(0.2);

                if (omniInch < stealthInch)
                    if (reversed)
                        leftDrive.motor.setPower(0.2);
                    else
                        rightDrive.motor.setPower(-0.2);

                stealthInch = ultrasonicToInches(ultraStealth.getUltrasonicLevel());
                omniInch = ultrasonicToInches(ultraOmni.getUltrasonicLevel());
            }

            leftDrive.motor.setPower(0);
            rightDrive.motor.setPower(0);
    }

    public void ultraParallel() {
            double stealthInch = ultrasonicToInches(ultraStealth.getUltrasonicLevel());
            double omniInch = ultrasonicToInches(ultraOmni.getUltrasonicLevel());

            int c =0;
            while (stealthInch != omniInch) {
                if (c==3) break;

                ultraParallelOne();
                this.pause(250);

                stealthInch = ultrasonicToInches(ultraStealth.getUltrasonicLevel());
                omniInch = ultrasonicToInches(ultraOmni.getUltrasonicLevel());

                c++;
            }
    }

    public void goToDistance(double min,double max) throws UnfoundHardwareException {
        double stealthInch = ultrasonicToInches(ultraStealth.getUltrasonicLevel());

        if (stealthInch > max) {
            EncoderInstructionSet slightTurn = new EncoderInstructionSet();
            if (reversed)
                slightTurn.add(leftDrive.createEncoderInstruction(0.5, 4, 5));
            else
                slightTurn.add(rightDrive.createEncoderInstruction(0.5, -4, 5));
            slightTurn.run();

            while (stealthInch > max) {
                if (reversed) {
                    leftDrive.motor.setPower(0.25);
                    rightDrive.motor.setPower(0.25);
                } else {
                    leftDrive.motor.setPower(-0.25);
                    rightDrive.motor.setPower(-0.25);
                }

                stealthInch = ultrasonicToInches(ultraStealth.getUltrasonicLevel());
            }

            leftDrive.motor.setPower(0);
            rightDrive.motor.setPower(0);
        } else if (stealthInch < min) {
            EncoderInstructionSet slightTurn = new EncoderInstructionSet();
            if (reversed)
                slightTurn.add(leftDrive.createEncoderInstruction(0.5, 4, 5));
            else
                slightTurn.add(rightDrive.createEncoderInstruction(0.5, -4, 5));
            slightTurn.run();

            while (stealthInch < min) {
                if (reversed) {
                    leftDrive.motor.setPower(-0.25);
                    rightDrive.motor.setPower(-0.25);
                } else {
                    leftDrive.motor.setPower(0.25);
                    rightDrive.motor.setPower(0.25);
                }

                stealthInch = ultrasonicToInches(ultraStealth.getUltrasonicLevel());
            }

            leftDrive.motor.setPower(0);
            rightDrive.motor.setPower(0);
        }
    }
}
