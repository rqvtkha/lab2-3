package com.example.lab2_3;

import androidx.fragment.app.FragmentActivity;
import androidx.room.Room;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity{
    private boolean isConected;
    private boolean isEmpty = false;
    public static DetailFragment detailFragment;
    private URL url;
    private HttpURLConnection con;
    private TextView text;
    public static List<Item> items;
    private static Database db;
    private static EntityDao entityDao;
    public static List<EntityData> allEntities;

    private String convertStreamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        String str=result.toString();
        result.close();
        return str;
    }
    private void initDb()
    {
        db = Room.databaseBuilder(getApplicationContext(),Database.class,"database9").build();
        entityDao = db.entityDao();
    }
    private void getDataFromServer() throws IOException, JSONException {
        Log.i("LOCAL1","Данные получены с сервера");
        String res = convertStreamToString(con.getInputStream());
        JSONArray arr = new JSONArray(res);
        for(int i=0;i<arr.length();i++)
        {
            items.add(new Item (arr.getJSONObject(i).getInt("id"),arr.getJSONObject(i).getString("name"), arr.getJSONObject(i).getString("username")));
            EntityData entity = new EntityData();
            entity.id = arr.getJSONObject(i).getInt("id");
            entity.name = arr.getJSONObject(i).getString("name");
            entity.userName=arr.getJSONObject(i).getString("username");
            entity.email=arr.getJSONObject(i).getString("email");
            entityDao.insert(entity);
        }
        allEntities=entityDao.getAll();
    }
    private void getFromLocalDb()
    {
        if(!entityDao.getAll().isEmpty())
        {
            Log.i("LOCAL1","Данные получены с локальной бд");
            allEntities = entityDao.getAll();
            for (EntityData ed:allEntities) {
                items.add(new Item(ed.id,ed.name,ed.userName));
            }
        }
        else {
            isEmpty=true;
            Log.i("LOCAL1", "Данные не получены ни локально, ни с сервера");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items= new ArrayList();
        text=findViewById(R.id.requestStatus);
        isConected=false;
        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                initDb();
                try {
                    if(entityDao.getAll().isEmpty()) {
                        url = new URL("https://jsonplaceholder.typicode.com/users");
                        con = (HttpURLConnection) url.openConnection();
                        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                            getDataFromServer();
                            isConected = true;
                        }
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally {
                    if(!isConected)
                        getFromLocalDb();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(isConected || !isEmpty) {
            if(isConected)
                text.setText("Данные получены с удаленного сервера");
            else
                text.setText("Данные получены с локальной бд");

        }
        else
            text.setText("Данные не получены ни локально, ни с сервера");
    }

    public void showList(View view)
    {
        if(detailFragment!=null)
            getSupportFragmentManager().beginTransaction().remove(detailFragment).commit();

    }

}
