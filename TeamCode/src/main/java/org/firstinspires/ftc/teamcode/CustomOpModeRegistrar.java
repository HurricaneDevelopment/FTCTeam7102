package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegistrar;

import org.firstinspires.ftc.teamcode.opmodes.teleop.EncoderTool;


/*
*   OpMode Types
*   ------------
*   @Autonomous(name="NAME", group ="GROUP")
*   @TeleOp(name="NAME", group ="GROUP")
 */
public class CustomOpModeRegistrar {

    @OpModeRegistrar
    public static void registerOpModes(OpModeManager manager) {
        manager.register("Encoder Tool", EncoderTool.class);
    }
}
