package clickme.clickme.service;

import clickme.clickme.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;

    public String addAndGetCount(String URI) {
        heartRepository.addCount(URI);
        System.out.println(heartRepository.getCount(URI));
        return heartRepository.getCount(URI);
    }
}
