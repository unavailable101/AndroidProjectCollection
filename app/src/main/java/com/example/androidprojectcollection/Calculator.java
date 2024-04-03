package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class Calculator extends AppCompatActivity{

    //numbers
    Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero, btnZero2;

    //operations
    Button btnDivide, btnMultiply, btnPlus, btnMinus, btnEquals;

    //clear, all clear, and backspace
    Button btnC, btnAC, btnBS, btnPeriod;
    private static final int[] nums_ID = {
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

    private static final int[] ops_ID = {
            R.id.divide,
            R.id.multiply,
            R.id.minus,
            R.id.plus,
            R.id.equals
    };
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
        btnOne = (Button) findViewById(nums_ID[0]);
        btnTwo = (Button) findViewById(nums_ID[1]);
        btnThree = (Button) findViewById(nums_ID[2]);
        btnFour = (Button) findViewById(nums_ID[3]);
        btnFive = (Button) findViewById(nums_ID[4]);
        btnSix = (Button) findViewById(nums_ID[5]);
        btnSeven = (Button) findViewById(nums_ID[6]);
        btnEight = (Button) findViewById(nums_ID[7]);
        btnNine = (Button) findViewById(nums_ID[8]);
        btnZero = (Button) findViewById(nums_ID[9]);
        btnZero2 = (Button) findViewById(nums_ID[10]);

        //operations
        btnDivide = (Button) findViewById(ops_ID[0]);
        btnMultiply = (Button) findViewById(ops_ID[1]);
        btnMinus = (Button) findViewById(ops_ID[2]);
        btnPlus = (Button) findViewById(ops_ID[3]);
        btnEquals = (Button) findViewById(ops_ID[4]);

        //excess buttons
        btnAC = (Button) findViewById(R.id.allClear);
        btnC = (Button) findViewById(R.id.clear);
        btnBS = (Button) findViewById(R.id.backspace);
        btnPeriod = (Button) findViewById(R.id.period);


        //for textviews
        output = (TextView) findViewById(R.id.view);
        result = (TextView) findViewById(R.id.result);
        result.setText(null);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText() + "" +  btnOne.getText());
                initialResult(String.valueOf(output.getText()));
            }
        });
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText()  + "" +  btnTwo.getText());
                initialResult(String.valueOf(output.getText()));
            }
        });
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText()  + "" +  btnThree.getText());
                initialResult(String.valueOf(output.getText()));
            }
        });
        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText() + "" +  btnFour.getText());
                initialResult(String.valueOf(output.getText()));
            }
        });
        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText()  + "" +  btnFive.getText());
                initialResult(String.valueOf(output.getText()));
            }
        });
        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText()  + "" +  btnSix.getText());
                initialResult(String.valueOf(output.getText()));
            }
        });
        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText()  + "" +  btnSeven.getText());
                initialResult(String.valueOf(output.getText()));
            }
        });
        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText() + "" +  btnEight.getText());
                initialResult(String.valueOf(output.getText()));
            }
        });
        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText()  + "" +  btnNine.getText());
                initialResult(String.valueOf(output.getText()));
            }
        });
        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText()  + "" +  btnZero.getText());
                initialResult(String.valueOf(output.getText()));
            }
        });
        btnZero2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText()  + "" +  btnZero2.getText());
                initialResult(String.valueOf(output.getText()));
            }
        });

        //ops buttons
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = String.valueOf(output.getText());
                if (isOp(output.getText().charAt(output.getText().length()-1))) {
                    temp = temp.substring(0, temp.length()-1) + btnDivide.getText();
                }else{
                    temp += btnDivide.getText();
                }
                output.setText(temp);
            }
        });
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = String.valueOf(output.getText());
                if (isOp(output.getText().charAt(output.getText().length()-1))) {
                    temp = temp.substring(0, temp.length()-1) + btnMultiply.getText();
                }else{
                    temp += btnMultiply.getText();
                }
                output.setText(temp);
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = String.valueOf(output.getText());
                if (isOp(output.getText().charAt(output.getText().length()-1))) {
                    temp = temp.substring(0, temp.length()-1) + btnMinus.getText();
                }else{
                    temp += btnMinus.getText();
                }
                output.setText(temp);
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = String.valueOf(output.getText());
                if (isOp(output.getText().charAt(output.getText().length()-1))) {
                    temp = temp.substring(0, temp.length()-1) + btnPlus.getText();
                }else{
                    temp += btnPlus.getText();
                }
                output.setText(temp);
            }
        });
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
        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(null);
                result.setText(null);
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
}