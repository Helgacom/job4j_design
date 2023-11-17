package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

public class ReportJsonTest {

    @Test
    public void whenJsonGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee employee1 = new Employee("Ivanov_I.I.", now, now, 10000.0);
        Employee employee2 = new Employee("Romanov_L.V.", now, now, 16000.0);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(employee1);
        store.add(employee2);
        Report report = new ReportJson(store, parser);
        String expect = "[\n"
                + "  {\n"
                + "    \"name\": " + "\"John Doe\",\n"
                + "    \"hired\": " + "\"" + parser.parse(employee1.getHired()) + "\",\n"
                + "    \"fired\": " + "\"" + parser.parse(employee1.getFired()) + "\",\n"
                + "    \"salary\": " + employee1.getSalary() + "\n"
                + "  },\n"
                + "  {\n"
                + "    \"name\": " + "\"Jane Smith\",\n"
                + "    \"hired\": " + "\"" + parser.parse(employee2.getHired()) + "\",\n"
                + "    \"fired\": " + "\"" + parser.parse(employee2.getFired()) + "\",\n"
                + "    \"salary\": " + employee2.getSalary() + "\n"
                + "  }\n" + "]";
        assertThat(report.generate(em -> true)).isEqualTo(expect);
    }
}