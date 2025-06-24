import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {
    JFrame frame;
    JTextField textfield;
    JLabel historyLabel;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[8];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton;
    JPanel panel;

    Font myFont = new Font("Times New Roman", Font.BOLD, 30);
    Font historyFont = new Font("Arial", Font.ITALIC, 16);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 600);
        frame.setLayout(null);
        frame.setResizable(true);
        frame.getContentPane().setBackground(Color.BLACK);

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);
        textfield.setBackground(Color.DARK_GRAY);
        textfield.setForeground(Color.WHITE);
        textfield.setHorizontalAlignment(JTextField.RIGHT);

        historyLabel = new JLabel(" ");
        historyLabel.setBounds(50, 5, 300, 20);
        historyLabel.setFont(historyFont);
        historyLabel.setForeground(Color.LIGHT_GRAY);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;

        Color numberColor = new Color(169, 169, 169); // Gray
        Color operationColor = new Color(255, 140, 0); // Orange

        for (int i = 0; i < 8; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
            functionButtons[i].setBackground(operationColor);
            functionButtons[i].setForeground(Color.WHITE);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(numberColor);
            numberButtons[i].setForeground(Color.BLACK);
        }

        delButton.setBounds(75, 470, 120, 50);
        clrButton.setBounds(225, 470, 120, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 350);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(Color.BLACK);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textfield);
        frame.add(historyLabel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decButton) {
            if (!textfield.getText().contains(".")) {
                textfield.setText(textfield.getText().concat("."));
            }
        }

        if (e.getSource() == addButton || e.getSource() == subButton ||
            e.getSource() == mulButton || e.getSource() == divButton) {

            String currentText = textfield.getText();

            if (e.getSource() == subButton && currentText.isEmpty()) {
                textfield.setText("-");
                return;
            }

            try {
                num1 = Double.parseDouble(currentText);
                operator = ((JButton) e.getSource()).getText().charAt(0);
                textfield.setText("");
            } catch (NumberFormatException ex) {
                textfield.setText("Error");
            }
        }

        if (e.getSource() == equButton) {
            try {
                num2 = Double.parseDouble(textfield.getText());
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/':
                        if (num2 == 0) {
                            textfield.setText("Error");
                            return;
                        }
                        result = num1 / num2;
                        break;
                }
                historyLabel.setText(num1 + " " + operator + " " + num2 + " =");
                textfield.setText(String.valueOf(result));
                num1 = result;
            } catch (NumberFormatException ex) {
                textfield.setText("Error");
            }
        }

        if (e.getSource() == clrButton) {
            textfield.setText("");
            historyLabel.setText(" ");
        }

        if (e.getSource() == delButton) {
            String text = textfield.getText();
            if (!text.isEmpty()) {
                textfield.setText(text.substring(0, text.length() - 1));
            }
        }
    }
}
