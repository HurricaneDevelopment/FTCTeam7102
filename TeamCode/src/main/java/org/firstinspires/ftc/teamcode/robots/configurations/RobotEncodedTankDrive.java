package org.firstinspires.ftc.teamcode.robots.configurations;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;
import org.firstinspires.ftc.teamcode.hardware.DcMotorW;

import org.firstinspires.ftc.teamcode.robots.Robot;

public class RobotEncodedTankDrive extends Robot {

    public DcMotorW leftDrive;
    public DcMotorW rightDrive;

    public int rightDriveRelativePosition;
    public int rightDriveBasePosition;
    public int leftDriveRelativePosition;
    public int leftDriveBasePosition;

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
    }

    public void reverseDrive() {
        DcMotorW temp = leftDrive;
        leftDrive = rightDrive;
        rightDrive = temp;

        leftDrive.motor.setDirection((leftDrive.motor.getDirection() == DcMotor.Direction.FORWARD ? (DcMotor.Direction.REVERSE) : (DcMotor.Direction.FORWARD)));
        rightDrive.motor.setDirection((rightDrive.motor.getDirection() == DcMotor.Direction.FORWARD ? (DcMotor.Direction.REVERSE) : (DcMotor.Direction.FORWARD)));
    }
}