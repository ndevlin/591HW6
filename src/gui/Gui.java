
package gui;

import javax.swing.*;
import java.util.*;
import java.awt.*;

/**
 * @author Nathan
 */
public class Gui extends JFrame
{
	int WIDTH = 1280;
	int HEIGHT = 720;
	
	public Gui()
	{
		this.setSize(WIDTH+6, HEIGHT+29);
		this.setTitle("BlackJack ");
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	
}
