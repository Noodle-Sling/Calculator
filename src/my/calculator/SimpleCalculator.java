package my.calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

public class SimpleCalculator {
		 
	    
	private enum Operator
    {
        ADD(1), SUBTRACT(2), MULTIPLY(3), DIVIDE(4), EXP(5), TRIG(6);
        final int precedence;
        Operator(int p) { precedence = p; }
    }

    private static Map<String, Operator> ops = new HashMap<String, Operator>() {{
        put("+", Operator.ADD);
        put("-", Operator.SUBTRACT);
        put("*", Operator.MULTIPLY);
        put("/", Operator.DIVIDE);
        put("^", Operator.EXP);
        put("sin", Operator.TRIG);
        put("cos", Operator.TRIG);
        put("tan", Operator.TRIG);
        put("csc", Operator.TRIG);
        put("sec", Operator.TRIG);
        put("cot", Operator.TRIG);
    }};

    private static boolean isHigherPrec(String op, String sub)
    {
        return (ops.containsKey(sub) && ops.get(sub).precedence >= ops.get(op).precedence);
    }
    
    private static boolean isAlpha(String str) {
    	char[] ch = str.toCharArray();
    	for(char c : ch) {
    		if(!Character.isLetter(c)) {
    			return false;
    		}
    	}
    	return true;
    }

