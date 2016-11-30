package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by Vivek Sreenivasan, Team 7102, 11/5/2016.
 */
@TeleOp(name="7102TeleOp", group="K9bot")
public class TeleOp7102 extends LinearOpMode {
    Hardware7102 robot = new Hardware7102();
    private ElapsedTime runtime = new ElapsedTime();
    private static final double INCREMENTAL = 1.0;
    private static final double SERVO_DEGREES = 200.0;
    @Override
    public void runOpMode() throws InterruptedException {
        double left;
        double right;
        double collectPower = 1.0;
        double switchPosition = robot.switcher.getPosition();



        robot.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            //robot.ls1.enableLed(true);
            //robot.ls2.enableLed(true);
            //robot.cs1.enableLed(true);
            /*
            telemetry.addData("Light Detected Normal level", robot.ls1.getLightDetected());
            telemetry.addData("Light Detected Normal level", robot.ls2.getLightDetected());
            telemetry.addData("Ultrasonic Sensor Reading", robot.us1.getUltrasonicLevel());
            telemetry.addData("Ultrasonic Sensor Reading", robot.us2.getUltrasonicLevel());
            telemetry.addData("Optical Distance Sensor Reading", robot.ods1.getLightDetected());
            telemetry.addData("Optical Distance Sensor Reading", robot.ods2.getLightDetected());
            telemetry.addData("Color sensor reading", robot.cs1.argb());
            telemetry.addData("Path2",  "Running at %7d :%7d", robot.leftMotor.getCurrentPosition(), robot.rightMotor.getCurrentPosition());
            */
            //telemetry.addData("Servo Min: ",robot.switcher.MIN_POSITION);
           // telemetry.addData("Servo Max: ",robot.switcher.MAX_POSITION);
           // telemetry.addData("Servo Position: ",robot.switcher.getPosition());
            //telemetry.update();

            left = -gamepad1.left_stick_y;
            right = -gamepad1.right_stick_y;
            robot.leftMotor.setPower(left);

            robot.rightMotor.setPower(right);

            if(gamepad1.left_trigger == 1)
                switchPosition += INCREMENTAL;
            if(gamepad1.right_trigger == 1)
                switchPosition -= INCREMENTAL;

            robot.switcher.setPosition(degree200ToServo(switchPosition));

            telemetry.addData("Current Position: ","%f",robot.switcher.getPosition());
            telemetry.update();
            idle();

/*
            if (gamepad1.y)
            {
                robot.collect.setPower(-collectPower);
                robot.shooter.setPower(-collectPower);
            }
            if(gamepad1.x)
            {
                boolean checker = true;
                do
                {
                    if(gamepad1.b)
                    checker = false;

                    robot.collect.setPower(-collectPower);
                    robot.shooter.setPower(-collectPower);
                }while(checker);
            }
            else
            {
                robot.collect.setPower(0);
                robot.shooter.setPower(0);
            }
            if(gamepad1.right_bumper)
            {
                robot.drawBridge.setPower(-0.5);

                runtime.reset();
                while (opModeIsActive() && (runtime.seconds() < 7)) {
                    telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
                    telemetry.update();
                }
                robot.drawBridge.setPower(0);


            }
            if(gamepad1.left_bumper)
            {
                robot.drawBridge.setPower(-0.5);

                runtime.reset();
                while (opModeIsActive() && (runtime.seconds() < 5.38))
                {
                    telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
                    telemetry.update();
                }
                robot.drawBridge.setPower(0);
                runtime.reset();
                robot.drawBridge.setPower(0.5);
                while (opModeIsActive() && (runtime.seconds() < 5))
                {
                    telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
                    telemetry.update();
                }robot.drawBridge.setPower(0);
            }*/




/*
           switchPosition = Range.clip(switchPosition, 0.2, 0.9);
           robot.switcher.setPosition(switchPosition);
           */
            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
    }
    private static double degree200ToServo(double in) {
        return Range.scale(Range.clip(in,0.0,SERVO_DEGREES),0.0,SERVO_DEGREES, Servo.MIN_POSITION,Servo.MAX_POSITION);
    }

    private static double degree200FromServo(double in) {
        return Range.scale(Range.clip(in,Servo.MIN_POSITION,Servo.MAX_POSITION),Servo.MIN_POSITION,Servo.MAX_POSITION,0.0,SERVO_DEGREES);
    }
}
