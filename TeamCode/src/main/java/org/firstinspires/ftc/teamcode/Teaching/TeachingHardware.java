package org.firstinspires.ftc.teamcode.Teaching;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by drjekyll on 2/28/17.
 */

public class TeachingHardware {
    public DcMotor left;
    public DcMotor right;
    public HardwareMap hwmap = null;
    public void start()
    {
        right = hwmap.dcMotor.get("right");
        left = hwmap.dcMotor.get("left");
    }
}
