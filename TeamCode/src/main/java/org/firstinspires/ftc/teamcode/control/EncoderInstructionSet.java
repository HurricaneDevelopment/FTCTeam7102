package org.firstinspires.ftc.teamcode.control;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.HashSet;
public class EncoderInstructionSet extends HashSet<EncoderDirective> implements Instruction {
    
    private ElapsedTime timer;
    private boolean running;

    public EncoderInstructionSet() {
        timer = new ElapsedTime();
        running = false;
    }

    public boolean busy() {
        if (!running)
            return false;
        for (EncoderDirective inst : this)
            if (inst.busy(timer)) return true;
        return false;
    }

    public void run() {
        running = true;

        for (EncoderDirective inst : this)
            inst.start();

        timer.reset();

        while (true) {
            boolean done = true;

            for (EncoderDirective inst : this) {
                if (!inst.busy(timer)) {
                    inst.stop();
                } else {
                    done = false;
                }
            }

            if (done) break;
        }

        running = false;
    }
}