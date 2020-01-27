package de.killbugs.janalysislib.core.expression.function;

import java.util.List;

import de.killbugs.janalysislib.core.expression.Constant;
import de.killbugs.janalysislib.core.expression.Expression;
import de.killbugs.janalysislib.core.expression.Variable;
import de.killbugs.janalysislib.core.expression.evaluation.EvaluationContext;
import de.killbugs.janalysislib.core.operator.Operator;

public class Pow implements Expression {

	private final Expression a;
	private final Expression b;

	public Pow(Expression theA, Expression theB) {
		this.a = theA;
		this.b = theB;
	}

	@Override
	public Expression integrate(Operator op) {
		if (a == Constant.E) {
			return new Multiply(this, b.differentiate(op));
		}
		if (a.equals(op.getVariable())) {
			if (b instanceof Constant) {
				if (b.equals(Constant.MINUS_ONE)) {
					return new Ln(a);
				}
				Expression bPlusOne = ((Constant)b).add(Constant.ONE);
				return new Multiply(new Pow(bPlusOne, Constant.MINUS_ONE),
						new Pow(a, bPlusOne));
			}
		}
		throw new RuntimeException("Not yet implemented.");
	}

	@Override
	public Expression differentiate(Operator op) {
		if (a == Constant.E) {
			return new Multiply(this, b.differentiate(op));
		}

		if (a.equals(op.getVariable())) {
			if (b instanceof Constant) {
				Expression newExponent = ((Constant)b).add(Constant.MINUS_ONE);
				if (newExponent.equals(Constant.ZERO)) {
					return Constant.ONE;
				}
				return new Multiply(b, new Pow(a, newExponent));
			}
		}
		throw new RuntimeException("Not yet implemented.");
	}

	@Override
	public double evaluate(EvaluationContext context) {
		return Math.pow(a.evaluate(context), b.evaluate(context));
	}

	@Override
	public boolean contains(Variable v) {
		return a.contains(v) || b.contains(v);
	}

	@Override
	public String toString() {
		return "(" + a.toString() + " ^ " + b.toString() + ")";
	}

	public Expression getA() {
		return a;
	}
	
	public Expression getB() {
		return b;
	}

}
