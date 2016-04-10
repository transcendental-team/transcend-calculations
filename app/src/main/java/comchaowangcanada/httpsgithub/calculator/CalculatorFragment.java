package comchaowangcanada.httpsgithub.calculator;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


import java.util.Arrays;

/**
 * Created by Transcendental Team on 23/02/2016.
 * Author: Chao Wang
 * Chao Wang  24/03/2016  Enhance the GUI to have new look, and additional buttons
 * Chao Wang  27/03/2016  Migrate from MainActivity to Calculator Fragment
 * Chao Wang  06/04/2016  Modify the button, replace factorial button to Memory button.
 * Chao Wang  09/04/2016  Enable DEG button, allow user input to be degree or radian
 */

public class CalculatorFragment extends Fragment implements View.OnClickListener {

    private Button[] btnNum = new Button[11];    //0-9 numer operand button and . button
    private Button[] btnArithmetic = new Button[4];  //4 arithmetic operand button
    private Button[] btnTranscendental = new Button[5];  //4 arithmetic operand button
    private TextView drgText  = null;
    private TextView mText  = null;
    private EditText expressionText = null;
    private TextView resultText = null;
    private Button btnClear = null;
    private Button btnDelete = null;
    private Button btnEqual = null;
    private Button btnLeftBracket = null;
    private Button btnRightBracket = null;
    private Button btnAbsolute = null;
    private Button btnHistory = null;
    private Button btnDegRad = null;
    private Button btnPi = null;
    private Button btnEuler = null;
    private Button btnMemory = null;
    private Button btnFraction = null;
    private Button btnPercentage = null;
    private Button btnSign = null;
    private CheckBox chkFavorite = null;
    private boolean needClear;
    boolean isResultString;
    boolean isRadians ;
    private int ansCount;
    private String memorySlot;


    public CalculatorFragment() {
        needClear = false;
        isResultString = false;
        isRadians = true;
        ansCount = 0;
        memorySlot = "";
    }

    public void setResultText (String str){

        resultText.setText(str);
    }

    public void setExpressionText (String str){

        expressionText.setText(str);
    }

    public void setExpressionTextSize (float size){

        expressionText.setTextSize(size);
    }

