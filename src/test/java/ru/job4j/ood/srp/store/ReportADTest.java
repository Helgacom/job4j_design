package ru.job4j.ood.srp.store;

import org.junit.jupiter.api.Test;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.Report;
import ru.job4j.ood.srp.report.ReportAD;
import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportADTest {

    @Test
    public void whenOldGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee employee = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(employee);
        Report accounting = new ReportAD(store, parser, Currency.RUB, Currency.EUR);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary ")
                .append(System.lineSeparator())
                .append(employee.getName()).append(" ")
                .append(parser.parse(employee.getHired())).append(" ")
                .append(parser.parse(employee.getFired())).append(" ")
                .append(1.02)
                .append(System.lineSeparator());
        assertThat(accounting.generate(em -> true)).isEqualTo(expect.toString());
    }
}