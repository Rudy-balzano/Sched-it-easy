package persist;

import java.util.Date;
import core.Topic;

public interface MeetingDAO {

    public boolean insert(Topic topic,Date date,int duration);
}
