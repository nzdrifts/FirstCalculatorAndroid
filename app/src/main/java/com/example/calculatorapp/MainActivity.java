package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculatorapp.models.EvaluateOperations;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Button btnClear, btnPlus, btnEquals;
    private List<Button> btnList;
    private TextView tvValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniViews();
        clickListeners();
    }

    private void clickListeners(){
        // sets text to "" when btnClear is pressed
        this.btnClear.setOnClickListener(v -> this.tvValue.setText(""));

        // appends "+" to display
        this.btnPlus.setOnClickListener(v -> {
            String value = this.tvValue.getText().toString();

            if(value.isEmpty()) return;
            if(value.charAt(value.length()-1) == ' ') return;
            this.tvValue.append(" + ");

        });

        // for each value in btnList, if it is pressed that number is appended to the display
        for (int i = 0; i < this.btnList.size(); i++) {
            final int iFinal = i;
            this.btnList.get(i).setOnClickListener(v -> this.tvValue.append(String.valueOf(iFinal)));
        }

        // equals button
        this.btnEquals.setOnClickListener((v)->{
            // evaluated the value of operations currently displayed
            long result = EvaluateOperations.evaluate(this.tvValue.getText().toString());
            // set text to result
            this.tvValue.setText(String.valueOf(result));
        });
    }

    private void iniViews(){
        this.btnPlus = findViewById(R.id.button_add);
        this.btnEquals = findViewById(R.id.button_equals);
        this.btnClear = findViewById(R.id.button_ac);

        // populate btnList array with buttons, index = button number
        this.btnList = new ArrayList<>();
        this.btnList.add(findViewById(R.id.button_0));
        this.btnList.add(findViewById(R.id.button_1));
        this.btnList.add(findViewById(R.id.button_2));
        this.btnList.add(findViewById(R.id.button_3));
        this.btnList.add(findViewById(R.id.button_4));
        this.btnList.add(findViewById(R.id.button_5));
        this.btnList.add(findViewById(R.id.button_6));
        this.btnList.add(findViewById(R.id.button_7));
        this.btnList.add(findViewById(R.id.button_8));
        this.btnList.add(findViewById(R.id.button_9));

        this.tvValue = findViewById(R.id.textView);
    }

}