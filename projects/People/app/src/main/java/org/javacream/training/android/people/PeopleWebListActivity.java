package org.javacream.training.android.people;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PeopleWebListActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_web_list);
        webView = findViewById(R.id.webView);
        webView.clearCache(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new Helper(), "helper");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://10.28.11.41:9090/referent");
    }


    public class Helper{
        @JavascriptInterface
        public String doSomething(){
            Log.i("from webview", "Hello");
            return "Back from JS";
        }
        @JavascriptInterface
        public void doThis(){
            Log.i("from webview", "Hello");
        }
        @JavascriptInterface
        public void doThat(){
            Log.i("from webview", "Hello");
        }
        @JavascriptInterface
        public void takePhoto(){
            Log.i("from webview", "Hello");
        }
    }
}

