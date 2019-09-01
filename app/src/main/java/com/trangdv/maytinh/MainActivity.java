package com.trangdv.maytinh;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {
    private EditText numberOne;
    private EditText numberTwo;
    private ImageView add;
    private ImageView sub;
    private ImageView mul;
    private ImageView div;
    private ImageView clearOne;
    private ImageView clearTwo;
    private HorizontalScrollView scrollerDisplayNumber;
    private TextView numberDisplay;
    private String operation;
    private String textOne;
    private String textTwo;
    private Expression expression;
    private double value = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }
    public void getInput(EditText editText1, EditText editText2){
        textOne = editText1.getText().toString();
        textTwo = editText2.getText().toString();
    }

    public void initViews() {
        numberOne = findViewById(R.id.number_one_edt);
        numberTwo = findViewById(R.id.number_two_edt);
        add = findViewById(R.id.add_btn);
        sub = findViewById(R.id.sub_btn);
        mul = findViewById(R.id.mul_btn);
        div = findViewById(R.id.div_btn);
        scrollerDisplayNumber = findViewById(R.id.displayNumberScroller);
        numberDisplay = findViewById(R.id.displayNumber);
        clearOne = findViewById(R.id.ivClearSearchTextOne);
        clearTwo = findViewById(R.id.ivClearSearchTextTwo);

        // edt chứa số thứ nhất
        numberOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0){
                    clearOne.setVisibility(View.VISIBLE);
                }
                else {
                    clearOne.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // edt chứa số thứ hai
        numberTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0){
                    clearTwo.setVisibility(View.VISIBLE);
                }
                else {
                    clearTwo.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // clear số trong edt
        clearOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOne.setText("");
            }
        });
        clearTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberTwo.setText("");
            }
        });

        // các phép toán
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInput(numberOne, numberTwo);
                if (textOne.equals("")==false&&textTwo.equals("")==false){
                    numberDisplay.setText(numberOne.getText()+ "+" + numberTwo.getText());
                    operation = numberDisplay.getText().toString();
                    expression = new ExpressionBuilder(operation).build();
                    value = expression.evaluate();
                    numberDisplay.setText(numberDisplay.getText()+ "=" + Double.toString(value));
                }
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInput(numberOne, numberTwo);
                if (textOne.equals("")==false&&textTwo.equals("")==false){
                    numberDisplay.setText(numberOne.getText()+ "-" + numberTwo.getText());
                    operation = numberDisplay.getText().toString();
                    expression = new ExpressionBuilder(operation).build();
                    value = expression.evaluate();
                    numberDisplay.setText(numberDisplay.getText()+ "=" + Double.toString(value));
                }
            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInput(numberOne, numberTwo);
                if (textOne.equals("")==false&&textTwo.equals("")==false){
                    numberDisplay.setText(numberOne.getText()+ "*" + numberTwo.getText());
                    operation = numberDisplay.getText().toString();
                    expression = new ExpressionBuilder(operation).build();
                    value = expression.evaluate();
                    numberDisplay.setText(numberDisplay.getText()+ "=" + Double.toString(value));
                }
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInput(numberOne, numberTwo);
                if (textOne.equals("")==false&&textTwo.equals("")==false){
                    numberDisplay.setText(numberOne.getText()+ "/" + numberTwo.getText());
                    operation = numberDisplay.getText().toString();
                    expression = new ExpressionBuilder(operation).build();
                    try {
                        value = expression.evaluate();
                        numberDisplay.setText(numberDisplay.getText()+ "=" + Double.toString(value));
                    }catch (ArithmeticException e){
                        numberDisplay.setText("∞");
                    }
                }
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
