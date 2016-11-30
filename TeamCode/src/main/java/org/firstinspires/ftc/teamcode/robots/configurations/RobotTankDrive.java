public class RobotEncodedTankDrive extends Robot {

    public DcMotorW leftDrive;
    public DcMotorW rightDrive;

    private int motorRightRelativePosition;
    private int motorRightBasePosition;
    private int motorLeftRelativePosition;
    private int motorLeftBasePosition;

    public void start() throws UnfoundHardwareException {
        leftDrive = motors.get("left");
        rightDrive = motors.get("right");

        leftDrive.setDirection(DcMotorW.Direction.REVERSE);
        rightDrive.setDirection(DcMotorW.Direction.REVERSE);

        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftDriveBasePosition = leftDrive.getCurrentPosition();
        rightDriveBasePosition = rightDrive.getCurrentPosition();
    }

    public void reverseDrive() {
        DcMotorW temp = leftDrive;
        leftDrive = rightDrive;
        rightDrive = leftDrive;

        leftDrive.setDirection((leftDrive.getDirection() == DcMotor.Direction.FORWARD ? (DcMotor.Direction.REVERSE) : (DcMotor.Direction.FORWARD)));
        rightDrive.setDirection((rightDrive.getDirection() == DcMotor.Direction.FORWARD ? (DcMotor.Direction.REVERSE) : (DcMotor.Direction.FORWARD)));
    }
}