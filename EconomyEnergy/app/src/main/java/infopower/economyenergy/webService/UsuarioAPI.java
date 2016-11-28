package infopower.economyenergy.webService;

import infopower.economyenergy.dominio.Usuario;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Mathe on 31/10/2016.
 */

public interface UsuarioAPI {

    @POST("cliente/autenticar/{login}/{senha}")
    Call<Usuario> login(@Path("login")String login, @Path("senha")String senha);

    @Multipart
    @POST("cliente/enviarImagem/{imagemBinaria}")
    Call<Usuario> enviarImagen(@Part("imagemBinaria")RequestBody requestBody);
}
