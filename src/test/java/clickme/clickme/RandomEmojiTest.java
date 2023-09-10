package clickme.clickme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RandomEmojiTest {

    @Test
    void randomEmoji() {
        List<String> emojis = List.of("🥰", "🥺", "😍", "😎", "😵", "😘", "😴", "🤩", "😋", "😃", "🤣");
        List<Integer> randomIndexes = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));


        for (int i = 0; i < 100 ; i++) {
            Collections.shuffle(randomIndexes);
            System.out.println(emojis.get(randomIndexes.get(0)));
        }

    }
}
