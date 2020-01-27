package de.killbugs.janalysislib.core.expression.evaluation;

public class EvaluationContextBuilder {
	private final EvaluationContext context = new EvaluationContext();
	
	public EvaluationContextBuilder addVariable(String name, double value) {
		context.addValue(name,value);
		return this;
	}
	
	public EvaluationContext build() {
		return context;
	}
}
