/**
 * 
 */
/**
 * @author DemiDemon
 *
 */
package gui;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class gui {
	public static JFrame frame1;
	public static Container pane;
	public static JButton btnNewGame, btnExit, btnLoadGame;
	public static JLabel lblGameName;
	
	public static void launchGUI(){

		//JTextField txtServer;
		//Insets insets;

		//Create the frame
		frame1 = new JFrame ("Game GUI Test");
		//Set its size to 800x800 pixels
		frame1.setSize (800,800);
		//Prepare panel
		pane = frame1.getContentPane();
		//insets = pane.getInsets();
		//Apply the null layout
		pane.setLayout (null);

		btnNewGame = new JButton ("New Game");
		btnLoadGame = new JButton ("Load Game");
		btnExit = new JButton ("Exit");
		lblGameName = new JLabel ("Le Creative Game Name");
		//txtServer = new JTextField (10);

		//set font
		lblGameName.setFont(new Font("Century Gothic", Font.PLAIN, 30));

		pane.add (lblGameName); //Add component to panel
		lblGameName.setBounds (215, 275, 400, 35);

		//Add all components to panel
		pane.add (btnNewGame);
		pane.add (btnLoadGame);
		pane.add (btnExit);

		//Place all components  
		/*      lblServer.setBounds (insets.left + 5, insets.top + 5, lblServer.getPreferredSize().width, lblServer.getPreferredSize().height);
        txtServer.setBounds (lblServer.getX() + lblServer.getWidth() + 5, insets.top + 5, txtServer.getPreferredSize().width, txtServer.getPreferredSize().height);*/
		btnNewGame.setBounds (350, 350, 100, 30);
		btnLoadGame.setBounds (350, 400, 100, 30);
		btnExit.setBounds (350, 450, 100, 30);

		frame1.setVisible (true);
	}
}
