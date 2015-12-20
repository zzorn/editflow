package org.editflow.parameter.calculator.calculators;

import com.badlogic.gdx.graphics.Color;
import org.demoflow.parameter.Parameter;
import org.demoflow.parameter.calculator.CalculationContext;
import org.demoflow.parameter.calculator.CalculatorBase;

/**
 * Calculates the value of a color from components.
 */
public final class ColorCalculator extends CalculatorBase<Color> {

    public final Parameter<Double> r;
    public final Parameter<Double> g;
    public final Parameter<Double> b;
    public final Parameter<Double> a;

    public ColorCalculator() {
        this(0.5);
    }

    public ColorCalculator(double grey) {
        this(grey, grey, grey, 1.0);
    }

    public ColorCalculator(double red, double green, double blue) {
        this(red, green, blue, 1.0);
    }

    public ColorCalculator(double red, double green, double blue, double alpha) {
        r = addParameter("red", red);
        g = addParameter("green", green);
        b = addParameter("blue", blue);
        a = addParameter("alpha", alpha);
    }

    @Override
    protected Color doCalculate(CalculationContext calculationContext,
                                Color currentValue,
                                Parameter<Color> parameter) {
        final double r = this.r.get();
        final double g = this.g.get();
        final double b = this.b.get();
        final double a = this.a.get();

        currentValue.set((float) r,
                         (float) g,
                         (float) b,
                         (float) a);

        return currentValue;
    }

    @Override protected void doResetState() {
        // No changing state to reset.
    }

    @Override public Class<Color> getReturnType() {
        return Color.class;
    }

}
