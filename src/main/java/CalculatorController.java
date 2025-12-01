public class CalculatorController {
    private final Calculator calculator;
    private final ExpressionEvaluator evaluator;

    public CalculatorController() {
        this.calculator = new Calculator();
        this.evaluator = new BasicExpressionEvaluator();
    }

    public void press(char ch) {
        calculator.append(ch);
    }

    public void clear() {
        calculator.clear();
    }

    public void backspace() {
        calculator.backspace();
    }

    public String getDisplay() {
        return calculator.getInput();
    }

    public String evaluateExpression() {
        try {
            String expr = calculator.getInput();
            double result = evaluator.evaluate(expr);

            // Clear and replace input with result
            calculator.clear();

            String resultStr = formatResult(result);
            for (char c : resultStr.toCharArray()) {
                calculator.append(c);
            }

            return resultStr;

        } catch (Exception e) {
            calculator.clear();
            return "Error";
        }
    }


    private String formatResult(double val) {
        if (val == (long) val)
            return Long.toString((long) val);
        return Double.toString(val);
    }

}

