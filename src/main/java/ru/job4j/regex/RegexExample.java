package ru.job4j.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("Я учусь на Job4j");

        String text1 = "Я учусь на Job4j";
        Matcher matcher1 = pattern.matcher(text1);
        boolean isPresent1 = matcher1.matches();
        System.out.println(isPresent1);

        String text2 = "Я учусь на курсе Job4j";
        Matcher matcher2 = pattern.matcher(text2);
        boolean isPresent2 = matcher2.matches();
        System.out.println(isPresent2);

        Pattern pattern1 = Pattern.compile("job4j", Pattern.CASE_INSENSITIVE);
        String text3 = "JoB4j";
        Matcher matcher3 = pattern1.matcher(text3);
        boolean isPresent3 = matcher3.matches();
        System.out.println(isPresent3);

        Pattern pattern4 = Pattern.compile("Job4j");
        String text4 = "Я учусь на курсе Job4j";
        Matcher matcher4 = pattern4.matcher(text4);
        boolean isPresent = matcher4.find();
        System.out.println(isPresent);

        /*Метод find() ищет ближайшее совпадение. Его можно применять многократно.
        Каждый вызов метода find() начинается с места, где закончился его предыдущий вызов.
        Чтобы найти многократные вхождения шаблона в коде, нужно использовать find() в цикле*/
        String text5 = "Job4j и Job4j и Job4j";
        Matcher matcher5 = pattern4.matcher(text5);
        while (matcher5.find()) {
            System.out.println("Найдено совпадение");
        }

        /*Получить найденное совпадение в виде строки можно с помощью метода group().
        Этот метод выводит ту часть текста, которая совпадает с шаблоном регулярного выражения*/
        while (matcher5.find()) {
            System.out.println("Найдено совпадение: " + matcher5.group());
        }

        /*Метод start() - получает индекс, в котором находится первый символ искомой последовательности символов.
          Метод end() - получает индекс, следующий за последним символом искомой последовательности символов.*/
        while (matcher5.find()) {
            System.out.println("Найдено совпадение. iStart: " + matcher5.start()
                    + " iEnd: " + matcher5.end());
        }

        /*Найденные совпадения можно заменить другой строкой с помощью метода replaceAll(),
        который применяется к сопоставителю matcher.*/
        Pattern pattern6 = Pattern.compile("123");
        String text6 = "1231 и 1232 и 1233";
        Matcher matcher6 = pattern6.matcher(text6);
        String rsl = matcher6.replaceAll("Job4j");
        System.out.println(rsl);

        /*из строки, состоящей из цифр и символов, вычленяются только
        последовательности цифр, а символы отсеиваются с помощью регулярного выражения
        Регулярное выражение "\\D+" значит "любой нецифровой символ от одного раза".*/
        String str = "123+=-456:/789";
        String[] rsl1 = str.split("\\D+");
        System.out.println(Arrays.toString(rsl1));

        /*найти в тексте дату, записанную в виде "дд.мм.гггг"*/
        Pattern pattern7 = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
        String text7 = "24.04.1987 11.11.111111 99.99.99991 99.99.9999 99999999 0000.00.00";
        Matcher matcher7 = pattern7.matcher(text7);
        while (matcher7.find()) {
            System.out.println("Найдено совпадение: " + matcher7.group());
        }

        /*найти адреса электронной почты в тексте*/
        Pattern pattern8 = Pattern.compile("\\S{1,}@\\S{1,}\\.\\S{1,}");
        String text8 = "peter-2022@example.com example65@mail_box.ru 123_45@example-mailbox.com";
        Matcher matcher8 = pattern.matcher(text8);
        while (matcher8.find()) {
            System.out.println("Найдено совпадение: " + matcher8.group());
        }
    }
}
