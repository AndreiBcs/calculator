package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorUI extends JFrame {

    private JTextField display;
    private Calculator calculator;
    private ExpressionParser parser;

    public CalculatorUI() {
        calculator = new Calculator();
        parser = new BasicExpressionEvaluator();

        setTitle("Java Calculator");
        setSize(350, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 4, 5, 5));

        String[] buttons = {
                "MC", "MR", "M+", "M-",
                "C", "←", "/", "*",
                "7", "8", "9", "-",
                "4", "5", "6", "+",
                "1", "2", "3", "=",
                "0", ".", "(", ")"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(new ButtonHandler());
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
}