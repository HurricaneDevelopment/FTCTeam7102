package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.control.EncoderInstructionSet;
import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;
import org.firstinspires.ftc.teamcode.opmodes.MasterOpMode;
import org.firstinspires.ftc.teamcode.robots.TetrixConstants;
import org.firstinspires.ftc.teamcode.robots.configurations.RobotEncodedTankDrive;
import org.firstinspires.ftc.teamcode.util.Converter;

/**
 * Created by vivek on 11/28/2016.
 */
@TeleOp(name="Encoder Tool", group="Tools")
@Disabled
public class EncoderTool extends MasterOpMode {

    public RobotEncodedTankDrive robot;

    private int savedLeftTicks;
    private int savedRightTicks;

    @Override
    public void setup() {
        setRobot(new RobotEncodedTankDrive());
        robot = (RobotEncodedTankDrive) robotI;
    }

    @Override
    public void or_loop() {
        if (gh1.onDown("A")) {
            robot.leftDriveBasePosition = robot.leftDrive.motor.getCurrentPosition();
            robot.rightDriveBasePosition = robot.rightDrive.motor.getCurrentPosition();
        }

        if (gh1.onDown("Y"))
            robot.reverseDrive();

        if (gh1.onDown("B")) {
            try {
                EncoderInstructionSet encoderInst = new EncoderInstructionSet();
                encoderInst.add(robot.leftDrive.createEncoderInstruction(0.5,-1 * Converter.ticksToInches(robot.leftDriveRelativePosition,TetrixConstants.ENCODER_TICKS_PER_REV,TetrixConstants.OMNIWHEEL_LARGE_CIRCUMFERENCE_IN),10));
                encoderInst.add(robot.rightDrive.createEncoderInstruction(0.5,-1 * Converter.ticksToInches(robot.rightDriveRelativePosition,TetrixConstants.ENCODER_TICKS_PER_REV,TetrixConstants.OMNIWHEEL_LARGE_CIRCUMFERENCE_IN),10));
                encoderInst.run();
            } catch (UnfoundHardwareException ex) {
                while (runtime.seconds() < SCREEN_FREEZE_TIME) {
                    telemetry.addData("Error", ex.getMessage());
                    telemetry.update();
                }
                requestOpModeStop();
            }
        }

        if (gh1.onDown("X")) {
            savedLeftTicks = robot.leftDriveRelativePosition;
            savedRightTicks = robot.rightDriveRelativePosition;
        }

        if (gh1.onDown("rt")) {
            try {
                EncoderInstructionSet encoderInst = new EncoderInstructionSet();
                encoderInst.add(robot.leftDrive.createEncoderInstruction(0.5,Converter.ticksToInches(savedLeftTicks,TetrixConstants.ENCODER_TICKS_PER_REV,TetrixConstants.OMNIWHEEL_LARGE_CIRCUMFERENCE_IN),10));
                encoderInst.add(robot.rightDrive.createEncoderInstruction(0.5,Converter.ticksToInches(savedRightTicks,TetrixConstants.ENCODER_TICKS_PER_REV,TetrixConstants.OMNIWHEEL_LARGE_CIRCUMFERENCE_IN),10));
                encoderInst.run();
            } catch (UnfoundHardwareException ex) {
                while (runtime.seconds() < SCREEN_FREEZE_TIME) {
                    telemetry.addData("Error", ex.getMessage());
                    telemetry.update();
                }
                requestOpModeStop();
            }
        }

        robot.leftDrive.motor.setPower(Math.signum(-gamepad1.left_stick_y) * 0.5);
        robot.rightDrive.motor.setPower(Math.signum(-gamepad1.right_stick_y) * 0.5);

        robot.rightDriveRelativePosition = robot.rightDrive.motor.getCurrentPosition() - robot.rightDriveBasePosition;
        robot.leftDriveRelativePosition = robot.leftDrive.motor.getCurrentPosition() - robot.leftDriveBasePosition;

        telemetry.addData("Left Motor RelPos [tick]", "%d ticks", robot.leftDriveRelativePosition);
        telemetry.addData("Right Motor RelPos [tick]", "%d ticks", robot.rightDriveRelativePosition);
        telemetry.addData("Left Motor RelPos [inch]", "%f in", Converter.ticksToInches(robot.leftDriveRelativePosition,TetrixConstants.ENCODER_TICKS_PER_REV,TetrixConstants.OMNIWHEEL_LARGE_CIRCUMFERENCE_IN));
        telemetry.addData("Right Motor RelPos [inch]", "%f in", Converter.ticksToInches(robot.rightDriveRelativePosition,TetrixConstants.ENCODER_TICKS_PER_REV,TetrixConstants.OMNIWHEEL_LARGE_CIRCUMFERENCE_IN));

    }
}