import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Frame;

public class ChatClientRoom extends JFrame{

	private JFrame frame;
	private JPanel totalPanel;
	private FileDialog fd;
	private JPanel roomInfoPanel;
	private JLabel roomInfoNameLabel;
	
	private JScrollPane chattingPanel;
	
	private JPanel inputPanel;
	public static JTextField txtInput;
	private JButton sendButton;
	private JButton sendImgButton;
	public JTextPane textArea;
	
	ChatClient ChatClient;
	public String userName;
	public String room_Id;
	public String userList;
	private JLabel roomIdLabel;
	
	public Vector<ChatMsgLabel> chatList;
	public int count = 0;
	public ImageIcon ori_icon;
	
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
		
		inputPanel = new JPanel();
		inputPanel.setLayout(null);
		inputPanel.setBounds(0,600,400,50);
		
		getContentPane().add(roomInfoPanel);

		chattingPanel = new JScrollPane();
		chattingPanel.setBounds(0,50,400,550);

		textArea = new JTextPane();
		textArea.setEditable(true);
		textArea.setPreferredSize(new Dimension(400,50));
		chattingPanel.setViewportView(textArea);

		getContentPane().add(chattingPanel);
		getContentPane().add(inputPanel);
		
		roomInfoNameLabel = new JLabel(userList + "의 채팅방");
		roomInfoNameLabel.setBounds(8,0,318,50);
		roomInfoPanel.add(roomInfoNameLabel);
		
		roomIdLabel = new JLabel(room_id);
		roomIdLabel.setBounds(338, 0, 62, 50);
		roomInfoPanel.add(roomIdLabel);
		
		txtInput = new JTextField();
		txtInput.setBounds(60,600,270,50);
		txtInput.setColumns(10);
		inputPanel.add(txtInput);
		
		sendButton = new JButton("Send");
		sendButton.setBounds(330,600,70,50);
		inputPanel.add(sendButton);
		
		sendImgButton = new JButton("+");
		sendImgButton.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		sendImgButton.setBounds(6, 600, 50, 50);
		inputPanel.add(sendImgButton);
	
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = txtInput.getText();
				txtInput.setText("");
				msg = msg.trim(); // 앞뒤 공백 제거
				ChatMsg cm = new ChatMsg(userName, "200", room_Id, userList, msg); 
				ChatClient.SendObject(cm);
				
			}
		});
		
		sendImgButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame = new JFrame("이미지첨부");
				fd = new FileDialog(frame, "이미지 선택", FileDialog.LOAD);
				// frame.setVisible(true);
				// fd.setDirectory(".\\");
				fd.setVisible(true);
				//System.out.println(fd.getDirectory() + fd.getFile());
				ChatMsg obcm = new ChatMsg(userName, "201", room_Id, userList, "IMG");
				ImageIcon img = new ImageIcon(fd.getDirectory() + fd.getFile());
				obcm.setImg(img);
				ChatClient.SendObject(obcm);				
			}
		});
	}
	public void AppendText(ChatMsg cm) {
		ChatMsgLabel chatMsgLabel = new ChatMsgLabel(cm.getId(), getTime(new Date()).toString(), cm.getData());
		chatMsgLabel.chatPane.setBackground(Color.yellow);
		textArea.setCaretPosition(textArea.getDocument().getLength());
		textArea.insertComponent(chatMsgLabel);
		chattingPanel.repaint();
	}
	
	public void AppendImage(ChatMsg cm) {
		ori_icon = cm.getImg();
		int len = textArea.getDocument().getLength();
		textArea.setCaretPosition(len); // place caret at the end (with no selection)
		Image ori_img = ori_icon.getImage();
		int width, height;
		double ratio;
		width = ori_icon.getIconWidth();
		height = ori_icon.getIconHeight();
		// Image가 너무 크면 최대 가로 또는 세로 200 기준으로 축소시킨다.
		if (width > 200 || height > 200) {
			if (width > height) { // 가로 사진
				ratio = (double) height / width;
				width = 200;
				height = (int) (width * ratio);
			} else { // 세로 사진
				ratio = (double) width / height;
				height = 200;
				width = (int) (height * ratio);
			}
			Image new_img = ori_img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			ImageIcon new_icon = new ImageIcon(new_img);
			textArea.insertIcon(new_icon);
		} else
			textArea.insertIcon(ori_icon);
		len = textArea.getDocument().getLength();
		textArea.setCaretPosition(len);
		textArea.replaceSelection("\n");
		chattingPanel.repaint();

		// ImageViewAction viewaction = new ImageViewAction();
		// new_icon.addActionListener(viewaction); // 내부클래스로 액션 리스너를 상속받은 클래스로
	}
	
	public String getTime(Date date) {
		SimpleDateFormat sdate = new SimpleDateFormat("HH:mm");
		return sdate.format(date);
	}
}
