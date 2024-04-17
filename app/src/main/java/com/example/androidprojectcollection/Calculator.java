package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Calculator extends AppCompatActivity implements View.OnClickListener{

    //TODO: there are a lot from here
    // 1. find a way na ma add ang equals sa ops while retaining it's unique feat
    // 2. i sagol ang stack sa initial result and sa equals if pwede
    // 3. (if input is empty) dapat ig enter sa number kay ma kita sa result ang inputted nums
    // 4. do something when (worst case) the ops button is clicked first
    // 5. lastly, modify the landscape orientation of calc using landscape qualifier
    // 6. GOODNIGHT!
    //kapoy mn,, balikon nlng tika kng naa nakoy gana ehe
    Button btnDivide, btnMultiply, btnPlus, btnMinus, btnEquals;
    Button[] btn_nums = new Button[11];
    Button[] btn_ops = new Button[4];
    Button btnC, btnHome, btnBS, btnPeriod;
    private static final Integer[] nums_ID = {
            R.id.one,
            R.id.two,
            R.id.three,
            R.id.four,
            R.id.five,
            R.id.six,
            R.id.seven,
            R.id.eight,
            R.id.nine,
            R.id.zero,
            R.id.doubleZero,
    };

    private static final Integer[] ops_ID = {
            R.id.divide,
            R.id.multiply,
            R.id.minus,
            R.id.plus,
            R.id.equals
    };
    List<Integer> numId = Arrays.asList(nums_ID);
    List<Integer> opId = Arrays.asList(ops_ID);
    TextView output;
    TextView result;
    Stack<Double> numbers = new Stack<>();
    Stack<Character> operators = new Stack<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        //assign all buttons by their respective findviewbyid
        //numbers first

        //for btns na array nums
        for (int i = 0; i<btn_nums.length; i++){
            btn_nums[i] = (Button) findViewById(nums_ID[i]);
            btn_nums[i].setOnClickListener(this);
        }

        //for btns na array ops
        for (int i = 0; i<btn_ops.length; i++){
            btn_ops[i] = (Button) findViewById(ops_ID[i]);
            btn_ops[i].setOnClickListener(this);
        }
        //planning to add at ops but equals is unique
        btnEquals = (Button) findViewById(ops_ID[4]);

        //excess buttons
        btnHome = (Button) findViewById(R.id.uli);
        btnC = (Button) findViewById(R.id.clear);
        btnBS = (Button) findViewById(R.id.backspace);
        btnPeriod = (Button) findViewById(R.id.period);

        //for textviews
        output = (TextView) findViewById(R.id.view);
        result = (TextView) findViewById(R.id.result);
        result.setText(null);

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String exp = output.getText().toString();

                for (int i = 0; i<exp.length(); i++){
                    char temp = exp.charAt(i);
                    StringBuilder num = new StringBuilder();
                    if (Character.isDigit(temp) || temp == '.'){
                        while (i < exp.length() && (Character.isDigit(exp.charAt(i)) || exp.charAt(i) == '.')) {
                            num.append(exp.charAt(i));
                            i++;
                        }
                        i--;
                        numbers.push(Double.parseDouble(num.toString()));
                    } else {
                        //assume na operators ang naa dire
                        while (!operators.isEmpty() && ( precedence(temp) <= precedence(operators.peek() ) ) ) {
                            try {
                                performOperation();
                            } catch (ArithmeticException e){
                                result.setText(e.getMessage());
                                return;
                            }
                        }
                        operators.push(temp);
                    }
                }
//                if (num.length() > 0) {
//                    numbers.push(Double.parseDouble(num.toString()));
//                }
                while (!operators.isEmpty()) {
                    try {
                        performOperation();
                    } catch (ArithmeticException e){
                        result.setText(e.getMessage());
                        return;
                    }
                }
                result.setText(Double.toString(numbers.pop()));
            }
        });

        //others all clear, clear, backspace
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(null);
                result.setText(null);
            }
        });
        btnBS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = String.valueOf(output.getText());
                if (temp.isEmpty()) return;
                temp = temp.substring(0, temp.length()-1);
                output.setText(temp);
            }
        });
        btnPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = String.valueOf(output.getText());

                if (hasPeriod(String.valueOf(output.getText()))){
                    if (temp.charAt(temp.length()-1) == '.') {
                        temp = temp.substring(0, temp.length()-1);
                    }
                    output.setText(temp);
                    return;
                }

                if ( isOp(temp.charAt(temp.length()-1)) ){
                    temp = temp + "0.";
                } else if (temp.charAt(temp.length()-1) == '.') {
                    temp = temp.substring(0, temp.length()-1);
                } else {
                    temp += btnPeriod.getText();
                }
                output.setText(temp);
                initialResult(String.valueOf(output.getText()));

            }
        });
    }

    private boolean hasPeriod(String valueOf) {
        String[] temps = valueOf.split("[+\\-x/]");
        if (temps[temps.length-1].contains(".")) return true;
        return false;
    }

    private boolean isOp(char c) {
        switch(c){
            case '+':
            case '-':
            case 'x':
            case '/':
                return true;
        }
        return false;
    }

    private int precedence(char op) {
        switch(op){
            case '+':
            case '-':
                return 1;
            case 'x':
            case '/':
                return 2;
        }
        return 0;
    }


    private void performOperation() throws ArithmeticException{

        if (numbers.size() < 2 || operators.isEmpty()){
            return;
        }

        double num2 = numbers.pop();
        double num1 = numbers.pop();
        char op = operators.pop();

        double res = 0;
        switch (op) {
            case '+':
                res =  num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case 'x':
                res = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
//                    result.setText("Math Error");
//                    return;
                    throw new ArithmeticException("Math Error");
                } else {
                    res = num1/num2;
                }
                break;
        }
        numbers.push(res);
    }

    public void initialResult(String expression){

        result = (TextView) findViewById(R.id.result);

        Stack<Double> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();

        StringBuilder num = new StringBuilder();

        //sugod last pag read
        //not using PEMDAS
        for (int i = expression.length()-1; i >= 0; i--){
            char temp = expression.charAt(i);
            if (Character.isDigit(temp) || temp == '.'){
                num.insert(0, expression.charAt(i));

            } else {
                if (num.length() != 0){
                    nums.push(Double.valueOf(num.toString()));
                    num.setLength(0);
                }
                //assume na operators ang naa dire
                ops.push(temp);
            }
        }

        //last number
        if (num.length() != 0){
            nums.push(Double.valueOf(num.toString()));
            num.setLength(0);
        }

        double res = 0;
        while (!ops.isEmpty()){

            double num1 = nums.pop();
            if (!nums.isEmpty()) {
                double num2 = nums.pop();
                char op = ops.pop();

                switch (op){
                    case '+':
                        res = num1 + num2;
                        break;
                    case '-':
                        res = num1 - num2;
                        break;
                    case 'x':
                        res = num1 * num2;
                        break;
                    case '/':
                        if(num2 == 0) {
                            result.setText("Math Error");
                            return;
                        }
                        res = num1/num2;
                        break;
                }
                nums.push(res);
            } else {
                res += num1;
            }
        }
        result.setText(Double.toString(res));
    }

    @Override
    public void onClick(View view) {
        if ( numId.contains( view.getId() ) ){
            output.setText(output.getText() + "" +  btn_nums[ numId.indexOf( view.getId() ) ].getText());
            initialResult(String.valueOf(output.getText()));
        }
        if ( opId.contains( view.getId() ) ){
            String temp = String.valueOf(output.getText());
            if (!temp.isEmpty()){
                if (isOp(output.getText().charAt(output.getText().length() - 1))) {
                    temp = temp.substring(0, temp.length() - 1) + btn_ops[opId.indexOf(view.getId())].getText();
                } else {
                    temp += btn_ops[opId.indexOf(view.getId())].getText();
                }
                output.setText(temp);
            }
        }
    }
}