package org.editflow.parameter.calculator;

/**
 * Context to pass to parameter calculators.
 */
public interface CalculationContext {

    /**
     * @return Duration of this update step in seconds.
     *         For the first update, this is zero.
     */
    double deltaTimeSeconds();


    /**
     * @return relative position within the demo, 0 = at start, 1 = at end.
     */
    double getRelativeDemoTime();

    /**
     * @return seconds from the start of the demo.
     */
    double getSecondsFromDemoStart();

    /**
     * @return seconds from the end of the demo.
     */
    double getSecondsFromDemoEnd();

    void init(double totalDemoDuration_s);

    /**
     * Updates demo time and sets current relative effect position to the duration of the demo (0..1).
     */
    void update(double deltaTime_s);



    /**
     * @return relative position within the current effect, 0 = at start, 1 = at end.
     */
    double getRelativeEffectPosition();

    /**
     * @return seconds from the start of the effect.
     */
    double getSecondsFromEffectStart();

    /**
     * @return seconds from the end of the effect.
     */
    double getSecondsFromEffectEnd();

    /**
     * Sets current effect relative time period.
     *
     * @param relativeStartTime relative start time of the effect in the demo, 0 = start of demo, 1 = end of demo.
     * @param relativeEndTime   relative end time of the effect in the demo, 0 = start of demo, 1 = end of demo.
     */
    void setEffectPeriod(double relativeStartTime, double relativeEndTime);

}
