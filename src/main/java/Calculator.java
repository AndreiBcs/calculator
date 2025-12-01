public class Calculator {
    private StringBuilder input;

    public Calculator() {
        this.input = new StringBuilder();
    }

    public String getInput() {
        return input.toString();
    }

    public void clear() {
        input.setLength(0);
    }

    public void backspace() {
        if (input.length() > 0) {
            input.deleteCharAt(input.length() - 1);
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' ||
                c == '%' || c == '^';
    }

    private boolean isDigitOrDot(char c) {
        return (c >= '0' && c <= '9') || c == '.';
    }

    private boolean lastTokenIsNumber() {
        if (input.length() == 0) return false;
        char last = input.charAt(input.length() - 1);
        return Character.isDigit(last) || last == '.';
    }

    private boolean lastTokenIsOperator() {
        if (input.length() == 0) return false;
        return isOperator(input.charAt(input.length() - 1));
    }

    /**
     * Append a character safely.
     */
    public void append(char ch) {
        if (input.length() == 0) {
            // First character rules:
            if (Character.isDigit(ch) || ch == '(') {
                input.append(ch);
            } else if (ch == '-') {
                input.append(ch); // allow negative start
            }
            return;
        }

        char last = input.charAt(input.length() - 1);

        // Prevent two operators in a row
        if (isOperator(last) && isOperator(ch)) {
            input.setCharAt(input.length() - 1, ch);
            return;
        }

        // Prevent two dots in the same number
        if (ch == '.') {
            int i = input.length() - 1;
            while (i >= 0 && isDigitOrDot(input.charAt(i))) {
                if (input.charAt(i) == '.') return; // double dot
                i--;
            }
        }

        // Add implicit multiplication: 2(3+4) or )( or 3(4)
        if ((Character.isDigit(last) || last == ')') && ch == '(') {
            input.append('*');
        }
        if (last == ')' && Character.isDigit(ch)) {
            input.append('*');
        }

        input.append(ch);
    }
}
