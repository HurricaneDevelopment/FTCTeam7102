package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;

public class AdaFruitColorSensor {

    private DeviceInterfaceModule dim;
    private ColorSensor sensorRGB;

    private String name;
    private int channel;

    public AdaFruitColorSensor(String name) {
        this.name = name;
        channel = 5;
    }

    public AdaFruitColorSensor(String name,int channel) {
        this.name = name;
        this.channel = channel;
    }

    public void loadHardware(HardwareMap hwmap) throws UnfoundHardwareException {
        dim = hwmap.deviceInterfaceModule.get("dim");
        if (dim == null)
            throw new UnfoundHardwareException("DeviceInterfaceModule","dim");

        dim.setDigitalChannelMode(channel, DigitalChannelController.Mode.OUTPUT);

        sensorRGB = hwmap.colorSensor.get(name);
        if (sensorRGB == null)
            throw new UnfoundHardwareException("ColorSensor",name);

        ledOff();
    }

    public void ledOn() {
        if (dim != null)
            dim.setDigitalChannelState(channel,true);
    }

    public void ledOff() {
        if (dim != null)
            dim.setDigitalChannelState(channel,false);
    }

    public int red() {
        return sensorRGB.red();
    }

    public int green() {
        return sensorRGB.green();
    }

    public int blue() {
        return sensorRGB.blue();
    }

    public boolean isBeaconBlue() {
        if (red() - blue() < -100)
            return true;
        return false;
    }

    public boolean isBeaconRed() {
        if (red() - blue() > 100)
            return true;
        return false;
    }
}
