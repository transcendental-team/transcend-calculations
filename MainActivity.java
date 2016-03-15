package comchaowangcanada.httpsgithub.calculator;

import android.app.Activity;
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
import android.widget.TextView;


import java.util.Arrays;

import comchaowangcanada.httpsgithub.calculator.R;



public class MainActivity extends Activity implements View.OnClickListener {
    private Button[] btnNum = new Button[11];    //0-9 numer operand button and . button
    private Button[] btnArithmetic = new Button[4];  //4 arithmetic operand button
    private Button[] btnTranscendental = new Button[5];  //4 arithmetic operand button
    private EditText expressionText = null;
    private TextView resultText = null;
    private Button btnClear = null;
    private Button btnDelete = null;
    private Button btnEqual = null;
    private Button btnLeftBracket = null;
    private Button btnRightBracket = null;
    private String lastCommand = null;
    private boolean needClear;
    boolean isResultString ;
    private double result;

    public MainActivity() {
        result = 0.0;
        needClear = false;
        isResultString = false;
        lastCommand = "=";
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        //initialize display
        expressionText = (EditText) findViewById(R.id.text1);
        resultText = (TextView) findViewById(R.id.text2);

        //link arithmetic operator button
        btnArithmetic[0] = (Button) findViewById(R.id.plus_button);
        btnArithmetic[1] = (Button) findViewById(R.id.minus_button);
        btnArithmetic[2] = (Button) findViewById(R.id.produce_button);
        btnArithmetic[3] = (Button) findViewById(R.id.divide_button);

        //link transcendental operator button
        btnTranscendental[0] = (Button) findViewById(R.id.sine_button);
        btnTranscendental[1] = (Button) findViewById(R.id.sinh_button);
        btnTranscendental[2] = (Button) findViewById(R.id.log_button);
        btnTranscendental[3] = (Button) findViewById(R.id.power_button);
        btnTranscendental[4] = (Button) findViewById(R.id.sqrt_button);

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


        for (Button bc : btnArithmetic) {
            bc.setOnClickListener(this);
        }

        for (Button bc : btnTranscendental) {
            bc.setOnClickListener(this);

            bc.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Button btn= (Button) view;
                    for (Button bc : btnTranscendental){
                        if( bc.getText().toString() .equals( btn.getText().toString() ))
                            clicked(view, Arrays.asList(btnTranscendental).indexOf(bc));
                    }
                    return true;
                }
            });
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

    public void clicked(View view, int index ){
        ChromeHelpPopup chromeHelpPopup =new ChromeHelpPopup(MainActivity.this, CalculatorGlossary.TRANSCDENTALGLOSSARY[index]);
        chromeHelpPopup.show(view);
    }


    public void onClick(View view) {
        Button btn = (Button) view;

        // deal with error message clean out
        if(isResultString){
            resultText.setTextSize(30);
            expressionText.setText("");
            resultText.setText("");
            isResultString = false;
        }
        // setSize(45)= getSize.135. difference is 90. setSize(65) = getSize .195
        // deal with autosize expression
        if (expressionText.length() > 10){
            if (  expressionText.getTextSize() >= 135 )
                expressionText.setTextSize(45);
            if (expressionText.length() > 13){
                expressionText.setTextSize(35);
                    if (expressionText.length() > 17){
                            expressionText.setTextSize(25);
                        }
                }
        }

        // deal with has results, want to enter number sign to do new calculation
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
                || btn.getText().equals("."))) {
            expressionText.setText("");
            resultText.setText("");
            expressionText.setTextSize(55);
            needClear = false;
        }


        // clean out button
        if (btn.getText().equals("C")) {
            expressionText.setText("");
            expressionText.setTextSize(55);
            resultText.setText("");
            needClear = false;
        }
        // delete button
        else if (btn.getText().equals("del")) {
            // empty expression has nothing to delete
            if (isEmpty(expression)) {
                expressionText.setText("");
                expressionText.setTextSize(55);
                return;
            }// delete the one previous item before cursor.
            else {
                int cursorPosition = expressionText.getSelectionStart();
                if(cursorPosition > 0) {
                    expressionText.setText(expression.substring(0, cursorPosition - 1)
                                                + expression.substring(cursorPosition, expression.length()));
                    expressionText.setSelection(cursorPosition-1);
                }
            }
        }  // preprocess expression and pass expression message to parser class.
        else if (btn.getText().equals("=")) {
            if (isEmpty(expression)) {
                return;
            }
            expression = expression.replaceAll("×", "*");
            expression = expression.replaceAll("÷", "/");
            expression = expression.replaceAll("√", "sqrt");
            resultText.setText(ExpressionParser.eval(expression));
            if(  resultText.getText().toString().matches(".*[a-z]+.*")){
                resultText.setTextSize(20);
                isResultString = true;
            }
            needClear = true;
        }
        // any other button than “=" "C", "del"
        else {
            // if result text has value, will add transcendental sign + bring result from result text to expression test
            if (!isEmpty(resultText.getText().toString()) &&(
                    btn.getText().equals("sin")
                            || btn.getText().equals("sinh")
                            || btn.getText().equals("log")
                            || btn.getText().equals("10^")
                            || btn.getText().equals("√"))) {
                expressionText.setText(btn.getText() + "(" + resultText.getText());
                expressionText.setSelection(expressionText.getText().length());
                expressionText.setTextSize(35);
                resultText.setText("");
            }
            // if result text has no value, will add transcendental operator + ( | )
            else if(isEmpty(resultText.getText().toString()) &&(
                    btn.getText().equals("sin")
                            || btn.getText().equals("sinh")
                            || btn.getText().equals("log")
                            || btn.getText().equals("10^")
                            || btn.getText().equals("√"))){
                int cursorPosition = expressionText.getSelectionStart();
                expressionText.setText(expressionText.getText().toString().substring(0, cursorPosition) + btn.getText() + "()"
                        + expressionText.getText().toString().substring(cursorPosition, expressionText.getText().toString().length()));
                expressionText.setSelection(cursorPosition + btn.getText().length() + 1);
            }
            // if result txt has value, and next input is operator,  bring result from result text to expression test, then add operator
            else if (!isEmpty(resultText.getText().toString()) && (
                    btn.getText().equals("+")
                            || btn.getText().equals("-")
                            || btn.getText().equals("×")
                            || btn.getText().equals("÷"))){
                expressionText.setText(resultText.getText() + btn.getText().toString());
                expressionText.setTextSize(35);
                expressionText.setSelection(expressionText.getText().length());
                resultText.setText("");
                needClear = false;
            }
            // if user enter any arithmetic operator or number, cursor moves the new item length
            else {
                int cursorPosition = expressionText.getSelectionStart();
                expressionText.setText(expressionText.getText().toString().substring(0, cursorPosition) + btn.getText()
                        + expressionText.getText().toString().substring(cursorPosition, expressionText.getText().toString().length()));
                expressionText.setSelection(cursorPosition + btn.getText().length());
            }
            needClear = false;
        }
    }



    private boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }


}
