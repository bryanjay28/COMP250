package A2PostedCode;

/*
 * Bryan Jay
 * 260738764
 * 
 */

import java.util.Stack;
import java.util.ArrayList;

public class Expression  {
	private ArrayList<String> tokenList;

	//  Constructor    
	/**
	 * The constructor takes in an expression as a string
	 * and tokenizes it (breaks it up into meaningful units)
	 * These tokens are then stored in an array list 'tokenList'.
	 */

	Expression(String expressionString) throws IllegalArgumentException{
		tokenList = new ArrayList<String>();
		StringBuilder token = new StringBuilder();

		//ADD YOUR CODE BELOW HERE
		//..

		int i, length = expressionString.length();
		for(i = 0; i < length; i++) {
			
			char value = expressionString.charAt(i);		
			token.append(value);
						
			if(isInteger(token.toString())) {

				int j = 0;
				while(isInteger(token.toString())) {
					token.append(expressionString.charAt(++i));
					j++;
				}
				token.deleteCharAt(j);
				tokenList.add(token.toString());

				token.delete(0, token.length());
				i--;
			
			} else if(token.charAt(0) == '+' || token.charAt(0) == '-') {
				token.append(expressionString.charAt(i+1));

				if(token.toString().equalsIgnoreCase("++")|| token.toString().equalsIgnoreCase("--")) {
					tokenList.add(token.toString());
					token.delete(0, 2);
					i++;
				} else {
					token.deleteCharAt(1);
					tokenList.add(token.toString());
					token.delete(0, 1);
				}
			} else if(token.charAt(0) == '/' || token.charAt(0) == '*' || token.charAt(0) == '(' || token.charAt(0) == ')' || token.charAt(0) == '[' || token.charAt(0) == ']') {

				tokenList.add(token.toString());
				token.delete(0, 1);
			} else if(token.charAt(0) == ' ') {
				token.delete(0, 1);
			}
			
		}

		//..
		//ADD YOUR CODE ABOVE HERE
	}

	/**
	 * This method evaluates the expression and returns the value of the expression
	 * Evaluation is done using 2 stack ADTs, operatorStack to store operators
	 * and valueStack to store values and intermediate results.
	 * - You must fill in code to evaluate an expression using 2 stacks
	 */
	public Integer eval(){
		Stack<String> operatorStack = new Stack<String>();
		Stack<Integer> valueStack = new Stack<Integer>();
		
		//ADD YOUR CODE BELOW HERE
		//..
		ArrayList<String> expressionList = new ArrayList<String>();
		expressionList = this.tokenList;
		
		int i, length = expressionList.size();
		Integer number1, number2, result;
		
		for(i = 0; i < length; i++) {
			
			String token = expressionList.get(i);
			if(isInteger(token)) {
				valueStack.push(Integer.parseInt(token));
				
			} else if(token.equalsIgnoreCase("/") || token.equalsIgnoreCase("*") || token.equalsIgnoreCase("+") || token.equalsIgnoreCase("-") || token.equalsIgnoreCase("++") || token.equalsIgnoreCase("--")) {
				operatorStack.push(token);
				
			} else if(token.equalsIgnoreCase(")")) {
				String operator;
				
				if(!operatorStack.isEmpty()) {
	
					operator = operatorStack.pop();
					switch(operator) {
					case "+":
						number1 = valueStack.pop();
						number2 = valueStack.pop();
						result = number1 + number2;
						valueStack.push(result);
						break;
						
					case "-":
						number1 = valueStack.pop();
						number2 = valueStack.pop();
						result = number2 - number1;
						valueStack.push(result);
						break;
						
					case "*":
						number1 = valueStack.pop();
						number2 = valueStack.pop();
						result = number1 * number2;
						valueStack.push(result);
						break;
						
					case "/":
						number1 = valueStack.pop();
						number2 = valueStack.pop();
						result = number2 / number1;
						valueStack.push(result);
						break;
							
					case "++":
						number1 = valueStack.pop();
						result = ++number1;
						valueStack.push(result);
						break;
						
					case "--":
						number1 = valueStack.pop();
						result = --number1;
						valueStack.push(result);
						break;
		
					}
				}
				
			} else if(token.equalsIgnoreCase("]")) {
				if(valueStack.peek() < 0) {
					number1 = valueStack.pop();
					result = number1 * -1;
					valueStack.push(result);
				}
				
			} else if(token.equalsIgnoreCase("(") || token.equalsIgnoreCase("[")) {
				continue;
			}			
		}
		int answer;
		
		answer = valueStack.pop();
		
		return answer;				
		//..
		//ADD YOUR CODE ABOVE HERE

	}

	//Helper methods
	/**
	 * Helper method to test if a string is an integer
	 * Returns true for strings of integers like "456"
	 * and false for string of non-integers like "+"
	 * - DO NOT EDIT THIS METHOD
	 */
	private boolean isInteger(String element){
		try{
			Integer.valueOf(element);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}

	/**
	 * Method to help print out the expression stored as a list in tokenList.
	 * - DO NOT EDIT THIS METHOD    
	 */

	@Override
	public String toString(){	
		String s = new String(); 
		for (String t : tokenList )
			s = s + "~"+  t;
		return s;		
	}

}

