package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private int age;

    @XmlAttribute
    private boolean sex;

    private Phone phone;

    @XmlElementWrapper(name = "subjects")
    @XmlElement(name = "subject")
    private String[] subjects;

    public Student() {
    }

    public Student(String name, int age, boolean sex, Phone phone, String[] subjects) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Student{"
                + "name='" + name
                + '\'' + ", age=" + age
                + ", sex=" + sex
                + ", phone=" + phone
                + ", subjects=" + Arrays.toString(subjects)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        Student candidate = new Student();
        System.out.println(candidate);
        candidate.phone = new Phone();
        String[] subjects = new String[]{"history", "literature", "english"};
        final Student student = new Student("Ivanov Ivan", 20, true, new Phone("+7 (111) 111-11-11"), subjects);

        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(student, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Student result = (Student) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
