package ru.job4j.ood.srp;

import java.io.*;

public class Report implements Serializable {
    String message;

    public Report(String message) {
        this.message = message;
    }

    /*Данный класс отвечает одновременно за сохранение отчета в файл и за его вывод на консоль*/

    public void saveToFile(Report report, File file) {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)) {
            oos.writeObject(report);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printReport(Report report) {
        System.out.println(report.message);
    }
}
