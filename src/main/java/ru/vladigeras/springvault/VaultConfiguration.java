package ru.vladigeras.springvault;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;
import org.springframework.vault.support.SslConfiguration;

@Configuration
@RequiredArgsConstructor
public class VaultConfiguration extends AbstractVaultConfiguration {

    private final VaultProperties vaultProperties;

    @Override
    public VaultEndpoint vaultEndpoint() {
        VaultEndpoint endpoint = VaultEndpoint.create(vaultProperties.getHost(), vaultProperties.getPort());
        endpoint.setScheme("http");
        return endpoint;
    }

    @Override
    public ClientAuthentication clientAuthentication() {
        return new TokenAuthentication(vaultProperties.getToken());
    }

    @Override
    public SslConfiguration sslConfiguration() {
        return SslConfiguration.unconfigured();
    }
}
