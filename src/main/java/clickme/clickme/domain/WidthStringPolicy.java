package clickme.clickme.domain;

import java.util.Arrays;

public enum WidthStringPolicy {
    ONE(1, 75),
    TWO(2, 95),
    THREE(3, 115),
    FOUR(4, 135),
    FIVE(5, 155),
    SIX(6, 180);


    private final int digit;
    private final int width;


    WidthStringPolicy(int digit, int width) {
        this.digit = digit;
        this.width = width;
    }

    public static WidthStringPolicy find(final int target) {
        return Arrays.stream(values()).filter(it -> it.digit == target).findFirst().orElseThrow(
                IllegalArgumentException::new);
    }

    public int getDigit() {
        return digit;
    }

    public int getWidth() {
        return width;
    }
}
