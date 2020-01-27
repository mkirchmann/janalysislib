package de.killbugs.janalysislib.core.expression;

import java.util.List;

import de.killbugs.janalysislib.core.expression.evaluation.EvaluationContext;
import de.killbugs.janalysislib.core.expression.function.Add;
import de.killbugs.janalysislib.core.expression.function.Multiply;
import de.killbugs.janalysislib.core.expression.function.Pow;
import de.killbugs.janalysislib.core.operator.Operator;

public class Variable implements Expression {
	private final String name;

	public Variable(String theName) {
		this.name=theName;
	}
	
	@Override
	public Expression integrate(Operator op) {
		if (equals(op.getVariable())) {
			return new Add(new Multiply(Constant.HALF,new Pow(this,Constant.TWO)),Constant.C);
		} else {
			return new Add(new Multiply(this,op.getVariable()),Constant.C);	
		}
	}

	@Override
	public Expression differentiate(Operator op) {
		if (equals(op.getVariable())) {
			return Constant.ONE;
		}
		return Constant.ZERO;
	}

	@Override
	public double evaluate(EvaluationContext context) {
		return context.getValue(name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Variable other = (Variable) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public boolean contains(Variable v) {
		return equals(v);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	
}
