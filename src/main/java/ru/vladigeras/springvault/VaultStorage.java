package ru.vladigeras.springvault;

public interface VaultStorage {

    <T> void save(String key, T value);

    <T> T get(String key, Class<T> type);

    void delete(String key);
}
