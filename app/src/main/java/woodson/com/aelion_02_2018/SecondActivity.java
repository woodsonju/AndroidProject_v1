package woodson.com.aelion_02_2018;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by AELION on 22/02/2018.
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    Button btPrevious;
    Button btWeb;
    Button btMap;

    private static final int ALERT_DIALOG = 25;
    private static final int TIME_PICKER = 26;
    private static final int DATE_PICKER = 27;
    private static final int ASYNC_TASK = 28;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btPrevious = findViewById(R.id.btPrevious);
        btPrevious.setOnClickListener(this);

        btMap = findViewById(R.id.btMap);
        btMap.setOnClickListener(this);

        btWeb = findViewById(R.id.btWeb);
        btWeb.setOnClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {

        menu.add(0, ALERT_DIALOG, 0, "AlertDialog");
        menu.add(0, TIME_PICKER, 0, "TimePicker");
        menu.add(0, DATE_PICKER, 0, "DatePicker");
        menu.add(0, ASYNC_TASK, 0, "AsyncTask");

        return super.onCreateOptionsMenu(menu);
    }

    /* Handles item selections */
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case ALERT_DIALOG:
                alertDialog();
                break;
            case TIME_PICKER:
                timePicker();
                break;
            case DATE_PICKER:
                datePicker();
                break;
            case ASYNC_TASK:
                MonAT monAT = new MonAT();
                monAT.execute();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    AlertDialog alertDialog()
    {
        //Préparation de la fenêtre
        AlertDialog.Builder alertDialogBuilder= new AlertDialog.Builder(this);
        //Message
                alertDialogBuilder.setMessage("Afficher un Toast");
        //titre
                alertDialogBuilder.setTitle("Mon titre");
        //bouton ok
                alertDialogBuilder.setPositiveButton("ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                //Affiche un toast apresle click sur le bouton ok

                                Toast.makeText(SecondActivity.this, "Click sur ok",
                                        Toast.LENGTH_SHORT).show();
                            }

            });
        //Icone
                alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
        //Afficher la fenêtre
             return   alertDialogBuilder.show();


    }


   TimePickerDialog timePicker()
   {
       TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, 10, 0, true);
       timePickerDialog.show();
       return  timePickerDialog;

   }


   DatePickerDialog datePicker()
   {
       //Gestion de la date
           Calendar calendar= Calendar.getInstance();
    //Création de la fenêtre
    //Pour le callback -> Alt+entree-> implémente méthode -> Génère la méthode onTimeSet
           DatePickerDialog datePickerDialog= new DatePickerDialog(this, this,
                   calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                   calendar.get(Calendar.DAY_OF_MONTH));
    //Afficher la fenêtre
          datePickerDialog.show();
          return datePickerDialog;
   }



    @Override
    public void onClick(View v) {
        if(v == btPrevious)
        {
            Intent intent= new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else  if(v == btWeb)
        {
            Intent intent= new Intent(this, WebActivity.class);
            startActivity(intent);
        }
        else  if(v == btMap)
        {
            Intent intent1= new Intent(this, MapsActivity.class);
            startActivity(intent1);
        }

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int houreofDay, int minute) {
        Toast.makeText(this, "Il est " + houreofDay + " h " + minute + " minute ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayofMonth) {
        Toast.makeText(this, "La date est : " + year + "/" + month + "/" + dayofMonth, Toast.LENGTH_LONG).show();

    }


    public class MonAT extends AsyncTask
    {
        @Override
        protected Object doInBackground(Object[] objects) {
            // TRAITEMNT LONG MAIS INTERDIT DE TOUCHER AUX COMPOSANT GRAPHIQUE
            WebServiceUtils.loadEleveFromWeb();
            return null;
        }

        // Dans UIThread
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        // Méthode appelée quand le doInBackgroundest terminé
        //On peut modifier les composants graphiques
            Toast.makeText(SecondActivity.this, "Chargement effecuté", Toast.LENGTH_LONG).show();

        }
    }
}
