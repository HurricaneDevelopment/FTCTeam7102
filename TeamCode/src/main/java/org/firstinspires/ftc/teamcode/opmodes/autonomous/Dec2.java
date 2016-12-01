package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.control.EncoderInstructionSet;
import org.firstinspires.ftc.teamcode.control.Instruction;
import org.firstinspires.ftc.teamcode.control.WaitInstruction;
import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;
import org.firstinspires.ftc.teamcode.opmodes.MasterOpMode;
import org.firstinspires.ftc.teamcode.robots.configurations.RobotTankDriveBeacon;

import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by Spencer on 12/1/2016.
 */
@Autonomous(name="Dec2 TeleOp", group="Competition")
@Disabled
public class Dec2 extends MasterOpMode {

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
    public void or_start() {
        robot.reverseDrive();

        try {

            ArrayList<Instruction> instructions = new ArrayList<Instruction>();

            EncoderInstructionSet moveFromWall = new EncoderInstructionSet();
            moveFromWall.add(robot.leftDrive.createEncoderInstruction(0.5, 10, 5));
            moveFromWall.add(robot.rightDrive.createEncoderInstruction(0.5, 10, 5));
            instructions.add(moveFromWall);
            instructions.add(new WaitInstruction(750));

            EncoderInstructionSet turnToBeacons = new EncoderInstructionSet();
            turnToBeacons.add(robot.leftDrive.createEncoderInstruction(0.5, 7, 5));
            turnToBeacons.add(robot.rightDrive.createEncoderInstruction(0.5, -7, 5));
            instructions.add(turnToBeacons);
            instructions.add(new WaitInstruction(750));

            EncoderInstructionSet moveToBeacons = new EncoderInstructionSet();
            moveToBeacons.add(robot.leftDrive.createEncoderInstruction(0.5, 25, 5));
            moveToBeacons.add(robot.rightDrive.createEncoderInstruction(0.5, 25, 5));
            instructions.add(moveToBeacons);
            instructions.add(new WaitInstruction(750));

        } catch (UnfoundHardwareException ex) {

        }
    }
}
