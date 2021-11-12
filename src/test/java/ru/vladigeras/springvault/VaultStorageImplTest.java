package ru.vladigeras.springvault;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class VaultStorageImplTest {

    @Autowired
    private VaultStorageImpl vaultStorage;

    @Test
    void shouldSaveAndGetSavedData() {
        String key = "test_key";
        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();
        TestData expected = new TestData(username, password);
        vaultStorage.save(key, expected);
        TestData actual = vaultStorage.get(key, TestData.class);
        assertThat(actual).isNotNull().isEqualTo(expected).usingRecursiveComparison();
    }

    @Test
    void shouldNotFoundData() {
        String key = "unknown";
        TestData actual = vaultStorage.get(key, TestData.class);
        assertNull(actual);
    }

    @Test
    void shouldSaveAndDeleteData() {
        String key = "delete_key";
        vaultStorage.save(key, new TestData("username", "password"));
        TestData actual = vaultStorage.get(key, TestData.class);
        assertNotNull(actual);
        vaultStorage.delete(key);
        actual = vaultStorage.get(key, TestData.class);
        assertNull(actual);
    }

    record TestData(String username, String password) {
    }
}
