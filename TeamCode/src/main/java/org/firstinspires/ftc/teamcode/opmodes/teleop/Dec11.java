package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robots.configurations.Hardware7102;
/**
 * Created by drjekyll on 12/11/16.
 */

public class Dec11 extends LinearOpMode{
    Hardware7102 robot = new Hardware7102();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        double left;
        double right;
        double collectPower = 1.0;
       // double switchPosition = robot.switcher.getPosition();



        waitForStart();

        while (opModeIsActive()) {


            left = -gamepad1.left_stick_y;
            right = -gamepad1.right_stick_y;
            robot.left.motor.setPower(left);

            robot.right.motor.setPower(right);

            if (gamepad1.right_trigger > 0.25)
                robot.beaconSwitcher.increment(-0.02);
            if (gamepad1.left_trigger > 0.25)
                robot.beaconSwitcher.increment(0.02);

            telemetry.update();
            idle();
        }
    }


}
