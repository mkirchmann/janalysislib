package de.killbugs.janalysislib.core.operator;

import de.killbugs.janalysislib.core.expression.Variable;

public class IntegrationOperator implements Operator {
	private final Variable variable;
	
	public IntegrationOperator(Variable theVariable) {
		this.variable = theVariable;
	}

	@Override
	public Variable getVariable() {
		return variable;
	}
}
