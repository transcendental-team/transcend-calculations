package comchaowangcanada.httpsgithub.calculator;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;


import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[] btnNum = new Button[11];    //0-9 numer operand button and . button
    private Button[] btnCommand = new Button[9];  //9 operand button
    private EditText expressionText = null;
    private EditText resultText = null;
    private Button btnClear = null;
    private Button btnDelete = null;
    private Button btnEqual = null;
    private Button btnLeftBracket = null;
    private Button btnRightBracket = null;
    private String lastCommand = null;
    private boolean needClear;
    private double result;

    public MainActivity() {
        result = 0.0;
        needClear = false;
        lastCommand = "=";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        //initialize display
        expressionText = (EditText) findViewById(R.id.text1);
        resultText = (EditText) findViewById(R.id.text2);

        //link operator button
        btnCommand[0] = (Button) findViewById(R.id.plus_button);
        btnCommand[1] = (Button) findViewById(R.id.minus_button);
        btnCommand[2] = (Button) findViewById(R.id.produce_button);
        btnCommand[3] = (Button) findViewById(R.id.divide_button);
        btnCommand[4] = (Button) findViewById(R.id.sine_button);
        btnCommand[5] = (Button) findViewById(R.id.sinh_button);
        btnCommand[6] = (Button) findViewById(R.id.sqrt_button);
        btnCommand[7] = (Button) findViewById(R.id.log_button);
        btnCommand[8] = (Button) findViewById(R.id.power_button);

        //link number button
        btnNum[0] = (Button) findViewById(R.id.zero_button);
        btnNum[1] = (Button) findViewById(R.id.one_button);
        btnNum[2] = (Button) findViewById(R.id.two_button);
        btnNum[3] = (Button) findViewById(R.id.three_button);
        btnNum[4] = (Button) findViewById(R.id.four_button);
        btnNum[5] = (Button) findViewById(R.id.five_button);
        btnNum[6] = (Button) findViewById(R.id.six_button);
        btnNum[7] = (Button) findViewById(R.id.seven_button);
        btnNum[8] = (Button) findViewById(R.id.eight_button);
        btnNum[9] = (Button) findViewById(R.id.nine_button);
        btnNum[10] = (Button) findViewById(R.id.point_button);


        btnClear = (Button) findViewById(R.id.AC_button);
        btnDelete = (Button) findViewById(R.id.delete_button);
        btnEqual = (Button) findViewById(R.id.equal_button);
        btnLeftBracket = (Button) findViewById(R.id.leftBracket_button);
        btnRightBracket = (Button) findViewById(R.id.rightBracket_button);


        for (Button bc : btnCommand) {
            bc.setOnClickListener(this);
        }

        for (Button bn : btnNum) {
            bn.setOnClickListener(this);
        }

        btnDelete.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
        btnLeftBracket.setOnClickListener(this);
        btnRightBracket.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    public void onClick(View view) {
        Button btn = (Button) view;
        String expression = expressionText.getText().toString();
        if (needClear && (
                btn.getText().equals("0")
                        || btn.getText().equals("1")
                        || btn.getText().equals("2")
                        || btn.getText().equals("3")
                        || btn.getText().equals("4")
                        || btn.getText().equals("5")
                        || btn.getText().equals("6")
                        || btn.getText().equals("7")
                        || btn.getText().equals("8")
                        || btn.getText().equals("9")
                        || btn.getText().equals("."))
                || btn.getText().equals("error")) {
            expressionText.setText("");
            needClear = false;
        }

        if (btn.getText().equals("C")) {
            expressionText.setText("");
            resultText.setText("");
        }
        else if (btn.getText().equals("del")) {
            if (isEmpty(expression)) {
                expressionText.setText("");
                return;
            }
            else {
                expressionText.setText(expression.substring(0, expression.length() - 1));
            }
        }
        else if (btn.getText().equals("=")) {
            if (isEmpty(expression)) {
                return;
            }
            expression = expression.replaceAll("×", "*");
            expression = expression.replaceAll("÷", "/");
            expression = expression.replaceAll("√", "sqrt");
            resultText.setText(ExpressionParser.eval(expression));
            needClear = false;
        } else {
            expressionText.setText(expressionText.getText() + "" + btn.getText());
            needClear = false;
        }
        expressionText.setSelection(expressionText.getText().length());
    }


//        private String calculate (String exp){
//            Interpreter bsh = new Interpreter();
//            Number result = null;
//            try {
//                exp = filterExp(exp);
//                result = (Number)bsh.eval(exp);
//            } catch (EvalError e) {
//                e.printStackTrace();
//                needClear = true;
//                return "error";
//            }
//            exp = result.doubleValue()+"";
//            if(exp.endsWith(".0"))
//                exp = exp.substring(0, exp.indexOf(".0"));
//            return exp;
//        }

//    private String filterExp(String exp) {
//        String num[] = exp.split("");
//        String temp = null;
//        int begin = 0, end = 0;
//        for (int i = 1; i < num.length; i++) {
//            temp = num[i];
//            if (temp.matches("[+-/()*]")) {
//                if (temp.equals(".")) continue;
//                end = i - 1;
//                temp = exp.substring(begin, end);
//                if (temp.trim().length() > 0 && temp.indexOf(".") < 0)
//                    num[i - 1] = num[i - 1] + ".0";
//                begin = end + 1;
//            }
//        }
//        return Arrays.toString(num).replaceAll("[\\[\\], ]", "");
//    }

    private boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }


}
