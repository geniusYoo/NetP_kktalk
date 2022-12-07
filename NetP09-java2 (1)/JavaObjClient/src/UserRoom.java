import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class UserRoom extends JPanel{
	private static final long serialVerionUID = 1L;
	private ImageIcon roomIcon;

	private JPanel contentPane;
	public JLabel roomImageLabel;
	public JLabel roomNameLabel;
	public JLabel roomLastChatLabel;
	public JLabel roomLastTimeLabel;
	
	public String username; // 채팅방을 생성한 username
	public String room_id;
	public String userList;
	public String userStatus;
	public String lastChatMsg;
	public String lastChatTime;
	public JButton chatRoomNameButton;
	public ChatClientRoom chatRoom; // 실제 채팅창
	public Boolean online;
	public ChatClient chatClientMain; // 메인 client
	public int flag; // 1:1채팅인지 그룹채팅인지 (룸 아이콘 다르게하려고)
	
	public UserRoom(ChatClient chatClientMain, ImageIcon icon, String name, String room_id, String userlist, String lastChatMsg, String lastChatTime) {
		this.chatClientMain = chatClientMain;
		this.username = name;
		this.room_id = room_id;
		this.userList = userlist;
		System.out.println("room_id >>> " + room_id);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		setBorder(null);
		setPreferredSize(new Dimension(300,62));
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		String [] users = this.userList.split(" ");
		String label = users[0];
		for(int i=1; i<users.length; i++) {
			label += (", " + users[i]);
		}
		if(users.length <= 2) flag = 1; // 1:1 채팅인경우
		else if(users.length > 2) flag = 2; // 그룹 채팅인경
		
//		profileImageLabel.setIcon(new ImageIcon(UserProfile.class.getResource("/icons/default_profile.jpeg")));
		ImageIcon soloIcon = new ImageIcon(UserRoom.class.getResource("/icons/soloroom.png"));
		Image soloImg = soloIcon.getImage();
        Image updateSoloImg = soloImg.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon updateSoloIcon = new ImageIcon(updateSoloImg);
        
		ImageIcon groupIcon = new ImageIcon(UserRoom.class.getResource("/icons/room.png"));
		Image groupImg = groupIcon.getImage();
        Image updateGroupImg = groupImg.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon updateGroupIcon = new ImageIcon(updateGroupImg);
		if(flag == 1) {
			roomImageLabel = new JLabel(updateSoloIcon);
		}
		else {
			roomImageLabel = new JLabel(updateGroupIcon);
		}
		roomImageLabel.setBorder(null);
		roomImageLabel.setBackground(Color.LIGHT_GRAY);
		roomImageLabel.setBounds(2, 1, 50, 50);
		add(roomImageLabel);
		
		chatRoomNameButton = new JButton(label);
		chatRoomNameButton.setBounds(55,1,148,22);
		chatRoomNameButton.setOpaque(false);
		chatRoomNameButton.setFocusPainted(false);
		chatRoomNameButton.setHorizontalAlignment(SwingConstants.LEFT);
		chatRoomNameButton.setBackground(Color.LIGHT_GRAY);
		chatRoomNameButton.setBorderPainted(false);
		chatRoomNameButton.setBorder(null);
		chatRoomNameButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chatRoom == null) {
					chatRoom = new ChatClientRoom(chatClientMain, username, room_id, userlist);
					chatRoom.setVisible(true);
				}
				else {
					chatRoom.setVisible(true);
				}
			}
		});
		
		add(chatRoomNameButton);
		
		roomLastChatLabel = new JLabel(lastChatMsg);
		roomLastChatLabel.setHorizontalAlignment(SwingConstants.LEFT);
		roomLastChatLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		roomLastChatLabel.setBorder(null);
		roomLastChatLabel.setBackground(Color.LIGHT_GRAY);
		roomLastChatLabel.setBounds(55,35,148,16);
		add(roomLastChatLabel);
		
		roomLastTimeLabel = new JLabel("time");
		roomLastTimeLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 9));
		roomLastTimeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		roomLastTimeLabel.setBounds(267,1,27,15);
		add(roomLastTimeLabel);
		
		setVisible(true);
		chatRoom = new ChatClientRoom(chatClientMain, username, room_id, userlist);
		setVisible(true);
	
	}
	
	
	public void AppendText(ChatMsg cm) {
		System.out.println("success append text!");
		setLastMsg(cm);
		chatRoom.AppendText(cm);
		
	}
	public void AppendImage(ChatMsg cm) {
		setLastMsg(cm);
		chatRoom.AppendImage(cm);
	}
	public void setLastMsg(ChatMsg cm) {
		lastChatMsg = cm.getData();
		roomLastChatLabel.setText(lastChatMsg);
		roomLastTimeLabel.setText(getTime(new Date()));
	}
	public String getTime(Date date) {
		SimpleDateFormat sdate = new SimpleDateFormat("hh:mm");
		return sdate.format(date);
	}

}
