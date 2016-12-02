package org.firstinspires.ftc.teamcode.robots.configurations;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.control.Instruction;
import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;
import org.firstinspires.ftc.teamcode.hardware.DcMotorW;
import org.firstinspires.ftc.teamcode.hardware.ServoW;

import org.firstinspires.ftc.teamcode.robots.Robot;
import org.firstinspires.ftc.teamcode.hardware.AdaFruitColorSensor;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Spencer on 11/30/2016.
 */
public class RobotTankDriveBeacon extends Robot {
    public DcMotorW leftDrive;
    public DcMotorW rightDrive;

    public ServoW beaconSwitcher;

    public AdaFruitColorSensor colorSensor;
    public UltrasonicSensor ultraOmni;
    public UltrasonicSensor ultraStealth;

    public LightSensor bsLightSensor;
    public LightSensor nbsLightSensor;

    public int rightDriveRelativePosition;
    public int rightDriveBasePosition;
    public int leftDriveRelativePosition;
    public int leftDriveBasePosition;

    public boolean reversed;

    public RobotTankDriveBeacon() {
        super();
        reversed = false;
    }

    public void loadHardware(HardwareMap hwmap) {
        super.loadHardware(hwmap);
        colorSensor = new AdaFruitColorSensor("color",5);
        try {
            colorSensor.loadHardware(hwmap);
        } catch (UnfoundHardwareException ex) {
            colorSensor = null;
        }
        ultraOmni = hwmap.ultrasonicSensor.get("uOmni");
        ultraStealth = hwmap.ultrasonicSensor.get("uStealth");
        bsLightSensor = hwmap.lightSensor.get("bsLS");
        nbsLightSensor =  hwmap.lightSensor.get("nbsLS");
    }

    @Override
    public void start() throws UnfoundHardwareException {
        leftDrive = motors.get("left");
        rightDrive = motors.get("right");

        leftDrive.motor.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.motor.setDirection(DcMotor.Direction.REVERSE);

        leftDrive.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftDriveBasePosition = leftDrive.motor.getCurrentPosition();
        rightDriveBasePosition = rightDrive.motor.getCurrentPosition();

        leftDriveRelativePosition = 0;
        rightDriveRelativePosition = 0;


        beaconSwitcher = servos.get("beacon");

        beaconSwitcher.home = 0.43;
        beaconSwitcher.goHome();


        colorSensor.ledOff();
    }

    public void reverseDrive() {
        DcMotorW temp = leftDrive;
        leftDrive = rightDrive;
        rightDrive = temp;

        leftDrive.motor.setDirection((leftDrive.motor.getDirection() == DcMotor.Direction.FORWARD ? (DcMotor.Direction.REVERSE) : (DcMotor.Direction.FORWARD)));
        rightDrive.motor.setDirection((rightDrive.motor.getDirection() == DcMotor.Direction.FORWARD ? (DcMotor.Direction.REVERSE) : (DcMotor.Direction.FORWARD)));

        reversed = !reversed;
    }

    public double ultrasonicToInches(double ultraVal) {
        double inches = 0.4301 * ultraVal - 2.1553;

        BigDecimal bd = new BigDecimal(inches);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public Instruction ultraParallel() {
        return new Instruction() {
            @Override
            public void run() {
                double stealthInch = ultrasonicToInches(ultraStealth.getUltrasonicLevel());
                double omniInch = ultrasonicToInches(ultraOmni.getUltrasonicLevel());

                while (stealthInch != omniInch) {
                    if (omniInch > stealthInch)
                        if (reversed)
                            leftDrive.motor.setPower(-0.25);
                        else
                            rightDrive.motor.setPower(0.25);

                    if (omniInch < stealthInch)
                        if (reversed)
                            leftDrive.motor.setPower(0.25);
                        else
                            rightDrive.motor.setPower(-0.25);

                    stealthInch = ultrasonicToInches(ultraStealth.getUltrasonicLevel());
                    omniInch = ultrasonicToInches(ultraOmni.getUltrasonicLevel());
                }

                leftDrive.motor.setPower(0);
                rightDrive.motor.setPower(0);
            }
        };
    }

    public void debugTelem(Telemetry telem) {
        ElapsedTime t = new ElapsedTime();
        while (t.seconds() < 5) {
            telem.addData("uOmni",ultraOmni == null);
            telem.addData("uOmni",ultraStealth == null);
            telem.addData("uOmni",bsLightSensor == null);
            telem.addData("uOmni",nbsLightSensor == null);
            telem.update();
        }
    }
}
