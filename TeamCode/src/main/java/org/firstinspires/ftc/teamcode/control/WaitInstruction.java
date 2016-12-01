package org.firstinspires.ftc.teamcode.control;

/**
 * Created by Spencer on 12/1/2016.
 */
public class WaitInstruction implements Instruction {

    private int delay;

    public WaitInstruction(int delay) {
        this.delay = delay;
    }

    public void run() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {

        }
    }
}
