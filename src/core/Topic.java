package core;

public class Topic {

    private String nameTopic;
    private String description;

    public Topic(String nameTopic, String description) {
        this.nameTopic = nameTopic;
        this.description = description;
    }

    public String getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
