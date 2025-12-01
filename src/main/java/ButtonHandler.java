import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = ((JButton) e.getSource()).getText();

        switch (cmd) {

            case "C": {   // Clear all
                calculator.clear();
                updateDisplay();
                break;
            }

            case "←": {   // Backspace
                calculator.backspace();
                updateDisplay();
                break;
            }

            case "=": {   // Evaluate expression
                evaluateExpression();
                break;
            }

            default: {    // Normal button (numbers/operators/etc.)
                char ch = cmd.charAt(0);
                calculator.append(ch);
                updateDisplay();
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
