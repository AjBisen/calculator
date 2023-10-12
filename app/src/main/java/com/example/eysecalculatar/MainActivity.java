package com.example.eysecalculatar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView result, soliution;
    MaterialButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.cal);
        soliution = findViewById(R.id.calculatar);

        assingId(b1, R.id.a);
        assingId(b2, R.id.b);
        assingId(b3, R.id.c);
        assingId(b4, R.id.d);

        assingId(b5, R.id.m7);
        assingId(b6, R.id.m8);
        assingId(b7, R.id.m9);
        assingId(b8, R.id.malti);

        assingId(b9, R.id.sun4);
        assingId(b10, R.id.sun5);
        assingId(b11, R.id.sun6);
        assingId(b12, R.id.sum);

        assingId(b13, R.id.mi1);
        assingId(b14, R.id.mi2);
        assingId(b15, R.id.mi3);
        assingId(b16, R.id.mi);

        assingId(b17, R.id.equal);
        assingId(b18, R.id.equal0);
        assingId(b19, R.id.equal_);
        assingId(b20, R.id.equalto);
    }

    void assingId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = soliution.getText().toString();
        dataToCalculate = dataToCalculate + buttonText;
        soliution.setText(dataToCalculate);
        if (buttonText.equals("A")) {
            soliution.setText("");
            result.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            soliution.setText((CharSequence) result);
        }
        if (buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + buttonText;

        }
        soliution.setText(dataToCalculate);
        String finalResult = getresult(dataToCalculate);
        if (!finalResult.equals("Err")){
            result.setText(finalResult);
        }
    }
    String getresult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "javascript" ,1, null).
            toString();
            if (finalResult.endsWith("0")){
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }
}