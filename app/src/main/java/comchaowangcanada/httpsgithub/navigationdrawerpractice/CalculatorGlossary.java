package comchaowangcanada.httpsgithub.navigationdrawerpractice;

/**
 * Created by Chao on 23/02/2016.
 */
public class CalculatorGlossary {

     static final String[] TRANSCDENTALGLOSSARY
             = new String[]{"Symbolic sine function of argument in radian",
                              "Hyperbolic sine of argument in radians",
                              "natural logarithm ln(x) of value X",
                              "the exponential of 10 for value X",
                              "Square root.",
                              "History Display",
                              "Absolute value",
                              "euler's number",
                              "Input value is Degree or Radian",
                              "Factorial of value x"};

     static final int ExtraLargeTextSize = 55;
     static final int LargeTextSize = 45;
     static final int LargeMediumTextSize = 35;
     static final int MediumTextSize = 30;
     static final int MediumSmallTextSize = 25;
     static final int SmallTextSize = 20;

     static final int MemorySlotSize = 10;

     static BoundedArrayList<String> favoriteList = new BoundedArrayList<String>(10);
     static BoundedArrayList<String> historyList = new BoundedArrayList<String>(10);




}
