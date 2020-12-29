package persist;

import core.Topic;

import java.util.ArrayList;

public interface TopicDAO {
    boolean insert (String name, String description);

    boolean update(String name, String description);

    boolean delete(String name);

    Topic findBy(String name);

    ArrayList<Topic> findAll();
}
