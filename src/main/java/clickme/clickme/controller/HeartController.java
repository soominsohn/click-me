package clickme.clickme.controller;

import clickme.clickme.service.EmojiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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

    @GetMapping(value = "/stringToImage", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] stringToImage() {
        return emojiService.serveImage("test");
    }
}
