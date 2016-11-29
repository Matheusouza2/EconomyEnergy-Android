package infopower.economyenergy.webService;

import infopower.economyenergy.dominio.Relatorio;
import infopower.economyenergy.dominio.Usuario;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Mathe on 31/10/2016.
 */

public interface UsuarioAPI {

    @POST("cliente/autenticar/{login}/{senha}")
    Call<Usuario> login(@Path("login")String login, @Path("senha")String senha);

    @POST("cliente/mudarSenha/{senhaAtual}/{novaSenha}/{login}")
    Call<Usuario> mudarSenha(@Path("senhaAtual") String senhaAtual, @Path("novaSenha") String novaSenha, @Path("login") String login);

    @GET("{comando}")
    Call<Usuario> comandaoRele(@Query("comando") String comando);

    @POST("cliente/relatorio/{id}")
    Call<Relatorio> receberRelatorio(@Path("id")int id);
}
