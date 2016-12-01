package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.ServoImpl;

/**
 * Created by Spencer on 11/30/2016.
 */
public class ServoW {
    public ServoImpl servo;

    public double home;

    public ServoW(ServoImpl s) {
        servo = s;
        home = 0.5;
    }

    public void goHome() {
        servo.setPosition(home);
    }

    public void increment(double inc) {
        servo.setPosition(servo.getPosition() + inc);
    }
}
