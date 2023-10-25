import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class HelloEV3 {

    public static void main(String[] args) {

        LCD.clearDisplay();
        LCD.drawString("Hello EV3 Today", 0,1);
        LCD.drawString("Hello EV3 Today", 0,2);
        LCD.drawString("Hello EV3 Today", 0,3);

        Delay.msDelay(10000);
    }
}
