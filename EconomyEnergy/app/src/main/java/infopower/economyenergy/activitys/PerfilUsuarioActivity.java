package infopower.economyenergy.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import infopower.economyenergy.R;
import infopower.economyenergy.dominio.Usuario;
import infopower.economyenergy.webService.ClienteDes;
import infopower.economyenergy.webService.UsuarioAPI;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PerfilUsuarioActivity extends AppCompatActivity {

    private ImageView imgUsu;
    private TextView nomeUsu, loginUsu, enderecoUsu;
    private Bitmap bitmap;
    private static final String baseURL = "http://192.168.0.110:9090/WebServiceMobile/";
    private static RequestBody requestBody ;
    private UsuarioAPI usuarioAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Usuario");
        actionBar.setDisplayHomeAsUpEnabled(true);

        imgUsu = (ImageView)findViewById(R.id.image_usu);
        nomeUsu = (TextView)findViewById(R.id.nome_usu);
        loginUsu = (TextView)findViewById(R.id.login_usu);
        enderecoUsu = (TextView)findViewById(R.id.endereco_usu);
        nomeUsu.setText("Nome: ");
        loginUsu.setText("Login: ");
        enderecoUsu.setText("Endereço: ");
        imgUsu.setImageResource(R.drawable.img_user);
        imgUsu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                carregarGaleria();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Gson gson = new GsonBuilder().registerTypeAdapter(Usuario.class, new ClienteDes()).create();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        usuarioAPI = retrofit.create(UsuarioAPI.class);
    }


    public void carregarGaleria(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        InputStream stream = null;
        if (requestCode == 1 && resultCode == RESULT_OK) {
            try {
                if (bitmap != null) {

                }
                stream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(stream);
                enviarImagem();
            }
            catch(FileNotFoundException e) {
                e.printStackTrace();
            }
            finally {
                if (stream != null)
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    public void enviarImagem(){
        // transforma o bitmap em um byte[]
        ByteArrayOutputStream bAOStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bAOStream);
        //Tranforma o byte[] em base64
        String bs64 = Base64.encodeToString(bAOStream.toByteArray(),Base64.DEFAULT);
        //Comprime o base64 em request body
        requestBody = RequestBody.create(MediaType.parse("image/*"), bs64);
        //Tenta enviar a imagem para o servidor-->
        Call<Usuario> call = usuarioAPI.enviarImagen(requestBody);
        //Cria o processDialog para o usuario ficar ciente doque esta acontecendo
        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Salvando Imagem");
        mProgressDialog.show();
        //Pega a resoista do servidor
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                //se conseguir enviar para o progress e seta a imagem no imageView
                if(mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                imgUsu.setImageBitmap(bitmap);
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                //Se der erro ele para o processDialog e diz que não foi possivel salvar a imagem
                Log.i("ERRO AO ENVIAR IMAGEM: ",t.getMessage());
                if(mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Erro ao salvar a imagem", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}