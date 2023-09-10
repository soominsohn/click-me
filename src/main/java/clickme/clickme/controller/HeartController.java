package clickme.clickme.controller;

import clickme.clickme.service.EmojiService;
import clickme.clickme.service.HeartService;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;
    private final EmojiService emojiService;

//    @GetMapping("/show")
//    public String heart(@RequestParam String URI, Model model) {
//        String count = heartService.addCount(URI);
//        model.addAttribute("counter", count);
//        return "heart";
//    }

    @GetMapping("/show")
    public String show(@RequestParam String URI) {
        return "heart.html";
    }

    @GetMapping(value = "/stringToImage", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] stringToImage() {
        return emojiService.serveImage("test");
    }

    @GetMapping(value = "/heart", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] heart(final @RequestParam String URI) throws IOException {
        String imagePath = "./static/images/1077035.png"; // 이미지 파일의 실제 경로에 맞게 수정해야 합니다.

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
        BufferedImage overlayImage = new BufferedImage(
                baseImage.getWidth(), baseImage.getHeight(), BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g2d = overlayImage.createGraphics();

        // Draw the base image
        g2d.drawImage(baseImage, 0, 0, null);

        // Draw a heart shape as overlay
        g2d.setColor(Color.RED);
        int[] xPoints = {overlayImage.getWidth() / 2, 100};  // Specify heart shape points
        int[] yPoints = {overlayImage.getHeight() / 2, 100};
        Polygon heartPolygon = new Polygon(xPoints, yPoints, xPoints.length);
        g2d.fill(heartPolygon);

        g2d.dispose();

//        "🥰9999+";

        // Convert the overlay image to bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        throw new IllegalArgumentException();

    }

//    @GetMapping(value = "/heart2", produces = MediaType.IMAGE_PNG_VALUE)
//    @ResponseBody
//    public byte[] heart2(@RequestParam String URI) {
//        String count = heartService.addCount(URI);
//        int width = 300;
//        int height = 300;
//
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2d = image.createGraphics();
//
//        g2d.setColor(Color.RED);
//
//        // Draw a heart shape
//        int[] xPoints = {150, 180, 225, 225, 150, 75, 75, 120, 150};
//        int[] yPoints = {100, 75, 75, 100, 150, 100, 75, 75, 100};
//        Polygon heartPolygon = new Polygon(xPoints, yPoints, xPoints.length);
//        g2d.fill(heartPolygon);
//
//        g2d.drawString(count, 150, 150);
//
//        g2d.dispose();
//
//        // Convert the image to bytes
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        try {
//            ImageIO.write(image, "png", baos);
//            baos.flush();
//            byte[] imageBytes = baos.toByteArray();
//            baos.close();
//
//            return imageBytes;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        throw new IllegalArgumentException();
//
//    }

//    @GetMapping(value = "/heart", produces = MediaType.IMAGE_PNG_VALUE)
//    @ResponseBody
//    public byte[] heart(@RequestParam String URI) {
//        String count = heartService.addCount(URI);
//        int width = 200;
//        int height = 200;
//
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2d = image.createGraphics();
//
//        // Draw something on the image
//        g2d.setColor(Color.BLUE);
//        g2d.fillRect(0, 0, width, height);
//        g2d.setColor(Color.WHITE);
//        g2d.drawString("Generated Image", 50, 100);
//        g2d.drawString(count, 50, 150);
//
//        g2d.dispose();
//
//        // Convert the image to bytes
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        try {
//            ImageIO.write(image, "png", baos);
//            baos.flush();
//            byte[] imageBytes = baos.toByteArray();
//            baos.close();
//            return imageBytes;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        throw new IllegalArgumentException();
//    }
}
