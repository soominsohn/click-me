package clickme.clickme.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class HeartMemoryRepository implements HeartRepository {

    private static final Map<String, Long> MAP =  new ConcurrentHashMap<>();
    @Override
    public void addCount(String URI) {
        MAP.put(URI, MAP.getOrDefault(URI, 0L) + 1);
    }

    @Override
    public String getCount(String URI) {
        return String.valueOf(MAP.getOrDefault(URI, 0L));
    }
}
