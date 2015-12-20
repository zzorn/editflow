package org.editflow.parameter.calculator.calculators;

import com.badlogic.gdx.math.Vector3;
import org.demoflow.parameter.Parameter;
import org.demoflow.parameter.calculator.CalculationContext;
import org.demoflow.parameter.calculator.CalculatorBase;

/**
 * Scales a parameter vector with a scale parameter.
 */
public final class Vector3ScaleCalculator extends CalculatorBase<Vector3> {

    public Parameter<Double> scale;
    public Parameter<Vector3> vector;

    public Vector3ScaleCalculator() {
        this(1.0, new Vector3(1, 1, 1));
    }

    public Vector3ScaleCalculator(double initialScale, Vector3 initialVector) {
        scale = addParameter("scale", initialScale);
        vector = addParameter("vector", initialVector);
    }

    @Override
    protected Vector3 doCalculate(CalculationContext calculationContext,
                                  Vector3 currentValue,
                                  Parameter<Vector3> parameter) {

        final Vector3 vectorToScale = vector.get();
        final double scale = this.scale.get();

        currentValue.set(vectorToScale);
        currentValue.scl((float) scale);

        return currentValue;
    }

    @Override protected void doResetState() {
        // No changing state to reset.
    }

    @Override public Class<Vector3> getReturnType() {
        return Vector3.class;
    }
}
