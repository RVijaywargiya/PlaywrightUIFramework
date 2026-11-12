package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:application.properties"
})
public interface ApplicationConfig extends Config {

    @Key("base.url")
    String baseUrl();

    @Key("browser")
    @DefaultValue("chromium")
    String browser();

    @Key("headless")
    @Config.DefaultValue("true")
    boolean headless();
}