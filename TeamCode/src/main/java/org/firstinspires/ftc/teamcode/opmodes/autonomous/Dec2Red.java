package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.VelocityVortex.FieldConstants;
import org.firstinspires.ftc.teamcode.control.EncoderInstructionSet;
import org.firstinspires.ftc.teamcode.control.Instruction;
import org.firstinspires.ftc.teamcode.control.WaitInstruction;
import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;
import org.firstinspires.ftc.teamcode.opmodes.MasterOpMode;
import org.firstinspires.ftc.teamcode.robots.configurations.RobotTankDriveBeacon;

import java.util.ArrayList;

/**
 * Created by Spencer on 12/1/2016.
 */
//For Blue Side
@Autonomous(name="Dec2 Red", group="Competition")
@Disabled
public class Dec2Red extends MasterOpMode {

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
        /*
    }
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

            robot.ultraParallel();
            robot.goToDistance(3, 4);
            robot.ultraParallel();



/*
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
            */
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


/*
            for (Instruction inst : instructions)
                inst.run();*//*

        } catch (UnfoundHardwareException ex) {
            runtime.reset();
            while (runtime.seconds() < SCREEN_FREEZE_TIME) {
                telemetry.addData("Error", ex.getMessage());
                telemetry.update();
            }
            requestOpModeStop();
        }*/
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

            robot.ultraParallel();
            robot.goToDistance(3, 4);
            robot.ultraParallel();
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
