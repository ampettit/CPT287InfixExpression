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
			case '|':
				return 1;
			case '&':
				return 2;
			case '!':
			case '=':
				return 3;
			case '>':
			case '<':
			case '@':
			case '~':
				return 4;
			case '+':
			case '-':
				return 5;
			case '*':
			case '/':
			case '%':
				return 6;
			case '^':
				return 7;
		}
			return -1;
		}
		
		public String performOperation(Stack<String> nums, Stack<Character> opStack) {
			int a = Integer.parseInt(nums.pop());
			int b = Integer.parseInt(nums.pop());
			char operation = opStack.pop();
			switch (operation) {// 1 == True  0 == False
				case '&': // && operator
					return (a == b) ? "1" : "0";
				case '|': // || operator
					return (a == 1 || b == 1) ? "1" : "0";
				case '@': // <= operator
					return (a >= b) ? "1" : "0";
				case '~': // >= operator
					return (a <= b) ? "1" : "0";
				case '!': // != operator
					return (a != b) ? "1" : "0";
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
	        return (c == '+'||c == '-'||c == '/'||c == '*'||c == '^' || c == '>' || c == '<' || c == '=' || c == '%' || c == '@' || c == '~' || c == '!');
	    }
	    public static String replaceOps(String problem){
	    	problem = problem.replace("<=", "@");
			problem = problem.replace(">=", "~");
			problem = problem.replace("!=", "!");
			problem = problem.replace("==", "=");
			problem = problem.replace("&&", "&");
			problem = problem.replace("||", "|");
	    	return problem;
	    }
	    
		public static void main(String[] args) throws Exception {
			
			finalProject i = new finalProject();
			@SuppressWarnings("resource")
			Scanner scnr = new Scanner(System.in);
			String problem = "";
			System.out.println("What would you like to be solved bro?");
			problem = scnr.nextLine();
			try {
				while (1 != 0) {
					problem = replaceOps(problem);
					System.out.println(i.solve(problem));
					System.out.println("Totaly.");
					System.out.println("What would you like to be solved bro?");
					problem = scnr.nextLine();
				}
			}
			catch (Exception e) { System.out.println("Woah brah.  What are you trying to do?!"); }
		}
}
