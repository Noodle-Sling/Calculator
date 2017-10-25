package my.calculator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;

public class CalculatorUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private Box horizontalBox;
	private JLabel label;
	private JPanel panel;
	private JLabel lblAngleA;
	private JLabel lblAngleB;
	private JLabel lblAngleC;
	private JTextField textFieldAngleA;
	private JTextField textFieldAngleB;
	private JTextField textFieldAngleC;
	private JLabel lblSideA;
	private JLabel lblSideB;
	private JLabel lblSideC;
	private JTextField textFieldSideA;
	private JTextField textFieldSideB;
	private JTextField textFieldSideC;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JLabel lblAnswer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorUI frame = new CalculatorUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CalculatorUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 634, 378);
		contentPane.add(tabbedPane);
		
		JPanel simpleCalculator = new JPanel();
		tabbedPane.addTab("Simple Calculator", null, simpleCalculator, null);
		simpleCalculator.setBorder(new TitledBorder(null, "Number Addition", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_simpleCalculator = new GridBagLayout();
		gbl_simpleCalculator.columnWidths = new int[]{1, 86, 408, 0};
		gbl_simpleCalculator.rowHeights = new int[]{1, 67, 56, 38, 49, 29, 29, 0};
		gbl_simpleCalculator.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_simpleCalculator.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		simpleCalculator.setLayout(gbl_simpleCalculator);
		
		JLabel lblFirstNumber = new JLabel("Expression:");
		GridBagConstraints gbc_lblFirstNumber = new GridBagConstraints();
		gbc_lblFirstNumber.anchor = GridBagConstraints.EAST;
		gbc_lblFirstNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstNumber.gridx = 1;
		gbc_lblFirstNumber.gridy = 2;
		simpleCalculator.add(lblFirstNumber, gbc_lblFirstNumber);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 2;
		simpleCalculator.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblThirdNumber = new JLabel("Answer:");
		GridBagConstraints gbc_lblThirdNumber = new GridBagConstraints();
		gbc_lblThirdNumber.anchor = GridBagConstraints.EAST;
		gbc_lblThirdNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblThirdNumber.gridx = 1;
		gbc_lblThirdNumber.gridy = 4;
		simpleCalculator.add(lblThirdNumber, gbc_lblThirdNumber);
		
		lblAnswer = new JLabel();
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 2;
		gbc_label_1.gridy = 4;
		simpleCalculator.add(lblAnswer, gbc_label_1);
		
		horizontalBox = Box.createHorizontalBox();
		GridBagConstraints gbc_horizontalBox = new GridBagConstraints();
		gbc_horizontalBox.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalBox.gridx = 2;
		gbc_horizontalBox.gridy = 5;
		simpleCalculator.add(horizontalBox, gbc_horizontalBox);
		
		btnNewButton_1 = new JButton("Solve");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleCalculator calc = new SimpleCalculator();
				String str;
				String result;
				str = textField.getText();
				result = calc.solve(str);
				lblAnswer.setText(result);
			}
		});
			
		horizontalBox.add(btnNewButton_1);
		
		btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				lblAnswer.setText("");
			}
		});
		horizontalBox.add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.gridx = 2;
		gbc_btnExit.gridy = 6;
		simpleCalculator.add(btnExit, gbc_btnExit);
		
		JPanel triangleCalculator = new JPanel();
		tabbedPane.addTab("Triangle Calculator", null, triangleCalculator, null);
		
		label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/triangle_pic.gif")).getImage();
		triangleCalculator.setLayout(new BoxLayout(triangleCalculator, BoxLayout.X_AXIS));
		label.setIcon(new ImageIcon(img));
		triangleCalculator.add(label);
		
		panel = new JPanel();
		triangleCalculator.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{58, 69, 69, 74, 0};
		gbl_panel.rowHeights = new int[]{89, 45, 0, 45, 0, 45, 0, 63, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblAngleA = new JLabel("Angle A:");
		GridBagConstraints gbc_lblAngleA = new GridBagConstraints();
		gbc_lblAngleA.anchor = GridBagConstraints.EAST;
		gbc_lblAngleA.insets = new Insets(0, 0, 5, 5);
		gbc_lblAngleA.gridx = 0;
		gbc_lblAngleA.gridy = 1;
		panel.add(lblAngleA, gbc_lblAngleA);
		
		textFieldAngleA = new JTextField();
		GridBagConstraints gbc_textFieldAngleA = new GridBagConstraints();
		gbc_textFieldAngleA.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAngleA.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAngleA.gridx = 1;
		gbc_textFieldAngleA.gridy = 1;
		panel.add(textFieldAngleA, gbc_textFieldAngleA);
		textFieldAngleA.setColumns(10);
		
		lblSideA = new JLabel("Side a:");
		GridBagConstraints gbc_lblSideA = new GridBagConstraints();
		gbc_lblSideA.anchor = GridBagConstraints.EAST;
		gbc_lblSideA.insets = new Insets(0, 0, 5, 5);
		gbc_lblSideA.gridx = 2;
		gbc_lblSideA.gridy = 1;
		panel.add(lblSideA, gbc_lblSideA);
		
		textFieldSideA = new JTextField();
		GridBagConstraints gbc_textFieldSideA = new GridBagConstraints();
		gbc_textFieldSideA.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldSideA.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSideA.gridx = 3;
		gbc_textFieldSideA.gridy = 1;
		panel.add(textFieldSideA, gbc_textFieldSideA);
		textFieldSideA.setColumns(10);
		
		lblAngleB = new JLabel("Angle B:");
		GridBagConstraints gbc_lblAngleB = new GridBagConstraints();
		gbc_lblAngleB.anchor = GridBagConstraints.EAST;
		gbc_lblAngleB.insets = new Insets(0, 0, 5, 5);
		gbc_lblAngleB.gridx = 0;
		gbc_lblAngleB.gridy = 3;
		panel.add(lblAngleB, gbc_lblAngleB);
		
		textFieldAngleB = new JTextField();
		GridBagConstraints gbc_textFieldAngleB = new GridBagConstraints();
		gbc_textFieldAngleB.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAngleB.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAngleB.gridx = 1;
		gbc_textFieldAngleB.gridy = 3;
		panel.add(textFieldAngleB, gbc_textFieldAngleB);
		textFieldAngleB.setColumns(10);
		
		lblSideB = new JLabel("Side b:");
		GridBagConstraints gbc_lblSideB = new GridBagConstraints();
		gbc_lblSideB.anchor = GridBagConstraints.EAST;
		gbc_lblSideB.insets = new Insets(0, 0, 5, 5);
		gbc_lblSideB.gridx = 2;
		gbc_lblSideB.gridy = 3;
		panel.add(lblSideB, gbc_lblSideB);
		
		textFieldSideB = new JTextField();
		GridBagConstraints gbc_textFieldSideB = new GridBagConstraints();
		gbc_textFieldSideB.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldSideB.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSideB.gridx = 3;
		gbc_textFieldSideB.gridy = 3;
		panel.add(textFieldSideB, gbc_textFieldSideB);
		textFieldSideB.setColumns(10);
		
		lblAngleC = new JLabel("Angle C:");
		GridBagConstraints gbc_lblAngleC = new GridBagConstraints();
		gbc_lblAngleC.anchor = GridBagConstraints.EAST;
		gbc_lblAngleC.insets = new Insets(0, 0, 5, 5);
		gbc_lblAngleC.gridx = 0;
		gbc_lblAngleC.gridy = 5;
		panel.add(lblAngleC, gbc_lblAngleC);
		
		textFieldAngleC = new JTextField();
		GridBagConstraints gbc_textFieldAngleC = new GridBagConstraints();
		gbc_textFieldAngleC.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAngleC.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAngleC.gridx = 1;
		gbc_textFieldAngleC.gridy = 5;
		panel.add(textFieldAngleC, gbc_textFieldAngleC);
		textFieldAngleC.setColumns(10);
		
		lblSideC = new JLabel("Side c:");
		GridBagConstraints gbc_lblSideC = new GridBagConstraints();
		gbc_lblSideC.anchor = GridBagConstraints.EAST;
		gbc_lblSideC.insets = new Insets(0, 0, 5, 5);
		gbc_lblSideC.gridx = 2;
		gbc_lblSideC.gridy = 5;
		panel.add(lblSideC, gbc_lblSideC);
		
		textFieldSideC = new JTextField();
		GridBagConstraints gbc_textFieldSideC = new GridBagConstraints();
		gbc_textFieldSideC.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldSideC.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSideC.gridx = 3;
		gbc_textFieldSideC.gridy = 5;
		panel.add(textFieldSideC, gbc_textFieldSideC);
		textFieldSideC.setColumns(10);
		
		button = new JButton("Solve");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TriangleCalculator triangleCalc = new TriangleCalculator(textFieldAngleA.getText(), 
						textFieldAngleB.getText(), 
						textFieldAngleC.getText(), 
						textFieldSideA.getText(),
						textFieldSideB.getText(),
						textFieldSideC.getText());
				
				triangleCalc.calculate();
				
				textFieldAngleA.setText(triangleCalc.getAngA().toPlainString());
				textFieldAngleB.setText(triangleCalc.getAngB().toPlainString());
				textFieldAngleC.setText(triangleCalc.getAngC().toPlainString());
				textFieldSideA.setText(triangleCalc.getSideA().toPlainString());
				textFieldSideB.setText(triangleCalc.getSideB().toPlainString());
				textFieldSideC.setText(triangleCalc.getSideC().toPlainString());
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.EAST;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 6;
		panel.add(button, gbc_button);
		
		button_1 = new JButton("Clear");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldAngleA.setText("");
				textFieldAngleB.setText("");
				textFieldAngleC.setText("");
				textFieldSideA.setText("");
				textFieldSideB.setText("");
				textFieldSideC.setText("");
			}
		});
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 2;
		gbc_button_1.gridy = 6;
		panel.add(button_1, gbc_button_1);
		
		button_2 = new JButton("Exit");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.anchor = GridBagConstraints.NORTHEAST;
		gbc_button_2.insets = new Insets(0, 0, 5, 5);
		gbc_button_2.gridx = 1;
		gbc_button_2.gridy = 7;
		panel.add(button_2, gbc_button_2);
		
	}
}
