package Project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MessageGenerator {
    public static List<Message> generate(int count) {
        List<Message> messages = new ArrayList<>();
        Random random = new Random();
        Priority[] priorities = Priority.values();
        String[] texts = {"Сбой сети", "Все ок", "Ошибка БД", "Перезагрузка"};

        for (int i = 0; i < count; i++) {
            Priority p = priorities[random.nextInt(priorities.length)];
            int code = random.nextInt(3);
            String text = texts[random.nextInt(texts.length)];
            messages.add(new Message(p, code, text));
        }
        return messages;
    }
}
