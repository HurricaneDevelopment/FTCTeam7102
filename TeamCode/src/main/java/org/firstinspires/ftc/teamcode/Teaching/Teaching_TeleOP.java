package org.firstinspires.ftc.teamcode.Teaching;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by drjekyll on 2/28/17.
 */

public class TeachingTeleOP extends LinearOpMode {
    TeachingHardware bob  = new TeachingHardware();
    public void runOpMode() {
        waitForStart();
        while(opModeIsActive()) {
            double left = gamepad1.left_stick_y;
            double right = gamepad1.right_stick_y;
            bob.left.setPower(left);
            bob.right.setPower(right);
            idle();
        }
    }

}
