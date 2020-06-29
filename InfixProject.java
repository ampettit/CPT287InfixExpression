//Last Updated 6-29-2020
//             5:22pm
package Project_2;
import java.util.Stack;
import java.util.Scanner;
public class finalProject {
	
	public int solve(String input){
		//Number Stack
		final Stack<Integer> nums = new Stack<>();
		
		//Stack for Operators(+ - * / )
		final Stack<Character> operators = new Stack<>();
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
				nums.push(num);}
			else if(a == '('){
				operators.push(a);
				}
			else if(a ==')') {
				while(operators.peek() != '('){
					int output = performOperation(nums, operators);
					//put it back to the stack
					nums.push(output);
				}
				operators.pop();
			}
			else if(isOperator(a)){
				// if this operator has higher precedence then it will be put on top of the stack
				//otherwise it will keep popping from the stack
				while(!operators.isEmpty() && precedence(a) <= precedence(operators.peek())){
					int output = performOperation(nums, operators);
					//put it back on stack
					nums.push(output);
				}
				operators.push(a);
			}
		}
		while(!operators.isEmpty()){
			int output = performOperation(nums, operators);
			//puts it back on the stack
			nums.push(output);
			}
			return nums.pop();
		}
		
		int precedence(char i){
			switch(i){
				case'+':
				case'-':
					return 1;
				case'*':
				case'/':
					return 2;
				case'^':
					return 3;
			}
			return -1;
		}


		public int performOperation(Stack<Integer> nums, Stack<Character> operators) {
			int a = nums.pop();
			int b = nums.pop();
			char operation = operators.pop();
			switch (operation) {
				case '+':
					return a + b;
				case '-':
					return b - a;
				case '*':
					return a * b;
				case '/':
					if (a == 0)
						throw new
								UnsupportedOperationException("You can not divide by zero my friend");
					return b / a;
			}
			return 0;
		}
	    public boolean isOperator(char c){
	        return (c=='+'||c=='-'||c=='/'||c=='*'||c=='^');
	    }
		public static void main(String[] args) {
			finalProject i = new finalProject();
			Scanner scnr = new Scanner(System.in);
			System.out.println("What would you like to be solved bro? ");
			String problem = scnr.nextLine();
			System.out.println(i.solve(problem));
			
		}

}
