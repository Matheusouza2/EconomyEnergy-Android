package infopower.economyenergy.webService;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import infopower.economyenergy.dominio.Usuario;

/**
 * Created by Mathe on 01/11/2016.
 */

public class ClienteDes implements JsonDeserializer<Object> {

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement usuario = json.getAsJsonObject();

        if( json.getAsJsonObject().get("usuario") != null ){
        usuario = json.getAsJsonObject().get("usuario");
        }
        return ( new Gson().fromJson( usuario, Usuario.class ));
        }
}
