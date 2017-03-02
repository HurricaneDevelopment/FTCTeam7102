package org.firstinspires.ftc.teamcode.Teaching;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by drjekyll on 2/28/17.
 */

public class TeachingHardware {
    public DcMotor left;
    public DcMotor right;
    public DcMotor shooter;
    public DcMotor firstSweeper;
    public DcMotor scndSweeper;

    public Servo beacon;

    private final static double beaconHome = 0.43;

    HardwareMap hwmap = null;

    public TeachingHardware(){

    }

    public void start(HardwareMap ahwmap)
    {
        hwmap = ahwmap;
        right = hwmap.dcMotor.get("right");
        left = hwmap.dcMotor.get("left");
        shooter = hwmap.dcMotor.get("shooter");
        firstSweeper = hwmap.dcMotor.get("firstSweeper");
        scndSweeper = hwmap.dcMotor.get("scndSweeper");

        beacon = hwmap.servo.get("beacon");

        left.setDirection(DcMotor.Direction.REVERSE);


        left.setPower(0);
        right.setPower(0);

        shooter.setPower(0);
        firstSweeper.setPower(0);
        scndSweeper.setPower(0);

        beacon.setPosition(beaconHome);

        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        shooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        firstSweeper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        scndSweeper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
