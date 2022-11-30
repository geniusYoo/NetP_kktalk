import java.awt.EventQueue;

import javax.swing.*;

import java.awt.BorderLayout;

public class UserChatRoom extends JFrame{

	private JFrame frame;
	private JPanel totalPanel;
	
	private JPanel roomInfoPanel;
	private JLabel roomInfoImageLabel;
	private JLabel roomInfoNameLabel;
	
	private JScrollPane chattingPanel;
	private JLabel nameLabel;
	private JLabel imageLabel;
	private JLabel chatLabel;
	private JLabel timeLabel;
	
	private JPanel inputPanel;
	
	public static JTextPane textArea;
	public static JTextField txtInput;
	private JButton sendButton;

	public UserChatRoom() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 400, 670);
		
		totalPanel = new JPanel();
		totalPanel.setLayout(null);
		totalPanel.setBounds(0, 0, 400, 670);
		setContentPane(totalPanel);
		
		roomInfoPanel = new JPanel();
		roomInfoPanel.setLayout(null);
		roomInfoPanel.setBounds(0,0,400,50);
		
		chattingPanel = new JScrollPane();
		chattingPanel.setLayout(null);
		chattingPanel.setBounds(0,50,400,530);
		
		inputPanel = new JPanel();
		inputPanel.setLayout(null);
		inputPanel.setBounds(0,580,400,65);
		
		totalPanel.add(roomInfoPanel);
		totalPanel.add(chattingPanel);
		totalPanel.add(inputPanel);
		
		roomInfoImageLabel = new JLabel("image");
		roomInfoNameLabel = new JLabel("name");
		roomInfoImageLabel.setBounds(10,0,50,50);
		roomInfoNameLabel.setBounds(60,0,340,50);
		
		roomInfoPanel.add(roomInfoImageLabel);
		roomInfoPanel.add(roomInfoNameLabel);
		
		textArea = new JTextPane();
		textArea.setEditable(true);
		chattingPanel.setViewportView(textArea);
		
		txtInput = new JTextField();
		txtInput.setBounds(0,530,330,50);
		chattingPanel.add(txtInput);
		txtInput.setColumns(10);

		sendButton = new JButton("Send");
		sendButton.setBounds(330,530,70,50);
		chattingPanel.add(sendButton);
		
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
