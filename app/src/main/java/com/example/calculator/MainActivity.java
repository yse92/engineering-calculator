package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mCalculatorDisplay;
    private Boolean userIsInTheMiddleOfTypingANumber = false;
    private CalculatorBrain mCalculatorBrain;
    private static final String DIGITS = "0123456789.";

    DecimalFormat decimalFormat = new DecimalFormat("@###########");


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //спрятать заголовок окна
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //спрятать status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCalculatorBrain = new CalculatorBrain();
        mCalculatorDisplay = (TextView) findViewById(R.id.textView1);

        //минимальное количество цифр, в дробной части числа
        decimalFormat.setMinimumFractionDigits(0);
        //минимальное количество цифр, в целой части числа
        decimalFormat.setMinimumIntegerDigits(1);
        //макс количество цифр, в целой части числа
        decimalFormat.setMaximumIntegerDigits(8);

        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);

        findViewById(R.id.buttonAdd).setOnClickListener(this);
        findViewById(R.id.buttonSubstract).setOnClickListener(this);
        findViewById(R.id.buttonMultiply).setOnClickListener(this);
        findViewById(R.id.buttonDivide).setOnClickListener(this);

        findViewById(R.id.buttonToggleSign).setOnClickListener(this);
        findViewById(R.id.buttonDecimalPoint).setOnClickListener(this);
        findViewById(R.id.buttonRecallMemory).setOnClickListener(this);
        findViewById(R.id.buttonAddToMemory).setOnClickListener(this);
        findViewById(R.id.buttonClear).setOnClickListener(this);
        findViewById(R.id.buttonClearMemory).setOnClickListener(this);
        findViewById(R.id.buttonSubstractFromMemory).setOnClickListener(this);
        findViewById(R.id.buttonEquals).setOnClickListener(this);

        //существуют только в layout-landscape
        if(findViewById(R.id.buttonSquareRoot) != null)
            findViewById(R.id.buttonSquareRoot).setOnClickListener(this);
        if(findViewById(R.id.buttonSquared) != null)
            findViewById(R.id.buttonSquared).setOnClickListener(this);
        if(findViewById(R.id.buttonInvert) != null)
            findViewById(R.id.buttonInvert).setOnClickListener(this);
        if(findViewById(R.id.buttonSin) != null)
            findViewById(R.id.buttonSin).setOnClickListener(this);
        if(findViewById(R.id.buttonCos) != null)
            findViewById(R.id.buttonCos).setOnClickListener(this);
        if(findViewById(R.id.buttonTan) != null)
            findViewById(R.id.buttonTan).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String buttonPressed = ((Button)v).getText().toString();

        if (DIGITS.contains(buttonPressed)) {
            if(userIsInTheMiddleOfTypingANumber) {
                if (buttonPressed.equals(".") && mCalculatorDisplay.getText().toString().contains(".")) {
                    //Error
                } else {
                    mCalculatorDisplay.append(buttonPressed);
                }
            } else {
                if (buttonPressed.equals(".")) {
                    mCalculatorDisplay.setText(0+buttonPressed);
                } else {
                    mCalculatorDisplay.setText(buttonPressed);
                }

                userIsInTheMiddleOfTypingANumber = true;
            }
        } else {
            if(userIsInTheMiddleOfTypingANumber) {
                mCalculatorBrain.setOperand(Double.parseDouble(mCalculatorDisplay.getText().toString()));
                userIsInTheMiddleOfTypingANumber = false;
            }
            mCalculatorBrain.performOperation(buttonPressed);
            mCalculatorDisplay.setText(decimalFormat.format(mCalculatorBrain.getResult()));

        }

    }


    @Override
    protected void onSaveInstanceState(Bundle onState) {

        super.onSaveInstanceState(onState);

        onState.putDouble("OPERAND", mCalculatorBrain.getResult());
        onState.putDouble("MEMORY", mCalculatorBrain.getMemory());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);


        mCalculatorBrain.setOperand(savedState.getDouble("OPERAND"));
        mCalculatorBrain.setMemory(savedState.getDouble("MEMORY"));

        mCalculatorDisplay.setText(decimalFormat.format(mCalculatorBrain.getResult()));
    }
}