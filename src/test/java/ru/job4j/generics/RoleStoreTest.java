package ru.job4j.generics;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleNameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Client"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Client");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Client"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Client"));
        store.add(new Role("1", "Vendor"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Client");
    }

    @Test
    void whenReplaceThenRoleNameIsClient() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Client"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Client");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Client"));
        store.replace("2", new Role("2", "Vendor"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Client");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Client"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsClient() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Client"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Client");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Client"));
        boolean rsl = store.replace("1", new Role("1", "Customer"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Client"));
        boolean rsl = store.replace("10", new Role("10", "Customer"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Client"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Client"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}