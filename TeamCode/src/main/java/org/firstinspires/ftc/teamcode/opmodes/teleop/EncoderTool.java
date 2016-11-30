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
        
    }

    @Override
    public void or_start() {

    }

    @Override
    public void or_loop() {
        Set<EncoderInstruction> instructions = new HashSet<EncoderInstruction>();
        instructions.add(new EncoderInstruction(motorLeft, 0.5, 10, 5));
        instructions.add(new EncoderInstruction(motorRight, 0.5, 10, 5));

        EncoderInstruction.driveInstructionSet(instructions);

        telemetry.addData("Status", "Running");
        telemetry.addData("Runtime", "%f", runtime.seconds());

        if (gamepad1.a && !a) {
            motorLeftBasePosition = motorLeft.getCurrentPosition();
            motorRightBasePosition = motorRight.getCurrentPosition();
        }

        if (gamepad1.y && !y) {
            motorLeft.setDirection((motorLeft.getDirection() == DcMotor.Direction.FORWARD ? (DcMotor.Direction.REVERSE) : (DcMotor.Direction.FORWARD)));
            motorRight.setDirection((motorRight.getDirection() == DcMotor.Direction.FORWARD ? (DcMotor.Direction.REVERSE) : (DcMotor.Direction.FORWARD)));
        }

        y = gamepad1.y;
        a = gamepad1.a;

        motorLeft.setPower((motorLeft.getDirection() == DcMotor.Direction.REVERSE ? -gamepad1.left_stick_y : -gamepad1.right_stick_y) / 2);
        motorRight.setPower((motorRight.getDirection() == DcMotor.Direction.REVERSE ? -gamepad1.right_stick_y : -gamepad1.left_stick_y) / 2);

        motorRightRelativePosition = motorRight.getCurrentPosition() - motorRightBasePosition;
        motorLeftRelativePosition = motorLeft.getCurrentPosition() - motorLeftBasePosition;

        telemetry.addData("Left Motor RelPos [tick]", "%d ticks", motorLeftRelativePosition);
        telemetry.addData("Right Motor RelPos [tick]", "%d ticks", motorRightRelativePosition);
        telemetry.addData("Left Motor RelPos [inch]", "%f in", motorLeftRelativePosition / Constants.ENCODER_TICKS_PER_REV * Constants.OMNIWHEEL_LARGE_CIRCUMFERENCE_IN);
        telemetry.addData("Right Motor RelPos [inch]", "%f in", motorRightRelativePosition / Constants.ENCODER_TICKS_PER_REV * Constants.OMNIWHEEL_LARGE_CIRCUMFERENCE_IN);
    }
}