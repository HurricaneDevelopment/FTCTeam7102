package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.control.EncoderInstructionSet;
import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;
import org.firstinspires.ftc.teamcode.opmodes.MasterOpMode;
import org.firstinspires.ftc.teamcode.robots.configurations.RobotTankDriveBeacon;

/**
 * Created by drjekyll on 1/17/17.
 */
@Autonomous(name="Dec2 Red", group="Competition")
@Disabled
public class AtonomousRed extends MasterOpMode{

    public RobotTankDriveBeacon robot;

    @Override
    public void setup() {
        setRobot(new RobotTankDriveBeacon());
        robot = (RobotTankDriveBeacon) robotI;
    }

    @Override
    public void or_init() {
        runtime.reset();
    }

    @Override
    public void or_loop() {
        try {

            EncoderInstructionSet encStruct = new EncoderInstructionSet();
            encStruct.add(robot.leftDrive.createEncoderInstruction(0.5, 5, 5));
            encStruct.add(robot.rightDrive.createEncoderInstruction(0.5, 5, 5));
            encStruct.run();
            robot.pause(1500);

            encStruct = new EncoderInstructionSet();
            encStruct.add(robot.leftDrive.createEncoderInstruction(0.5, -2, 5));
            encStruct.add(robot.rightDrive.createEncoderInstruction(0.5, 2, 5));
            encStruct.run();
            robot.pause(1500);

            encStruct = new EncoderInstructionSet();
            encStruct.add(robot.leftDrive.createEncoderInstruction(0.5, -2, 5));
            encStruct.add(robot.rightDrive.createEncoderInstruction(0.5, -2, 5));
            encStruct.run();
            robot.pause(1500);


            robot.goToDistance(3, 4);
            robot.lightLineUp();

            encStruct = new EncoderInstructionSet();

            encStruct.add(robot.leftDrive.createEncoderInstruction(0.5,-4,5));
            encStruct.add(robot.rightDrive.createEncoderInstruction(0.5, -4, 5));
            encStruct.run();
            robot.pause(1500);

            robot.lightLineUp();



        } catch (UnfoundHardwareException ex) {
            runtime.reset();
            while (runtime.seconds() < SCREEN_FREEZE_TIME) {
                telemetry.addData("Error", ex.getMessage());
                telemetry.update();
            }
            requestOpModeStop();
        }
    }


}
