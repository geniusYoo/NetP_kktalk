import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;

public class UserProfile extends JPanel{

	private JFrame frame;
	private JPanel profilePane;
	private JPanel userImagePane;
	private JPanel usernamePane;
	private ImageIcon icon;
	
	public UserProfile() {
		frame = new JFrame();
		frame.setBounds(0,0,300,50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		profilePane = new JPanel();
		profilePane.setBounds(0,0,300,50);
		
		JLabel profileImageLabel = new JLabel("");
        profileImageLabel.setIcon(new ImageIcon("/Users/kimjw/Desktop/JavaChatImg/icon/default_profile.jpeg"));
        profileImageLabel.setBounds(0,0,50,50);
        
        JLabel profileNameLabel = new JLabel("username");
        profileNameLabel.setBounds(60,0,240,50);
        
		profilePane.setLayout(null);
		
		profilePane.add(profileImageLabel);
		profilePane.add(profileNameLabel);

		
	}


}
