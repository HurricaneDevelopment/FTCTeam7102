package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotDec2 {

    public static final double BEACONLINKAGE_CENTER = 0.5;
    public static final double BEACONLINKAGE_LEFT = 0.2;
    public static final double BEACONLINKAGE_RIGHT = 0.8;

    public HardwareMap hwMap;

    public DcMotor leftDrive;
    public DcMotor rightDrive;

    public Servo beaconLinkage;

    public RobotDec2(HardwareMap aHwMap) {
        this.hwMap = aHwMap;
        this.hwMap = aHwMap;

        leftDrive = hwMap.dcMotor.get("left");
        rightDrive = hwMap.dcMotor.get("right");

        beaconLinkage = hwMap.servo.get("beacon");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
    }

}
