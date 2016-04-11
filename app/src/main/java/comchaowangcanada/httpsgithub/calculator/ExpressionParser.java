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
    private static Queue<String> tokenQueue = new LinkedList<String>();	//A queue for tokens of expression
    private static Deque<String> opStack = new ArrayDeque<String>();		//A stack for operations
    private static Deque<String> valueStack = new ArrayDeque<String>();	//A stack for operands
    private static Deque<String> functionStack = new ArrayDeque<String>(); //A stack for transcendental functions

    /**
     * Evaluates infixExpression and returns resulting integer value
     * @return result of infix expression
     */
    public static String eval(String infixExpression, boolean isRadian){
        //remove tokens from previous expressions
        tokenQueue.clear();
        opStack.clear();
        valueStack.clear();
        functionStack.clear();
        tokenize(infixExpression);	//Convert String expression to Queue of string tokens

        return evaluateInfix(isRadian); //Evaluate expression using the token queue.
    }

    /**
     * Converts infix expression from String to a Queue of string tokens.
     * @param infixExp
     */
    private static void tokenize(String infixExp){
    	/* The "-" is overloaded to mean both minus and negative. 
    	 * If the current character is "-", isNextNegative informs us
    	 * whether "-" will represent a minus (and be added as an independent token,
    	 * or a negative and be appended onto the following token..
    	 */
    	boolean isNextNegative = true;
    	// Iterate through string, grouping characters into tokens
        for (int iter = 0; iter < infixExp.length(); iter++){
            String current = infixExp.charAt(iter) + "";
            if (!current.equals(" ")){		// Remove spaces from expression
                // A "-" after a left bracket or at the beginning should be appended to the next value.
            	if (current.equals("-") && isNextNegative == true){
            		current = current + infixExp.charAt(++iter);
            	}
                //multi-digit double becomes one number. WON'T MATCH xE-4.
                if (current.matches("(-)?[0-9[.]]")){
                    while((iter < (infixExp.length() - 1))
                            &&(infixExp.charAt(iter+1) + "").matches("(-)?[0-9E[.]]")){
                        current = current + infixExp.charAt(++iter);
                        //Facilitate scientific notation E-x
                        if (current.substring(current.length() - 1).equals("E") &&
                        		(infixExp.charAt(iter + 1) == '-')){
                        	current = current + "-";
                        	iter++;
                        }
                    }
                }
                //multi letter tokens (transcendental functions) one token
                else if(current.matches("(-)?[a-z]") && !current.matches("(-)?e")){
                    while((iter < (infixExp.length() - 1))
                            && (infixExp.charAt(iter+1) + "").matches("[a-z]")){
                        current = current + infixExp.charAt(++iter);
                    }
                }                
                // If current is a left bracket, then a "-" that immediately follows is a negative sign. 
                if (current.equals("(")){
                	isNextNegative = true;
                }
                else{
                	isNextNegative = false;
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
    private static String evaluateInfix(boolean isRadian){
    	String token; //The current token removed from token queue.

        //Classify the tokens as operators, functions or operands, and begin evaluating
        while(!tokenQueue.isEmpty()){
            token = tokenQueue.remove();
            //If token is a valid double
            if (token.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")){
                if (!tokenQueue.isEmpty() && tokenQueue.peek().matches("sin|log|sqrt|sinh|abs|\\(|e|π")){
                    opStack.push("*"); //enable multiplication with brackets
                }
                valueStack.push(token);
            }
            //If token is Euler's number
            else if (token.matches("(-)?e")){
            	if (!tokenQueue.isEmpty() && tokenQueue.peek().matches("sin|log|sqrt|sinh|abs|\\(|e|π")){
                    opStack.push("*"); //enable multiplication with brackets
            	}
            	if (token.startsWith("-")){
                	valueStack.push(-Functions.E_NUMBER + "");
            	}
            	else{
            		valueStack.push(Functions.E_NUMBER + "");
            	}
            }
            //If token is Pi
            else if (token.equals("π")){
            	if (!tokenQueue.isEmpty() && tokenQueue.peek().matches("sin|log|sqrt|sinh|abs|\\(|e|π")){
                    opStack.push("*"); //enable multiplication with brackets
            	}
            	if (token.startsWith("-")){
                	valueStack.push(-Functions.PI + "");
            	}
            	else{
            		valueStack.push(Functions.PI + "");
            	}
            }
            //If token is a valid operator
            else if(token.matches("(-)?(sin|log|sqrt|sinh|abs)")){
                if (!tokenQueue.isEmpty() && tokenQueue.peek().equals("(")){
                    tokenQueue.remove();
                    opStack.push("@");
                }
                else{
                    return Error.funcCall;
                }
                functionStack.push(token);
            }
            else if (token.matches("(-)?[(@]")) {
                opStack.push(token);
            }
            else if (token.equals(")")){
                while (!opStack.isEmpty() && !opStack.peek().matches("(-)?[(@]")
                		&& valueStack.size() > 1){
                    applyOperation();	// apply operation until left bracket reached
                }
                if(opStack.isEmpty()){ //If there is not corresponding left bracket.
                    return Error.unevenBrackets;
                }
                String leftBracket = opStack.pop();	// remove corresponding left bracket from opStack
                //if there was a function outside of the brackets
                if (leftBracket.equals("@")){
                	if (!valueStack.isEmpty()){
                		callFunction(functionStack.pop(), valueStack.pop(), isRadian);
                	}
                	else {
                		return Error.funcCall;
                	}
                }
                else if (leftBracket.equals("-(")){
                	valueStack.push(-Double.parseDouble(valueStack.pop()) + "");
                }
                
                //If there's a number directly after then brackets with no * sign, multiply!
                if (!tokenQueue.isEmpty()
                      && !tokenQueue.peek().matches("[*/%^+-]|\\)")){
                	opStack.push("*");
                }
            }
            else if (token.matches("[*/%^+-]")){ 		//token is an operator
                while (!opStack.isEmpty() && testPrecedence(token)){
                    if (valueStack.size() < 2){
                        return Error.missingVal;
                    }
                    //apply operation using operands on opStack with greater or equal precedence than current token.
                    applyOperation();
                }
                opStack.push(token);
            }
            else{
                return (token + " is not a valid expression");
            }
        }
        // apply any remaining operations
        while (!opStack.isEmpty()){
            if (valueStack.size() < 2){		//Missing operand
                return Error.missingVal;
            }
            else if (opStack.peek().matches("[(@]")){ //There is a left bracket without matching right bracket.
                return Error.unevenBrackets;
            }
            applyOperation();
        }
        if(valueStack.isEmpty()){
            return Error.missingVal;
        }
        String result = valueStack.pop();
        result = verifyResult(result);

        return result;
    }//End evaluateInfix()


    /**
     *
     * @param resultTemp
     * @return the verified result, or a specific error message
     */
    private static String verifyResult(String resultTemp){
        //Catch invalid expressions
        if (resultTemp == null){   // If no operands have been entered
            return "Invalid expression.";
        }
        else if(resultTemp.matches("NaN")){
            return Error.undefined;
        }
        else if(resultTemp.matches("Infinity|-Infinity")){
            return Error.outOfRange;
        }
        else if(!valueStack.isEmpty()){
            return Error.missingOp;
        }
        else if(!functionStack.isEmpty()){
            return Error.funcCall;
        }
        //Return correct number of decimal places
        if ( resultTemp.indexOf('.') == resultTemp.length()-2 && resultTemp.charAt(resultTemp.length()-1) == '0'){
            resultTemp = resultTemp.substring(0, resultTemp.indexOf('.'));
        }
        else if ( resultTemp.length() > 13 && resultTemp.indexOf('E') == -1){
            resultTemp = resultTemp.substring(0,12);
        }
        else if ( resultTemp.indexOf('.') > 0 && resultTemp.length() > 13 && resultTemp.indexOf('E') > 0  ){
            resultTemp = resultTemp.substring(0,12)+resultTemp.substring(resultTemp.indexOf('E'),resultTemp.length());
        }
        return resultTemp; //Evaluate expression using the token queue.
    }

    /**
     * Compares the precedence of current operator with that of the top operator in opStack.
     * @param currOp the current operator
     * @return true if operator a's precedence is less than or equal to the precedence
     * of the top operator in opStack
     */
    private static boolean testPrecedence(String currOp){
        String nextOp = opStack.peek();
        int precCurr = getPrecendence(currOp);
        int precNext = getPrecendence(nextOp);
        if (precCurr <= precNext) return true;
        return false;
    }//End testPrecedence

    /**
     * Gets the precedence of an operator
     * @param operator
     * @return an integer value representing precedence
     */
    private static int getPrecendence(String operator){
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
    private static void applyOperation(){
        double b = Double.parseDouble(valueStack.pop());
        double a = Double.parseDouble(valueStack.pop());
        char operation = opStack.pop().charAt(0);
        switch (operation){		//Compute operation based on top operation in opStack
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
            	if (b == 0){
            		valueStack.push("NaN");
            	}
            	else{
            		valueStack.push(a / b + "");
            	}
                break;
            case '%':
                valueStack.push(a % b + "");
                break;
            //SWITCH TO Functions.pow(a, b)
            case '^':
                valueStack.push(Functions.pow10(b) + "");
                break;
        }
        return;
    }//End applyOperation

    /**
     * Calls a transcendental function.
     * @param funcName is the name of a transcendental function to be called
     * @param strValue is a string containing a floating point value to be evaluated
     */
    private static void callFunction(String funcName, String strValue, boolean isRadian){
    	// Avoid calling functions with Infinity, -Infinity, and NaN as inputs
    	if (strValue.matches("(-)?Infinity")){
            valueStack.push(strValue);
            return;
    	}
    	if (strValue.matches("NaN")){
            valueStack.push("NaN");
            return;
    	}
    	// Parse the input as a double
        double value = Double.parseDouble(strValue);
        // Call the necessary function
        if (funcName.matches("(-)?sin")){
            valueStack.push(Functions.sine(value, isRadian) + "");
        }
        else if (funcName.matches("(-)?sqrt")){
            valueStack.push(Functions.sqrt(value) + "");
        }
        else if (funcName.matches("(-)?log")){
            valueStack.push(Functions.ln(value) + "");
        }
        else if (funcName.matches("(-)?sinh")){
            valueStack.push(Functions.sinh(value) + "");
        }
        else if (funcName.matches("(-)?abs")){
        	valueStack.push(Functions.abs(value) + "");
        }
        // If there is a leading "-", multiply result by negative one.
        if (funcName.charAt(0) == '-'){
        	valueStack.push(-Double.parseDouble(valueStack.pop()) + "");
        }
    }//End call function

}
