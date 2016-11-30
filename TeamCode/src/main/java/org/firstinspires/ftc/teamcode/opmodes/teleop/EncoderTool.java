package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.control.EncoderInstruction;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vivek on 11/28/2016.
 */
@TeleOp(name="EncoderTool", group="Tools")
public class EncoderTool extends MasterOpMode {

    private void autonomous() {
        EncoderInstructionSet encoderInst = new EncoderInstructionSet();
        encoderInst.add(robot.leftDrive.createEncoderInstruction(0.5,10,5));
        encoderInst.add(robot.rightDrive.createEncoderInstruction(0.5,10,5));
        encoderInst.run();
    }

    @Override
    public void or_start() {
        autonomous();
    }

    @Override
    public void or_loop() {
        if (gh1.onDown("A")) {
            robot.leftDriveBasePosition = robot.leftDrive.getCurrentPosition();
            robot.rightDriveBasePosition = robot.rightDrive.getCurrentPosition();
        }

        if (gh1.onDown("Y"))
            robot.reverseDrive();

        leftDrive.setPower(Math.signum(-gamepad1.left_stick) * 0.5);
        rightDrive.setPower(Math.signum(-gamepad1.right_stick) * 0.5);

        robot.rightDriveRelativePosition = robot.rightDrive.getCurrentPosition() - robot.rightDriveBasePosition;
        robot.leftDriveRelativePosition = robot.leftDrive.getCurrentPosition() - robot.leftDriveBasePosition;

        telemetry.addData("Left Motor RelPos [tick]", "%d ticks", robot.leftDriveRelativePosition);
        telemetry.addData("Right Motor RelPos [tick]", "%d ticks", robot.rightDriveRelativePosition);
        telemetry.addData("Left Motor RelPos [inch]", "%f in", robot.leftDriveRelativePosition / Constants.ENCODER_TICKS_PER_REV * Constants.OMNIWHEEL_LARGE_CIRCUMFERENCE_IN);
        telemetry.addData("Right Motor RelPos [inch]", "%f in", robot.rightDriveRelativePosition / Constants.ENCODER_TICKS_PER_REV * Constants.OMNIWHEEL_LARGE_CIRCUMFERENCE_IN);
    }
}