package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by vivek on 11/17/2016.
 */
@Autonomous(name="Encoder Basic Tool", group="Tools")
@Disabled
public class EncoderTest extends LinearOpMode {


    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP

    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /(WHEEL_DIAMETER_INCHES * 3.1415);



    private DcMotor motor;
    private ElapsedTime runtime = new ElapsedTime();

    public void runOpMode(){
            motor = hardwareMap.dcMotor.get("motor");
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            idle();
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            waitForStart();
            encoderDrive(0.5,2.0,0.5);
            telemetry.addData("Current Position of DcMotor: ",motor.getCurrentPosition());
            telemetry.addData("Target Position of DcMotor: ",motor.getTargetPosition());
            telemetry.addData("Max Speed of DcMotor: ",motor.getMaxSpeed());
            telemetry.update();
        idle();


    }
    public void encoderDrive(double speed,double inches,double timeoutS) {
        int newTarget;

        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller
            newTarget = motor.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH);

            motor.setTargetPosition(newTarget);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            runtime.reset();
            motor.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive() && runtime.seconds() < timeoutS && motor.isBusy()) {
                telemetry.addData("Current Encoder Tick","%f",motor.getCurrentPosition());
            }

            // Stop all motion;
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}
