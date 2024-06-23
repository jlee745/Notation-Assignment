import java.util.HashMap;

/**
 * This Notation utility class is responsible for conversions 
 * between infix and postfix expressions as well as their evaluations.
 * @author Jaegyoon Lee
 */
class Notation {
    
    /**
     * No-arg constructor.
     */
    public Notation() {
        
    }
    
    /**
     * This method takes an infix expression 
     * and converts it to a postfix expression.
     * @param infix infix expression to be converted
     * @return postfix expression
     */
    public static String convertInfixToPostfix(String infix) {
        MyStack<String> operatorStack = new MyStack<>(infix.length());
        operatorStack.push("");
        
        MyQueue<String> postfixSolution = new MyQueue<>(infix.length());
        postfixSolution.enqueue("");
        
        HashMap<String, Integer> precedence = new HashMap<>();
        precedence.put("-", 1);
        precedence.put("+", 2);
        precedence.put("/", 3);
        precedence.put("*", 4);
            
        boolean isTopOperator = false, isHigherPrecedence;
        
        for (char c : infix.toCharArray()) {
                        
            String nextChar = String.valueOf(c);
            
            for (String operator : precedence.keySet()) {
                isTopOperator = operatorStack.peek().equals(operator);
                if (isTopOperator) {
                    break;
                }
            }

            try {
                isHigherPrecedence = precedence.get(operatorStack.peek()) 
                        >= precedence.get(nextChar);
            } catch (NullPointerException e) {
                isHigherPrecedence = false;
            }
            
            if (Character.isDigit(c)) {
                postfixSolution.enqueue(nextChar);
            } else if (c == '(') {
                operatorStack.push(nextChar);
            } else if (c == '-' || c == '+' || c == '/' || c == '*') {
                if (operatorStack.peek().equals("-") || 
                        operatorStack.peek().equals("+") ||
                        operatorStack.peek().equals("/") ||
                        operatorStack.peek().equals("*")) {
                    throw new InvalidNotationFormatException();
                }
                if (isTopOperator && isHigherPrecedence) {
                    postfixSolution.enqueue(operatorStack.pop());
                }
                operatorStack.push(nextChar);
            } else if (c == ')') {

                try {
                    while (!operatorStack.peek().equals("(")) {
                        postfixSolution.enqueue(operatorStack.pop());
                    }
                } catch (StackUnderflowException e) {
                    throw new InvalidNotationFormatException();
                }
                operatorStack.pop();
            }                        
        } 
        
        while (!operatorStack.isEmpty() && (isTopOperator)) {
            postfixSolution.enqueue(operatorStack.pop());
        }
        
        for (char c : postfixSolution.toString().toCharArray()) {
            if (c == '(') {
                throw new InvalidNotationFormatException();
            }
        }
                
        return postfixSolution.toString();
    }
    
    /**
     * This method takes a postfix expression 
     * and converts it to an infix expression.
     * @param postfix postfix expression to be converted
     * @return infix expression
     */
    public static String convertPostfixToInfix(String postfix) {
        MyStack<String> infixSolution = new MyStack<>(postfix.length());
        infixSolution.push("");
        
        for (char c : postfix.toCharArray()) {
            
            String nextChar = String.valueOf(c);
            
            if (Character.isDigit(c)) {
                infixSolution.push(nextChar);
            } else if (c == '-' || c == '+' || c == '/' || c == '*') {
                if (infixSolution.size() < 3) {
                    throw new InvalidNotationFormatException();
                }
                String first = infixSolution.pop(), second = infixSolution.pop();
                String result = '(' + second + c + first + ')';
                infixSolution.push(result);
            }
        }
        
        if (infixSolution.size() > 2) {
            throw new InvalidNotationFormatException();
        }
        
        return infixSolution.toString();
    }
    
    /**
     * This method takes a infix expression to find its result.
     * @param infixExpr infix expression to be evaluated
     * @return result value of infix expression
     */
    public static double evaluateInfixExpression(String infixExpr) {
        
        return evaluatePostfixExpression(convertInfixToPostfix(infixExpr));
    }
    
    /**
     * This method takes a postfix expression to find its result.
     * @param postfixExpr postfix expression to be evaluated
     * @return result value of infix expression
     */
    public static double evaluatePostfixExpression(String postfixExpr) {
        MyStack<Double> valueStack = new MyStack<>(postfixExpr.length());
        valueStack.push(Double.NaN);
        
        for (char c : postfixExpr.toCharArray()) {
                        
            if (Character.isDigit(c)) {
                valueStack.push(Double.parseDouble(String.valueOf(c)));
            } else if (c == '-' || c == '+' || c == '/' || c == '*') {
                if (valueStack.size() < 3) {
                    throw new InvalidNotationFormatException();
                }
                double first = valueStack.pop(), second = valueStack.pop();
                double result = (c == '-') ? second - first : 
                        (c == '+') ? second + first : 
                        (c == '/') ? second / first :
                        (c == '*') ? second * first : 0;
                valueStack.push(result);
            } 
            
        }
        
        if (valueStack.size() > 2) {
            throw new InvalidNotationFormatException();
        }
        
        return valueStack.peek();
    }
}