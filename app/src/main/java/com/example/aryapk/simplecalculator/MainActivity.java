package com.example.aryapk.simplecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tvDisplayInput) TextView tvInput;
    @Bind(R.id.tvDisplayFinish) TextView tvFinish;
    @Bind(R.id.btnDel) Button btnDel;
    @Bind(R.id.btnDiv) Button btnDiv;
    @Bind(R.id.btnMulti) Button btnMulti;
    @Bind(R.id.btnEqual) Button btnEqual;
    @Bind(R.id.btnMinus) Button btnMin;
    @Bind(R.id.btnPlus) Button btnPlus;
    @Bind(R.id.btnZero) Button btn0;
    @Bind(R.id.btnOne) Button btn1;
    @Bind(R.id.btnTwo) Button btn2;
    @Bind(R.id.btnThree) Button btn3;
    @Bind(R.id.btnFour) Button btn4;
    @Bind(R.id.btnFive) Button btn5;
    @Bind(R.id.btnSix) Button btn6;
    @Bind(R.id.btnSeven) Button btn7;
    @Bind(R.id.btnEight) Button btn8;
    @Bind(R.id.btnNine) Button btn9;

    String displayString=" ",secondString=" ",currentSymbol="C";
    Integer firstValue = 0,secondValue = 0,flag = 0;
    String finishValue=" ";
    MainActivity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        activity = this;
        setLayout();
    }

    private void setLayout(){
        btn0.setOnClickListener(click);
        btn1.setOnClickListener(click);
        btn2.setOnClickListener(click);
        btn3.setOnClickListener(click);
        btn4.setOnClickListener(click);
        btn5.setOnClickListener(click);
        btn6.setOnClickListener(click);
        btn7.setOnClickListener(click);
        btn8.setOnClickListener(click);
        btn9.setOnClickListener(click);
        btnEqual.setOnClickListener(click);
        btnDel.setOnClickListener(click);
        btnDiv.setOnClickListener(click);
        btnMulti.setOnClickListener(click);
        btnMin.setOnClickListener(click);
        btnPlus.setOnClickListener(click);
    }

    private void setNewInput(String newInput,String symbol){
        Integer neoFlag=0;
        if (!symbol.equals(" ")){
            if (currentSymbol.equals("C")) {
                currentSymbol = symbol;
                firstValue = Integer.valueOf(displayString);
                displayString = displayString+currentSymbol;
                flag = 1;
            }
            else if (!currentSymbol.equals("C") && secondString.equals(" ")){
                currentSymbol = symbol;
                displayString = firstValue+currentSymbol;
            }
            else {
                currentSymbol = symbol;
                displayString = finishValue+currentSymbol;
                firstValue = Integer.valueOf(finishValue);
                secondString = " ";
            }
        }
        if (flag == 1 && !newInput.equals(" ")){
            if (secondString.equals(" ")){
                if (!newInput.equals("0")) secondString = newInput;
                else neoFlag  = 1;
            }
            else secondString = secondString+newInput;
            if (!newInput.equals("0")&&!secondString.equals(" ")){
                secondValue = Integer.valueOf(secondString);
                finishValue = onCalculate(firstValue,secondValue,currentSymbol);
                tvFinish.setText(finishValue);
            }
        }
        if (displayString.equals(" ") && !newInput.equals("0")&& neoFlag==0) displayString = newInput;
        else if (!newInput.equals(" ")&& !displayString.equals(" ") && neoFlag==0) displayString = displayString+newInput;
        if (displayString.equals(" ")) tvInput.setText("0");
        else tvInput.setText(displayString);
    }

    private void deleteDisplay(){
        firstValue = 0;
        displayString=" ";
        tvInput.setText("0");
        tvFinish.setText("");
        secondString=" ";
        secondValue = 0;
        currentSymbol="C";
        finishValue = " ";
        flag = 0;
    }

    private void setAsEqual(Integer value){
        firstValue = value;
        displayString=String.valueOf(value);
        tvInput.setText(displayString);
        tvFinish.setText("");
        secondString=" ";
        secondValue = 0;
        currentSymbol="C";
        finishValue = " ";
        flag = 0;
    }

    private String onCalculate(Integer firstValue,Integer secondValue,String currentSymbol){
        Integer finalValue=0;
        if (currentSymbol.equals("-")) finalValue = firstValue-secondValue;
        if (currentSymbol.equals("+")) finalValue = firstValue+secondValue;
        if (currentSymbol.equals("/")) finalValue = firstValue/secondValue;
        if (currentSymbol.equals("*")) finalValue = firstValue*secondValue;
        String returnFinal = String.valueOf(finalValue);
        return returnFinal;
    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String newInput=" ";
            String symbol=" ";
            String equals = " ";
            Integer flag = 0;
            switch (v.getId()){
                case R.id.btnDel:
                    flag = 1;
                    break;
                case R.id.btnDiv:
                    symbol="/";
                    break;
                case R.id.btnMulti:
                    symbol="*";
                    break;
                case R.id.btnEqual:
                    equals = "E";
                    break;
                case R.id.btnMinus:
                    symbol="-";
                    break;
                case R.id.btnPlus:
                    symbol="+";
                    break;
                case R.id.btnZero:
                    newInput="0";
                    break;
                case R.id.btnOne:
                    newInput="1";
                    break;
                case R.id.btnTwo:
                    newInput="2";
                    break;
                case R.id.btnThree:
                    newInput="3";
                    break;
                case R.id.btnFour:
                    newInput="4";
                    break;
                case R.id.btnFive:
                    newInput="5";
                    break;
                case R.id.btnSix:
                    newInput="6";
                    break;
                case R.id.btnSeven:
                    newInput="7";
                    break;
                case R.id.btnEight:
                    newInput="8";
                    break;
                case R.id.btnNine:
                    newInput="9";
                    break;
            }
            if (!equals.equals("E")){
                if (flag==0) setNewInput(newInput,symbol);
                else deleteDisplay();
            }
            else if (!finishValue.equals(" "))setAsEqual(Integer.valueOf(finishValue));
        }
    };
}
