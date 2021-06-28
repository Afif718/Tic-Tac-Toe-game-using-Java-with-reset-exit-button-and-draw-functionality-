/**
 *
 */
package tic.tac.toe.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicTacToe implements ActionListener {

	// to determine who is going to start first
	Random random = new Random();

	JFrame frame = new JFrame();

	JPanel title_Panel = new JPanel();
	JPanel button_panel = new JPanel();

	JLabel textfield = new JLabel();

	JButton[] buttons = new JButton[9];

	boolean player1_turn;

	// constructor
	public TicTacToe() {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 700);
		frame.getContentPane().setBackground(new Color(50, 50, 50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);

		// title panel style, color and size
		textfield.setBackground(new Color(25, 25, 25));
		textfield.setForeground(new Color(0x00FF00));
		textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-Tac-Toe");
		textfield.setOpaque(true);

		// title panel
		title_Panel.setLayout(new BorderLayout());
		title_Panel.setBounds(0, 0, 800, 100); // title position on the frame

		// button panel is the below part of the title bar
		button_panel.setLayout(new GridLayout(4, 3)); // 3 columns and 3 rows
		button_panel.setBackground(new Color(150, 150, 150));

		// button
		for (int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);


		}

		// set and reset button

		JButton reset = new JButton("Reset");
		JButton exit = new JButton("Exit");

		reset.setForeground(new Color(0x00FF00));
		reset.setBackground(Color.gray);
		reset.setFont(new Font("sans-serif", Font.BOLD, 30));

		exit.setForeground(new Color(0x00FF00));
		exit.setBackground(Color.gray);
		exit.setFont(new Font("sans-serif", Font.BOLD, 30));

		button_panel.add(reset);
		button_panel.add(exit);

		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 9; i++) {
					buttons[i].setText("");
					buttons[i].setEnabled(true);
					buttons[i].setBackground(new Color(38, 38, 38));
				}
				firstTurn();
			}
		});

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// adding those things
		title_Panel.add(textfield);
		frame.add(title_Panel, BorderLayout.NORTH);
		frame.add(button_panel);

		// calling firstTurn() method
		firstTurn();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < 9; i++) {

			if (e.getSource() == buttons[i]) {
				// if its player 1's turn
				if (player1_turn) {

					if (buttons[i].getText() == "") {

						buttons[i].setForeground(new Color(255, 0, 0));
						buttons[i].setText("X");

						player1_turn = false;
						textfield.setText("O turn");
						check();
					}
				} else {

					if (buttons[i].getText() == "") {

						buttons[i].setForeground(new Color(0, 0, 255));
						buttons[i].setText("O");

						player1_turn = true;
						textfield.setText("X turn");
						check();
					}
				}
			}
		}

	}

	// who's turn first(randomly)
	public void firstTurn() {

		// wait two seconds before showing who's turn
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// here we will generate two numbers 1 and 2.
		// where 0 is first palyer and 1 is second player
		if (random.nextInt(2) == 0) {

			player1_turn = true;
			textfield.setText("X turn");
		} else {

			player1_turn = false;
			textfield.setText("O turn");
		}
	}

	// check wining condition
	public void check() {

		// check x win conditions
		if (buttons[0].getText() == "X" && buttons[1].getText() == "X" && buttons[2].getText() == "X") {

			xWins(0, 1, 2);
		}

		if (buttons[3].getText() == "X" && buttons[4].getText() == "X" && buttons[5].getText() == "X") {

			xWins(3, 4, 5);
		}

		if (buttons[6].getText() == "X" && buttons[7].getText() == "X" && buttons[8].getText() == "X") {

			xWins(6, 7, 8);
		}

		if (buttons[0].getText() == "X" && buttons[3].getText() == "X" && buttons[6].getText() == "X") {

			xWins(0, 3, 6);
		}

		if (buttons[1].getText() == "X" && buttons[4].getText() == "X" && buttons[7].getText() == "X") {

			xWins(1, 4, 7);
		}

		if (buttons[2].getText() == "X" && buttons[5].getText() == "X" && buttons[8].getText() == "X") {

			xWins(2, 5, 8);
		}

		if (buttons[0].getText() == "X" && buttons[4].getText() == "X" && buttons[8].getText() == "X") {

			xWins(0, 4, 8);
		}

		if (buttons[2].getText() == "X" && buttons[4].getText() == "X" && buttons[6].getText() == "X") {

			xWins(2, 4, 6);
		} else if (!buttons[0].getText().isEmpty() && !buttons[1].getText().isEmpty() && !buttons[2].getText().isEmpty()
				&& !buttons[3].getText().isEmpty() && !buttons[4].getText().isEmpty() && !buttons[5].getText().isEmpty()
				&& !buttons[6].getText().isEmpty() && !buttons[7].getText().isEmpty()
				&& !buttons[8].getText().isEmpty()) {
			tie();
		}


		// check O win conditions

		if (buttons[0].getText() == "O" && buttons[1].getText() == "O" && buttons[2].getText() == "O") {

			oWins(0, 1, 2);
		}

		if (buttons[3].getText() == "O" && buttons[4].getText() == "O" && buttons[5].getText() == "O") {

			oWins(3, 4, 5);
		}

		if (buttons[6].getText() == "O" && buttons[7].getText() == "O" && buttons[8].getText() == "O") {

			oWins(6, 7, 8);
		}

		if (buttons[0].getText() == "O" && buttons[3].getText() == "O" && buttons[6].getText() == "X") {

			oWins(0, 3, 6);
		}

		if (buttons[1].getText() == "O" && buttons[4].getText() == "O" && buttons[7].getText() == "O") {

			oWins(1, 4, 7);
		}

		if (buttons[2].getText() == "O" && buttons[5].getText() == "X" && buttons[8].getText() == "O") {

			oWins(2, 5, 8);
		}

		if (buttons[0].getText() == "O" && buttons[4].getText() == "O" && buttons[8].getText() == "O") {

			oWins(0, 4, 8);
		}

		if (buttons[2].getText() == "O" && buttons[4].getText() == "O" && buttons[6].getText() == "O") {

			oWins(2, 4, 6);
		} else if (!buttons[0].getText().isEmpty() && !buttons[1].getText().isEmpty() && !buttons[2].getText().isEmpty()
				&& !buttons[3].getText().isEmpty() && !buttons[4].getText().isEmpty() && !buttons[5].getText().isEmpty()
				&& !buttons[6].getText().isEmpty() && !buttons[7].getText().isEmpty()
				&& !buttons[8].getText().isEmpty()) {
			tie();
		}

	}

	// if x wins
	public void xWins(int a, int b, int c) {

		buttons[a].setBackground(Color.green);
		buttons[b].setBackground(Color.green);
		buttons[c].setBackground(Color.green);

		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("X wins");
	}

	// if O wins
	public void oWins(int a, int b, int c) {

		buttons[a].setBackground(Color.green);
		buttons[b].setBackground(Color.green);
		buttons[c].setBackground(Color.green);

		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("O wins");
	}

	public void tie() {
		System.out.println("it's a tie!");
		textfield.setText("Draw");
		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
	}

}
