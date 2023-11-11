import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class ElementSorterHelper {

    private static int SENSOR_RETRY_TIMEOUT = 200;

    /**
     * Helper method to wait until the given button is pressed.<br/>
     * Code example:<br/>
     * <code>ElementSorterHelper.waitUntilButtonPressed(buttonS1);</code>
     *
     * @param button
     */
    public static void waitUntilButtonPressed(EV3TouchSensor button) {
        SampleProvider sample = button.getMode("Touch");
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
     * CodeExample:<br/>
     * <code>float distance = ElementSorterHelper.getDistance(ultrasonicSensorS3);</code>
     *
     * @return
     */
    public static float getDistance(EV3UltrasonicSensor ultrasonicSensorS3) {
        SampleProvider ultrasonicSampleProvider = ultrasonicSensorS3.getDistanceMode();
        float[] sampleValue = new float[ultrasonicSampleProvider.sampleSize()];
        ultrasonicSampleProvider.fetchSample(sampleValue, 0);
        float distance = (float) sampleValue[0];
        return distance;
    }

    /**
     * Class to encapsulate the color numbers of a sensor.
     * Code example:<br/>
     * <code><br/>
     *     ElementSorterHelper.ElementColor elementColor = ElementSorterHelper.ElementColor.byValue(color)<br/>
     *
     *     if(elementColor == ElementSorterHelper.ElementColor.RED) {<br/>
     *         ...<br/>
     *     }<br/>
     * </code>
     */
    public enum ElementColor {

        RED(0),
        GREEN(1),
        BLUE(2),
        YELLOW(3),
        MAGENTA(4),
        ORANGE(5),
        WHITE(6),
        BLACK(7),
        PINK(8),
        GRAY(9),
        LIGHT_GRAY(10),
        DARK_GRAY(11),
        CYAN(12),
        BROWN(13),
        NONE(-1);

        public final int colorID;

        private ElementColor(int colorID) {
            this.colorID = colorID;
        }

        public static ElementColor byValue(int color) {
            for (ElementColor e : values()) {
                if (e.colorID == color) {
                    return e;
                }
            }
            return null;
        }
    }
}
