import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class UserChatRoom extends JFrame{

	private JFrame frame;
	private JPanel totalPanel;
	
	private JPanel roomInfoPanel;
	private JLabel roomInfoImageLabel;
	private JLabel roomInfoNameLabel;
	
	private JPanel chattingPanel;
	private JLabel nameLabel;
	private JLabel imageLabel;
	private JLabel chatLabel;
	private JLabel timeLabel;
	
	public UserChatRoom() {
		setBounds(0, 0, 400, 630);
		
		totalPanel = new JPanel();
		totalPanel.setLayout(null);
		totalPanel.setBounds(0, 0, 400, 630);
		
		roomInfoPanel = new JPanel();
		roomInfoPanel.setLayout(null);
		roomInfoPanel.setBounds(0,0,400,50);
		
		chattingPanel = new JPanel();
		chattingPanel.setLayout(null);
		chattingPanel.setBounds(0,50,400,580);
		
		totalPanel.add(roomInfoPanel);
		totalPanel.add(chattingPanel);
		
		roomInfoImageLabel = new JLabel("image");
		roomInfoNameLabel = new JLabel("name");
		roomInfoImageLabel.setBounds(10,0,50,50);
		roomInfoNameLabel.setBounds(60,0,340,50);
		
		roomInfoPanel.add(roomInfoImageLabel);
		roomInfoPanel.add(roomInfoNameLabel);
		
		
		
		
		nameLabel = new JLabel();
		imageLabel = new JLabel();
		chatLabel = new JLabel();
		timeLabel = new JLabel();

		chattingPanel.add(nameLabel);
		chattingPanel.add(imageLabel);
		chattingPanel.add(chatLabel);
		chattingPanel.add(timeLabel);
		
		
	}

}
