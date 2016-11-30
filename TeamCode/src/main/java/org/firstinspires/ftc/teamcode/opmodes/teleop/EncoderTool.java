package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TetrixConstants;
import org.firstinspires.ftc.teamcode.control.EncoderInstructionSet;
import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;
import org.firstinspires.ftc.teamcode.opmodes.MasterOpMode;
import org.firstinspires.ftc.teamcode.robots.configurations.RobotEncodedTankDrive;

/**
 * Created by vivek on 11/28/2016.
 */
@TeleOp(name="EncoderTool", group="Tools")
public class EncoderTool extends MasterOpMode {

    public RobotEncodedTankDrive robot;

    private void autonomous() throws UnfoundHardwareException {
        EncoderInstructionSet encoderInst = new EncoderInstructionSet();
        encoderInst.add(robot.leftDrive.createEncoderInstruction(0.5,10,5));
        encoderInst.add(robot.rightDrive.createEncoderInstruction(0.5,10,5));
        encoderInst.run();
    }

    @Override
    public void or_init() {
        setRobot(new RobotEncodedTankDrive());
        robot = (RobotEncodedTankDrive) robotI;
    }

    @Override
    public void or_start() {
        try {
            autonomous();
        } catch (UnfoundHardwareException ex) {
            telemetry.clearAll();
            telemetry.addData("Error",ex.getMessage());
            telemetry.update();
            requestOpModeStop();
        }
    }

    @Override
    public void or_loop() {
        if (gh1.onDown("A")) {
            robot.leftDriveBasePosition = robot.leftDrive.motor.getCurrentPosition();
            robot.rightDriveBasePosition = robot.rightDrive.motor.getCurrentPosition();
        }

        if (gh1.onDown("Y"))
            robot.reverseDrive();

        robot.leftDrive.motor.setPower(Math.signum(-gamepad1.left_stick_y) * 0.5);
        robot.rightDrive.motor.setPower(Math.signum(-gamepad1.right_stick_y) * 0.5);

        robot.rightDriveRelativePosition = robot.rightDrive.motor.getCurrentPosition() - robot.rightDriveBasePosition;
        robot.leftDriveRelativePosition = robot.leftDrive.motor.getCurrentPosition() - robot.leftDriveBasePosition;

        telemetry.addData("Left Motor RelPos [tick]", "%d ticks", robot.leftDriveRelativePosition);
        telemetry.addData("Right Motor RelPos [tick]", "%d ticks", robot.rightDriveRelativePosition);
        telemetry.addData("Left Motor RelPos [inch]", "%f in", robot.leftDriveRelativePosition / TetrixConstants.ENCODER_TICKS_PER_REV * TetrixConstants.OMNIWHEEL_LARGE_CIRCUMFERENCE_IN);
        telemetry.addData("Right Motor RelPos [inch]", "%f in", robot.rightDriveRelativePosition / TetrixConstants.ENCODER_TICKS_PER_REV * TetrixConstants.OMNIWHEEL_LARGE_CIRCUMFERENCE_IN);
    }
}