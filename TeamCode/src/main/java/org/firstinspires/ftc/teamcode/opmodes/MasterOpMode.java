public class MasterOpMode extends OpMode {
    
    public Robot robot;

    public GamepadHelper gh1;
    public GamepadHelper gh2;

    public ElapsedTime runtime;

    public MasterOpMode() {
        super();
        gh1 = new GamepadHelper();
        gh2 = new GamepadHelper();
    }

    public setRobot(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void init() {
        runtime.reset();
        robot.start();
        telemetry.addData("Status", "Initializing");
        telemetry.addData("Runtime", "%f", runtime.seconds());
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
        robot.stop();
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