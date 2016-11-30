package org.firstinspires.ftc.teamcode.control;
import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadHelper {

    public Gamepad gp;

    public boolean a;
    public boolean b;
    public boolean x;
    public boolean y;

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
            default:
                return false;
        }
    }
}