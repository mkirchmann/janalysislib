package de.killbugs.janalysislib.core.expression.function;

import de.killbugs.janalysislib.core.expression.Constant;
import de.killbugs.janalysislib.core.expression.Expression;
import de.killbugs.janalysislib.core.expression.Variable;
import de.killbugs.janalysislib.core.expression.evaluation.EvaluationContext;
import de.killbugs.janalysislib.core.operator.Operator;

public class Ln implements Expression {
	
	private final Expression expression;
	
	public Ln(Expression theExpression) {
		this.expression=theExpression;
	}

	@Override
	public Expression integrate(Operator op) {
		if (expression instanceof Multiply) {
			Expression a = ((Multiply)expression).getA();
			Expression b = ((Multiply)expression).getB();
			return new Add(new Ln(a),new Ln(b)).integrate(op);
		} else if (expression instanceof Pow) {
			Expression a = ((Pow)expression).getA();
			Expression b = ((Pow)expression).getB();
			return new Multiply(b,new Ln(a)).integrate(op);
		} else if (expression instanceof Constant) {
			return new Add(new Multiply(op.getVariable(),this),Constant.C);
		} else if (expression instanceof Variable) {
			if (op.getVariable().equals(expression)) {
				return new Add(new Multiply(op.getVariable(),new Add(this,Constant.MINUS_ONE)),Constant.C);
			}
			return new Add(new Multiply(op.getVariable(),this),Constant.C);
		}
		return null;
	}

	@Override
	public Expression differentiate(Operator op) {
		return new Multiply(new Pow(expression,Constant.MINUS_ONE), expression.differentiate(op));
	}

	@Override
	public boolean contains(Variable v) {
		return expression.contains(v);
	}

	@Override
	public double evaluate(EvaluationContext context) {
		return Math.log(expression.evaluate(context));
	}

}
