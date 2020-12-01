public class CalculateException extends Exception{

    private static final String CALCULATE_ERROR = "Calculate Error";

    public CalculateException() {
        super(CALCULATE_ERROR);
    }
}
