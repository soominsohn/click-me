package clickme.clickme.controller.api;

import clickme.clickme.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiHeartController {

    private final HeartService heartService;

    @GetMapping("/api/heart")
    public String addCount(@RequestParam String URI) {
        String count = heartService.addAndGetCount(URI);
        throw new IllegalArgumentException();
//        return count;
    }


}
