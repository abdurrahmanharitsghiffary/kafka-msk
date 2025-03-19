package com.example.kafka;

import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClientConfig;

public class Config {

    private final Properties properties;

    public Config() {
        properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, System.getenv("BOOTSTRAP_SERVERS"));
        properties.put("security.protocol", "SASL_SSL");
        properties.put("sasl.mechanism", "AWS_MSK_IAM");
        properties.put("sasl.jaas.config", "software.amazon.msk.auth.iam.IAMLoginModule required;");
        properties.put("sasl.client.callback.handler.class", "software.amazon.msk.auth.iam.IAMClientCallbackHandler");
    }

    public Properties build() {
        return properties;
    }
}
