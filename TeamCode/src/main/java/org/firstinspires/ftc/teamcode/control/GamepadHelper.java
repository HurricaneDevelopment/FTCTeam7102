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
        
        switch (comp.lowerCase()) {
            case "a":
                return gp.a && !a;
                break;
            case "b":
                return gp.b && !b;
                break;
            case "x":
                return gp.x && !x;
                break;
            case "y":
                return gp.y && !y;
                break;
            default:
                return false;
                break;
        }
    }
}