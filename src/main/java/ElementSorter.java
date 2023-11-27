import lejos.hardware.Brick;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.remote.ev3.RemoteEV3;
import lejos.robotics.ColorAdapter;
import lejos.robotics.RegulatedMotor;
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

        Brick brick = new RemoteEV3("10.49.128.167");
        brick.setDefault();
        final EV3UltrasonicSensor ultrasonicSensorS3 = new EV3UltrasonicSensor(SensorPort.S3);

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

        //EV3MediumRegulatedMotor motorRelease = new EV3MediumRegulatedMotor();
        //motorRelease.rotate();
    }

}
