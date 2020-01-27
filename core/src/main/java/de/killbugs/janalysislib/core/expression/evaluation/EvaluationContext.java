package de.killbugs.janalysislib.core.expression.evaluation;

import java.util.HashMap;
import java.util.Map;

/**
 * Context of an evaluation. Used to store values of variables.
 *
 */
public class EvaluationContext {

	private final Map<String, Double> values = new HashMap<>();

	void addValue(String name, double value) {
		values.put(name, value);
	}

	/**
	 * Get the value of the variable with the given name.
	 * 
	 * @param name given name of the variable
	 * @return Returns the value of the variable.
	 * @throws UndefinedVariableException if the variable has not been defined.
	 */
	public double getValue(String name) {
		Double value = values.get(name);
		if (value == null) {
			throw new UndefinedVariableException(name);
		}
		return value;
	}

}
