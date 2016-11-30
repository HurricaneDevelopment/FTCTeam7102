public class DcMotorW {

    private DcMotor DCMotor;
    private boolean encoder;
    private boolean reverse;

    public DcMotorW(DcMotor motor,boolean enc,boolean rev) {
        DCMotor = motor;
        encoder = enc;
        reverse = rev;
        updateEncoder();
        updateDirection();

        DCMotor.setPower(0);
    }

    public DcMotorW(DcMotor motor,Properties props) {
        DcMotor = motor;

        String pReverse = props.getProperty(motorName + ".reverse");
        String pEncoder = props.getProperty(motorName + ".encoder");
        
        reverse = (reverse != null && Boolean.parseBoolean(reverse));
        encoder = (encoder != null && Boolean.parseBoolean(encoder));

        updateEncoder();
        updateDirection();

        DCmotor.setPower(0);
    }

    public void setForward() {
        reverse = false;
        updateDirection();
    }

    public void setReverse() {
        reverse = true;
        updateDirection();
    }

    public void swapDirection() {
        reverse = !reverse;
        updateDirection();
    }

    private void updateDirection() {
        DCMotor.setDirection(!reverse ? DcMotor.Direction.FORWARD : DcMotor.Direction.REVERSE);
    }

    public DcMotor.Direction getDirection() {
        return !reverse ? DcMotor.Direction.FORWARD : DcMotor.Direction.REVERSE
    }

    public void enableEncoder() {
        encoder = true;
        updateEncoder();
    }

    public void disableEncoder() {
        encoder = false;
        updateEncoder();
    }

    private void updateEncoder() {
        DCMotor.setMode(encoder ? DcMotor.RunMode.RUN_USING_ENCODER : DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void getEncoder() {
        return encoder ? DcMotor.RunMode.RUN_USING_ENCODER : DcMotor.RunMode.RUN_WITHOUT_ENCODER;
    }

    public DcMotor getMotor() {
        return DCMotor;
    }

    public void setPower(double pow) {
        DCMotor.setPower(Range.clip(pow,0.0,1.0));
    }

    public void linearTimedDrive(double power,double time,) {
        ElapsedTime runtime = new ElapsedTime();
        linearTimedDrive(power,time,runtime);
    }

    public void linearTimedDrive(double power,double time,ElapsedTime runtime) {
        double startTime = runtime.milliseconds();

        setPower(power);
        while(runtime.milliseconds() - startTime < time) {
            // DCMotor.getDeviceName() is running
        }

        setPower(0);
    }

    public void linearEncoderDrive(double power,double target,Units tUnits,int timeout,Unit units) {
        power = Range.clip(power,0.0,1.0);
        target = DCMotor.getCurrentPosition() + 
    }

}