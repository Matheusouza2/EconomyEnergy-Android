package infopower.economyenergy.webService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import infopower.economyenergy.dominio.Usuario;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mathe on 20/11/2016.
 */

public class InstanciaRetrofit {
    private String baseURL = "http://192.168.0.100:9090/WebServiceMobile/";
    private UsuarioAPI usuarioAPI;

    public InstanciaRetrofit() {}

    public UsuarioAPI intanciarRetrofit(){
        Gson gson = new GsonBuilder().registerTypeAdapter(Usuario.class, new ClienteDes()).create();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return usuarioAPI = retrofit.create(UsuarioAPI.class);
    }
}