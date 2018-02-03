//still needs to be connected to the right code 

package physics;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class GUI extends JPanel
{
	
	 public static void main(String[] args)
	    {
		
	        JFrame frame = new JFrame("GUI Application for Percolation Project");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(500, 500); 

	        GUI p = new GUI();            
	        frame.add(p);
	       
	        frame.setVisible(true);
	      
	    }
	   
	 
private JButton[] buttons;
private JLabel Vertex , Probability , Conductivity;
private JTextField  Ver , Prob , Cond;
private JButton Build;
private BufferedImage image;

public GUI()
{
	
	setLayout(new BorderLayout());
	add(new HeadPanel(), BorderLayout.NORTH);
	add(new BodyPanel(), BorderLayout.CENTER);
	add(new bottomBarPanel(), BorderLayout.SOUTH);

}

private class HeadPanel extends JPanel
{
	public HeadPanel()
	{
		JPanel p = new JPanel(new GridBagLayout());
		
		setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        
		JLabel head = new JLabel("Percolation");
		JLabel SUBhead = new JLabel("a WebSite for Physical JCE Collage");
		
		head.setFont(new Font ("serif", Font.BOLD, 40));
		head.setFont(new Font ("serif", Font.PLAIN, 30));
		add(head);
		add(SUBhead);
    }
}

private class bottomBarPanel extends JPanel
{
	public bottomBarPanel()
	{
		 JButton Build = new JButton("Build");
		add(Build);	
	}
}

private class BodyPanel extends JPanel
{
	//JLabel Vertex , Probability , Conductivity;
	// JTextField  Ver , Prob , Cond;

	public BodyPanel()
	{
		Vertex = new JLabel("Input vertices :");
		Probability = new JLabel("Input Probability :");
		Conductivity = new JLabel("Input Conductivity :");
		Vertex.setFont(new Font ("serif", Font.PLAIN, 30));
		Conductivity.setFont(new Font ("serif", Font.PLAIN, 30));
		Probability.setFont(new Font ("serif", Font.PLAIN, 30));
		Ver = new JTextField("Input vertices :",30);
		Prob = new JTextField("Input Prob :");
		Cond = new JTextField("Input Cond :");
		JPanel controls = new JPanel();
		controls.setBackground(Color.GRAY);
		controls.setLayout(new GridLayout(3,3,0,5));
		
		controls.add(Vertex);
		controls.add(Ver);
		controls.add(Probability);
		controls.add(Prob);
		controls.add(Conductivity);
		controls.add(Cond);
			
		this.setLayout(new BorderLayout());
		add(controls, BorderLayout.CENTER);
		
		   
	    }
	}
}
