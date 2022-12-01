import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.*;

import java.awt.BorderLayout;

public class ChatClientRoom extends JFrame{

	private JFrame frame;
	private JPanel totalPanel;
	
	private JPanel roomInfoPanel;
	private JLabel roomInfoNameLabel;
	
	private JScrollPane chattingPanel;
	
	private JPanel inputPanel;
	public static JTextField txtInput;
	private JButton sendButton;
	
	ChatClient ChatClient;
	public String userName;
	public String room_Id;
	public String userList;
	private JLabel roomIdLabel;
	public ChatClientRoom(ChatClient chatClient, String username, String room_id, String userlist) {
		ChatClient = chatClient;
		userName = username; // 현재 자신이름!!!!
		room_Id = room_id;
		userList = userlist;
		System.out.println("roomId : " + room_Id);
		setBounds(0, 0, 400, 680);
	
		roomInfoPanel = new JPanel();
		roomInfoPanel.setLayout(null);
		roomInfoPanel.setBounds(0,0,400,50);
		
		chattingPanel = new JScrollPane();
		chattingPanel.setLayout(null);
		chattingPanel.setBounds(0,50,400,550);
		
		inputPanel = new JPanel();
		inputPanel.setLayout(null);
		inputPanel.setBounds(0,600,400,50);
		
		getContentPane().add(roomInfoPanel);
		getContentPane().add(chattingPanel);
		getContentPane().add(inputPanel);
		
		roomInfoNameLabel = new JLabel(userList + "의 채팅방");
		roomInfoNameLabel.setBounds(8,0,318,50);
		roomInfoPanel.add(roomInfoNameLabel);
		
		roomIdLabel = new JLabel(room_id);
		roomIdLabel.setBounds(338, 0, 62, 50);
		roomInfoPanel.add(roomIdLabel);
		
		txtInput = new JTextField();
		txtInput.setBounds(0,600,330,50);
		txtInput.setColumns(10);
		inputPanel.add(txtInput);
		
		sendButton = new JButton("Send");
		sendButton.setBounds(330,600,70,50);
		inputPanel.add(sendButton);
		
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = txtInput.getText();
				txtInput.setText("");
				msg = msg.trim(); // 앞뒤 공백 제거
				ChatMsg cm = new ChatMsg(userName, "200", room_Id, userList, msg); 
				ChatClient.SendObject(cm);
				
			}
		});
	}
	public void AppendText(ChatMsg cm) {
	}
	public void AppendImage(ChatMsg cm) {
		
	}

}
