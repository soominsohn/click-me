package clickme.clickme.repository;

public interface HeartRepository {

    void addCount(String URI);

    String getCount(String URI);
}
