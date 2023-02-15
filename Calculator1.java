package start;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator1 implements ActionListener {

  private JFrame window;
  private JTextField outputField;
  private JPanel buttonPanel;
  private JButton[] buttons;

  public Calculator1() {

    // frame
    window = new JFrame("Calculator");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setSize(400, 400);

    // textfield
    outputField = new JTextField();
    outputField.setPreferredSize(new Dimension(300, 50));
    outputField.setEditable(false);
    window.add(outputField, BorderLayout.NORTH);

    // buttons
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(4, 3, 0, 0));
    buttons = new JButton[15];
    for (int i = 0; i < 9; i++) {
      buttons[i] = new JButton(String.valueOf(i + 1));
    }
    buttons[9] = new JButton("+");
    buttons[10] = new JButton("-");
    buttons[11] = new JButton("*");
    buttons[12] = new JButton("/");
    buttons[13] = new JButton("=");
    buttons[14] = new JButton("CLR");

    for (JButton button : buttons) {
      button.addActionListener(this);
      buttonPanel.add(button);
    }

    window.add(buttonPanel);

    window.setVisible(true);

  }

  public void actionPerformed(ActionEvent e) {
    String buttonLabel = e.getActionCommand();
    String currentText = outputField.getText();
    String newText = "";

    // if equals button is pressed, calculate result
    if (buttonLabel.equals("=")) {
      String[] terms = currentText.split("[+\\-*/]");
      String operator = currentText.replaceAll("[\\d.]", "");
      double result = 0;

      if (terms.length > 1) {
        double num1 = Double.parseDouble(terms[0]);
        double num2 = Double.parseDouble(terms[1]);

        switch (operator) {
          case "+":
            result = num1 + num2;
            break;
          case "-":
            result = num1 - num2;
            break;
          case "*":
            result = num1 * num2;
            break;
          case "/":
            result = num1 / num2;
            break;
        }

        newText = String.valueOf(result);
      }

    } else if (buttonLabel.equals("CLR")) {
      // clear output field
      newText = "";

    } else {
      // append button label to output field
      newText = currentText + buttonLabel;
    }

    outputField.setText(newText);
  }

  public static void main(String[] args) {
    new Calculator1();
  }

}