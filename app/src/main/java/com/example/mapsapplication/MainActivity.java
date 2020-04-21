package com.example.mapsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button btnIrMapa;
    EditText latitud;
    EditText longitud;
    String numberPick;
    String textSpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIrMapa  =findViewById(R.id.btnIrMapa);
        latitud = findViewById(R.id.edtLatitud);
        longitud = findViewById(R.id.edtLongitud);

        //number picker

        NumberPicker np = findViewById(R.id.numberPicker);

        np.setMinValue(1);
        np.setMaxValue(23);
        np.setOnValueChangedListener(onValueChangeListener);

        //spinner
        Spinner capital = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colores, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        capital.setAdapter(adapter);
        capital.setOnItemSelectedListener(this);

        btnIrMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(latitud.getText()!=null && longitud.getText()!=null && numberPick!=null){
                    Intent i = new Intent(MainActivity.this, MapsActivity1.class);
                    i.putExtra("lati",latitud.getText().toString());
                    i.putExtra("longi",longitud.getText().toString());
                    i.putExtra("zoom",numberPick);
                    i.putExtra("color",textSpin);
                    startActivity(i);
                }else{

                    Toast.makeText(MainActivity.this,"Por favor, llene todos los campos",Toast.LENGTH_SHORT).show();

                }



            }
        });

    }

    NumberPicker.OnValueChangeListener onValueChangeListener =
            new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker,int i, int i1) {
                    numberPick= String.valueOf(numberPicker.getValue());
                    Toast.makeText(MainActivity.this, "selected zoom " + numberPicker.getValue(), Toast.LENGTH_SHORT);
                }
            };

    //spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        textSpin = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), textSpin, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
