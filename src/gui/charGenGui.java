package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class charGenGui extends Frame implements ActionListener {
	
	private JLabel lblListStats, lblListHeader, lblName, lblCurrentStats;
	private JButton btnSubmit;
	private JTextField txtName, txtHealth, txtDamage, txtAccuracy, txtDefense;
	
//    ArrayList<JTextField> fields = new ArrayList<JTextField>();
    ArrayList<String> stats = new ArrayList<String>();
	
	//what allows the input into the many input boxes
	//MUST ACCOUNT FOR GREATER THAN 20 POINTS BEING ALLOCATED!!!
	public void statsInput() {
		
	}
	
	//creates the set up for the charGen GUI
	public void launchStatsGui(JPanel panel){
		
		lblName = new JLabel("Name:");
		lblListHeader = new JLabel("Stats:");
		lblListStats = new JLabel("<html>Health<br>Defense<br>Accuracy<br>Damage</html>");
		lblCurrentStats = new JLabel("<html>20<br>10<br>10<br>2</html>");
		
		btnSubmit = new JButton("Submit");
		
		txtName = new JTextField("", 20);
		txtHealth = new JTextField("", 3);
		txtDefense = new JTextField("", 3);
		txtAccuracy = new JTextField("", 3);
		txtDamage = new JTextField("", 3);
		
		panel.add(lblName);
		panel.add(lblListHeader);
		panel.add(lblListStats);
		panel.add(lblCurrentStats);
		panel.add(btnSubmit);
		panel.add(txtName);
		panel.add(txtHealth);
		panel.add(txtDefense);
		panel.add(txtAccuracy);
		panel.add(txtDamage);
		
		lblName.setFont(new Font("Ariel", Font.BOLD, 17));
		lblListHeader.setFont(new Font("Ariel", Font.BOLD, 17));
		lblListStats.setFont(new Font("Ariel", Font.PLAIN, 15));
		lblCurrentStats.setFont(new Font("Ariel", Font.PLAIN, 15));
		
		//Maybe just try to center them instead with a constant like setBounds (CENTER), something like that?
		lblName.setBounds (170, 225, 50, 20);
		lblListHeader.setBounds (170, 265, 100, 20);
		lblListStats.setBounds (175, 275, 100, 100);
		lblCurrentStats.setBounds (302, 275, 100, 100);
		
		btnSubmit.setBounds (535, 400, 100, 30);
		
		txtName.setBounds (230, 228, 125, 20);
		txtHealth.setBounds (565, 285, 40, 20);
		txtDefense.setBounds (565, 305, 40, 20);
		txtAccuracy.setBounds (565, 325, 40, 20);
		txtDamage.setBounds (565, 345, 40, 20);
	}
	
	//Will have actions for the submit button
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand() == "Submit") {
            

//                stats.add();


 //           System.out.println(stats.size());
 //           System.out.println(stats.toString());
        }
	}
	

	//Whenever new game is clicked, it overrides the last saved game!! Override function?
	
}
