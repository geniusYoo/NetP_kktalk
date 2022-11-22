import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;

public class UserRoom extends JPanel{

	private JFrame frame;
	private JPanel roomPane;
	public JLabel roomImageLabel;
	public JLabel roomNameLabel;
	public JLabel roomLastChatLabel;

	private ImageIcon icon;
	
	
	public UserRoom() {
		frame = new JFrame();
		frame.setBounds(0,0,300,50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		roomPane = new JPanel();
		roomPane.setBounds(0,0,300,50);
		
		roomImageLabel = new JLabel("");
        roomImageLabel.setIcon(new ImageIcon("/Users/geniuus/Downloads/JavaChatImg/default_profile.jpeg"));
        roomImageLabel.setBounds(0,0,50,50);
        
        roomNameLabel = new JLabel("roomName");
        roomNameLabel.setBounds(60,2,240,20);
        
        roomLastChatLabel = new JLabel("last chats");
        roomLastChatLabel.setBounds(60,20,240,30);
        
		roomPane.setLayout(null);
		
		roomPane.add(roomImageLabel);
		roomPane.add(roomNameLabel);
		roomPane.add(roomLastChatLabel);		
	}
	
	public void addActionListener() {
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(roomPane.equals(e.getSource())) {		
					UserChatRoom userChatRoom = new UserChatRoom();
					setVisible(false);
				}
				
			}
		};
	}


}
