package comchaowangcanada.httpsgithub.calculator;

/**
 * Created by Transcendental Team on 23/02/2016.
 * Author: Chao Wang
 */
public class CalculatorGlossary {

     static final String[] TRANSCDENTALGLOSSARY
             = new String[]{"Symbolic sine function of argument in radian or in degree",
                              "Hyperbolic sine function of argument in radian",
                              "natural logarithm ln(x) of value x",
                              "the power of 10 for value x",
                              "Square root of argument as positive integer",
                              "History Display",
                              "Absolute value",
                              "euler's number",
                              "Input value is Degree or Radian",};


    static final String[] helpInformation
            = new String[]{"Glossary",
                            "Absolute Value - The non-negative value of a number.",
                            "Euler’s Number ( e ) - Mathematical constant derived from: lim((1+1/n)^n)",
                            "Hyperbolic Sine ( sinh ) - Defined as x, such that (e^x - e^(-x)) / 2",
                            "Logarithm - The power in which a number is to be raised to get another number.",
                            "MacLaurin Series - A Taylor series expansion about 0.",
                            "Newton’s Iteration - An algorithm to compute the square root of a number.",
                            "pi (π) - Mathematical constant given by the ratio of a circle’s circumference to its diameter.",
                            "Sine ( sin ) - A trigonometric function. In regards to a right triangle, it is the ratio of the length opposite of the angle and the hypotenuse.",
                            "Square Root - A number x such that x^2 = a",
                            "Taylor Series - The infinite sum calculated from a function’s derivatives at a single point.",
                            "Transcendental Function - A function that does not satisfy a polynomial equation.",
                            "Transcendental Number - A number that is not algebraic.",
                            "Features",
                            "* Quick add to favorite list ",
                            "* Editable Expression Text ",
                            "* Auto Size Expression Text ",
                            "* Memory Slot to save expression result: short press to add, long press to remove. ",
                            "* History key \"ANS\" to quickly access history ",
                            "* Favorite List Management ",
                            "* History List Management "};

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
