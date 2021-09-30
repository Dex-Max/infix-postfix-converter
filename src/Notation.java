/**
 * Utility class that converts and evaluates postfix and infix expressions
 * @author Randall Kim
 */
public class Notation {
    /**
     * Converts an infix expression to postfix
     * @param infix the infix expression to be converted
     * @return the converted postfix expression
     * @throws InvalidNotationFormatException if the infix expression is not valid
     */
    public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException{
        NotationStack<Character> operatorStack = new NotationStack<>();
        String postfixExpression = "";

        for(int i = 0; i < infix.length(); i++){
            char currentChar = infix.charAt(i);

            switch(currentChar){
                case ')':
                    try {
                        char operator = operatorStack.pop();

                        while (operator != '(') {
                            postfixExpression += operator;
                            operator = operatorStack.pop();
                        }
                    } catch (StackUnderflowException e){
                        throw new InvalidNotationFormatException();
                    }

                    break;
                case '(':
                case '^':
                    operatorStack.push(currentChar);
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    while(!operatorStack.isEmpty() && precedence(operatorStack.top()) < precedence(currentChar)){
                        postfixExpression += operatorStack.pop();
                    }

                    operatorStack.push(currentChar);

                    break;
                default:
                    postfixExpression += currentChar;
                    break;
            }
        }

        while(!operatorStack.isEmpty()){
            postfixExpression += operatorStack.pop();
        }

        return postfixExpression;
    }

    /**
     * Converts a postfix expression to infix
     * @param postfix the postfix expression to be converted
     * @return the converted infix expression
     * @throws InvalidNotationFormatException if the postfix expression is not valid
     */
    public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException{
        NotationStack<String> stack = new NotationStack<>();
        String infixExpression = "";

        for(int i = 0; i < postfix.length(); i++){
            char currentChar = postfix.charAt(i);

            if(Character.isDigit(currentChar)){
                stack.push(Character.toString(currentChar));
            } else {
                try {
                    String firstExp = stack.pop();
                    String operation = ("(" + stack.pop() + currentChar + firstExp + ")");
                    stack.push(operation);
                } catch(StackUnderflowException e){
                    throw new InvalidNotationFormatException();
                }
            }
        }

        if(stack.size() == 1){
            return stack.pop();
        } else {
            throw new InvalidNotationFormatException();
        }
    }

    /**
     * Evaluates a postfix expression
     * @param postfixExpr the postfix expression to be evaluated
     * @return the number that the expression evaluates to
     * @throws InvalidNotationFormatException if the postfix experssion is not valid
     */
    public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException{
        NotationStack<Double> stack = new NotationStack<>();
        String infixExpression = "";

        for(int i = 0; i < postfixExpr.length(); i++){
            char currentChar = postfixExpr.charAt(i);

            if(Character.isDigit(currentChar)){
                stack.push(Double.valueOf(Character.toString(currentChar)));
            } else {
                try {
                    double second = stack.pop();
                    double first = stack.pop();

                    switch(currentChar){
                        case '^':
                            stack.push(Math.pow(first, second));
                            break;
                        case '+':
                            stack.push(first + second);
                            break;
                        case '-':
                            stack.push(first - second);
                            break;
                        case '*':
                            stack.push(first * second);
                            break;
                        case '/':
                            stack.push(first / second);
                            break;
                    }
                } catch (StackUnderflowException e){
                    throw new InvalidNotationFormatException();
                }
            }
        }

        if(stack.size() == 1){
            return stack.pop();
        } else {
            throw new InvalidNotationFormatException();
        }
    }

    /**
     * Evaluates an infix expression
     * @param infix the infix expression to be evaluated
     * @return the number that the infix expression evaluates to
     */
    public static double evaluateInfixExpression(String infix){
        return evaluatePostfixExpression(convertInfixToPostfix(infix));
    }

    /**
     * Finds the precedence of an operator
     * @param operator the operator to find the precedence of
     * @return an integer between 1-4 that represents the precedence of an operator
     */
    private static int precedence(char operator){
        int precedence;

        switch(operator){
            case '+':
            case '-':
                precedence = 1;
                break;
            case '*':
            case '/':
                precedence = 2;
                break;
            case '^':
                precedence = 3;
                break;
            case '(':
                precedence = 4;
                break;
            default:
                precedence = -1;
                break;
        }

        return precedence;
    }
}