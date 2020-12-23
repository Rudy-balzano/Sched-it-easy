package persist;

import java.util.Date;
import core.Topic;

public interface MeetingDAO {

    public boolean insert(String topicName,Date date,int duration);
}
