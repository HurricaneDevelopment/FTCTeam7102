package org.firstinspires.ftc.teamcode.robots.configurations;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;
import org.firstinspires.ftc.teamcode.hardware.DcMotorW;
import org.firstinspires.ftc.teamcode.hardware.ServoW;

import org.firstinspires.ftc.teamcode.robots.Robot;
import org.firstinspires.ftc.teamcode.hardware.AdaFruitColorSensor;

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


        colorSensor.ledOff();
    }

    public void reverseDrive() {
        DcMotorW temp = leftDrive;
        leftDrive = rightDrive;
        rightDrive = temp;

        leftDrive.motor.setDirection((leftDrive.motor.getDirection() == DcMotor.Direction.FORWARD ? (DcMotor.Direction.REVERSE) : (DcMotor.Direction.FORWARD)));
        rightDrive.motor.setDirection((rightDrive.motor.getDirection() == DcMotor.Direction.FORWARD ? (DcMotor.Direction.REVERSE) : (DcMotor.Direction.FORWARD)));
    }
}
