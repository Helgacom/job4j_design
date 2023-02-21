package ru.job4j.generics;

import java.util.Objects;

public class Animal {

    private String name;

    private float maxSpeed;

    private int lifespan;


    public Animal(String name, float maxSpeed, int lifespan) {
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.lifespan = lifespan;
    }

    public String getName() {
        return name;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public int getLifespan() {
        return lifespan;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Animal animal = (Animal) o;
        return Float.compare(animal.maxSpeed, maxSpeed) == 0
                && lifespan == animal.lifespan
                && Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, maxSpeed, lifespan);
    }

    @Override
    public String toString() {
        return "Animal{"
                + "name='" + name + '\''
                + ", speed=" + maxSpeed
                + ", lifespan=" + lifespan
                + '}';
    }
}
