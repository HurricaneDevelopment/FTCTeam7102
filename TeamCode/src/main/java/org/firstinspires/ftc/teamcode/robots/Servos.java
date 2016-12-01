package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoImpl;

import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;
import org.firstinspires.ftc.teamcode.hardware.ServoW;

import java.util.HashMap;
import java.util.List;

public class Servos {
    public HashMap<String,ServoW> servos;

    public Servos() {
        servos = new HashMap<String,ServoW>();
    }

    public void loadHardware(HardwareMap hwmap) {
        List<ServoImpl> devices = hwmap.getAll(ServoImpl.class);
        for (ServoImpl device : devices)
            servos.put(hwmap.getNamesOf(device).iterator().next(),new ServoW(device));
    }

    public ServoW get(String str) throws UnfoundHardwareException {
        if (!servos.containsKey(str))
            throw new UnfoundHardwareException("Servo",str);
        return servos.get(str);
    }

    public String toString() {
        return servos.toString();
    }
}
