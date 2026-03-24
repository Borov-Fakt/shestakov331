package Project1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Message> rawMessages = MessageGenerator.generate(20);

        MessageCollection collection = new MessageCollection(rawMessages);

        System.out.println("Исходный список:");
        collection.getMessages().forEach(System.out::println);

        System.out.println("\n3. Уникальные сообщения:");
        collection.getUnique().forEach(System.out::println);

        System.out.println("\n4. Удаление сообщений LOW.");
        System.out.println("Размер ДО: " + collection.getMessages().size());
        collection.removeByPriority(Priority.LOW);
        System.out.println("Размер ПОСЛЕ: " + collection.getMessages().size());

        MessageCollection collection2 = new MessageCollection(rawMessages);
        System.out.println("\n5. Оставить только URGENT.");
        System.out.println("Размер ДО: " + collection2.getMessages().size());
        collection2.retainOnlyPriority(Priority.URGENT);
        System.out.println("Размер ПОСЛЕ: " + collection2.getMessages().size());

        System.out.println("\n6. Подсчет по приоритетам:");
        System.out.println(collection.countByPriority());

        System.out.println("\n7. Подсчет по кодам:");
        System.out.println(collection.countByCode());

        System.out.println("\n8. Количество уникальных: " + collection.countUnique());
    }
}
