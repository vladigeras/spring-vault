package ru.vladigeras.springvault;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "vault")
public class VaultProperties {

    @NonNull
    private final String host;
    @NonNull
    private final Integer port;
    @NonNull
    private final String token;
}
