package com.example.pam;

import android.arch.persistence.room.Room;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int NewValue;
    int InputValue;
    boolean databaseempty;
    public static MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "userdb").allowMainThreadQueries().build();
        final TextView ValueView = (TextView) findViewById(R.id.ValueView);
        final EditText ValueChange = (EditText) findViewById(R.id.ValueChange);
        Button PlusButton = (Button) findViewById(R.id.PlusButton);
        Button MinusButton = (Button) findViewById(R.id.MinusButton);

        if(myDatabase.mydao().getCount()==0){
            databaseempty=true;
            ValueView.setText(String.valueOf(0));
        } else {
            databaseempty=false;
            ValueView.setText(String.valueOf(myDatabase.mydao().savedvalue()));
        }



        PlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NewValue = Integer.parseInt(ValueView.getText().toString());
                InputValue = Integer.parseInt(ValueChange.getText().toString());
                NewValue = NewValue+ InputValue;
                //int newValueView = Integer.valueOf(ValueView)+ValueChange;
                User wynik = new User();
                wynik.setWynik(NewValue);
                if(databaseempty==true){
                    myDatabase.mydao().addValue(wynik);
                }
                if(databaseempty==false){
                    myDatabase.mydao().updateValue(NewValue);
                }
                ValueView.setText(String.valueOf(NewValue));
            }
        });
        MinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewValue = Integer.parseInt(ValueView.getText().toString());
                InputValue = Integer.parseInt(ValueChange.getText().toString());
                NewValue = NewValue-InputValue;
                User wynik = new User();
                wynik.setWynik(NewValue);
                if(databaseempty==true){
                    myDatabase.mydao().addValue(wynik);
                }
                if(databaseempty==false){
                    myDatabase.mydao().updateValue(NewValue);
                }
                ValueView.setText(String.valueOf(NewValue));
            }
        });
    }
}
