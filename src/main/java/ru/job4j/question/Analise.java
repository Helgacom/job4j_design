package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analise {

    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> map = Stream.of(current)
                .flatMap(Set::stream)
                .collect(Collectors.toMap(User::getId, User::getName));
        for (User user : previous) {
            if (map.containsKey(user.getId()) && !user.getName().equals(map.get(user.getId()))) {
                changed++;
            }
            if (!map.containsKey(user.getId())) {
                    deleted++;
            }
        }
        int added = current.size() + deleted - previous.size();
        return new Info(added, changed, deleted);
    }
}
