package org.firstinspires.ftc.teamcode.control;
import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadHelper {

    public Gamepad gp;

    public boolean a;
    public boolean b;
    public boolean x;
    public boolean y;

    public boolean rt;
    public boolean rb;
    public boolean lt;
    public boolean lb;

    public void updateGamepad(Gamepad gamepad) {
        gp = gamepad;
    }

    public void endLoop() {
        if (gp == null)
                return;

        a = gp.a;
        b = gp.b;
        x = gp.x;
        y = gp.y;

        rt = Math.abs(gp.right_trigger) > 0.5;
        rb = gp.right_bumper;
        lt = Math.abs(gp.left_trigger) > 0.5;
        lb = gp.left_bumper;
    }

    public boolean onDown(String comp) {
        if (gp == null)
            return false;
        
        switch (comp.toLowerCase()) {
            case "a":
                return gp.a && !a;
            case "b":
                return gp.b && !b;
            case "x":
                return gp.x && !x;
            case "y":
                return gp.y && !y;
            case "rt":
                return Math.abs(gp.right_trigger) > 0.5 && !rt;
            case "rb":
                return gp.right_bumper && !rb;
            case "lt":
                return Math.abs(gp.left_trigger) > 0.5 && !lt;
            case "lb":
                return gp.left_bumper && !lb;
            default:
                return false;
        }
    }
}