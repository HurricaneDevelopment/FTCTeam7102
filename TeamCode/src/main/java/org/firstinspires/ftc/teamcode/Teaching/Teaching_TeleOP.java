package org.firstinspires.ftc.teamcode.Teaching;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by drjekyll on 2/28/17.
 */

public class Teaching_TeleOP extends LinearOpMode {
    TeachingHardware bob  = new TeachingHardware();
    public void runOpMode() {
        bob.start(hardwareMap);
        waitForStart();
        while(opModeIsActive()) {
            double left = gamepad1.left_stick_y;
            double right = gamepad1.right_stick_y;
            bob.left.setPower(left);
            bob.right.setPower(right);

            if (gamepad1.a)
            {
                bob.firstSweeper.setPower(0.5);
            }
            else {
                bob.firstSweeper.setPower(0);
            }

            if (gamepad1.x) {
                bob.scndSweeper.setPower(0.5);
            }
            else {
                bob.scndSweeper.setPower(0);
            }

            if (gamepad1.y) {
                bob.shooter.setPower(1.0);
            }
            else {
                bob.shooter.setPower(0);
            }

            if(gamepad1.right_trigger > 0.25) {
                double temp = bob.beacon.getPosition();
                bob.beacon.setPosition(temp - 0.02);
            }
            else {
                double temp = bob.beacon.getPosition();
                bob.beacon.setPosition(temp + 0.02);
            }

            idle();
        }
    }

}
