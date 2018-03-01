package woodson.com.aelion_02_2018;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WebActivity extends AppCompatActivity implements View.OnClickListener {
    Button btOk;
    String selectedText;
    EditText et1;
    TextView tv;
    WebView wv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);



        btOk = findViewById(R.id.btOk);
        et1 = findViewById(R.id.et1);
        tv = findViewById(R.id.tv);
        wv = findViewById(R.id.wv);

        btOk.setOnClickListener(this);

        wv.setWebViewClient(new WebViewClient());

        et1.setText("https://www.google.fr/");

    }



    @Override
    public void onClick(View v) {

        if(v == btOk)
        {
            wv.loadUrl(et1.getText().toString());
            WebActivity.MonAT monAT = new MonAT(et1.getText().toString());

            monAT.execute();
          //  selectedText = et1.getText().toString();


            //Toast.makeText(this, "Le texte est : " + selectedText,   Toast.LENGTH_SHORT).show();
        }
    }

    public class MonAT extends AsyncTask
    {
        String reponse ;
        String url;

        public MonAT(String url)
        {
            this.url = url;
        }
        @Override
        protected Object doInBackground(Object[] objects) {

            try {
               reponse =  WSUtils.sendGetOkHttpRequest(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        // Dans UIThread
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
           tv.setText(reponse);
        }
    }
}
