package infopower.economyenergy.webService;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import infopower.economyenergy.dominio.Relatorio;

/**
 * Created by Mathe on 20/11/2016.
 */

public class RelatorioDes implements JsonDeserializer<Object> {

@Override
public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement relatorio = json.getAsJsonObject();

        if( json.getAsJsonObject().get("relatorio") != null ){
        relatorio = json.getAsJsonObject().get("relatorio");
        }
        return ( new Gson().fromJson( relatorio, Relatorio.class ));
        }
        }
