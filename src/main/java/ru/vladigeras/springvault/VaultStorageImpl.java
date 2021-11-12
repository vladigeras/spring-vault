package ru.vladigeras.springvault;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.VaultResponseSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class VaultStorageImpl implements VaultStorage {

    private final VaultOperations vaultOperations;
    private final ObjectMapper objectMapper;

    @Override
    public <T> void save(String key, T value) {
        VaultDataWrapper<T> data = new VaultDataWrapper<>(value);
        vaultOperations.write(getPath(key), data);
        log.info("Data by key = {} was persisted {}", key, data.data());
    }

    @Override
    @SuppressWarnings("rawtypes")
    public <T> T get(String key, Class<T> type) {
        VaultResponseSupport<VaultDataWrapper> rs = vaultOperations.read(getPath(key), VaultDataWrapper.class);
        if (rs != null && rs.getData() != null) {
            VaultDataWrapper<?> wrapper = rs.getData();
            T data = objectMapper.convertValue(wrapper.data(), type);
            log.info("Data by key = {} was retrieved {}", key, data);
            return data;
        }
        log.info("Missing data by key = {}", key);
        return null;
    }

    @Override
    public void delete(String key) {
        vaultOperations.delete(getPath(key));
        log.info("Data by key = {} was deleted", key);
    }

    private String getPath(String key) {
        return "secret/data/" + key;
    }
}
