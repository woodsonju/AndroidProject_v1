package woodson.com.aelion_02_2018;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by AELION on 23/02/2018.
 */

public class WSUtils {



    public static String sendGetOkHttpRequest(String url) throws Exception {
        Log.w("tag","url: "+ url);
        OkHttpClient client= new OkHttpClient();

        //Création de la requete
        Request request= new Request.Builder().url(url).build();

        //Executionde la requête
        Response response= client.newCall(request).execute();

        //Analyse du code retour.
        if(response.code() != 200) {
            throw new Exception("Réponse du serveur incorrect : "+ response.code());
        }
        else{
        //Résultat de la requete.
        //ATTENTION .string() ne peut être appelée qu’une seule fois.
            return response.body().string();
        }
    }

    public static String sendPostOkHttpRequest(String url, String paramJson)
    throws Exception {
        Log.w("tag","url: "+ url);
        OkHttpClient client= new OkHttpClient();
        MediaType JSON= MediaType.parse("application/json; charset=utf-8");

        //Corps de la requête
        RequestBody body= RequestBody.create(JSON, paramJson);

        //Création de la requete
        Request request= new Request.Builder().url(url).post(body).build();

        //Executionde la requête
        Response response= client.newCall(request).execute();

        //Analyse du code retour
        if(response.code() != 200) {
            throw new Exception("Réponse du serveur incorrect : "+ response.code());
        }
        else{
        //Résultat de la requete.
            return response.body().string();
        }
    }

    public static ArrayList<StationBean> getStations() throws Exception {
        //url
        String url = "https://api.jcdecaux.com/vls/v1/stations?contract=Toulouse&apiKey=b633e73d1cadd575af117c62de8e705332416d9b";

        //Faire ma requete
        String monStringJson = sendGetOkHttpRequest(url);

        Gson gson= new Gson();

        //Parser une ArrayListtypée
        ArrayList<StationBean> list= gson.fromJson(monStringJson,
                new TypeToken<ArrayList<StationBean>>(){}.getType());

        return list;

    }


}
