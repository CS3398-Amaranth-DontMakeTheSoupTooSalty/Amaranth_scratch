/**
 * 
 */
/**
 * @author DemiDemon
 *
 */
package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Frame extends JFrame implements ActionListener {
	private JFrame frame1;
	private Container container;
	private JPanel panel;
	private JButton btnNewGame, btnExit, btnLoadGame;
	private JLabel lblGameName;
	
	public void launchGUI(){

		//JTextField txtServer;
		//Insets insets;

		//Create the frame
		frame1 = new JFrame ("Game GUI Test");
		//Set its size to 800x800 pixels
		frame1.setSize (new Dimension(800,800));
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setLocationRelativeTo(null);
		//Prepare panel
		panel = new JPanel();
		container = frame1.getContentPane();
		//insets = pane.getInsets();
		container.add(panel);
		//Apply the null layout
		panel.setLayout (null);

		btnNewGame = new JButton ("New Game");
		btnLoadGame = new JButton ("Load Game");
		btnExit = new JButton ("Exit");
		lblGameName = new JLabel ("Le Creative Game Name");
		
		btnNewGame.addActionListener(this);
		btnLoadGame.addActionListener(this);
	 	btnExit.addActionListener(this);

		//txtServer = new JTextField (10);

		//set font
		lblGameName.setFont(new Font("Century Gothic", Font.PLAIN, 30));

		panel.add (lblGameName); //Add component to panel
		lblGameName.setBounds (215, 275, 400, 35);

		//Add all components to panel
		panel.add (btnNewGame);
		panel.add (btnLoadGame);
		panel.add (btnExit);

		//Place all components  
		/*      lblServer.setBounds (insets.left + 5, insets.top + 5, lblServer.getPreferredSize().width, lblServer.getPreferredSize().height);
        txtServer.setBounds (lblServer.getX() + lblServer.getWidth() + 5, insets.top + 5, txtServer.getPreferredSize().width, txtServer.getPreferredSize().height);*/
		btnNewGame.setBounds (350, 350, 100, 30);
		btnLoadGame.setBounds (350, 400, 100, 30);
		btnExit.setBounds (350, 450, 100, 30);
		

		frame1.setVisible (true);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()== "New Game"){
			panel.removeAll();
			panel.updateUI();
			//call charGen?
		}
		else if(e.getActionCommand()== "Load Game"){
			panel.removeAll();
			panel.updateUI();
			//load the saved game? Deal with reading save file?
		}
		else if(e.getActionCommand()== "Exit"){
			System.exit(0);
		}
	}
	
/*	private class CloseListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	
	private class NewGameListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			panel.removeAll();
			panel.updateUI();
			//call charGen?
		}
	}
	
	private class LoadGameListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			panel.removeAll();
			panel.updateUI();
			//load the saved game? Deal with reading save file?
		}
	}*/
}
