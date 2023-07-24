package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "phone")
public class Phone {

    @XmlAttribute
    private String phone;

    public Phone() {

    }

    public Phone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Phone {"
                + "phone='" + phone + '\''
                + '}';
    }
}
