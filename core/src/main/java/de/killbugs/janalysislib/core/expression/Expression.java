package de.killbugs.janalysislib.core.expression;

import java.util.List;

import de.killbugs.janalysislib.core.expression.evaluation.EvaluationContext;
import de.killbugs.janalysislib.core.operator.Operator;

/**
 * Expression interface.
 *
 */
public interface Expression {
	/**
	 * Calculates the integral of this expression.
	 * 
	 * @param op given operation.
	 * @return Returns the resulting {@link Expression}.
	 */
	Expression integrate(Operator op);

	/**
	 * Calculates the differential of this expression.
	 * 
	 * @param op given operation.
	 * @return Returns the resulting {@link Expression}.
	 */
	Expression differentiate(Operator op);

	/**
	 * Checks if the given variable is contained in this expression.
	 * 
	 * @param v given variable
	 * @return <code>true</code> if contained, <code>false</code> otherwise.
	 */
	boolean contains(Variable v);

	/**
	 * Given context.
	 * 
	 * @param context given context
	 * @return the value for the context.
	 */
	double evaluate(EvaluationContext context);
}
