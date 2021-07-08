package ch.zli.counter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import ch.zli.m335.counter.R;


public class MainActivity extends AppCompatActivity {
    private TextView counter;
    int cntr = 0;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        Button plusButton = findViewById(R.id.plusOne);
        Button minusButton = findViewById(R.id.minusOne);
        Button resetButton = findViewById(R.id.reset);
        counter = findViewById(R.id.counter);

        int cntrRestore = sh.getInt("cntr", 0);
        counter.setText(String.valueOf(cntrRestore));
        if (cntrRestore == 0 ){
            cntr = 0;
        } else {
            cntr = cntrRestore;
        }
        counter.setText(String.valueOf(cntr));
        plusButton.setOnClickListener(v -> {
            cntr++;
            counter.setText(String.valueOf(cntr));

        });
        minusButton.setOnClickListener(v -> {
            cntr--;
            counter.setText(String.valueOf(cntr));

        });
        resetButton.setOnClickListener(v -> {
            cntr = 0;
            counter.setText(String.valueOf(cntr));

        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("param", counter.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        counter.setText(savedInstanceState.getString("param"));
        cntr = Integer.parseInt(savedInstanceState.get("param").toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        int cntrRestore = sh.getInt("cntr", 0);
        counter.setText(String.valueOf(cntrRestore));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putInt("cntr", Integer.parseInt(counter.getText().toString()));
        myEdit.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putInt("cntr", Integer.parseInt(counter.getText().toString()));
        myEdit.apply();
    }
}