    public static String postfix(String infix)
    {
        StringBuilder output = new StringBuilder();
        Deque<String> stack  = new LinkedList<>();
        
        List <String> nums = new ArrayList<>();
        int count = 0;
        for(int i = 0; i < infix.length(); i++) {
        		if(Character.isDigit(infix.charAt(i)) && i == infix.length()-1) {
        			nums.add(infix.substring(i-count, i+1));
        		}
        		else if(i < infix.length()-1 && Character.isDigit(infix.charAt(i)) && Character.isLetter(infix.charAt(i+1))) {
        			nums.add(infix.substring(i-count, i+1) + " * ");
        			int k = 0;
	        		for(int j = i+1; j < infix.length(); j++) {
	        			if(!Character.isLetter(infix.charAt(j))) {
	        				break;
	        			}
	        			k++;
	        		}
	        		nums.add(infix.substring(i+1, k+i+1));
	        		i += k;
        			count = 0;
        		}
        		else if(Character.isLetter(infix.charAt(i))) {
	        		int k = 0;
	        		for(int j = i; j < infix.length(); j++) {
	        			if(!Character.isLetter(infix.charAt(j))) {
	        				break;
	        			}
	        			k++;
	        		}
	        		nums.add(infix.substring(i, i+k));
	        		i += k-1;
        		}
        		
        		else if(Character.isDigit(infix.charAt(i))) {
        			count++;
        		}
        		else {
        			nums.add(infix.substring(i-count, i) + " ");
        			nums.add(infix.substring(i, i+1) + " ");
        			count = 0;
        		}
        }
        for(int i = 0; i < nums.size(); i++) {
        		if(nums.get(i).equals(" ")) {
        			nums.remove(i);
        		}
        }
        String infix2 = "";
        for(int i = 0; i < nums.size(); i++) {
        		infix2 += nums.get(i) + " ";
        }
        System.out.println(infix2);

        for (String token : infix2.trim().split("\\s")) {
            
            if (ops.containsKey(token)) {
                while ( ! stack.isEmpty() && isHigherPrec(token, stack.peek()))
                    output.append(stack.pop()).append(' ');
                stack.push(token);

            
            } else if (token.equals("(")) {
                stack.push(token);

            
            } else if (token.equals(")")) {
                while ( ! stack.peek().equals("("))
                    output.append(stack.pop()).append(' ');
                stack.pop();

            
            } else {
                output.append(token).append(' ');
            }
        }

        while ( ! stack.isEmpty())
            output.append(stack.pop()).append(' ');

        return output.toString();
    }
    BigDecimal piCalc() {
    	int count = 1000000;
    	BigDecimal pi = BigDecimal.ZERO;
    	BigDecimal denominator = BigDecimal.ONE;
    	for(int i = 0; i < count; i++) {
    		if(i % 2 == 0) {
    			pi = pi.add(BigDecimal.ONE.divide(denominator, MathContext.DECIMAL64));
    		}
    		else {
    			pi = pi.subtract(BigDecimal.ONE.divide(denominator, MathContext.DECIMAL64));
    		}
    		denominator = denominator.add(new BigDecimal("2"));
    	}
    	pi = pi.multiply(new BigDecimal("4"));
    	return pi;
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
		
		for (String token : expr.trim().split("\\s+")) {
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
		            stack.push(stack.pop().pow((int)exponent));
		            break;
		        case "sin":
		        	System.out.print("Operate\t\t");
		        	stack.push(new BigDecimal(Math.sin(Math.toRadians(stack.pop().doubleValue()))));
		        	break;
		        case "cos":
		        	System.out.print("Operate\t\t");
		        	stack.push(new BigDecimal(Math.cos(Math.toRadians(stack.pop().doubleValue()))));
		        	break;
		        case "tan":
		        	System.out.print("Operate\t\t");
		        	stack.push(new BigDecimal(Math.tan(Math.toRadians(stack.pop().doubleValue()))));
		        	break;
		        case "csc":
		        	System.out.print("Operate\t\t");
		        	stack.push(new BigDecimal(1.0 / (Math.sin(Math.toRadians(stack.pop().doubleValue())))));
		        	break;
		        case "sec":
		        	System.out.print("Operate\t\t");
		        	stack.push(new BigDecimal(1.0 / (Math.cos(Math.toRadians(stack.pop().doubleValue())))));
		        	break;
		        case "cot":
		        	System.out.print("Operate\t\t");
		        	stack.push(new BigDecimal(1.0 / (Math.tan(Math.toRadians(stack.pop().doubleValue())))));
		        	break;
		        case "pi":
		        	System.out.print("Push\t\t");
		        	stack.push(piCalc().setScale(64, BigDecimal.ROUND_HALF_EVEN));
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
	
	public static BigDecimal generateRandom(BigDecimal min, BigDecimal max) {
	    BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
	    return randomBigDecimal.setScale(4,BigDecimal.ROUND_HALF_EVEN);
	}
	
	BigDecimal algebra(String str) {
		BigDecimal x1 = new BigDecimal("-10000000000000000000");
		BigDecimal x2 = new BigDecimal("10000000000000000000");
		BigDecimal x3 = BigDecimal.ZERO;
		String prn1 = str.replaceAll("x", x1.toPlainString());
		String prn2 = str.replaceAll("x", x2.toPlainString());
		String prn3 = str.replaceAll("x", x3.toPlainString());
		double dubfx1 = compute(prn1).doubleValue();
		double dubfx2 = compute(prn2).doubleValue();
		if(dubfx1 > 0) {
			x1 = new BigDecimal("10000000000000000000");
			x2 = new BigDecimal("-10000000000000000000");
		}
//		while(dubfx1 < 0) {
//			x1 = generateRandom(new BigDecimal(min).setScale(4, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(max).setScale(4, BigDecimal.ROUND_HALF_EVEN));
//			prn1 = str.replaceAll("x", x1.toPlainString());
//			dubfx1 = compute(prn1).doubleValue();
//		}
//		while(dubfx2 > 0) {
//			x2 = generateRandom(new BigDecimal(min).setScale(4, BigDecimal.ROUND_HALF_EVEN), new BigDecimal(max).setScale(4, BigDecimal.ROUND_HALF_EVEN));
//			prn2 = str.replaceAll("x", x2.toPlainString());
//			dubfx2 = compute(prn1).doubleValue();
//		}
		BigDecimal fx1 = new BigDecimal(dubfx1);
		BigDecimal fx2 = new BigDecimal(dubfx2);
		BigDecimal fx3 = BigDecimal.ZERO;
		while(Math.abs(x1.doubleValue()-x2.doubleValue()) > .000001) {
			x3 = x1.add(x2).divide(new BigDecimal("2")).setScale(10, BigDecimal.ROUND_HALF_EVEN);
			prn3 = str.replaceAll("x", x3.toPlainString());
			fx3 = compute(prn3);
			if(fx3.floatValue() >= 0) {
				x2 = x3;
			}
			if(fx3.floatValue() <= 0) {
				x1 = x3;
			}
			System.out.println(x1.toPlainString());
			System.out.println(x2.toPlainString());
			System.out.println(Math.abs(x1.doubleValue()-x2.doubleValue()));
		}
		return x1;
	}
	    
	
	
	    
    BigDecimal solve(String str) {
    		if(str.contains("=")) {
    			int equalIndex = str.indexOf("=");
    			String movedOver = str.substring(0, equalIndex) + " - ( " + str.substring(equalIndex+1, str.length()) + " )";
    			String prn = postfix(movedOver);
    			System.out.println(prn);
    			BigDecimal result = algebra(prn).setScale(6, BigDecimal.ROUND_HALF_EVEN);
    			return result;
    		}
    		else {
		    	String prn = postfix(str);
		    	BigDecimal result = compute(prn).setScale(4, BigDecimal.ROUND_HALF_EVEN);
		    	return result;
    		}
    }
}
