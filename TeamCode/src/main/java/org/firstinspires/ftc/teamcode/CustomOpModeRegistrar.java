package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegistrar;

import org.firstinspires.ftc.teamcode.opmodes.autonomous.Dec2BlueLinear;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.Dec2RedLinear;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.DriveUp;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.LineUpTest;
import org.firstinspires.ftc.teamcode.opmodes.teleop.Dec11;
import org.firstinspires.ftc.teamcode.opmodes.teleop.Dec2;
import org.firstinspires.ftc.teamcode.opmodes.teleop.EncoderTool;
import org.firstinspires.ftc.teamcode.opmodes.tests.ColorTest;
import org.firstinspires.ftc.teamcode.opmodes.tests.Custo_ultra_test;
import org.firstinspires.ftc.teamcode.opmodes.tests.ELGooUltrasonicTest;
import org.firstinspires.ftc.teamcode.opmodes.tests.ODSTest;
import org.firstinspires.ftc.teamcode.opmodes.tests.ServoColorTest;
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
        manager.register("Light Test", org.firstinspires.ftc.teamcode.LightTest.class);
        manager.register("Color Test", ColorTest.class);
        manager.register("Ultrasonic Test", UltrasonicTestDouble.class);
       // manager.register("Dec2 Red", Dec2Red.class);
        manager.register("Dec2 Auto Red L", Dec2RedLinear.class);
        manager.register("Dec2 Teleop", Dec2.class);
        manager.register("Optical Distance Sensor Test", ODSTest.class);
        manager.register("Dec2 Auto Blue L", Dec2BlueLinear.class);
        manager.register("Color Beacon Test", ServoColorTest.class);
        manager.register("Dec11 Teleop", Dec11.class);
        manager.register("DriveUP", DriveUp.class);
        manager.register("Ultra_Test", Custo_ultra_test.class);
        manager.register("LineUp Test", LineUpTest.class);
        manager.register("ElgooUltrasonic test", ELGooUltrasonicTest.class);

    }
}
