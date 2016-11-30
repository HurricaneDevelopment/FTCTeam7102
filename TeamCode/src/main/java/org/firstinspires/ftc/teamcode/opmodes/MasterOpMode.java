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

public class MasterOpMode extends OpMode {
    
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
        or_init();
        try {
            robotI.start();
        } catch (UnfoundHardwareException ex) {
            //telemetry.clearAll();
            telemetry.addData("Error",ex.getMessage());
            telemetry.update();

            Motors motos = new Motors();
            List<DcMotorImpl> devices = hardwareMap.getAll(DcMotorImpl.class);
            telemetry.addData("Devices",devices);

            for(DcMotorImpl device : devices) {
                telemetry.addData(hardwareMap.getNamesOf(device).iterator().next(),new DcMotorW(device));
                motos.Motors.put(hardwareMap.getNamesOf(device).iterator().next(),new DcMotorW(device));
            }
            telemetry.update();
            while (runtime.seconds() < 5) {
                //telemetry.addData("Error",ex.getMessage());
                //telemetry.addData("Motors",robotI.motors.toString());
                //telemetry.addData("All Hardware",hardwareMap.getNamesOf(hardwareMap.getAll(DcMotor.class).get(0)).iterator().next());
                //telemetry.update();
                List<DcMotorImpl> devi = hardwareMap.getAll(DcMotorImpl.class);
                telemetry.addData("Devices",devi);
            }
            requestOpModeStop();
        }
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