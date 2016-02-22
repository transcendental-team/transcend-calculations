package comchaowangcanada.httpsgithub.calculator;

/**
 * Created by Transcendental Team.
 * Author Daniel Thagard
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.LinkedList;

public class ExpressionParser {

        private static Queue<String> tokenQueue = new LinkedList<String>();    //A queue for tokens of expression
        private static Deque<String> opStack = new ArrayDeque<String>();        //A stack for operations
        private static Deque<String> valueStack = new ArrayDeque<String>();    //A stack for operands
        private static Deque<String> functionStack = new ArrayDeque<String>(); //A stack for transcendental functions

        /**
         * Evaluates infixExpression and returns resulting integer value
         *
         * @return result of infix expression
         */
        public static String eval(String infixExpression) {
            //remove tokens from previous expressions
            tokenQueue.clear();
            opStack.clear();
            valueStack.clear();
            functionStack.clear();

            tokenize(infixExpression);    //Convert String expression to Queue of string tokens
            return evaluateInfix(); //Evaluate expression using the token queue.
        }

        /**
         * Converts infix expression from String to a Queue of string tokens.
         *
         * @param infixExp
         */
        private static void tokenize(String infixExp) {
            for (int iter = 0; iter < infixExp.length(); iter++) {
                String current = infixExp.charAt(iter) + "";
                if (!current.equals(" ")) {        // Remove spaces from expression
                    //multi-digit double becomes one number. WON'T MATCH xE-4.
                    if (current.matches("[0-9[.]]")) {
                        while ((iter < (infixExp.length() - 1))
                                && (infixExp.charAt(iter + 1) + "").matches("[0-9E[.]]")) {
                            current = current + infixExp.charAt(++iter);
                        }
                    }
                    //multi letter tokens (transcendental functions) one token
                    else if (current.matches("[a-z]")) {
                        while ((iter < (infixExp.length() - 1))
                                && (infixExp.charAt(iter + 1) + "").matches("[a-z]")) {
                            current = current + infixExp.charAt(++iter);
                        }
                    }
                    tokenQueue.add(current);
                }
            }
            return;
        }//end tokenize()


        /**
         * Evaluates infix expression from queue of string infix tokens
         * @return result of infix expression
         */
        private static String evaluateInfix() {
            String numPreBracket = ""; //keep track if a number is before a left bracket
            double nextValue;
            double lastValue;

            //Classify the tokens as operators, functions or operands, and begin evaluating
            while (!tokenQueue.isEmpty()) {
                String token = tokenQueue.remove();
                //If token is a valid double
                if (token.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
                    if (!tokenQueue.isEmpty() && tokenQueue.peek().equals("(")) {
                        numPreBracket = token; //enable multiplication with brackets
                    } else {
                        valueStack.push(token);
                    }
                }
                //If token is a valid operator
                else if (token.matches("sin|log|sqrt|sinh")) {
                    if (!tokenQueue.isEmpty() & tokenQueue.peek().equals("(")) {
                        tokenQueue.remove();
                        opStack.push("@");
                    } else {
                        return "Invalid input. Functions must be enclosed in brackets.";
                    }
                    functionStack.push(token);
                } else if (token.matches("[(@]")) {
                    opStack.push(token);
                } else if (token.equals(")")) {
                    while (!opStack.isEmpty() && !opStack.peek().matches("[(@]")) {
                        applyOperation();    // apply operation until left bracket reached
                    }
                    if (opStack.isEmpty()) { //If there is not corresponding left bracket.
                        return "Invalid syntax: check your brackets and try again.";
                    }
                    String leftBracket = opStack.pop();    // remove corresponding left bracket from opStack
                    //if there was a function outside of the brackets
                    if (leftBracket.equals("@")) {
                        callFunction(functionStack.pop(), valueStack.pop());
                    }
                    //If there's a number directly before brackets with no * sign, multiply it.
                    else if (!numPreBracket.equals("")) {
                        lastValue = Double.parseDouble(valueStack.pop());
                        valueStack.push(Double.parseDouble(numPreBracket) * lastValue + "");
                        numPreBracket = "";
                    }
                    //If there's a number directly after then brackets with no * sign, multiply!
                    if (!tokenQueue.isEmpty()
                            && tokenQueue.peek().matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
                        nextValue = Double.parseDouble(tokenQueue.remove());
                        valueStack.push(Double.parseDouble(valueStack.pop()) * nextValue + "");
                    }
                    //If a left bracket follows, set so the values enclosed in brackets will be multiplied
                    else if (!tokenQueue.isEmpty()
                            && tokenQueue.peek().equals("(")) {
                        numPreBracket = valueStack.pop();
                    }

                } else if (token.matches("[*/%^+-]")) {        //token is an operator
                    while (!opStack.isEmpty() && testPrecedence(token)) {
                        if (valueStack.size() < 2) {
                            return "Invalid input: An operator must be between two operands.";
                        }
                        //apply operation using operands on opStack with greater or equal precedence than current token.
                        applyOperation();
                    }
                    opStack.push(token);
                } else {
                    return (token + " is not a valid expression");
                }
            }
            // apply any remaining operations
            while (!opStack.isEmpty()) {
                if (valueStack.size() < 2) {        //Missing operand
                    return "Invalid syntax!";
                } else if (opStack.peek().matches("[(@]")) { //There is a left bracket without matching right bracket.
                    return "Invalid syntax: check your brackets";
                }
                applyOperation();
            }
            if (valueStack.isEmpty()) {
                return "Invalid expression. Make sure you enter a number in your expression";
            }
            String result = valueStack.pop();
            if (result == null) {   // If no operands have been entered
                return "Invalid expression. Please try again.";
            } else if (!valueStack.isEmpty()) {
                return "Invalid expression. You may have forgotten to add an operator";
            } else if (!functionStack.isEmpty()) {
                return "Invalid expression. A function needs to be followed by brackets";
            }
            return result;
        }//End evaluateInfix()

        /**
         * Compares the precedence of current operator with that of the top operator in opStack.
         *
         * @param currOp the current operator
         * @return true if operator a's precedence is less than or equal to the precedence
         * of the top operator in opStack
         */
        private static boolean testPrecedence(String currOp) {
            String nextOp = opStack.peek();
            int precCurr = getPrecendence(currOp);
            int precNext = getPrecendence(nextOp);
            if (precCurr <= precNext) return true;
            return false;
        }//End testPrecedence

        /**
         * Gets the precedence of an operator
         *
         * @param operator
         * @return an integer value representing precedence
         */
        private static int getPrecendence(String operator) {
            if (operator.matches("[*/^%]"))
                return 2;
            else if (operator.matches("[+-]"))
                return 1;
            return 0;
        }//End getPrecedence

        /**
         * Pops the top two operands from the valueStack and computes them using the top operator
         * popped from the opStack.
         * Adds the output to the valueStack.
         */
        private static void applyOperation() {
            double b = Double.parseDouble(valueStack.pop());
            double a = Double.parseDouble(valueStack.pop());
            char operation = opStack.pop().charAt(0);
            switch (operation) {        //Compute operation based on top operation in opStack
                case '+':
                    valueStack.push(a + b + "");
                    break;
                case '-':
                    valueStack.push(a - b + "");
                    break;
                case '*':
                    valueStack.push(a * b + "");
                    break;
                case '/':
                    valueStack.push(a / b + "");
                    break;
                case '%':
                    valueStack.push(a % b + "");
                    break;
                //SWITCH TO Functions.pow(a, b)
                case '^':
                    valueStack.push(Math.pow(a, b) + "");
                    break;
            }
            return;
        }//End applyOperation


        public static void callFunction(String funcName, String strValue) {
            double value = Double.parseDouble(strValue);
            //WILL CHANGE Math.x() to Functions.x to call our functions
            if (funcName.equals("sin")) {
                valueStack.push(Math.sin(value) + "");
            } else if (funcName.equals("sqrt")) {
                valueStack.push(Math.sqrt(value) + "");
            } else if (funcName.equals("log")) {
                valueStack.push(Math.log(value) + "");
            } else if (funcName.equals("sinh")) {
                valueStack.push(Math.sinh(value) + "");
            }
        }//End call function
    }


