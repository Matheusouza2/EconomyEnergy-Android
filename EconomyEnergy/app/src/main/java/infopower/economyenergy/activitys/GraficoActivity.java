package infopower.economyenergy.activitys;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import infopower.economyenergy.R;

public class GraficoActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);

        webView = (WebView) findViewById(R.id.webview);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Grafico");

        // Ativar Javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Chamar métodos nativos pelo javascript
        webView.loadUrl("http://192.168.0.100:9090/InfoPower/JSP/Usuario.jsp");
        webView.addJavascriptInterface(new ObjetoAndroidInterface(), "ObjetoAndroid");

    }

    class ObjetoAndroidInterface {

        @JavascriptInterface
        public void teste() {
            Toast.makeText(getBaseContext(), "Método executado no Javascript", Toast.LENGTH_SHORT).show();
        }
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
