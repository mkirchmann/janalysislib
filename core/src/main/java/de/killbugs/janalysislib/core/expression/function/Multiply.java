package de.killbugs.janalysislib.core.expression.function;

import java.util.List;

import de.killbugs.janalysislib.core.expression.Constant;
import de.killbugs.janalysislib.core.expression.Expression;
import de.killbugs.janalysislib.core.expression.Variable;
import de.killbugs.janalysislib.core.expression.evaluation.EvaluationContext;
import de.killbugs.janalysislib.core.operator.Operator;

public class Multiply implements Expression {

	private Expression a;
	private Expression b;

	public Multiply(Expression theA, Expression theB) {
		this.a=theA;
		this.b=theB;
	}

	@Override
	public Expression integrate(Operator op) {
		if (a.contains(op.getVariable())) {
			if (b.contains(op.getVariable())) {
				throw new RuntimeException("Partial integration not yet implemented.");
			} else {
				return new Multiply(a.integrate(op),b);
			}
		} else if (b.contains(op.getVariable())) {
			return new Multiply(a,b.integrate(op));
		}
		return new Add(new Multiply(this,op.getVariable()),Constant.C);
	}

	@Override
	public Expression differentiate(Operator op) {
		if (a.contains(op.getVariable())) {
			if (b.contains(op.getVariable())) {
				return new Add( //
						new Multiply(a.differentiate(op),b), //
						new Multiply(a,b.differentiate(op)) //
								);
			} else {
				return new Multiply(a.differentiate(op),b);
			}
		} else if (b.contains(op.getVariable())) {
			return new Multiply(a,b.differentiate(op));
		}
		return Constant.ZERO;
	}

	@Override
	public double evaluate(EvaluationContext context) {
		return a.evaluate(context)*b.evaluate(context);
	}
	
	@Override
	public boolean contains(Variable v) {
		return a.contains(v) || b.contains(v);
	}
	
	@Override
	public String toString() {
		return "(" + a.toString() + " * " + b.toString() + ")";
	}

	public Expression getA() {
		return a;
	}
	
	public Expression getB() {
		return b;
	}
	
}
