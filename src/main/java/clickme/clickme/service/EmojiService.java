package clickme.clickme.service;

import clickme.clickme.domain.HeightPolicy;
import clickme.clickme.domain.WidthPolicy;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class EmojiService {

    private static final List<String> EMOJIS = List.of("🥰", "🥺", "😍", "😎", "😵", "😘", "😴", "🤩", "😋", "😃", "🤣",
            "🥳", "🤗",
            "🤓", "🤑");

    private final HeartService heartService;

    public byte[] serveImage(final String URI) {
        final String emoji = getRandomEmoji();
        final String count = getClickCount(URI);
        final BufferedImage image = convertEmojiCountToImage(emoji, count);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
            baos.flush();
            byte[] imageBytes = baos.toByteArray();
            baos.close();
            return imageBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException();
    }

    private String getClickCount(String URI) {
        String count = heartService.addAndGetCount(URI);
        if (Integer.parseInt(count) > 99999) {
            count = "99999+";
        }
        return count;
    }

    private String getRandomEmoji() {
        final List<Integer> randomIndexes = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
        Collections.shuffle(randomIndexes);

        return EMOJIS.get(randomIndexes.get(0));
    }

    private BufferedImage convertEmojiCountToImage(String emoji, String count) {
        Font font = new Font("Arial", Font.BOLD, 36);
        BufferedImage image = new BufferedImage(WidthPolicy.getWidth(count), HeightPolicy.TOTAL_HEIGHT,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, WidthPolicy.getWidth(count), HeightPolicy.TOTAL_HEIGHT);
        g2d.setColor(Color.BLACK);
        g2d.drawString(emoji, WidthPolicy.EMOJI_START_WIDTH, HeightPolicy.STRING_HEIGHT);
        g2d.drawString(count, WidthPolicy.NUMBER_START_WIDTH, HeightPolicy.STRING_HEIGHT);
        g2d.dispose();
        return image;
    }
}
