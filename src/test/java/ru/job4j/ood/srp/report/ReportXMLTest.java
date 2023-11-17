package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

public class ReportXMLTest {

    @Test
    public void whenXMLGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee employee1 = new Employee("Ivanov_I.I.", now, now, 10000.0);
        Employee employee2 = new Employee("Romanov_L.V.", now, now, 16000.0);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String date = format.format(now.getTime());
        store.add(employee1);
        store.add(employee2);
        Report report = new ReportXML(store);
        String expect =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<employee>\n"
                        + "    <employee>\n"
                        + "        <name>" + employee1.getName() + "</name>\n"
                        + "        <hired>" + date + "</hired>\n"
                        + "        <fired>" + date + "</fired>\n"
                        + "        <salary>" + employee1.getSalary() + "</salary>\n"
                        + "    </employee>\n"
                        + "    <employee>\n"
                        + "        <name>" + employee2.getName() + "</name>\n"
                        + "        <hired>" + date + "</hired>\n"
                        + "        <fired>" + date + "</fired>\n"
                        + "        <salary>" + employee2.getSalary() + "</salary>\n"
                        + "    </employee>\n"
                        + "</employee>\n";
        assertThat(report.generate(em -> true)).isEqualTo(expect);

    }
}