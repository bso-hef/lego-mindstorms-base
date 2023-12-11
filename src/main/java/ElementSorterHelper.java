import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class ElementSorterHelper {

    private static final int SENSOR_RETRY_TIMEOUT = 200;

    /**
     * Helper method to wait until the given button is pressed.<br/>
     * Code example:<br/>
     * <code>ElementSorterHelper.waitUntilButtonPressed(buttonS1);</code>
     *
     * @param button, an object of EV3TouchSensor that is connected to any sensor port
     */
    public static void waitUntilButtonPressed(EV3TouchSensor button) {
        final SampleProvider sampleProvider = button.getTouchMode();
        int touchValue = 0;

        //Control loop
        while (touchValue == 0) {

            float[] sampleValue = new float[sampleProvider.sampleSize()];
            sampleProvider.fetchSample(sampleValue, 0);
            touchValue = (int) sampleValue[0];

            Delay.msDelay(SENSOR_RETRY_TIMEOUT);
        }
    }

    /**
     * Returns the distance in meters.<br/>
     * <br/>
     * Code Example:<br/>
     * <code>//Creates an Objekt of the class EV3UltrasonicSensor that is connected to<br/>
     * //an EV3UltrasonicSensor at Port 3<br/>
     * EV3UltrasonicSensor ultrasonicSensor = new EV3UltrasonicSensor(SensorPort.S3);<br/>
     * //ließt den Abstand des Sensors aus.<br/>
     * float distance = ElementSorterHelper.getDistance(ultrasonicSensor);</code>
     *
     * @return the distance measured by EV3UltrasonicSensor
     * @param ultrasonicSensor, an EV3UltrasonicSensor object that is connected to any Sensor Port
     */
    public static float getDistance(EV3UltrasonicSensor ultrasonicSensor) {
        SampleProvider ultrasonicSampleProvider = ultrasonicSensor.getDistanceMode();
        float[] sampleValue = new float[ultrasonicSampleProvider.sampleSize()];
        ultrasonicSampleProvider.fetchSample(sampleValue, 0);
        return sampleValue[0];
    }

}
