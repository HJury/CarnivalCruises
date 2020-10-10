package util;

import interactions.Exceptions;
import org.apache.xmlbeans.SystemProperties;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonReader {
    static PropertiesReader propertiesReader;

    static public String getValue(String userInterface,String value) {
        String result = "";
        try {
            propertiesReader = new PropertiesReader();
            JSONParser jsonParser = new JSONParser();
            FileReader reader = new FileReader(SystemProperties.getProperty("user.dir") + propertiesReader.getPropoValues("JsonPath"));
            JSONObject obj = (JSONObject) jsonParser.parse(reader);
            JSONObject obj2 = (JSONObject) obj.get(userInterface);
            obj2.put("Email", System.getProperty("userEmail"));
            obj2.put("Password", System.getProperty("userPassword"));
            result = (String) obj2.get(value);
        }catch(IOException e){
            Exceptions.exceptionMessage(e);
        }catch(ParseException e){
            Exceptions.exceptionMessage(e);
        }finally {
            return result;
        }

    }

    static public JSONObject getInfoPackage(String packageName){
        JSONObject obj2 = new JSONObject();
        try {
            propertiesReader = new PropertiesReader();
            JSONParser jsonParser = new JSONParser();
            FileReader reader = new FileReader(SystemProperties.getProperty("user.dir") +  propertiesReader.getPropoValues("JsonPath"));
            JSONObject obj = (JSONObject) jsonParser.parse(reader);
             obj2 = (JSONObject) obj.get(packageName);
        }catch(IOException e){
            Exceptions.exceptionMessage(e);
        }catch(ParseException e){
            Exceptions.exceptionMessage(e);
        }finally {
            return obj2;
        }
    }

    static public void setEmailAndPassword()  {
        JSONObject obj2 = new JSONObject();
        try {
            propertiesReader = new PropertiesReader();
            JSONParser jsonParser = new JSONParser();
            FileReader reader = new FileReader(SystemProperties.getProperty("user.dir") +  propertiesReader.getPropoValues("JsonPath"));
            JSONObject obj = (JSONObject) jsonParser.parse(reader);
            obj2 = (JSONObject) obj.get("Sesion Credentials");
        }catch(IOException e){
            Exceptions.exceptionMessage(e);
        }catch(ParseException e){
            Exceptions.exceptionMessage(e);
        }finally {
                System.out.println("Successfully updated json object to file...!!");

        }
    }

    static public String getValueFromPackage(JSONObject jsonObject, String key){
        return (String) jsonObject.get(key);
    }

}
