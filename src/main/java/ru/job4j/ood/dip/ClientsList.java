package ru.job4j.ood.dip;

import java.util.HashMap;

public class ClientsList {
    /*В поле класса для хранения данных мапа.
    С точки зрения DIP, это нарушение, потому что мы зависим от реализации, а не абстракции*/

    HashMap<String, String> clientsStore;

    public boolean add(String clientName, String phone) {
        return clientsStore.put(clientName, phone) != null;
    }
}
