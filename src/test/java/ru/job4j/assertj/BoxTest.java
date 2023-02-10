package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 4);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(0, -2);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void getNumberOfVerticesIfTetrahedron() {
        Box box = new Box(4, 3);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(4);
    }

    @Test
    void getNumberOfVerticesIfUnknown() {
        Box box = new Box(4, -1);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(-1);
    }

    @Test
    void whenObjectIsExist() {
        Box box = new Box(4, 2);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void whenObjectIsNotExist() {
        Box box = new Box(0, -5);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void getAreaWhenSphere() {
        Box box = new Box(0, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(1256.63d, withPrecision(0.01d));
    }
}