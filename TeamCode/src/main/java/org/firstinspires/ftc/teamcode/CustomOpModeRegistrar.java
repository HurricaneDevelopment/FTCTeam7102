package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegistrar;

import org.firstinspires.ftc.teamcode.LightTest;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.Dec2Red;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.Dec2RedLinear;
import org.firstinspires.ftc.teamcode.opmodes.teleop.Dec2;
import org.firstinspires.ftc.teamcode.opmodes.teleop.EncoderTool;
import org.firstinspires.ftc.teamcode.opmodes.tests.ColorTest;
import org.firstinspires.ftc.teamcode.opmodes.tests.UltrasonicTest;
import org.firstinspires.ftc.teamcode.opmodes.tests.UltrasonicTestDouble;


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
        manager.register("Light Test", LightTest.class);
        manager.register("Color Test", ColorTest.class);
        manager.register("Ultrasonic Test", UltrasonicTestDouble.class);
       // manager.register("Dec2 Red", Dec2Red.class);
        manager.register("Dec2 Auto Red L", Dec2RedLinear.class);
        manager.register("Dec2 Teleop", Dec2.class);

    }
}
