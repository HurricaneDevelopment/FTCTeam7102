package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.control.EncoderInstructionSet;
import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;
import org.firstinspires.ftc.teamcode.robots.configurations.RobotTankDriveBeacon;

/**
 * Created by Eli Zidel on 12/11/16.
 */

public class DriveUp extends LinearOpMode {
    public RobotTankDriveBeacon robot;
    public ElapsedTime runtime;

    @Override
    public void runOpMode() {
        runtime = new ElapsedTime();
        try {
            EncoderInstructionSet encStruct = new EncoderInstructionSet();
            robot.leftDrive.createEncoderInstruction(0.5,45.75,5);
            robot.rightDrive.createEncoderInstruction(0.5,45.75,5);
            encStruct.run();
            idle();
            robot.stop();
        } catch (UnfoundHardwareException ex) {
            while (runtime.seconds() < 3) {
                telemetry.addData("Error", ex.getMessage());
                telemetry.update();
            }
            requestOpModeStop();
        }
    }
    /*
    public void makeParallel()//replace ultraParallel with this.
    {
        //make the right side of the robot
        while(robot.getUltraOmni() > robot.getUltraStealth())
            robot.rightDrive.motor.setPower(-0.5);
        while(robot.getUltraOmni()<robot.getUltraStealth())
            robot.rightDrive.motor.setPower(0.5);
    }
    */
}
