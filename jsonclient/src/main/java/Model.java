// Model.java
// Fred Swartz - December 2004
// Model
//     This model is completely independent of the user interface.
//     It could as easily be used by a command line or web interface.

import java.math.BigInteger;

public class Model {
    //... Constants
    static final String INITIAL_VALUE = "1";
    
    //... Member variable defining state of calculator.
    private BigInteger m_total;  // The total current value state.
    private int chartDays;

    //============================================================== constructor
    /** Constructor */
    Model() {
        reset();
    }
    
    //==================================================================== reset
    /** Reset to initial value. */
    public void reset() {
        m_total = new BigInteger(INITIAL_VALUE);
    }
    
    //=============================================================== multiplyBy
    /** Multiply current total by a number.
    *@param days Number (as string)
    */
    public void getChartData(String days) {
        chartDays = Integer.parseInt(days);

    }

    public void getChartDataProgram(String hours) {
        chartDays = Integer.parseInt(hours);

    }
    
    //================================================================= setValue
    /** Set the total value. 
    *@param value New value that should be used for the calculator total. 
    */
    public void setValue(String value) {
        m_total = new BigInteger(value);
    }
    
    //================================================================= getValue
    /** Return current calculator total. */
    public String getValue() {
        return m_total.toString();
    }

    public int getChartDays() {
        return chartDays;
    }

    public void setChartDays(int chartDays) {
        this.chartDays = chartDays;
    }

    public Double calculateBaseline() {
        Double baseline = 0.0;

        return baseline;
    }
}
