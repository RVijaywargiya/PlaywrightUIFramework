package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigManager {

    private static final ApplicationConfig CONFIG = ConfigFactory.create(ApplicationConfig.class);

    public static ApplicationConfig getConfig() {
        return CONFIG;
    }
}