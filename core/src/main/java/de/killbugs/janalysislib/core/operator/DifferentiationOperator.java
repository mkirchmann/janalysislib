package de.killbugs.janalysislib.core.operator;

import de.killbugs.janalysislib.core.expression.Variable;

public class DifferentiationOperator implements Operator {

	private final Variable variable;
	
	public DifferentiationOperator(Variable theVariable) {
		this.variable = theVariable;
	}

	@Override
	public Variable getVariable() {
		return variable;
	}

}
