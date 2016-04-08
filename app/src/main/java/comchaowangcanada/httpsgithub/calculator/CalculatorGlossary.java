package comchaowangcanada.httpsgithub.calculator;

/**
 * Created by Transcendental Team on 23/02/2016.
 * Author: Chao Wang
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
            "Input value is Degree or Radian",};

    static final int ExtraLargeTextSize = 55;
    static final int LargeTextSize = 45;
    static final int LargeMediumTextSize = 35;
    static final int MediumTextSize = 30;
    static final int MediumSmallTextSize = 25;
    static final int SmallTextSize = 20;

    static final int MemorySlotSize = 10;

    static BoundedArrayList<String> favoriteList = new BoundedArrayList<String>(MemorySlotSize);
    static BoundedArrayList<String> historyList = new BoundedArrayList<String>(MemorySlotSize);




}
