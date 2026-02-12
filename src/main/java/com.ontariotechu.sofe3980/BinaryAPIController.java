package com.ontariotechu.sofe3980;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class BinaryAPIController {

	@GetMapping("/add")
	public String calculate(
		@RequestParam(name="operand1", required=false, defaultValue="") String operand1,
		@RequestParam(name="operator", required=false, defaultValue="+") String operator,
		@RequestParam(name="operand2", required=false, defaultValue="") String operand2) {

		Binary number1 = new Binary(operand1);
		Binary number2 = new Binary(operand2);

		switch(operator) {
			case "+": return Binary.add(number1,number2).getValue();
			case "*": return Binary.multiply(number1,number2).getValue();
			case "&": return Binary.and(number1,number2).getValue();
			case "|": return Binary.or(number1,number2).getValue();
			default: return "Invalid Operator";
		}
	}
	
	@GetMapping("/add_json")
	public BinaryAPIResult calculateJSON(
		@RequestParam(name="operand1", required=false, defaultValue="") String operand1,
		@RequestParam(name="operator", required=false, defaultValue="+") String operator,
		@RequestParam(name="operand2", required=false, defaultValue="") String operand2) {

		Binary number1 = new Binary(operand1);
		Binary number2 = new Binary(operand2);
		Binary result;
		String operatorName;

		switch(operator) {
			case "+":
				result = Binary.add(number1,number2);
				operatorName = "add";
				break;
			case "*":
				result = Binary.multiply(number1,number2);
				operatorName = "multiply";
				break;
			case "&":
				result = Binary.and(number1,number2);
				operatorName = "and";
				break;
			case "|":
				result = Binary.or(number1,number2);
				operatorName = "or";
				break;
			default:
				result = new Binary("0");
				operatorName = "unknown";
		}

		return new BinaryAPIResult(number1, operatorName, number2, result);
	}

}