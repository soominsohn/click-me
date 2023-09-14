package clickme.clickme.service;

import clickme.clickme.domain.HeightPolicy;
import clickme.clickme.domain.WidthPolicy;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmojiService {

    private static final List<String> EMOJIS = List.of("🥰", "🥺", "😍", "😎", "😵", "😘", "😴", "🤩", "😋", "😃", "🤣",
            "🥳", "🤗",
            "🤓", "🤑");

//        private static final List<String> EMOJIS = List.of("\\U+1F600");

    private final HeartService heartService;

    public byte[] serveImage(final String URI) {
        final String emoji = getRandomEmoji();
        final String count = getClickCount(URI);
        final BufferedImage image = convertEmojiCountToImage(emoji, count);
        System.out.println(emoji + count);
        System.out.println(image);

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
//        return EMOJIS.get(0);
    }

    private BufferedImage convertEmojiCountToImage(String emoji, String count) {
        Font font = new Font(null, Font.BOLD, 36);
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

    public byte[] heart() throws IOException {
        int randomIndex = getRandomIndex();

        String imagePath =
                "./static/images/emoji_" + String.valueOf(randomIndex) + ".png"; // 이미지 파일의 실제 경로에 맞게 수정해야 합니다.
        // ClassPathResource를 사용하여 이미지 파일을 읽어옵니다.
        ClassPathResource resource = new ClassPathResource(imagePath);
        InputStream baseImageStream = resource.getInputStream();
        BufferedImage baseImage = null;
        try {
            baseImage = ImageIO.read(baseImageStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a new image with the same dimensions as the base image
        assert baseImage != null;
        // Draw a heart shape as overlay

//        "🥰9999+";
//        for (int i = 0; i < 100; i++) {
//            getClickCount("test");
//        }
        final String count = getClickCount("test");
//final String count="99999+";
        // Convert the overlay image to bytes
        Font font = new Font("Arial", Font.BOLD, 34);
        BufferedImage image = new BufferedImage(WidthPolicy.getWidth(count), HeightPolicy.TOTAL_HEIGHT,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, WidthPolicy.getWidth(count), HeightPolicy.TOTAL_HEIGHT);
        g2d.setColor(Color.BLACK);
        g2d.drawImage(baseImage, WidthPolicy.EMOJI_START_WIDTH, 7, null);
        g2d.drawString(count, WidthPolicy.NUMBER_START_WIDTH, HeightPolicy.STRING_HEIGHT);
        g2d.dispose();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
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

    private int getRandomIndex() {
        final List<Integer> randomIndexes = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
        Collections.shuffle(randomIndexes);

        return randomIndexes.get(0);
//        return EMOJIS.get(0);
    }
}
