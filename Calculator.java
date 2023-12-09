import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textField;
    JButton[] opButtons = new JButton[8];
    JButton[] numberButtons = new JButton[10];
    JPanel panel;
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);

        textField = new JTextField("0");
        textField.setBounds(55, 25, 280, 50);
        frame.add(textField);
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setEditable(false);

        initializeButtons();

        frame.setVisible(true);
    }

    private void initializeButtons() {
        String[] buttonTexts = {"+", "-", "*", "/", "=", ".", "Delete", "Clear"};

        for (int i = 0; i < 8; i++) {
            opButtons[i] = new JButton(buttonTexts[i]);
            opButtons[i].addActionListener(this);
            opButtons[i].setFont(new Font("Ink Free", Font.BOLD, 20));
            opButtons[i].setFocusable(false);
            frame.add(opButtons[i]);
        }

        opButtons[6].setBounds(50, 430, 145, 50);
        opButtons[7].setBounds(205, 430, 145, 50);

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(9 - i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(new Font("Ink Free", Font.BOLD, 20));
            numberButtons[i].setFocusable(false);
        }

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBounds(55, 100, 280, 280);
        panel.setBackground(Color.BLACK);
        frame.add(panel);

        setButtonGrid();
    }

    private void setButtonGrid() {
        panel.add(numberButtons[2]);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[0]);
        panel.add(opButtons[2]);

        panel.add(numberButtons[5]);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[3]);
        panel.add(opButtons[3]);

        panel.add(numberButtons[8]);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[6]);
        panel.add(opButtons[1]);

        panel.add(opButtons[0]);
        panel.add(numberButtons[9]);
        panel.add(opButtons[5]);
        panel.add(opButtons[4]);
    }

    private void handleNumberButtonClick(int index) {
        textField.setText(textField.getText().concat(String.valueOf(9 - index)));
    }

    private void handleDotButtonClick() {
        textField.setText(textField.getText().concat("."));
    }

    private void handleOperatorButtonClick(char op) {
        num1 = Double.parseDouble(textField.getText());
        operator = op;
        textField.setText("");
    }

    private void handleEqualsButtonClick() {
        num2 = Double.parseDouble(textField.getText());

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
        }

        textField.setText(String.valueOf(result));
        num1 = result;
    }

    private void handleClearButtonClick() {
        textField.setText("");
    }

    private void handleDeleteButtonClick() {
        String string = textField.getText();
        textField.setText("");
        for (int i = 0; i < string.length() - 1; i++) {
            textField.setText(textField.getText() + string.charAt(i));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                handleNumberButtonClick(i);
            }
        }

        if (e.getSource() == opButtons[5]) {
            handleDotButtonClick();
        }

        if (e.getSource() == opButtons[0]) {
            handleOperatorButtonClick('+');
        }

        if (e.getSource() == opButtons[1]) {
            handleOperatorButtonClick('-');
        }

        if (e.getSource() == opButtons[2]) {
            handleOperatorButtonClick('*');
        }

        if (e.getSource() == opButtons[3]) {
            handleOperatorButtonClick('/');
        }

        if (e.getSource() == opButtons[4]) {
            handleEqualsButtonClick();
        }

        if (e.getSource() == opButtons[7]) {
            handleClearButtonClick();
        }

        if (e.getSource() == opButtons[6]) {
            handleDeleteButtonClick();
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
