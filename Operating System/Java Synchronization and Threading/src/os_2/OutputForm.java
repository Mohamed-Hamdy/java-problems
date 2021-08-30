/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os_2;

/**
 *
 * @author AA
 */
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;

public class OutputForm {

	private JFrame frame;
	JTextArea output_textArea;
	
	public OutputForm() {
		initialize();	
		frame.setVisible(true);
				
	}
	
	public void addUpdates(String str) {
		output_textArea.append(str);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(150, 150, 1100, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Our Router's Connections Simulator");
		label.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 29));
		label.setBounds(180, 20, 767, 30); // right , top ,div size , font size  
		frame.getContentPane().add(label);
		
		
		output_textArea = new JTextArea();
		output_textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
                    output_textArea.setBounds(39, 80, 1000, 750); // right , top , width , high 
		frame.getContentPane().add(output_textArea);
	}
}
