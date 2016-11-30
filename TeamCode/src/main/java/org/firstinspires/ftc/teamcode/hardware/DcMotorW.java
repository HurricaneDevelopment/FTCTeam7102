package org.firstinspires.ftc.teamcode.hardware;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;
import org.firstinspires.ftc.teamcode.control.EncoderDirective;

public class DcMotorW {

    public DcMotorImpl motor;

    public DcMotorW(DcMotorImpl m) {
        motor = m;
    }

    public EncoderDirective createEncoderInstruction(double speed, double inches, double timeout) throws UnfoundHardwareException {
        return new EncoderDirective(this,speed,inches,timeout);
    }
}