package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculatorapp.models.EvaluateOperations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Button btnClear, btnPlus, btnEquals, btnMul, btnDiv, btnMinus, btnErase, btnDecimal, btnMEM, btnSignChange;
    private List<Button> btnList;
    private TextView tvValue,tvLastOperation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniViews();
        clickListeners();
    }

    private void clickListeners(){
        // sets text to "" when btnClear is pressed
        this.btnClear.setOnClickListener(v -> {
            this.tvValue.setText("");
            String lo = "Last Operation: ";
            this.tvLastOperation.setText(lo);
        });

        // btnPlus
        this.btnPlus.setOnClickListener(v -> appendOperation(" + "));
        // btnMinus
        this.btnMinus.setOnClickListener(v -> appendOperation(" - "));
        // btnMul
        this.btnMul.setOnClickListener(v -> appendOperation(" x "));
        // btnDiv
        this.btnDiv.setOnClickListener(v -> appendOperation(" / "));

        // btnDecimal
        this.btnDecimal.setOnClickListener(v -> this.tvValue.append("."));

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
            String txtValue = this.tvValue.getText().toString();

            // remove operation at end if it is present
            if(txtValue.endsWith(" ")) txtValue=txtValue.substring(0,txtValue.length()-3);
            //remove "-" at end if is present
            if(txtValue.endsWith("-")) txtValue=txtValue.substring(0,txtValue.length()-1);

            // append last_operation
            String s = "Last Operation: ";
            this.tvLastOperation.setText(s.concat(txtValue));

            // evaluated the value of operations currently displayed
            double result = EvaluateOperations.evaluatePriority(txtValue);

            // set text to result
            result = (int) result;
            this.tvValue.setText(String.valueOf(result));
        });

        // MEM button
        this.btnMEM.setOnClickListener((v -> {
            String memText = this.tvLastOperation.getText().toString();
            this.tvValue.setText(memText.substring(16));
            String lo = "Last Operation: ";
            this.tvLastOperation.setText(lo);
        }));

        // sign change button
        this.btnSignChange.setOnClickListener(v -> {
            // puts tvValue into a list
            String[] list = this.tvValue.getText().toString().split(" ");
            if(this.tvValue.getText().toString().isEmpty()) return;
            int listSize = list.length-1;
            if(list[listSize].equals("+") || list[listSize].equals("-") || list[listSize].equals("/") || list[listSize].equals("x")){
                double newVal = Double.parseDouble(list[listSize-1]) * -1;
                list[listSize-1] = Double.toString(newVal);
            }
            else{
                double newVal = Double.parseDouble(list[listSize]) * -1;
                list[listSize] = Double.toString(newVal);
            }
            if(list[listSize].equals("+") || list[listSize].equals("-") || list[listSize].equals("/") || list[listSize].equals("x")){
                String returnString = String.join(" ", Arrays.asList(list)) + " ";
                this.tvValue.setText(returnString);
            }else{
                this.tvValue.setText( String.join(" ", Arrays.asList(list)));
            }
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
        this.btnDecimal = findViewById(R.id.button_decimal);
        this.btnMEM = findViewById(R.id.button_MEM);
        this.btnSignChange = findViewById(R.id.button_change_sign);

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
        this.tvLastOperation = findViewById(R.id.last_operation);
    }

}