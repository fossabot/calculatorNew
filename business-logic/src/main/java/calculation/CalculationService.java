package calculation;

import java.math.BigDecimal;
import java.math.MathContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.CalculationException;
import exceptions.DivisionByZeroException;
import parser.CalculationObject;

public class CalculationService {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	public String getResult(CalculationObject calculationObject) throws CalculationException, DivisionByZeroException {
		
		BigDecimal result = new BigDecimal("0.0");
		BigDecimal operand1 = new BigDecimal(calculationObject.getOperand1());
		BigDecimal operand2 = new BigDecimal(calculationObject.getOperand2());
		String operator = calculationObject.getOperator();
		
		switch(operator) {
		case "+":
			result = addition(operand1, operand2);
			break;
		case "-":
			result = subtraction(operand1, operand2);
			break;
		case "*":
			result = multiplication(operand1, operand2);
			break;
		case "/":
			try {
				result = division(operand1, operand2);
			}
			catch (ArithmeticException e) {
				e.printStackTrace();
				LOGGER.error("Division by zero.", e);
				throw new DivisionByZeroException();
			}
			break;
		default:
			LOGGER.error("Operator is not valid.");
			throw new CalculationException();
		}
		String returnResult = outputToString(result);
		
		return returnResult;
	}
		
	public BigDecimal addition(BigDecimal operand1, BigDecimal operand2) {
		BigDecimal result = operand1.add(operand2);
		return result;
	}
	
	public BigDecimal subtraction(BigDecimal operand1, BigDecimal operand2) {
		BigDecimal result = operand1.subtract(operand2);
		return result;
	}
	
	public BigDecimal multiplication(BigDecimal operand1, BigDecimal operand2) {
		BigDecimal result = operand1.multiply(operand2);
		return result;
	}
	
	public BigDecimal division(BigDecimal operand1, BigDecimal operand2) {
		BigDecimal result = operand1.divide(operand2, MathContext.DECIMAL32);
		return result;
	}

	public String outputToString(BigDecimal result) {
		String string = "";
		int value = result.intValue();
		double res = result.doubleValue();
		
		if (res != value) {
			string = result.toString();
		}
		else {
			string = Integer.toString(value);
		}
		return string;
	}
}