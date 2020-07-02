//Last Updated 6-29-2020
//             7:24 PM
package Project_2;
import java.util.Stack;
import java.util.Scanner;
public class finalProject {
	
	public String solve(String input){
		//Number Stack
		final Stack<String> numStack = new Stack<>();
		
		//Stack for opStack(+ - * / )
		final Stack<Character> opStack = new Stack<>();
		for(int i = 0; i < input.length(); i ++){
			char a = input.charAt(i);
			//check to see if it is a num
			if(Character.isDigit(a)){
				//Entry is a digit
				int num = 0;
				while(Character.isDigit(a)) {
					num = num * 10 + (a - '0');
					i++;
					if(i < input.length()){
						a = input.charAt(i);}
					else{
						break;}
				}
				i --;
				//put the number into the stack
				numStack.push(Integer.toString(num));}
			else if(a == '('){
				opStack.push(a);
				}
			else if(a ==')') {
				while(opStack.peek() != '('){
					String output = performOperation(numStack, opStack);
					//put it back to the stack
					numStack.push(output);
				}
				opStack.pop();
			}
			else if(isOperator(a)){
				// if this operator has higher precedence then it will be put on top of the stack
				//otherwise it will keep popping from the stack
				while(!opStack.isEmpty() && precedence(a) <= precedence(opStack.peek())){
					String output = performOperation(numStack, opStack);
					//put it back on stack
					numStack.push(output);
				}
				opStack.push(a);
			}
		}
		while(!opStack.isEmpty()){
			String output = performOperation(numStack, opStack);
			//puts it back on the stack
			numStack.push(output);
			}
			return numStack.pop();
		}
	
		int precedence(char i) {
			// this is a comment.  lol jk
			switch(i){
			case '>':
			case '<':
			case '=':
				return 1;
			case '+':
			case '-':
				return 2;
			case '*':
			case '/':
			case '%':
				return 3;
			case '^':
				return 4;
		}
			return -1;
		}
		
		public String performOperation(Stack<String> nums, Stack<Character> opStack) {
			int a = Integer.parseInt(nums.pop());
			int b = Integer.parseInt(nums.pop());
			char operation = opStack.pop();
			switch (operation) {
				case '<':
					return (a > b) ? "1" : "0";
				case '>':
					return (a < b) ? "1" : "0";
				case '=':
					return (a == b) ? "1" : "0";
				case '+':
					return Integer.toString(a + b);
				case '-':
					return Integer.toString(b - a);
				case '*':
					return Integer.toString(a * b);
				case '/':
					if (a == 0) { return ("You can not divide by zero my friend"); }
					return Integer.toString(b / a);
				case '%':
					return Integer.toString(b % a);
				case '^':
					return Integer.toString((int) Math.pow(b, a));
					
			}
			return Integer.toString(0);
		}
	    public boolean isOperator(char c){
	        return (c == '+'||c == '-'||c == '/'||c == '*'||c == '^' || c == '>' || c == '<' || c == '=' || c == '%');
	    }
	    public static String replaceOps(String problem){
	    	for(int x = 0; x < problem.length(); x ++){
				if(problem.charAt(x) == '<'){
					if(problem.charAt(x + 1) == '='){
						problem = problem.substring(0,x) + problem.substring(x + 1);
						
					}
				}
				if(problem.charAt(x) == '>'){
					if(problem.charAt(x + 1) == '='){
						problem = problem.substring(0,x) + problem.substring(x + 1);
						problem = problem.replace('=', '@');
					}
				}
				if(problem.charAt(x) == '!'){
					if(problem.charAt(x + 1) == '='){
						problem = problem.substring(0,x) + problem.substring(x + 1);
					}
				}
			}
	    	return problem;
	    }
	    
		public static void main(String[] args) {
			finalProject i = new finalProject();
			Scanner scnr = new Scanner(System.in);
			System.out.println("What would you like to be solved bro? ");
			String problem = scnr.nextLine();
			problem = replaceOps(problem);
			System.out.println(problem);
			System.out.println(i.solve(problem));
		}
}
