package org.firstinspires.ftc.teamcode.util;

public class Converter {
    public static double ticksToInches(int ticks,double ticksPerRev,double inchesPerRev) {
        return ticks / ticksPerRev * inchesPerRev;
    }

    public static double inchesToTicks(double inches,int ticksPerRev,double inchesPerRev) {
        return inches / inchesPerRev * ticksPerRev;
    }
}
