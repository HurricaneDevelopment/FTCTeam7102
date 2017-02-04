package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.VelocityVortex.FieldConstants;
import org.firstinspires.ftc.teamcode.control.EncoderInstructionSet;
import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;
import org.firstinspires.ftc.teamcode.robots.configurations.RobotTankDriveBeacon;

/**
 * Created by Spencer on 12/2/2016.
 */
@Autonomous(name="Dec2 Red Line", group="Competition")
public class Dec2BlueLinear extends LinearOpMode {

    public RobotTankDriveBeacon robot;
    public ElapsedTime runtime;

    @Override
    public void runOpMode() {
        runtime = new ElapsedTime();
        try {
            robot = new RobotTankDriveBeacon();
            robot.loadHardware(hardwareMap);
            robot.start();

            //robot.reverseDrive();

            waitForStart();

            EncoderInstructionSet encStruct = new EncoderInstructionSet();
            encStruct.add(robot.leftDrive.createEncoderInstruction(1, 9, 5));
            encStruct.add(robot.rightDrive.createEncoderInstruction(1, 9, 5));
            encStruct.run();
            idle();
            robot.ultraParallel();

            encStruct = new EncoderInstructionSet();
            encStruct.add(robot.leftDrive.createEncoderInstruction(1, 3.5, 5));
            encStruct.add(robot.rightDrive.createEncoderInstruction(1, -3.5, 5));
            encStruct.run();
            idle();

            encStruct = new EncoderInstructionSet();
            encStruct.add(robot.leftDrive.createEncoderInstruction(1, 76, 5));
            encStruct.add(robot.rightDrive.createEncoderInstruction(1, 76, 5));
            encStruct.run();
            idle();

            robot.ultraParallel();
            idle();
            robot.pause(100);

            encStruct = new EncoderInstructionSet();
            encStruct.add(robot.leftDrive.createEncoderInstruction(0.75, 22, 5));
            encStruct.add(robot.rightDrive.createEncoderInstruction(0.75, 22, 5));
            encStruct.run();
            idle();
            robot.pause(100);

            robot.goToDistance(3.1, 4.0);
            idle();
            robot.pause(100);
            robot.ultraParallel();
            robot.pause(100);



            // Drive until one light sensor hits the line
            while(robot.bsLightSensor.getLightDetected() < FieldConstants.LIGHT_REFLECTION_10MM_WHITE_SECURE && robot.nbsLightSensor.getLightDetected() < FieldConstants.LIGHT_REFLECTION_15MM_WHITE_SECURE) {
                runtime.reset();

                boolean found = false;

                while (runtime.seconds() <  3) {
                    robot.rightDrive.motor.setPower(0.15);
                    robot.leftDrive.motor.setPower(0.15);

                    if (!(robot.bsLightSensor.getLightDetected() < FieldConstants.LIGHT_REFLECTION_10MM_WHITE_SECURE && robot.nbsLightSensor.getLightDetected() < FieldConstants.LIGHT_REFLECTION_15MM_WHITE_SECURE)) {
                        found = true;
                        break;
                    }
                }

                robot.leftDrive.motor.setPower(0);
                robot.rightDrive.motor.setPower(0);

                if (found)
                    break;

                robot.ultraParallel();
            }

            robot.pause(100);
            idle();

            if (robot.bsLightSensor.getLightDetected() >= FieldConstants.LIGHT_REFLECTION_10MM_WHITE_SECURE)
            {
                while (robot.nbsLightSensor.getLightDetected() < FieldConstants.LIGHT_REFLECTION_15MM_WHITE_SECURE)
                    if (robot.reversed)
                        robot.leftDrive.motor.setPower(0.2);
                    else
                        robot.rightDrive.motor.setPower(0.2);
            }
            else
            {
                while(robot.bsLightSensor.getLightDetected() < FieldConstants.LIGHT_REFLECTION_10MM_WHITE_SECURE)
                    if (robot.reversed)
                        robot.rightDrive.motor.setPower(0.2);
                    else
                        robot.leftDrive.motor.setPower(0.2);
            }

            robot.leftDrive.motor.setPower(0);
            robot.rightDrive.motor.setPower(0);

            idle();

            encStruct = new EncoderInstructionSet();
            encStruct.add(robot.leftDrive.createEncoderInstruction(0.25, 3.05, 5));
            encStruct.add(robot.rightDrive.createEncoderInstruction(0.25, 3.05, 5));
            encStruct.run();
            idle();

            if (robot.colorSensor.isBeaconBlue())
                robot.beaconSwitcher.increment(-0.25);
            else if (robot.colorSensor.isBeaconRed())
                robot.beaconSwitcher.increment(0.25);
            idle();
            robot.pause(1000);
            idle();
            robot.beaconSwitcher.goHome();
            idle();
            robot.pause(1000);
            idle();
            if (robot.colorSensor.isBeaconBlue())
                robot.beaconSwitcher.increment(-0.25);
            else if (robot.colorSensor.isBeaconRed())
                robot.beaconSwitcher.increment(0.25);
            idle();
            robot.pause(1000);
            idle();
            robot.beaconSwitcher.goHome();

            /*
            //Second Beacon
            while(robot.bsLightSensor.getLightDetected() < FieldConstants.LIGHT_REFLECTION_10MM_WHITE_SECURE && robot.nbsLightSensor.getLightDetected() < FieldConstants.LIGHT_REFLECTION_15MM_WHITE_SECURE) {
                runtime.reset();

                boolean found = false;

                while (runtime.seconds() <  3) {
                    robot.rightDrive.motor.setPower(-0.1);
                    robot.leftDrive.motor.setPower(-0.1);

                    if (!(robot.bsLightSensor.getLightDetected() < FieldConstants.LIGHT_REFLECTION_10MM_WHITE_SECURE && robot.nbsLightSensor.getLightDetected() < FieldConstants.LIGHT_REFLECTION_15MM_WHITE_SECURE)) {
                        found = true;
                        break;
                    }
                }

                robot.leftDrive.motor.setPower(0);
                robot.rightDrive.motor.setPower(0);

                if (found)
                    break;

                robot.ultraParallel();
            }

            robot.pause(500);
            idle();

            if (robot.bsLightSensor.getLightDetected() >= FieldConstants.LIGHT_REFLECTION_10MM_WHITE_SECURE)
            {
                while (robot.nbsLightSensor.getLightDetected() < FieldConstants.LIGHT_REFLECTION_15MM_WHITE_SECURE)
                    if (robot.reversed)
                        robot.leftDrive.motor.setPower(0.1);
                    else
                        robot.rightDrive.motor.setPower(-0.1);
            }
            else
            {
                while(robot.bsLightSensor.getLightDetected() < FieldConstants.LIGHT_REFLECTION_10MM_WHITE_SECURE)
                    if (robot.reversed)
                        robot.rightDrive.motor.setPower(0.1);
                    else
                        robot.leftDrive.motor.setPower(-0.1);
            }

            robot.leftDrive.motor.setPower(0);
            robot.rightDrive.motor.setPower(0);

            idle();

            encStruct = new EncoderInstructionSet();
            encStruct.add(robot.leftDrive.createEncoderInstruction(0.2, 3.05, 5));
            encStruct.add(robot.rightDrive.createEncoderInstruction(0.2, 3.05, 5));
            encStruct.run();
            idle();

            if (robot.colorSensor.isBeaconRed())
                robot.beaconSwitcher.increment(0.25);
            else if (robot.colorSensor.isBeaconBlue())
                robot.beaconSwitcher.increment(-0.25);
            idle();
            robot.pause(500);
            idle();
            robot.beaconSwitcher.goHome();
            idle();
            robot.pause(500);
            idle();
            if (robot.colorSensor.isBeaconRed())
                robot.beaconSwitcher.increment(0.25);
            else if (robot.colorSensor.isBeaconBlue())
                robot.beaconSwitcher.increment(-0.25);
            idle();
            robot.pause(500);
            idle();
            robot.beaconSwitcher.goHome();*/

            robot.stop();
        } catch (UnfoundHardwareException ex) {
            while (runtime.seconds() < 3) {
                telemetry.addData("Error", ex.getMessage());
                telemetry.update();
            }
            requestOpModeStop();
        }
    }
}
