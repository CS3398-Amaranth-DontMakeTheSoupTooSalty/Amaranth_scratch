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
	private JFrame mainFrame;
	private Container container;
	private JPanel panel;
	private JButton btnNewGame, btnExit, btnLoadGame;
	private JLabel lblGameName;
	//private Insets insets;
	
	public void launchGUI(){

		//Create the frame
		mainFrame = new JFrame ("Game GUI Test");
		//Set its size to 800x800 pixels
		mainFrame.setSize (new Dimension(800,800));
		//Set it to end program when x button is clicked
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set its default opening position to center of screen
		mainFrame.setLocationRelativeTo(null);
		//Set so the window can't change size?
	    mainFrame.setResizable(false);
	    
		//Prepare panel
		panel = new JPanel();
		//allows the panels to actually show up on the frame
		container = mainFrame.getContentPane();
		//a representation of the borders of a container. It specifies the space that a container must leave at each of its edges
		//insets = panel.getInsets(); 
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
		

		mainFrame.setVisible (true);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()== "New Game"){
		    //mainFrame.revalidate();
            //mainFrame.repaint();
			panel.removeAll();
			panel.updateUI();
			//call charGen?
			charGenGui test = new charGenGui();
			test.launchStatsGui(panel);
		}
		else if(e.getActionCommand()== "Load Game"){
			//mainFrame.revalidate();
            //mainFrame.repaint();
			panel.removeAll();
			panel.updateUI();
			//load the saved game? Deal with reading save file?
		}
		else if(e.getActionCommand()== "Exit"){
			System.exit(0);
		}
	}
	
}
