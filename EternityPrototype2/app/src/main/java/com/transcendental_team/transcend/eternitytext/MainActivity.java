package com.transcendental_team.transcend.eternitytext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        String title = "Main Menu";
    //    int groupId = Menu.NONE;
     //   int itemID = MENU_ITEM;
        int order = 103;
    //    menu.add(groupID, itemID, order, title);
        return true;
    }
    public void add(View view){
        EditText progressDisplay = (EditText)findViewById(R.id.progress_display);
        String input = progressDisplay.getText().toString();
        input = input + " + ";
        progressDisplay.setText(input);
        progressDisplay.setSelection(progressDisplay.getText().length());
    }
    public void subtract(View view){
        EditText progressDisplay = (EditText)findViewById(R.id.progress_display);
        String input = progressDisplay.getText().toString();
        input = input + " - ";
        progressDisplay.setText(input);
        progressDisplay.setSelection(progressDisplay.getText().length());
    }
    public void multiply(View view){
        EditText progressDisplay = (EditText)findViewById(R.id.progress_display);
        String input = progressDisplay.getText().toString();
        input = input + " * ";
        progressDisplay.setText(input);
        progressDisplay.setSelection(progressDisplay.getText().length());
    }
    public void divide(View view){
        EditText progressDisplay = (EditText)findViewById(R.id.progress_display);
        String input = progressDisplay.getText().toString();
        input = input + " /";
        progressDisplay.setText(input);
        progressDisplay.setSelection(progressDisplay.getText().length());
    }
    public void decimal(View view) {
        EditText progressDisplay = (EditText) findViewById(R.id.progress_display);
        String input = progressDisplay.getText().toString();
        input = input + ".";
        progressDisplay.setText(input);
        progressDisplay.setSelection(progressDisplay.getText().length());
    }
    public void leftBracket(View view){
        EditText progressDisplay = (EditText)findViewById(R.id.progress_display);
        String input = progressDisplay.getText().toString();
        input = input + " (";
        progressDisplay.setText(input);
        progressDisplay.setSelection(progressDisplay.getText().length());
    }
    public void rightBracket(View view){
        EditText progressDisplay = (EditText)findViewById(R.id.progress_display);
        String input = progressDisplay.getText().toString();
        input = input + " )";
        progressDisplay.setText(input);
        progressDisplay.setSelection(progressDisplay.getText().length());
    }

    public void sine(View view){
        EditText progressDisplay = (EditText)findViewById(R.id.progress_display);
        String input = progressDisplay.getText().toString();
        input = input + " sin(";
        progressDisplay.setText(input);
        progressDisplay.setSelection(progressDisplay.getText().length());

    }
    public void hypSine(View view){
        EditText progressDisplay = (EditText)findViewById(R.id.progress_display);
        String input = progressDisplay.getText().toString();
        input = input + " sinh( ";
        progressDisplay.setText(input);
        progressDisplay.setSelection(progressDisplay.getText().length());
    }

    public void log(View view){
        EditText progressDisplay = (EditText)findViewById(R.id.progress_display);
        String input = progressDisplay.getText().toString();
        input = input + " log(";
        progressDisplay.setText(input);
        progressDisplay.setSelection(progressDisplay.getText().length());
    }

    public void squareRoot(View view){
        EditText progressDisplay = (EditText)findViewById(R.id.progress_display);
        String input = progressDisplay.getText().toString();
        input = input + " sqrt(";
        progressDisplay.setText(input);
        progressDisplay.setSelection(progressDisplay.getText().length());
    }

    public void power(View view){
        EditText progressDisplay = (EditText)findViewById(R.id.progress_display);
        String input = progressDisplay.getText().toString();
        input = input + "10^(";
        progressDisplay.setText(input);
        progressDisplay.setSelection(progressDisplay.getText().length());
    }


    /**
     * Called when the '=' button is pressed
     * evaluates the input and prints the result to the result display
     * @param view
     */
    public void evaluateInput(View view){
        EditText et = (EditText)findViewById(R.id.progress_display);
        String input = et.getText().toString();
        TextView resultDisplay = (TextView) findViewById(R.id.result_display);
        result = ExpressionParser.eval(input);
        resultDisplay.setText(result);
    }
}
