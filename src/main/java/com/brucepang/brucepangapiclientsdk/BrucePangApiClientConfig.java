package com.brucepang.brucepangapiclientsdk;

import com.brucepang.brucepangapiclientsdk.client.BrucePangApiClient;
import com.brucepang.brucepangapiclientsdk.model.User;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author BrucePang
 */
@Configuration
@ConfigurationProperties(prefix = "brucepang.api.client")
@Data
@ComponentScan
public class BrucePangApiClientConfig {
    private String accessKey;
    private String secretKey;

    @Bean
    public BrucePangApiClient brucePangApiClient() {
        return new BrucePangApiClient(accessKey, secretKey);
    }
}
