package clickme.clickme.controller;

import clickme.clickme.service.EmojiService;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class HeartController {

    private final EmojiService emojiService;

    @GetMapping("/show")
    public String show(@RequestParam String URI) {
        return "heart.html";
    }

    @GetMapping("/fonts")
    public String fonts() {
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] fonts = e.getAllFonts();
        for (Font font : fonts) {
            System.out.println(font.getFontName());
        }
        return Arrays.toString(fonts);
    }

    @GetMapping(value = "/stringToImage", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] stringToImage() {
        return emojiService.serveImage("test");
    }

    @GetMapping(value = "/justImage", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> justImage() throws IOException {
        CacheControl cacheControl = CacheControl.noCache();
        return ResponseEntity.ok()
                .cacheControl(cacheControl)
                .body(emojiService.heart());
    }

    @GetMapping(value = "/new/justImage", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> justImage2() throws IOException {
        CacheControl cacheControl = CacheControl.noCache();
        return ResponseEntity.ok()
                .cacheControl(cacheControl)
                .body(emojiService.heart());
    }

    @GetMapping(value = "/test1/justImage", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> test1() throws IOException {
        CacheControl cacheControl = CacheControl.maxAge(1, TimeUnit.SECONDS);
        return ResponseEntity.ok()
                .cacheControl(cacheControl)
                .body(emojiService.heart());
    }

    @GetMapping(value = "/test2/justImage", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> test2() throws IOException {
        CacheControl cacheControl = CacheControl.maxAge(2, TimeUnit.SECONDS);
        return ResponseEntity.ok()
                .cacheControl(cacheControl)
                .body(emojiService.heart());
    }

    @GetMapping(value = "/emoji", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] emoji(@RequestParam String idx) {
         List<String> EMOJIS = List.of("🥰", "🥺", "😍", "😎", "😵", "😘", "😴", "🤩", "😋", "😃", "🤣",
                "🥳", "🤗",
                "🤓", "🤑");
         int idex = Integer.parseInt(idx);
         String emogi = EMOJIS.get(idex);
        Font font = new Font(null, Font.BOLD, 36);
        BufferedImage image = new BufferedImage(40, 45,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.setColor(Color.BLACK);
        g2d.drawString(emogi, 0, 35);
        g2d.dispose();
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
}
