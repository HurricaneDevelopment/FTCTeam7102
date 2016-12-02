package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.control.EncoderInstructionSet;
import org.firstinspires.ftc.teamcode.control.Instruction;
import org.firstinspires.ftc.teamcode.control.WaitInstruction;
import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;
import org.firstinspires.ftc.teamcode.opmodes.MasterOpMode;
import org.firstinspires.ftc.teamcode.robots.configurations.RobotTankDriveBeacon;
import org.firstinspires.ftc.teamcode.VelocityVortex.FieldConstants;

import java.util.ArrayList;

/**
 * Created by Spencer on 12/1/2016.
 */
//For Blue Side
@Autonomous(name="Dec2 TeleOp", group="Competition")
@Disabled
public class Dec2Blue extends MasterOpMode {

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

            //Run Alistairs Straightening Algorithm
            instructions.add(robot.ultraParallel());

            instructions.add(new Instruction() {
                @Override
                public void run() {
                    double z = 3.5;

                    double stealthInch = robot.ultrasonicToInches(robot.ultraStealth.getUltrasonicLevel());
                    double omniInch = robot.ultrasonicToInches(robot.ultraOmni.getUltrasonicLevel());

                    if (stealthInch > z)
                        try {
                            EncoderInstructionSet slightTurn = new EncoderInstructionSet();
                            slightTurn.add(robot.leftDrive.createEncoderInstruction(0.5, 3, 5));
                            slightTurn.run();

                            while (stealthInch > 3.8 || stealthInch < 3.2 ) {
                                robot.leftDrive.motor.setPower(0.25);
                                robot.rightDrive.motor.setPower(0.25);

                                stealthInch = robot.ultrasonicToInches(robot.ultraStealth.getUltrasonicLevel());
                                omniInch = robot.ultrasonicToInches(robot.ultraOmni.getUltrasonicLevel());
                            }

                            robot.leftDrive.motor.setPower(0);
                            robot.rightDrive.motor.setPower(0);
                        } catch (UnfoundHardwareException ex) {
                            runtime.reset();
                            while (runtime.seconds() < SCREEN_FREEZE_TIME) {
                                telemetry.addData("Error", ex.getMessage());
                                telemetry.update();
                            }
                            requestOpModeStop();
                        }

                    if (stealthInch < z)
                        try {
                            EncoderInstructionSet slightTurn = new EncoderInstructionSet();
                            slightTurn.add(robot.leftDrive.createEncoderInstruction(0.5, 3, 5));
                            slightTurn.run();

                            while (stealthInch > 3.8 || stealthInch < 3.2 ) {
                                robot.leftDrive.motor.setPower(-0.25);
                                robot.rightDrive.motor.setPower(-0.25);

                                stealthInch = robot.ultrasonicToInches(robot.ultraStealth.getUltrasonicLevel());
                                omniInch = robot.ultrasonicToInches(robot.ultraOmni.getUltrasonicLevel());
                            }

                            robot.leftDrive.motor.setPower(0);
                            robot.rightDrive.motor.setPower(0);
                        } catch (UnfoundHardwareException ex) {
                            runtime.reset();
                            while (runtime.seconds() < SCREEN_FREEZE_TIME) {
                                telemetry.addData("Error", ex.getMessage());
                                telemetry.update();
                            }
                            requestOpModeStop();
                        }
                    }
            });

            instructions.add(robot.ultraParallel());

            instructions.add(new Instruction() {
                @Override
                public void run() {
                    // Drive until one light sensor hits the line
                    while(robot.bsLightSensor.getLightDetected() <= FieldConstants.LIGHT_REFLECTION_15MM_MAT_MAX || robot.nbsLightSensor.getLightDetected() <= FieldConstants.LIGHT_REFLECTION_15MM_MAT_MAX) {
                        runtime.reset();
                        while (runtime.seconds() <  3) {
                            robot.rightDrive.motor.setPower(0.5);
                            robot.leftDrive.motor.setPower(0.5);
                        }

                        robot.leftDrive.motor.setPower(0);
                        robot.rightDrive.motor.setPower(0);

                        robot.ultraParallel().run();
                    }

                    if (robot.bsLightSensor.getLightDetected() > FieldConstants.LIGHT_REFLECTION_15MM_MAT_MAX)
                    {
                        while (robot.nbsLightSensor.getLightDetected() <= FieldConstants.LIGHT_REFLECTION_15MM_MAT_MAX) 
                            robot.leftDrive.motor.setPower(0.25);
                    }
                    else
                    {
                        while(robot.bsLightSensor.getLightDetected() <= FieldConstants.LIGHT_REFLECTION_15MM_MAT_MAX)
                            robot.rightDrive.motor.setPower(0.25);
                    }

                    robot.leftDrive.motor.setPower(0);
                    robot.rightDrive.motor.setPower(0);
                }
            });
/*
            instructions.add(new Instruction() {
                @Override
                public void run() {
                    // Check color, Move the robot, hit the beacon
                    robot.beaconSwitcher.goHome();
                    if(robot.colorSensor.isBeaconBlue())
                        robot.beaconSwitcher.increment(0.25);
                    else
                        robot.beaconSwitcher.increment(-0.25);
                }
            });

            instructions.add(new Instruction() {
                @Override
                public void run() {
                    // Drive  BACKWARD until one light sensor hits the line
                    do {
                        robot.rightDrive.motor.setPower(-0.5);
                        robot.leftDrive.motor.setPower(-0.5);
                    }while(robot.bsLightSensor.getLightDetected() != 0.5 || robot.nbsLightSensor.getLightDetected() != 0.5);
                    // Then Line up the other side

                    if(robot.bsLightSensor.getLightDetected() == 0.5)
                    {
                        while (robot.nbsLightSensor.getLightDetected() != 0.5)
                            robot.leftDrive.motor.setPower(0.25);
                    }
                    else
                    {
                        while(robot.bsLightSensor.getLightDetected() == 0.5)
                            robot.rightDrive.motor.setPower(0.25);
                    }
                }

            });

            instructions.add(new Instruction() {
                @Override
                public void run() {
                    // Check color, hit the beacon
                    robot.beaconSwitcher.goHome();
                    if(robot.colorSensor.isBeaconBlue())
                        robot.beaconSwitcher.increment(0.25);
                    else
                        robot.beaconSwitcher.increment(-0.25);
                }
            });
            */



            for (Instruction inst : instructions)
                inst.run();

        } catch (UnfoundHardwareException ex) {

        }
    }
}
