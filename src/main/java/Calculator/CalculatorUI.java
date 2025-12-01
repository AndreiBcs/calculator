package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorUI extends JFrame {

    private JTextField display;
    private Calculator calculator;
    private BasicExpressionEvaluator evaluator;

    public CalculatorUI() {
        calculator = new Calculator();
        evaluator = new BasicExpressionEvaluator();

        setTitle("Java Calculator");
        setSize(400, 550);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // DISPLAY
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        add(display, BorderLayout.NORTH);

        // BUTTON PANEL: 6 rows × 5 columns
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 5, 5, 5));

        String[] buttons = {
                "C", "←", "%", "^", "/",
                "7", "8", "9", "*", "-",
                "4", "5", "6", "+", "(",
                "1", "2", "3", ")", "=",
                "0", ".", "", "", ""
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));

            // skip empty placeholders
            if (!text.equals("")) {
                button.addActionListener(new ButtonHandler());
            } else {
                button.setEnabled(false);
            }

            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }