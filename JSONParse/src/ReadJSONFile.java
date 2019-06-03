import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class ReadJSONFile
{

    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        Map<String,JSONArray> unique = new HashMap<>();
        Map<String,JSONArray> dupes = new HashMap<>();
        JSONArray udupe = new JSONArray();

        String line = null;
        JSONObject obj;
        try
        {

            FileReader fileReader = new FileReader("C:\\Users\\mihir\\Desktop\\username_sample.json");

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                obj = (JSONObject) new JSONParser().parse(line);
                String name = (String) obj.get("name");
                if (unique.containsKey(name)) {
                    if (dupes.containsKey(name)) {
                        udupe = dupes.get(name);
                        udupe.add(obj);
                        dupes.replace(name,udupe);
                    } else {
                        udupe = unique.get(name);
                        udupe.add(obj);
                        dupes.put(name,udupe);
                    }
                } else {
                    udupe.add(obj);
                    unique.put(name, udupe);
                }
                udupe = new JSONArray();

            }

            bufferedReader.close();


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        JSONObject objout = new JSONObject();
        objout.putAll(dupes);

        try {
            FileWriter file = new FileWriter("C:\\Users\\mihir\\Desktop\\output.json");
            file.write(objout.toString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}