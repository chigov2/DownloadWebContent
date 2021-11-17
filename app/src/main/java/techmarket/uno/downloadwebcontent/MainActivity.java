package techmarket.uno.downloadwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private String ukrNet = "https://www.ukr.net/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Log.i("Hello",ukrNet);

        DownLoadTask task = new DownLoadTask();
        try {
            String result = task.execute(ukrNet).get();//в данном случве процесс будет заполнен в другом потоке
            Log.i("url",result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private static class DownLoadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            //Log.i("url",strings[0]);///////////////////////////////   !!!!!!!!!!!!!!!
            StringBuilder result = new StringBuilder();
            URL url = null;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(strings[0]);

                //открываем соединение
                urlConnection = (HttpURLConnection)url.openConnection();//необходимо было приведение типов

                //начинаем читать данные - открываем поток
                InputStream in = urlConnection.getInputStream();//получаем поток ввода
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
            return "Done";
        }
    }
}