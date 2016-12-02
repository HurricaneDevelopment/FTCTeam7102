package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegistrar;

import org.firstinspires.ftc.teamcode.opmodes.autonomous.Dec2Red;
import org.firstinspires.ftc.teamcode.opmodes.teleop.EncoderTool;
import org.firstinspires.ftc.teamcode.opmodes.tests.UltrasonicTest;


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
        manager.register("Ultrasonic Test", UltrasonicTest.class);
        manager.register("Dec2 Red", Dec2Red.class);
    }
}
