package ru.job4j.ood.srp;

public class Converter {

    double exchangeRate;

    /*Несмотря на то что оба метода являются конверторами у них совсем разные назначения,
    первый переводит одну валюту в другую, а второй рассчитывает стоимость в долларах,
    таким образом, принцип единственной ответственности не соблюдается*/

    public double usdToEuro(int dollar) {
        return dollar * exchangeRate;
    }

    public double priceToUsd(int price) {
        return exchangeRate / price;
    }
}
