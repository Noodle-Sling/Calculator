package my.calculator;

import java.math.BigDecimal;
import java.util.*;

public class SimpleCalculator {
		 
	    
	private enum Operator
    {
        ADD(1), SUBTRACT(2), MULTIPLY(3), DIVIDE(4);
        final int precedence;
        Operator(int p) { precedence = p; }
    }

    private static Map<String, Operator> ops = new HashMap<String, Operator>() {{
        put("+", Operator.ADD);
        put("-", Operator.SUBTRACT);
        put("*", Operator.MULTIPLY);
        put("/", Operator.DIVIDE);
    }};

    private static boolean isHigherPrec(String op, String sub)
    {
        return (ops.containsKey(sub) && ops.get(sub).precedence >= ops.get(op).precedence);
    }

    public static String postfix(String infix)
    {
        StringBuilder output = new StringBuilder();
        Deque<String> stack  = new LinkedList<>();

        for (String token : infix.split("\\s")) {
            // operator
            if (ops.containsKey(token)) {
                while ( ! stack.isEmpty() && isHigherPrec(token, stack.peek()))
                    output.append(stack.pop()).append(' ');
                stack.push(token);

            // left parenthesis
            } else if (token.equals("(")) {
                stack.push(token);

            // right parenthesis
            } else if (token.equals(")")) {
                while ( ! stack.peek().equals("("))
                    output.append(stack.pop()).append(' ');
                stack.pop();

            // digit
            } else {
                output.append(token).append(' ');
            }
        }

        while ( ! stack.isEmpty())
            output.append(stack.pop()).append(' ');

        return output.toString();
    }
	    
	String[] convertToArray(String str) {
		int count = 0;
		List<String> nums = new ArrayList<>();
		for(int i = 0; i < str.length(); i++) {
			if(!Character.isDigit(str.charAt(i)) || i == str.length()-1) {
				nums.add(str.substring(count,i+1));
				count = i+1;
			}
		}
		return nums.stream().toArray(String[]::new);
	}
	
	BigDecimal compute(String expr) throws
    		ArithmeticException,
    		EmptyStackException {
		Stack<BigDecimal> stack = new Stack<>();

		System.out.println(expr);
		System.out.println("Input\tOperation\tStack after");
		
		for (String token : expr.split("\\s+")) {
		    System.out.print(token + "\t");
		    switch (token) {
		        case "+":
		            System.out.print("Operate\t\t");
		            stack.push(stack.pop().add(stack.pop()));
		            break;
		        case "-":
		            System.out.print("Operate\t\t");
		            stack.push(stack.pop().negate().add(stack.pop()));
		            break;
		        case "*":
		            System.out.print("Operate\t\t");
		            stack.push(stack.pop().multiply(stack.pop()));
		            break;
		        case "/":
		            System.out.print("Operate\t\t");
		            BigDecimal divisor = stack.pop();
		            stack.push(stack.pop().divide(divisor));
		            break;
		        case "^":
		            System.out.print("Operate\t\t");
		            double exponent = stack.pop().doubleValue();
		            stack.push((stack.pop().pow((int)exponent)));
		            break;
		        default:
		            System.out.print("Push\t\t");
		            stack.push(new BigDecimal(Double.parseDouble(token)));
		            break;
		    }
		
		    System.out.println(stack);
		}
		
		return stack.pop();
		}
	    
	    
    BigDecimal solve(String str) {
    	
    	String prn = postfix(str);
    	BigDecimal result = compute(prn);
    	return result;
    }
}
