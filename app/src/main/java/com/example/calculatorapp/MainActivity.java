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

    private Button btnClear, btnPlus, btnEquals, btnMul, btnDiv, btnMinus, btnErase;
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

        // btnPlus
        this.btnPlus.setOnClickListener(v -> appendOperation(" + "));
        // btnMinus
        this.btnMinus.setOnClickListener(v -> appendOperation(" - "));
        // btnMul
        this.btnMul.setOnClickListener(v -> appendOperation(" x "));
        // btnDiv
        this.btnDiv.setOnClickListener(v -> appendOperation(" / "));

        this.btnErase.setOnClickListener(v -> {
            String s = this.tvValue.getText().toString();
            if (s.isEmpty()) return;
            if (s.endsWith(" ")) this.tvValue.setText(s.substring(0,s.length() - 3));
            else this.tvValue.setText(s.substring(0,s.length() - 1));

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

    private void appendOperation(String operation){
        String value = this.tvValue.getText().toString();

        if(value.isEmpty()) return;
        if(value.charAt(value.length()-1) == ' ') return;
        this.tvValue.append(operation);
    }

    private void iniViews(){
        this.btnPlus = findViewById(R.id.button_add);
        this.btnEquals = findViewById(R.id.button_equals);
        this.btnClear = findViewById(R.id.button_ac);
        this.btnMinus = findViewById(R.id.button_subtract);
        this.btnMul = findViewById(R.id.button_multiply);
        this.btnDiv = findViewById(R.id.button_divide);
        this.btnErase = findViewById(R.id.button_erase);

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