package ru.job4j.ood.isp;

public interface Converter {

    float rubToDollar(float salary);
    float rubToEuro(float salary);
    float euroToDollar(float salary);
    float dollarToPound(float salary);
}

class SalaryCounter implements Converter {

    private Worker worker;

    public SalaryCounter(Worker worker) {
        this.worker = worker;
    }

    /*в данном случае расчетчик заработной платы определяет вознаграждение работников либо в рублях либо в долларах,
    при этом нициональной валютой является рубль и конвертации между иностранными валютами ему априори не нужны,
    таким образом класс расчетчика вынужден переопределять ненужные ему методы, что является нарушением приниципа
    разделения интерфейсов*/

    public float countSalary(int hours) {
        float salary = worker.getHourlyRateRub() * hours;
        return worker.getValueType().equals("RUB") ? salary : rubToDollar(salary);
    }

    @Override
    public float rubToDollar(float salary) {
        return salary * 0.01f;
    }

    @Override
    public float rubToEuro(float salary) {
        return 0;
    }

    @Override
    public float euroToDollar(float salary) {
        return 0;
    }

    @Override
    public float dollarToPound(float salary) {
        return 0;
    }
}

class Worker {
    private String name;
    private String valueType;
    private float hourlyRateRub;

    public Worker(String name, String valueType, float hourlyRateRub) {
        this.name = name;
        this.valueType = valueType;
        this.hourlyRateRub = hourlyRateRub;
    }

    public String getValueType() {
        return valueType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHourlyRateRub() {
        return hourlyRateRub;
    }
}