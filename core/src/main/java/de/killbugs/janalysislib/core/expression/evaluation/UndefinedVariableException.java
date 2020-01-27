package de.killbugs.janalysislib.core.expression.evaluation;

/**
 * Exception if variable has not been defined.
 *
 */
public class UndefinedVariableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4747065399169924489L;

	/**
	 * Constructor.
	 * @param name given name of the variable.
	 */
	public UndefinedVariableException(String name) {
		super("Variable "+name+" has not been defined.");
	}
}
