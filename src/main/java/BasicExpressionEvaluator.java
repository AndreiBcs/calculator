import java.util.Stack;

public class BasicExpressionEvaluator implements ExpressionEvaluator{
    private String expression;
    public BasicExpressionEvaluator(){
        this.expression = getInput();
    }
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^';

    }

    private int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/' || op=='%') return 2;
        if(op=='^') return 3;
        return 0;
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
            case '%' ->{
                if (b == 0) throw new ArithmeticException("Division by zero");
                yield a % b;
            }
            case '^'-> Math.pow(a, b);
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
                i--; // face pas inapoi ca sa nu incrementeze dupa .
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
                operators.pop(); // da remove la '('
            }

            else if (isOperator(c)) {
                while (!operators.isEmpty() &&
                        precedence(operators.peek()) >= precedence(c)) {
                    double b = numbers.pop();
                    double a = numbers.pop();
                    char op = operators.pop();
                    numbers.push(applyOperation(a, b, op));
                }
                operators.push(c);
            }
        }

        // ce operatii raman
        while (!operators.isEmpty()) {
            double b = numbers.pop();
            double a = numbers.pop();
            char op = operators.pop();
            numbers.push(applyOperation(a, b, op));
        }

        return numbers.pop();
    }
}
