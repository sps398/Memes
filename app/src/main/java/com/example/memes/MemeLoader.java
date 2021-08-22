package com.example.memes;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MemeLoader extends AsyncTask<String,Void, JSONArray> {
    static Context ctx;
    MemeLoader(Context context) {
        ctx = context;
    }

    @Override
    protected JSONArray doInBackground(String... strings) {
        String str = strings[0];
        URL url;
        HttpURLConnection conn = null;
        Bitmap image = null;
        JSONArray array = null;
        try {
            url = new URL(str);
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            InputStream stream = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            JSONObject object1 = new JSONObject(br.readLine());
            array = object1.getJSONArray("memes");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return array;
    }

    @Override
    protected void onPostExecute(final JSONArray array) {
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(ctx,array);
            MainActivity.recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
            MainActivity.recyclerView.setAdapter(adapter);
    }

}
