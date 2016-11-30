package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;
import org.firstinspires.ftc.teamcode.hardware.DcMotorW;

import java.util.HashMap;
import java.util.List;

public class Motors {

    public HashMap<String,DcMotorW> Motors;

    public  Motors() {
        Motors = new HashMap<String,DcMotorW>();
    }

    public void loadHardware(HardwareMap hwmap) {
        List<HardwareDevice> devices = hwmap.getAll(HardwareDevice.class);
        for(HardwareDevice device : devices)
            if (device instanceof DcMotor)
                Motors.put(hwmap.getNamesOf(device).iterator().next(),new DcMotorW((DcMotor) device));
    }

    public DcMotorW get(String str) throws UnfoundHardwareException {
        if (!Motors.containsKey(str))
            throw new UnfoundHardwareException("DcMotor",str);
        return Motors.get(str);
    }

    public String toString() {
        return Motors.toString();
    }
}