package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;
    public ConfigReader(){
        try{
            FileInputStream fs= new FileInputStream("src/test/resources/configuration.properties");
            properties = new Properties();
            properties.load(fs);
        }
        catch (IOException err){
            err.printStackTrace();
            System.out.println("File oxunmadi");
        }
    }
    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
