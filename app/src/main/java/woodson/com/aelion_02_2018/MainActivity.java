package woodson.com.aelion_02_2018;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    Button btValider, btAnnuler, btNext;
    RadioGroup rg1;
    RadioButton rb1, rb2;
    EditText et;
    String selectedButton;

    ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btValider = findViewById(R.id.btValider);
        btAnnuler = findViewById(R.id.btAnnuler);

        rb1 = findViewById((R.id.rb1));
        rb2 = findViewById(R.id.rb2);

        et = findViewById(R.id.et);

        rg1 = findViewById(R.id.rg1);

        img1 = findViewById(R.id.img1);

        btNext = findViewById(R.id.btNext);


        btValider.setOnClickListener(this);
        btAnnuler.setOnClickListener(this);
        btNext.setOnClickListener(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, 10, 0, true);


    }

    @Override
    public void onClick(View v) {

       if (v == btValider)
        {
            if(rb1.isChecked())
                {
                    selectedButton = rb1.getText().toString();
                    et.setText(selectedButton);
                }
            else if (rb2.isChecked())
              {
                  selectedButton = rb2.getText().toString();
                  et.setText(selectedButton);
              }

            img1.setImageResource(R.drawable.ic_done_black_48dp);
            img1.setColorFilter(Color.CYAN);

            //Toast.makeText(this, "Click sur Valider", Toast.LENGTH_SHORT).show();
        }

        else if(v.getId() == R.id.btAnnuler)
        {

            et.setText("");
            rg1.clearCheck();
            img1.setImageResource(R.drawable.ic_delete_black_48dp);
            img1.setColorFilter(Color.CYAN);

            //Toast.makeText(this, "Click sur Annuler", Toast.LENGTH_SHORT).show();
        }

        else if(v == btNext)
       {
           Intent intent= new Intent(this, SecondActivity.class);
           startActivity(intent);
       }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

    }
}
