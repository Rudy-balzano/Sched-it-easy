package persist;

import java.time.LocalDate;
import java.util.Date;
import core.Topic;

public interface MeetingDAO {

    public boolean insert(String topicName, LocalDate date, String time, String duration, String meetingCreator);
}
