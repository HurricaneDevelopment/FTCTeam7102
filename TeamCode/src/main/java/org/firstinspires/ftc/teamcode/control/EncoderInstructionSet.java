public class EncoderInstructionSet extends HashSet<EncoderDirective> implements Instruction {
    
    private ElapsedTime timer;
    private boolean complete;

    public EncoderInstructionSet() {
        timer = new ElapsedTime();
        complete = null;
    }

    public boolean busy() {
        if (complete == null || !complete)
            return false;
        for (EncoderDirective inst : this)
            if (inst.busy(timer)) return true;
        return false;
    }

    public void run() {
        complete = false;

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

        complete = false;
    }
}