package org.editflow.parameter.calculator;

import org.flowutils.Check;
import org.flowutils.MathUtils;

/**
 * Default CalculationContext implementation.
 */
public final class DefaultCalculationContext implements CalculationContext {

    private double deltaTime_s;

    private double currentDemoTime_s;
    private double totalDemoDuration_s;

    private double currentEffectStartTime_s;
    private double currentEffectEndTime_s;

    @Override public void init(double totalDemoDuration_s) {
        Check.positive(totalDemoDuration_s, "totalDemoDuration_s");

        this.totalDemoDuration_s = totalDemoDuration_s;
        this.currentDemoTime_s = 0;
        this.deltaTime_s = 0;
    }

    @Override public void update(double deltaTime_s) {
        Check.positiveOrZero(deltaTime_s, "deltaTime_s");

        this.deltaTime_s = deltaTime_s;
        this.currentDemoTime_s += deltaTime_s;

        setEffectPeriod(0, 1);
    }


    @Override public void setEffectPeriod(double relativeStartTime, double relativeEndTime) {
        Check.greaterOrEqual(relativeEndTime, "relativeEndTime", relativeStartTime, "relativeStartTime");

        currentEffectStartTime_s = relativeStartTime * totalDemoDuration_s;
        currentEffectEndTime_s = relativeEndTime * totalDemoDuration_s;
    }

    @Override public double deltaTimeSeconds() {
        return deltaTime_s;
    }

    @Override public double getRelativeDemoTime() {
        return currentDemoTime_s / totalDemoDuration_s;
    }

    @Override public double getSecondsFromDemoStart() {
        return currentDemoTime_s;
    }

    @Override public double getSecondsFromDemoEnd() {
        return totalDemoDuration_s - currentDemoTime_s;
    }

    @Override public double getRelativeEffectPosition() {
        return MathUtils.relPos(currentDemoTime_s, currentEffectStartTime_s, currentEffectEndTime_s);
    }

    @Override public double getSecondsFromEffectStart() {
        return currentDemoTime_s - currentEffectStartTime_s;
    }

    @Override public double getSecondsFromEffectEnd() {
        return currentEffectEndTime_s - currentDemoTime_s;
    }
}
