package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties prop;
    private static final String CONFIG_FILE_PATH = "./src/main/resources/application.properties";

    /**
     * Initializes and loads the properties file into memory.
     * @return Properties object containing all framework configurations.
     */
    public Properties initProperties() {
        prop = new Properties();
        try (FileInputStream ip = new FileInputStream(CONFIG_FILE_PATH)) {
            prop.load(ip);
        } catch (FileNotFoundException e) {
            System.err.println("Configuration file not found at path: " + CONFIG_FILE_PATH);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Failed to read configuration file.");
            e.printStackTrace();
        }
        return prop;
    }

    /**
     * Helper method to automatically fetch the matching environment URL
     * based on the active 'env' flag configured in the properties file.
     * @return String target URL
     */
    public String getEnvironmentUrl() {
        if (prop == null) {
            initProperties();
        }
        String currentEnv = prop.getProperty("env", "qa").trim().toLowerCase();
        String targetUrl = prop.getProperty("url." + currentEnv);

        if (targetUrl == null || targetUrl.isEmpty()) {
            throw new RuntimeException("Target URL missing for the environment specified: " + currentEnv);
        }
        return targetUrl;
    }
}
