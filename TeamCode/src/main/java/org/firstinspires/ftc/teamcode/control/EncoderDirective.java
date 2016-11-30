package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;

import java.util.Set;

public class EncoderDirective {
    public DcMotor motor;
    public double speed;
    public double inches;
    public double timeout;

    public int tickerTarget;

    private boolean complete;

    public EncoderInstruction(DcMotor motor, double speed, double inches, double timeout) throws UnfoundHardwareException {
        if (!(motor instanceof DcMotor))
            throw new UnfoundHardwareException("DcMotor","Unknown");

        this.motor = motor;
        this.speed = speed;
        this.inches = inches;
        this.timeout = timeout;

        this.tickerTarget = motor.getCurrentPosition() + (int) (inches / Constants.OMNIWHEEL_LARGE_CIRCUMFERENCE_IN * Constants.ENCODER_TICKS_PER_REV);

        complete = false;
    }

    public void start() {
        motor.setTargetPosition(tickerTarget);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(Math.abs(speed));
        complete = false;
    }

    public void stop() {
        motor.setPower(0);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        complete = true;
    }

    public boolean busy(ElapsedTime timer) {
        return !complete && motor.isBusy() && timer.seconds() < timeout;
    }
}