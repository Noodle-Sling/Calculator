package my.calculator;

import java.math.BigDecimal;
import java.math.MathContext;

public class TriangleCalculator {
	
	public BigDecimal angA, angB, angC, sideA, sideB, sideC;
	
	TriangleCalculator(String inangA, String inangB, String inangC, String insideA, String insideB, String insideC) {
		angA = parseBigDecimal(inangA);
		angB = parseBigDecimal(inangB);
		angC = parseBigDecimal(inangC);
		sideA = parseBigDecimal(insideA);
		sideB = parseBigDecimal(insideB);
		sideC = parseBigDecimal(insideC);
	}
	
	BigDecimal parseBigDecimal(String strNumber) {
		   if (strNumber != null && strNumber.length() > 0) {
		       try {
		          return new BigDecimal(Double.parseDouble(strNumber));
		       } catch(Exception e) {
		          return new BigDecimal("-1");
		       }
		   }
		   else return new BigDecimal("0");
		}
	
	BigDecimal angleSubtraction(BigDecimal A, BigDecimal B, BigDecimal C) {
		if(A.signum() <= 0 && B.signum() > 0 && C.signum() > 0){
			return new BigDecimal("180").subtract(B.add(C));
		}
		return A;
	}
	
	public static BigDecimal sqrt(BigDecimal A, final int SCALE) {
	    BigDecimal x0 = BigDecimal.ZERO;
	    BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));
	    while(!x0.equals(x1)) {
	    	x0 = x1;
	    	x1 = x0.subtract(x0.pow(2).subtract(A).divide(x0.multiply(new BigDecimal("2")), SCALE, BigDecimal.ROUND_HALF_UP));
	    }
	    return x1;
	}
	
	BigDecimal pythagorean(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal A) {
		if(A.compareTo(new BigDecimal("90")) == 0) {
			if(a.signum() <= 0 && b.signum() > 0 && c.signum() > 0) {
				return sqrt(b.pow(2).add(c.pow(2)), 64);
			}
		}
		return a;
	}
	
	BigDecimal lawOfSinsSides(BigDecimal a, BigDecimal b, BigDecimal A, BigDecimal B) {
		if(a.signum() <= 0 && b.signum() > 0 && A.signum() > 0 && B.signum() > 0) {
			return b.divide(new BigDecimal(Math.sin(B.doubleValue())), MathContext.DECIMAL64).multiply(new BigDecimal(Math.sin(A.doubleValue())));
		}
		else return a;
	}
	
	BigDecimal lawOfSinsAngles(BigDecimal A, BigDecimal B, BigDecimal a, BigDecimal b) {
		if(A.signum() <= 0 && b.signum() > 0 && A.signum() > 0 && B.signum() > 0) {
			return new BigDecimal(Math.toDegrees(Math.asin((Math.sin(Math.toRadians(B.doubleValue()) * a.doubleValue()) / b.doubleValue()))));
		}
		else return A;
	}
	
	BigDecimal lawOfCosAngles(BigDecimal A, BigDecimal a, BigDecimal b, BigDecimal c) {
		if(A.signum() <= 0 && a.signum() > 0 && b.signum() > 0 && c.signum() > 0) {
			return new BigDecimal(Math.toDegrees(Math.acos((a.multiply(a).subtract(b.multiply(b).add(c.multiply(c)))).divide(b.multiply(c).multiply(new BigDecimal("-2")), MathContext.DECIMAL64).doubleValue())));
		}
		else return A;
	}
	
	BigDecimal lawOfCosSides(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal A) {
		if(a.signum() <= 0 && b.signum() > 0 && c.signum() > 0 && A.signum() > 0) {
			return sqrt(b.pow(2).add(c.pow(2).add(a.multiply(b).multiply(new BigDecimal(Math.cos(A.doubleValue()) * 2)))), 64);
		}
		else return a;
	}
	
	public void calculate() {
		for(int i = 0; i < 100; i++){
			angA = angleSubtraction(angA,angB,angC);
	        angB = angleSubtraction(angB,angA,angC);
	        angC = angleSubtraction(angC,angA,angB);
	        sideA = pythagorean(sideA,sideB,sideC,angA);
	        sideB = pythagorean(sideB,sideA,sideC,angB);
	        sideC = pythagorean(sideC,sideA,sideB,angC);
	        sideA = lawOfSinsSides(sideA, sideB, angA, angB);
	        sideA = lawOfSinsSides(sideA, sideC, angA, angC);
	        sideB = lawOfSinsSides(sideB, sideA, angB, angA);
	        sideB = lawOfSinsSides(sideB, sideC, angB, angC);
	        sideC = lawOfSinsSides(sideC, sideA, angC, angA);
	        sideC = lawOfSinsSides(sideC, sideB, angC, angB);
	        angA = lawOfSinsAngles(angA,angB,sideA,sideB);
	        angA = lawOfSinsAngles(angA,angC,sideA,sideC);
	        angB = lawOfSinsAngles(angB,angA,sideB,sideA);
	        angB = lawOfSinsAngles(angB,angC,sideB,sideC);
	        angC = lawOfSinsAngles(angC,angA,sideC,sideA);
	        angC = lawOfSinsAngles(angC,angB,sideC,sideB);
	        angA = lawOfCosAngles(angA,sideA,sideB,sideC);
	        angB = lawOfCosAngles(angB,sideB,sideC,sideA);
	        angC = lawOfCosAngles(angC,sideC,sideB,sideA);
	        sideA = lawOfCosSides(sideA,sideB,sideC,angA);
	        sideB = lawOfCosSides(sideB,sideA,sideC,angB);
	        sideC = lawOfCosSides(sideC,sideB,sideA,angC);
		}
		angA = angA.setScale(4, BigDecimal.ROUND_HALF_EVEN);
		angB = angB.setScale(4, BigDecimal.ROUND_HALF_EVEN);
		angC = angC.setScale(4, BigDecimal.ROUND_HALF_EVEN);
		sideA = sideA.setScale(4, BigDecimal.ROUND_HALF_EVEN);
		sideB = sideB.setScale(4, BigDecimal.ROUND_HALF_EVEN);
		sideC = sideC.setScale(4, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public BigDecimal getAngA() {
		return angA;
	}
	public BigDecimal getAngB() {
		return angB;
	}
	public BigDecimal getAngC() {
		return angC;
	}
	public BigDecimal getSideA() {
		return sideA;
	}
	public BigDecimal getSideB() {
		return sideB;
	}
	public BigDecimal getSideC() {
		return sideC;
	}
}
