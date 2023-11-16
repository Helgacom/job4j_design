package ru.job4j.ood.srp.currency;

public class InMemoryCurrencyConverter implements CurrencyConverter {
    private static final int CURRENCIES_COUNT = Currency.values().length;
    private final double[][] conversationTable = new double[CURRENCIES_COUNT][CURRENCIES_COUNT];

    public InMemoryCurrencyConverter() {
        this.conversationTable[Currency.RUB.ordinal()][Currency.USD.ordinal()] = 0.0109;
        this.conversationTable[Currency.RUB.ordinal()][Currency.EUR.ordinal()] = 0.0102;
        this.conversationTable[Currency.USD.ordinal()][Currency.EUR.ordinal()] = 1.0769;
        this.conversationTable[Currency.USD.ordinal()][Currency.RUB.ordinal()] = 91D;
        this.conversationTable[Currency.EUR.ordinal()][Currency.USD.ordinal()] = 0.9285;
        this.conversationTable[Currency.EUR.ordinal()][Currency.RUB.ordinal()] = 98D;
    }

    @Override
    public double convert(Currency source, double sourceValue, Currency target) {
        return sourceValue * conversationTable[source.ordinal()][target.ordinal()];
    }
}
