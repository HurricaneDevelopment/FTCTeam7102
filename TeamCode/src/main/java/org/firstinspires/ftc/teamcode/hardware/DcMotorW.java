public class DcMotorW extends DcMotor {
    public EncoderInstruction createEncoderInstruction(double speed, double inches, double timeout) throws UnfoundHardwareException {
        return new EncoderInstruction(this,speed,inches,timeout);
    }
}