    public void setChkFavorite (boolean value){

        chkFavorite.setChecked(value);
    }


    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_calculator, container, false);


        //initialize display
        drgText = (TextView) rootview.findViewById(R.id.DRG_Text);
        mText = (TextView) rootview.findViewById(R.id.memory_Text);
        expressionText = (EditText) rootview.findViewById(R.id.text1);
        resultText = (TextView) rootview.findViewById(R.id.text2);

        //link arithmetic operator button
        btnArithmetic[0] = (Button) rootview.findViewById(R.id.plus_button);
        btnArithmetic[1] = (Button) rootview.findViewById(R.id.minus_button);
        btnArithmetic[2] = (Button) rootview.findViewById(R.id.product_button);
        btnArithmetic[3] = (Button) rootview.findViewById(R.id.divide_button);

        //link transcendental operator button
        btnTranscendental[0] = (Button) rootview.findViewById(R.id.sine_button);
        btnTranscendental[1] = (Button) rootview.findViewById(R.id.sinh_button);
        btnTranscendental[2] = (Button) rootview.findViewById(R.id.log_button);
        btnTranscendental[3] = (Button) rootview.findViewById(R.id.power_button);
        btnTranscendental[4] = (Button) rootview.findViewById(R.id.sqrt_button);

        //link number button
        btnNum[0] = (Button) rootview.findViewById(R.id.zero_button);
        btnNum[1] = (Button) rootview.findViewById(R.id.one_button);
        btnNum[2] = (Button) rootview.findViewById(R.id.two_button);
        btnNum[3] = (Button) rootview.findViewById(R.id.three_button);
        btnNum[4] = (Button) rootview.findViewById(R.id.four_button);
        btnNum[5] = (Button) rootview.findViewById(R.id.five_button);
        btnNum[6] = (Button) rootview.findViewById(R.id.six_button);
        btnNum[7] = (Button) rootview.findViewById(R.id.seven_button);
        btnNum[8] = (Button) rootview.findViewById(R.id.eight_button);
        btnNum[9] = (Button) rootview.findViewById(R.id.nine_button);
        btnNum[10] = (Button) rootview.findViewById(R.id.point_button);


        btnClear = (Button) rootview.findViewById(R.id.AC_button);
        btnDelete = (Button) rootview.findViewById(R.id.delete_button);
        btnEqual = (Button) rootview.findViewById(R.id.equal_button);
        btnLeftBracket = (Button) rootview.findViewById(R.id.leftBracket_button);
        btnRightBracket = (Button) rootview.findViewById(R.id.rightBracket_button);
        btnPercentage = (Button) rootview.findViewById(R.id.percentage_button);
        btnAbsolute = (Button) rootview.findViewById(R.id.abs_button);
        btnHistory = (Button) rootview.findViewById(R.id.history_button);
        btnDegRad = (Button) rootview.findViewById(R.id.DRG_button);
        btnPi = (Button) rootview.findViewById(R.id.pi_button);
        btnEuler = (Button) rootview.findViewById(R.id.euler_button);
        btnMemory = (Button) rootview.findViewById(R.id.memory_button);
        btnFraction = (Button) rootview.findViewById(R.id.fraction_button);
        btnPercentage = (Button) rootview.findViewById(R.id.percentage_button);
        btnSign = (Button) rootview.findViewById(R.id.sign_button);

        chkFavorite = (CheckBox ) rootview.findViewById(R.id.chk_faorite);


        expressionText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.onTouchEvent(event);
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                return true;
            }
        });

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
                        if( bc.getText().toString() .equals(btn.getText().toString()))
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
        btnMemory.setOnClickListener(this);
        btnFraction.setOnClickListener(this);
        btnPercentage.setOnClickListener(this);
        btnSign.setOnClickListener(this);

        // this part could be deleted, as additional pop up message
        btnHistory.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Button btn = (Button) view;
                if (btnHistory.getText().toString().equals(btn.getText().toString()))
                    clicked(view,5 );
                return true;
            }
        });

        btnAbsolute.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Button btn = (Button) view;
                    if (btnAbsolute.getText().toString().equals(btn.getText().toString()))
                        clicked(view,6 );
                return true;
            }
        });

        btnEuler.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Button btn = (Button) view;
                if (btnEuler.getText().toString().equals(btn.getText().toString()))
                    clicked(view,7 );
                return true;
            }
        });

        btnDegRad.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Button btn = (Button) view;
                if (btnDegRad.getText().toString().equals(btn.getText().toString()))
                    clicked(view,8 );
                return true;
            }
        });

        // above part could be deleted, as additional pop up message

        // long click memory slot will remove the "M"
        btnMemory.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Button btn = (Button) view;
                if (btnMemory.getText().toString().equals(btn.getText().toString())){
                    memorySlot = "" ;
                    mText.setText("");
                }
                return true;
            }
        });

        // click on favorite checkbox, add to favorite list. Click again remove from list
        chkFavorite.setChecked(false);
        chkFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = isDuplicateInFavorite(expressionText.getText().toString() + " = " + resultText.getText().toString());
                if((expressionText.getText().toString().isEmpty()) || (resultText.getText().toString().isEmpty())
                        ||( resultText.getText().toString().matches(".*[a-z]+.*"))
                        || (  index > -1 &&  chkFavorite.isChecked()) ){
                    chkFavorite.setChecked(false);
                }
                else {
                    if (chkFavorite.isChecked()){
                        CalculatorGlossary.favoriteList.add(expressionText.getText().toString() + " = " + resultText.getText().toString()) ;
                    }
                    else{
                        CalculatorGlossary.favoriteList.remove(index);
                    }
                }
            }
        });

        return rootview;

    }


    public void clicked(View view, int index ){
        ChromeHelpPopup chromeHelpPopup =new ChromeHelpPopup(getActivity(), CalculatorGlossary.TRANSCDENTALGLOSSARY[index]);
        chromeHelpPopup.show(view);
    }


    public void onClick(View view) {
        //favorite check box validation
        Button btn = (Button) view;

        // uncheck favorite
        chkFavorite.setChecked(false);

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
        if (needClear && ( btn.getText().equals("0")
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
                        || btn.getText().equals("e")
                        || (btn.getText().equals("M") && (! memorySlot.isEmpty())))) {
            expressionText.setText("");
            resultText.setText("");
            expressionText.setTextSize(CalculatorGlossary.ExtraLargeTextSize);
            needClear = false;
        }
        // history display
        if( btn.getText().equals("ANS") && CalculatorGlossary.historyList.size() != 0){
            String str = CalculatorGlossary.historyList.get(ansCount % (CalculatorGlossary.historyList.size()));
            str = str.replaceAll("\\s+","");
            expressionText.setText(str.substring(0, str.indexOf('=')));
            resultText.setText(str.substring(str.indexOf('=') + 1, str.length()));
            expressionText.setSelection(expressionText.getText().toString().length());
            ansCount++;
        }
        else{
            ansCount = 0;
        }

        // clean out button
        if (btn.getText().equals("C")) {
            expressionText.setText("");
            expressionText.setTextSize(CalculatorGlossary.ExtraLargeTextSize);
            resultText.setText("");
            needClear = false;
        }
        //Switch Rad to Deg
        else if(btn.getText().toString().equals("DRG")) {
            isRadians = ! isRadians;
            if(isRadians) {
                drgText.setText("RAD");
            }
            else{
                drgText.setText("\t\t\t\tDEG");
            }
        }
        // deal with memory slot, "M" button
        // if press "M", and memory slot is not used.
        else if(btn.getText().equals("M")
                    && ( memorySlot.isEmpty()) ){
            // if resultText has value, save it to memory slot, else do nothing.
                if(! resultText.getText().toString().isEmpty()) {
                    mText.setText("M");
                    memorySlot = resultText.getText().toString();
            }
        }
        // delete button
        else if (btn.getText().equals("DEL")) {
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
            expression = expression.replace("×", "*");
            expression = expression.replace("÷", "/");
            expression = expression.replace("√", "sqrt");
            expression = replaceWithinBracket(expression, "%", "/100");
            expression = multiplyConsecutiveM(expression);
            expression = expression.replace("M", memorySlot);


            expressionText.setText(closeOpenBracket(expressionText.getText().toString()));
            expressionText.setSelection(expressionText.getText().length());

            expression = closeOpenBracket(expression);
            resultText.setText(ExpressionParser.eval(expression, isRadians) );
            // if return result is an error message
            if(  resultText.getText().toString().matches(".*[a-z]+.*")){
                resultText.setTextSize(CalculatorGlossary.SmallTextSize);
                isResultString = true;
            }
            else {
                CalculatorGlossary.historyList.add(expressionText.getText().toString() + " = " + resultText.getText().toString());
            }
            needClear = true;
        }
        // any other button than “=" "C", "del", "DRG",
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
                            || btn.getText().equals("abs"))) {
                int cursorPosition = expressionText.getSelectionStart();
                expressionText.setText(expressionText.getText().toString().substring(0, cursorPosition) + btn.getText().toString()
                        + "(" + expressionText.getText().toString().substring(cursorPosition, expressionText.getText().toString().length()));
                expressionText.setSelection(cursorPosition + btn.getText().toString().length() + 1);
            }
            // if result text has value, will add "(1/ " bring result from result text to expression test
            else if (!isEmpty(resultText.getText().toString()) &&(btn.getText().equals("1/x"))) {
                expressionText.setTextSize(CalculatorGlossary.LargeMediumTextSize);
                expressionText.setText("(" + removeUselessChar(btn.getText().toString()) + resultText.getText());
                expressionText.setSelection(expressionText.getText().length());
                resultText.setText("");
            }
            // if result text has no value, will add transcendental operator + ( |
            else if (isEmpty(resultText.getText().toString()) &&( btn.getText().equals("1/x"))) {
                int cursorPosition = expressionText.getSelectionStart();
                expressionText.setText(expressionText.getText().toString().substring(0, cursorPosition) + "("
                        + removeUselessChar(btn.getText().toString())
                        + expressionText.getText().toString().substring(cursorPosition, expressionText.getText().toString().length()));
                expressionText.setSelection(cursorPosition + removeUselessChar(btn.getText().toString()).length() + 1);
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
            else if (!isEmpty(resultText.getText().toString())
                        && ( btn.getText().equals("+")
                            || btn.getText().equals("-")   //    || btn.getText().equals("x!")
                            || btn.getText().equals("×")
                            || btn.getText().equals("÷")
                            || btn.getText().equals("%"))){
                expressionText.setText(resultText.getText() + removeUselessChar(btn.getText().toString()));
                expressionText.setTextSize(CalculatorGlossary.LargeMediumTextSize);
                expressionText.setSelection(expressionText.getText().length());
                resultText.setText("");
                needClear = false;
            }
            // if user enter any arithmetic operator or number, cursor moves the new item length
            else {
                int cursorPosition = expressionText.getSelectionStart();
                expressionText.setText(expressionText.getText().toString().substring(0, cursorPosition) + removeUselessChar(btn.getText().toString())
                        + expressionText.getText().toString().substring(cursorPosition, expressionText.getText().toString().length()));
                expressionText.setSelection(cursorPosition + removeUselessChar(btn.getText().toString()).length());
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
            while ((currentIndex != 0) && ((charArray[currentIndex-1] >= '0'
                                        && charArray[currentIndex-1] <= '9')
                                            || charArray[currentIndex-1] == '.'
                                            || charArray[currentIndex-1] == 'π'
                                            || charArray[currentIndex-1] == 'e' )){
                currentIndex --;
            }
            // at the  begining of expression, add (- sign
            if (currentIndex == 0){
                strManipulation = "(-" + strManipulation;
            }
            // already has the (- sign, remove to make it positive.
            else if (charArray[currentIndex-1] == '-' && charArray[currentIndex-2] == '('){
                strManipulation = strManipulation.substring(0, currentIndex-2)
                                    + strManipulation.substring(currentIndex, charArray.length);
            }
            // not at the begining of expression, keep the previous part, add (-, append the rest part.
            else {
                strManipulation = strManipulation.substring(0,currentIndex) + "(-"
                                    + strManipulation.substring(currentIndex , charArray.length );
            }
        }
        return strManipulation;
    }

    private String replaceWithinBracket(String expression, String target, String replacement){
        String strManipulation = expression;
        int targetIndex;
        while( (targetIndex = strManipulation.indexOf(target)) != -1){
            strManipulation = strManipulation.substring(0,targetIndex+1) + ")"
                    + strManipulation.substring(targetIndex+1 , strManipulation.length() );
            char cha = strManipulation.charAt(targetIndex);
            while ((targetIndex != 0) && (( cha  >= '0' && cha <= '9')
                                        || cha == '.'|| cha == 'π'|| cha == 'e'
                                        || cha == '%')){
                targetIndex --;
                cha = strManipulation.charAt(targetIndex);
            }
            // at the  begining of expression, add ( sign
            if (targetIndex == 0){
                strManipulation = "(" + strManipulation;
            }
            // not at the begining of expression, keep the previous part, add (-, append the rest part.
            else {
                strManipulation = strManipulation.substring(0,targetIndex+1) + "("
                        + strManipulation.substring(targetIndex+1 , strManipulation.length() );
            }
            strManipulation = strManipulation.replaceFirst(target, replacement);
        }
        return strManipulation;
    }

        private String removeUselessChar(String str){
            return str.replace("ANS", "").replace("x", "");
    }

    private String multiplyConsecutiveM(String str){
        String regex = "(?<=(M))(?=\\1)";
        String expression = "";
        for ( String temp : str.split(regex)) {
            expression += temp + "*";
        }
        return expression.substring(0,expression.length()-1 );
    }

    private boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }

    public int isDuplicateInFavorite(String str){
        for( int index = 0 ; index < CalculatorGlossary.favoriteList.size() ; index ++){
            if (str.equals(CalculatorGlossary.favoriteList.get(index)))
                return index;
        }
        return -1;
    }


}


