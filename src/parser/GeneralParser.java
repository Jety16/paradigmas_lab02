package parser;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*Esta clase modela los atributos y metodos comunes a todos los distintos tipos de parser existentes en la aplicacion*/
public abstract class GeneralParser {

    public abstract JSONObject makeResponse(JSONObject jsonObj);
        
    public abstract JSONObject parseUrl(JSONArray urlList, String type);

    public abstract JSONObject parseFile(String filePath) throws IOException;

}
