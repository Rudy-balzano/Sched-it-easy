package core;

/**
 * Class that represents a Topic (Subject)
 * @version 1.0
 */
public class Topic {
    /**
     * name of the topic
     * @see Topic#getNameTopic()
     * @see Topic#setNameTopic(String)
     */
    private String nameTopic;
    /**
     * description of the topic, what it's about
     * @see Topic#setDescription(String)
     * @see Topic#getDescription()
     */
    private String description;

    /**
     * Constructor of  Topic
     * @param nameTopic
     * @param description
     *
     * @see Topic#description
     * @see Topic#nameTopic
     */
    public Topic(String nameTopic, String description) {
        this.nameTopic = nameTopic;
        this.description = description;
    }

    /**
     * Return the name of a topic
     * @return the name of a topic
     */
    public String getNameTopic() {
        return nameTopic;
    }

    /**
     * Set the name of a topic
     * @param nameTopic
     */
    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    /**
     * Return the description of a topic
     * @return the description of a topic
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of a topic
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}