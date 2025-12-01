import java.util.Stack;

public class BasicExpressionEvaluator implements ExpressionEvaluator {

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^';
    }

    private int precedence(char op) {
        return switch (op) {
            case '+', '-' -> 1;
            case '*', '/', '%' -> 2;
            case '^' -> 3;
            default -> 0;
        };
    }

    private double applyOperation(double a, double b, char op) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) throw new ArithmeticException("Division by zero");
                yield a / b;
            }
            case '%' -> {
                if (b == 0) throw new ArithmeticException("Division by zero");
                yield a % b;
            }
            case '^' -> Math.pow(a, b);
            default -> 0;
        };
    }

    @Override
    public double evaluate(String expression) throws Exception {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == ' ') continue;

            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() &&
                        (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                i--;
                numbers.push(Double.parseDouble(sb.toString()));
            }

            else if (c == '(') {
                operators.push(c);
            }

            else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    double b = numbers.pop();
                    double a = numbers.pop();
                    char op = operators.pop();
                    numbers.push(applyOperation(a, b, op));
                }
                operators.pop();
            }

            else if (isOperator(c)) {

                // FIX: ^ must be right-associative
                while (!operators.isEmpty() &&
                        (precedence(operators.peek()) > precedence(c) ||
                                (precedence(operators.peek()) == precedence(c) && c != '^'))) {

                    double b = numbers.pop();
                    double a = numbers.pop();
                    char op = operators.pop();
                    numbers.push(applyOperation(a, b, op));
                }

                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            double b = numbers.pop();
            double a = numbers.pop();
            char op = operators.pop();
            numbers.push(applyOperation(a, b, op));
        }

        return numbers.pop();
    }
}
