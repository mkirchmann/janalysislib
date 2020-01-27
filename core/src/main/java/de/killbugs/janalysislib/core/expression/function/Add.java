package de.killbugs.janalysislib.core.expression.function;

import java.util.List;

import de.killbugs.janalysislib.core.expression.Constant;
import de.killbugs.janalysislib.core.expression.Expression;
import de.killbugs.janalysislib.core.expression.Variable;
import de.killbugs.janalysislib.core.expression.evaluation.EvaluationContext;
import de.killbugs.janalysislib.core.operator.Operator;

public class Add implements Expression {

	private final Expression a;
	private final Expression b;

	public Add(Expression theA, Expression theB) {
		this.a = theA;
		this.b = theB;
	}

	@Override
	public Expression integrate(Operator op) {
		return new Add(a.integrate(op), b.integrate(op));
	}

	@Override
	public Expression differentiate(Operator op) {
		if (a.contains(op.getVariable())) {
			if (b.contains(op.getVariable())) {
				return new Add(a.differentiate(op), b.differentiate(op));				
			}
			return a.differentiate(op);
		} else if (b.contains(op.getVariable())) {
			return b.differentiate(op);
		}
		return Constant.ZERO;
	}

	@Override
	public double evaluate(EvaluationContext context) {
		return a.evaluate(context) + b.evaluate(context);
	}

	@Override
	public boolean contains(Variable v) {
		return a.contains(v) || b.contains(v);
	}
	
	@Override
	public String toString() {
		return "(" + a.toString() + " + " + b.toString() + ")";
	}

}
