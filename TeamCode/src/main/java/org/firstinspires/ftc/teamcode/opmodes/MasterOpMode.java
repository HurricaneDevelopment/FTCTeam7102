package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.control.GamepadHelper;
import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;
import org.firstinspires.ftc.teamcode.hardware.DcMotorW;
import org.firstinspires.ftc.teamcode.robots.Motors;
import org.firstinspires.ftc.teamcode.robots.Robot;

import java.util.List;
/*
Debugger Breaker

while (runtime.seconds() < 5) {
    telemetry.addData("status","break");
    telemetry.update();
}

 */
public abstract class MasterOpMode extends OpMode {

    public static final int SCREEN_FREEZE_TIME = 5;
    
    public Robot robotI;

    public GamepadHelper gh1;
    public GamepadHelper gh2;

    public ElapsedTime runtime;

    public MasterOpMode() {
        super();
        gh1 = new GamepadHelper();
        gh2 = new GamepadHelper();
    }

    public void setRobot(Robot robot) {
        this.robotI = robot;
    }

    @Override
    public void init() {
        runtime = new ElapsedTime();
        runtime.reset();
        telemetry.addData("Status", "Initializing");
        telemetry.addData("Runtime", "%f", runtime.seconds());

        setup();

        robotI.loadHardware(hardwareMap);

        try {
            robotI.start();
        } catch (UnfoundHardwareException ex) {
            while (runtime.seconds() < SCREEN_FREEZE_TIME) {
                telemetry.addData("Error", ex.getMessage());
                telemetry.update();
            }
            requestOpModeStop();
        }

        or_init();
    }

    @Override
    public void init_loop() {
        telemetry.addData("Status", "Preparatory");
        telemetry.addData("Runtime", "%f", runtime.seconds());
        loopInitPre();
        or_loopInit();
        loopInitPost();
    }

    public void loopInitPre() {
        gh1.updateGamepad(gamepad1);
        gh2.updateGamepad(gamepad2);
    }

    public void loopInitPost() {
        gh1.endLoop();
        gh2.endLoop();
    }

    @Override
    public void start() {
        runtime.reset();
        telemetry.addData("Status", "Starting");
        telemetry.addData("Runtime", "%f", runtime.seconds());
        or_start();
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running");
        telemetry.addData("Runtime", "%f", runtime.seconds());
        loopPre();
        or_loop();
        loopPost();
    }

    public void loopPre() {
        gh1.updateGamepad(gamepad1);
        gh2.updateGamepad(gamepad2);
    }

    public void loopPost() {
        gh1.endLoop();
        gh2.endLoop();
    }

    @Override
    public void stop() {
        runtime.reset();
        robotI.stop();
        telemetry.addData("Status", "Stopped");
        telemetry.addData("Runtime", "%f", runtime.seconds());
        or_stop();
    }

    public abstract void setup();

    public void or_init() {

    };

    public void or_loopInit() {

    };

    public void or_start() {

    };

    public void or_loop() {

    };

    public void or_stop() {

    };

}