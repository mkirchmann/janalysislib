package de.killbugs.janalysislib.core.expression;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import de.killbugs.janalysislib.core.expression.function.Multiply;
import de.killbugs.janalysislib.core.expression.function.Pow;
import de.killbugs.janalysislib.core.operator.DifferentiationOperator;

/**
 * Test for {@link Expression}.
 *
 */
public class ExpressionTest {
	Expression testee;
	
	@Before
	public void before() {
		testee=new Multiply(Constant.TWO, new Pow(new Variable("x"), Constant.TWO));
	}
	
	@Test
	public void testIntegrate() {
		DifferentiationOperator operator = new DifferentiationOperator(new Variable("x"));
		
		Expression result = testee.integrate(operator);
		
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.toString()).isEqualTo("(2.0 * ((3.0 ^ -1.0) * (x ^ 3.0)))");
	}
	
	@Test
	public void testDifferentiate() {
		DifferentiationOperator operator = new DifferentiationOperator(new Variable("x"));
		
		Expression result = testee.differentiate(operator);
		
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.toString()).isEqualTo("(2.0 * (2.0 * (x ^ 1.0)))");
	}
}
