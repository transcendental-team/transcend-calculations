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
    private Button btnAbsolute = null;
    private Button btnHistory  = null;
    private Button btnDegRad = null;
    private Button btnPi = null;
    private Button btnEuler = null;
    private Button btnFactorial = null;
    private Button btnFraction = null;
    private Button btnPercentage = null;
    private Button btnSign = null;
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
        btnArithmetic[2] = (Button) findViewById(R.id.product_button);
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
        btnPercentage = (Button) findViewById(R.id.percentage_button);
        btnAbsolute = (Button) findViewById(R.id.abs_button);
        btnHistory  = (Button) findViewById(R.id.history_button);
        btnDegRad = (Button) findViewById(R.id.deg_button);
        btnPi = (Button) findViewById(R.id.pi_button);
        btnEuler = (Button) findViewById(R.id.euler_button);
        btnFactorial = (Button) findViewById(R.id.factorial_button);
        btnFraction = (Button) findViewById(R.id.fraction_button);
        btnPercentage = (Button) findViewById(R.id.percentage_button);
        btnSign = (Button) findViewById(R.id.sign_button);

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
        btnAbsolute.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
        btnDegRad.setOnClickListener(this);
        btnPi.setOnClickListener(this);
        btnEuler.setOnClickListener(this);
        btnFactorial.setOnClickListener(this);
        btnFraction.setOnClickListener(this);
        btnPercentage.setOnClickListener(this);
        btnSign.setOnClickListener(this);
    }

    public void clicked(View view, int index ){
        ChromeHelpPopup chromeHelpPopup =new ChromeHelpPopup(MainActivity.this, CalculatorGlossary.TRANSCDENTALGLOSSARY[index]);
        chromeHelpPopup.show(view);
    }


    public void onClick(View view) {
        Button btn = (Button) view;

        // deal with error message clean out
        if(isResultString){
            resultText.setTextSize(CalculatorGlossary.LargeMediumTextSize);
            expressionText.setText("");
            resultText.setText("");
            isResultString = false;
        }
        // setSize(45)= getSize.135. difference is 90. setSize(65) = getSize .195
        // deal with autosize expression
        if (expressionText.length() > 10){
            if (  expressionText.getTextSize() >= 135 )
                expressionText.setTextSize(CalculatorGlossary.LargeTextSize);
            if (expressionText.length() > 13){
                expressionText.setTextSize(CalculatorGlossary.LargeMediumTextSize);
                    if (expressionText.length() > 17){
                            expressionText.setTextSize(CalculatorGlossary.MediumSmallTextSize);
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
                || btn.getText().equals(".")
                || btn.getText().equals("π")
                || btn.getText().equals("e"))) {
            expressionText.setText("");
            resultText.setText("");
            expressionText.setTextSize(CalculatorGlossary.ExtraLargeTextSize);
            needClear = false;
        }


        // clean out button
        if (btn.getText().equals("C")) {
            expressionText.setText("");
            expressionText.setTextSize(CalculatorGlossary.ExtraLargeTextSize);
            resultText.setText("");
            needClear = false;
        }
        // delete button
        else if (btn.getText().equals("del")) {
            // empty expression has nothing to delete
            if (isEmpty(expression)) {
                expressionText.setText("");
                expressionText.setTextSize(CalculatorGlossary.ExtraLargeTextSize);
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
            expression = expression.replaceAll("%", "/100");


            expressionText.setText(closeOpenBracket(expressionText.getText().toString()));
            expressionText.setSelection(expressionText.getText().length());

            expression = closeOpenBracket(expression);
            resultText.setText(ExpressionParser.eval(expression));
            // if return result is an error message
            if(  resultText.getText().toString().matches(".*[a-z]+.*")){
                resultText.setTextSize(CalculatorGlossary.SmallTextSize);
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
                            || btn.getText().equals("√")
                            || btn.getText().equals("abs"))) {
                expressionText.setTextSize(CalculatorGlossary.LargeMediumTextSize);
                expressionText.setText(btn.getText().toString() + "(" + resultText.getText());
                expressionText.setSelection(expressionText.getText().length());
                resultText.setText("");
            }
            // if result text has no value, will add transcendental operator + ( |
            else if(isEmpty(resultText.getText().toString()) &&(
                    btn.getText().equals("sin")
                            || btn.getText().equals("sinh")
                            || btn.getText().equals("log")
                            || btn.getText().equals("10^")
                            || btn.getText().equals("√")
                            || btn.getText().equals("abs"))){
                int cursorPosition = expressionText.getSelectionStart();
                expressionText.setText(expressionText.getText().toString().substring(0, cursorPosition) + btn.getText().toString()
                        + "(" + expressionText.getText().toString().substring(cursorPosition, expressionText.getText().toString().length()));
                expressionText.setSelection(cursorPosition + removeFractionX(btn.getText().toString()).length() + 1);
            }
            // if result text has value, will add "(1/ " bring result from result text to expression test
            else if (!isEmpty(resultText.getText().toString()) &&(btn.getText().equals("1/x"))) {
                expressionText.setTextSize(CalculatorGlossary.LargeMediumTextSize);
                expressionText.setText("(" + removeFractionX(btn.getText().toString()) + resultText.getText());
                expressionText.setSelection(expressionText.getText().length());
                resultText.setText("");
            }
            // if result text has no value, will add transcendental operator + ( |
            else if(isEmpty(resultText.getText().toString()) &&( btn.getText().equals("1/x"))){
                int cursorPosition = expressionText.getSelectionStart();
                expressionText.setText(expressionText.getText().toString().substring(0, cursorPosition) + "("
                            + removeFractionX(btn.getText().toString())
                            + expressionText.getText().toString().substring(cursorPosition, expressionText.getText().toString().length()));
                expressionText.setSelection(cursorPosition + removeFractionX(btn.getText().toString()).length() + 1);
            }
            // if result text has no value,  add +/- operator before recent value such as  -(88
            else if(isEmpty(resultText.getText().toString()) &&(
                             btn.getText().equals("+/-"))){
                int tempExpressionLength = expressionText.getText().length();
                int cursorPosition = expressionText.getSelectionStart();
                expressionText.setText(putNegativeSign(expressionText.getText().toString(), cursorPosition));
                if(expressionText.getText().length() > tempExpressionLength) {
                    expressionText.setSelection(cursorPosition + btn.getText().length() - 1);
                }
                if(expressionText.getText().length() < tempExpressionLength) {
                    expressionText.setSelection(cursorPosition - btn.getText().length() + 1);
                }
            }
            // if result text has  value, add +/- operator will negates the value from result. and clean out the ExpressionText
            else if(!isEmpty(resultText.getText().toString()) &&(
                            btn.getText().equals("+/-"))){
                String resultString = resultText.getText().toString();
                if (resultString.charAt(0) == '-'){
                    resultText.setText(resultString.substring(1,resultString.length()));
                }
                if (resultString.charAt(0) >= '0' && resultString.charAt(0) <= '9' ){
                    resultText.setText("-"+resultString);
                }
                expressionText.setText("");
            }
            // if result txt has value, and next input is operator,  bring result from result text to expression test, then add operator
            else if (!isEmpty(resultText.getText().toString()) && (
                    btn.getText().equals("+")
                            || btn.getText().equals("-")
                            || btn.getText().equals("×")
                            || btn.getText().equals("÷")
                            || btn.getText().equals("x!"))){
                expressionText.setText(resultText.getText() + removeFractionX(btn.getText().toString()));
                expressionText.setTextSize(CalculatorGlossary.LargeMediumTextSize);
                expressionText.setSelection(expressionText.getText().length());
                resultText.setText("");
                needClear = false;
            }
            // if user enter any arithmetic operator or number, cursor moves the new item length
            else {
                int cursorPosition = expressionText.getSelectionStart();
                expressionText.setText(expressionText.getText().toString().substring(0, cursorPosition) + removeFractionX(btn.getText().toString())
                        + expressionText.getText().toString().substring(cursorPosition, expressionText.getText().toString().length()));
                expressionText.setSelection(cursorPosition + removeFractionX(btn.getText().toString()).length());
            }
            needClear = false;
        }
    }

    private String closeOpenBracket(String str){
        int bracketDifference= countCharInString(str, '(') - countCharInString(str, ')');

        for (int i = 0; i < bracketDifference; i++)
            str += ')';

        return str;
    }

    private int countCharInString (String str, char cha){
        int distinct = 0;
        for (int i = 0; i < str.length(); i++){
            if(str.charAt(i) == cha)
                distinct++;
        }
        return distinct;
    }

    private String putNegativeSign(String str, int index){
        String strManipulation = str;
        int currentIndex = index;
        char[] charArray = strManipulation.toCharArray();
        if (charArray.length == 0){
            strManipulation = "(-" + strManipulation;
        }
        else{
            while ( (currentIndex != 0) && (charArray[currentIndex-1] >= '0' && charArray[currentIndex-1] <= '9') ){
                currentIndex --;
            }
            if (currentIndex == 0){
                strManipulation = "(-" + strManipulation;
            }
            else if (charArray[currentIndex-1] == '-' && charArray[currentIndex-2] == '('){
                strManipulation = strManipulation.substring(0, currentIndex-2) + strManipulation.substring(currentIndex, charArray.length);
            }
            else {
                strManipulation = strManipulation.substring(0,currentIndex) + "(-" + strManipulation.substring(currentIndex , charArray.length );
            }
        }
        return strManipulation;
    }

    private String removeFractionX(String str){
            return str.replace("x","");
    }

    private boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }


}
