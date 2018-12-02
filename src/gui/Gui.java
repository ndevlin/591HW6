
package gui;
// THIRD COMMENT 649PM -BJORN

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Nathan
 */
public class Gui extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// Height and width of window
	int WIDTH = 1280;
	int HEIGHT = 720;
	
	// Colors of various elements
	Color backgroundColor = new Color(40, 120, 20);
	Color colorButton = new Color(250, 250, 250);
	
	
	// Buttons
	JButton hitButton = new JButton();
	JButton stayButton = new JButton();
	JButton yesButton = new JButton();
	JButton noButton = new JButton();
	
	
	// Card grid positioning and Dimensions
	int gridX = 50;
	int gridY = 50;
	int gridW = 900;
	int gridH = 400;
	
	
	
	
	// Fonts
	Font buttonFont = new Font("Times New Roman", Font.PLAIN, 40);
	
	
	public Gui()
	{
		this.setSize(WIDTH+6, HEIGHT+29);
		this.setTitle("BlackJack ");
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Board board = new Board();
		this.setContentPane(board);
		this.setLayout(null);
		
		
		// Hit button
		ActionHit aHit = new ActionHit();
		hitButton.addActionListener(aHit);
		
		hitButton.setBounds(400, 400, 120, 80);
		hitButton.setBackground(colorButton);
		hitButton.setFont(buttonFont);
		hitButton.setText("HIT");
		
		board.add(hitButton);
		
		
		
		// Stay button
		ActionStay aStay = new ActionStay();
		stayButton.addActionListener(aStay);
		
		stayButton.setBounds(600, 400, 120, 80);
		stayButton.setBackground(colorButton);
		stayButton.setFont(buttonFont);
		stayButton.setText("STAY");
		
		board.add(stayButton);
		
		
		
		// Yes Button
		ActionYes aYes = new ActionYes();
		yesButton.addActionListener(aYes);
		
		yesButton.setBounds(400, 600, 120, 80);
		yesButton.setBackground(colorButton);
		yesButton.setFont(buttonFont);
		yesButton.setText("YES");
		
		board.add(yesButton);
		
		
		//
		ActionNo aNo = new ActionNo();
		noButton.addActionListener(aNo);
		
		noButton.setBounds(600, 600, 120, 80);
		noButton.setBackground(colorButton);
		noButton.setFont(buttonFont);
		noButton.setText("NO");
		
		board.add(noButton);
		
		
	}
	
	
	public class Board extends JPanel
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		 
		public void paintComponent(Graphics graphic)
		{
			graphic.setColor(backgroundColor);
			graphic.fillRect(0, 0, WIDTH, HEIGHT);
			
			
			// Temporary Grid Painting
			graphic.setColor(Color.black);
			graphic.drawRect(gridX, gridY, gridW, gridH);
			
			// Temporary Log Borders Painting
			graphic.drawRect(gridX, gridY+ gridH + 100, gridW, gridH);
			
			
		}
		
	}
	
	
	public class ActionHit implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Hit button clicked.");
			
		}
		
	}
	
	
	public class ActionStay implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Stay button clicked.");
			
		}
		
	}
	
	
	public class ActionYes implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Yes button clicked.");
			
		}
		
	}
	
	
	public class ActionNo implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("No button clicked.");
			
		}
		
	}
	
	
	
}
