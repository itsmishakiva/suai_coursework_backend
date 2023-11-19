package suai.coursework.auth.config;

import java.util.Random;

public class RandomColorGenerator {
    public static long generateColor(int minValue) {
        int R = 0x00;
        int G = 0x00;
        int B = 0x00;
        Random random = new Random();
        int index = random.nextInt(3);
        switch (index) {
            case (0): {
                R = minValue;
                G = random.nextInt(minValue, 0xFF);
                B = random.nextInt(minValue, 0xFF);
                break;
            }
            case (1): {
                R = random.nextInt(minValue, 0xFF);
                G = minValue;
                B = random.nextInt(minValue, 0xFF);
                break;
            }
            case (2): {
                R = random.nextInt(minValue, 0xFF);
                G = random.nextInt(minValue, 0xFF);
                B = minValue;
                break;
            }
        }
        long color = 0xFF000000L;
        color |= B;
        color |= ((long) G << 8);
        color |= ((long) R << 16);
        return color;
    }
}
