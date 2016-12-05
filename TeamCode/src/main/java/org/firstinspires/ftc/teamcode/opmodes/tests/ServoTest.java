package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by vivek on 11/17/2016.
 */

@TeleOp(name="ServoTool", group="Tools")
@Disabled
public class ServoTest extends LinearOpMode {

    private static final double SERVO_DEGREES = 200.0;
    private static final double DEFAULT_POSITION = 100;
    private static final double INCREMENTAL = 1.0;

    private Servo servo;

    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.servo.get("servo");

        servo.setPosition(degree200ToServo(DEFAULT_POSITION));
        double switchPosition = degree200FromServo(servo.getPosition());

        waitForStart();
        while (opModeIsActive()) {
            if(gamepad1.left_trigger == 1)
                switchPosition += INCREMENTAL;
            if(gamepad1.right_trigger == 1)
                switchPosition -= INCREMENTAL;

            servo.setPosition(degree200ToServo(switchPosition));

            telemetry.addData("Current Position: ","%f",servo.getPosition());
            telemetry.update();
            idle();
        }
    }

    private static double degree200ToServo(double in) {
        return Range.scale(Range.clip(in,0.0,SERVO_DEGREES),0.0,SERVO_DEGREES,Servo.MIN_POSITION,Servo.MAX_POSITION);
    }

    private static double degree200FromServo(double in) {
        return Range.scale(Range.clip(in,Servo.MIN_POSITION,Servo.MAX_POSITION),Servo.MIN_POSITION,Servo.MAX_POSITION,0.0,SERVO_DEGREES);
    }
}
