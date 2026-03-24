package Project1;

import java.util.*;

public class MessageCollection {
    private List<Message> messages;

    public MessageCollection(List<Message> messages) {
        this.messages = new ArrayList<>(messages);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<Message> getUnique() {
        Set<Message> set = new LinkedHashSet<>(messages);
        return new ArrayList<>(set);
    }

    public void removeByPriority(Priority priority) {
        messages.removeIf(msg -> msg.getPriority() == priority);
    }

    public void retainOnlyPriority(Priority priority) {
        messages.removeIf(msg -> msg.getPriority() != priority);
    }

    public Map<Priority, Integer> countByPriority() {
        Map<Priority, Integer> counts = new HashMap<>();
        for (Message msg : messages) {
            counts.put(msg.getPriority(), counts.getOrDefault(msg.getPriority(), 0) + 1);
        }
        return counts;
    }

    public Map<Integer, Integer> countByCode() {
        Map<Integer, Integer> counts = new HashMap<>();
        for (Message msg : messages) {
            counts.put(msg.getCode(), counts.getOrDefault(msg.getCode(), 0) + 1);
        }
        return counts;
    }

    public int countUnique() {
        return new HashSet<>(messages).size();
    }
}
