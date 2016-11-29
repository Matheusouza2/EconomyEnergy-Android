package infopower.economyenergy.webService;

import android.os.SystemClock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import infopower.economyenergy.dominio.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mathe on 20/11/2016.
 */

public class ComandoRele {
    private String baseURL = "http://192.168.0.130:9092/";
    private UsuarioAPI usuarioAPI;

    public ComandoRele() {  }

    public void enviarComando(String comando) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Usuario.class, new ClienteDes()).create();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        usuarioAPI = retrofit.create(UsuarioAPI.class);

        final Call<Usuario> clienteLogin = usuarioAPI.comandaoRele(comando);
        clienteLogin.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });
            new Thread() {
            @Override
            public void run() {
                super.run();
                SystemClock.sleep(2000);
                clienteLogin.cancel();
            }
        }.start();
    }
}
