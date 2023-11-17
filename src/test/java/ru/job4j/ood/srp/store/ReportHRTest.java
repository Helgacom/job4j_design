package ru.job4j.ood.srp.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.Report;
import ru.job4j.ood.srp.report.ReportHR;
import javax.xml.bind.JAXBException;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportHRTest {

    @Test
    public void whenOldGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee employee1 = new Employee("Ivanov_I.I.", now, now, 100);
        Employee employee2 = new Employee("Sinev_A.R.", now, now, 150);
        Employee employee3 = new Employee("Romanova_O.V.", now, now, 250);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(employee1);
        store.add(employee3);
        store.add(employee2);
        Report hr = new ReportHR(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary ")
                .append(System.lineSeparator())
                .append(employee1.getName()).append(" ")
                .append(employee1.getSalary()).append(" ")
                .append(System.lineSeparator())
                .append(employee2.getName()).append(" ")
                .append(employee2.getSalary()).append(" ")
                .append(System.lineSeparator())
                .append(employee3.getName()).append(" ")
                .append(employee3.getSalary()).append(" ")
                .append(System.lineSeparator());
        assertThat(hr.generate(em -> true)).isEqualTo(expect.toString());
    }
}