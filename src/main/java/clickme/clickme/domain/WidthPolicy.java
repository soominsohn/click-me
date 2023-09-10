package clickme.clickme.domain;


public class WidthPolicy {

    public static final int END_WIDTH = 180;
    public static final int EMOJI_START_WIDTH = 5;
    public static final int NUMBER_START_WIDTH = 50;

    public static int getWidth(String count) {
        int size = count.length();
        return WidthStringPolicy.find(size).getWidth();
    }
}
