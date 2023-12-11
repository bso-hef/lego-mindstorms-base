import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.utility.Delay;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * ElementSorter
 */
public class ElementSorter {

     /**
     * Main program to control the Element sorter
     * @param args args are not supported
     */
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {

        EV3UltrasonicSensor ultrasonicSensorS3 = new EV3UltrasonicSensor(SensorPort.S3);
        EV3MediumRegulatedMotor motorRelease = new EV3MediumRegulatedMotor(MotorPort.A);
        EV3MediumRegulatedMotor convoyBelt = new EV3MediumRegulatedMotor(MotorPort.B);
        EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S2);

        float distance = ElementSorterHelper.getDistance(ultrasonicSensorS3);
        LCD.drawString("Distance:" + distance, 0, 4);
        Delay.msDelay(1000);

        LCD.clear();
        if (distance > 0.045) {
            LCD.drawString("Teile einlegen", 0, 4);
        } else {
            LCD.drawString("Zum Starten Knopf", 0, 4);
            LCD.drawString("drücken", 0, 5);
        }
        Delay.msDelay(2000);
        ultrasonicSensorS3.close();

        motorRelease.rotate(360);
        convoyBelt.rotate((int)2.25 * 360);

        int colorID = colorSensor.getColorID();
        EV3Color color = EV3Color.byValue(colorID);

        //add code to print out the name of the detected color and the target basket
        //GREEN: basket 1
        //WHITE: basket 2
        //BLACK: basket 2
        //BLUE: basket 3
        //RED: basket 3

    }

}
