package de.killbugs.janalysislib.core.expression;

import java.util.List;

import de.killbugs.janalysislib.core.expression.evaluation.EvaluationContext;
import de.killbugs.janalysislib.core.expression.function.Add;
import de.killbugs.janalysislib.core.expression.function.Multiply;
import de.killbugs.janalysislib.core.operator.Operator;

public class Constant implements Expression {

	public static final Constant TWO = new Constant(null, 2.0);
	public static final Constant HALF = new Constant(null, 0.5);

	public static final Constant PI = new Constant("\\u03C0", Math.PI);
	public static final Constant E = new Constant("e", Math.E);
	public static final Constant C = new Constant("C", Double.NaN);
	public static final Constant ZERO = new Constant(null, 0.0);
	public static final Constant MINUS_ONE = new Constant(null, -1.0);
	public static final Constant ONE = new Constant(null, 1.0);

	private final double value;
	private final String name;

	public Constant(String theName, double theValue) {
		this.value = theValue;
		this.name = theName;
	}

	@Override
	public Expression integrate(Operator op) {
		return new Add(new Multiply(op.getVariable(), this), C);
	}

	@Override
	public Expression differentiate(Operator op) {
		return ZERO;
	}

	@Override
	public double evaluate(EvaluationContext context) {
		return value;
	}

	@Override
	public String toString() {
		if (name != null) {
			return name;
		}
		return "" + value;
	}

	@Override
	public boolean contains(Variable v) {
		return false;
	}

	public boolean isSpecial() {
		return name != null;
	}

	public static Expression combineAdd(Constant a, Constant b) {
		if (a.isSpecial()) {
			if (b.isSpecial()) {
				if (a == C && b == C) {
					return C;
				}
				if (a.equals(b)) {
					return new Multiply(TWO, a);
				}
			}
		} else if (!b.isSpecial()) {
			return new Constant(null, a.value + b.value);
		}
		return new Add(a, b);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Constant other = (Constant) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}

	public Expression add(Constant constant) {
		return combineAdd(this, constant);
	}

}
