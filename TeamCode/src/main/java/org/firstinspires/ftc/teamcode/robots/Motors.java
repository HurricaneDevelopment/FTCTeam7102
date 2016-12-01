package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.hardware.DcMotorImpl;
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
        List<DcMotorImpl> devices = hwmap.getAll(DcMotorImpl.class);
        for (DcMotorImpl device : devices)
            Motors.put(hwmap.getNamesOf(device).iterator().next(),new DcMotorW(device));
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