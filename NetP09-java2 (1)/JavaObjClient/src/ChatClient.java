import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JToggleButton;
import javax.swing.JList;

public class ChatClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtInput;
	private String UserName;
	private static final int BUF_LEN = 128; // Windows 처럼 BUF_LEN 을 정의
	private Socket socket; // 연결소켓

	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	private Frame frame;
	private FileDialog fd;
	private JButton imgBtn;

	// TabBar 구성하는 변수들
	private JPanel tabBarPanel;
	private JPanel userListPanel;
	private JPanel roomListPanel;
	private JButton userListButton;
	private JButton roomListButton;
	private JButton exitButton;
	private JButton createRoomButton;
	
	JTextPane textArea;
	JTextField textInput;
	
	public ChatClient(String username, String ip_addr, String port_no) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 630);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		makeTabBar(); // TabBar 구성하기
		makeUserListPanel();
		makeRoomListPanel();
		addActionListener(); // 탭바 버튼에 actionListener 달
//		UserChatRoom userChatRoom = new UserChatRoom();
//		textArea = UserChatRoom.textArea;
//		textInput = UserChatRoom.txtInput;
//		roomListPanel.setVisible(false);
		try {
			socket = new Socket(ip_addr, Integer.parseInt(port_no));

			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());

			//SendMessage("/login " + UserName);
			ChatMsg obcm = new ChatMsg(UserName, "100", "Hello");
			SendObject(obcm);
			
			ListenNetwork net = new ListenNetwork();
			net.start();

		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AppendText("connect error");
		}
		
		
	}
	public void makeTabBar() {
		tabBarPanel = new JPanel();
		tabBarPanel.setLayout(new GridLayout(8, 1, 0, 0));
		tabBarPanel.setBounds(0, 0, 100, 602);
		contentPane.add(tabBarPanel);
		
		userListButton = new JButton("");
		tabBarPanel.add(userListButton);
		
		roomListButton = new JButton("");
		tabBarPanel.add(roomListButton);
		
		ImageIcon personIcon = new ImageIcon(ChatClient.class.getResource("/icons/person.png"));
        Image personImg = personIcon.getImage();
        Image updatePersonImg = personImg.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon updatePersonIcon = new ImageIcon(updatePersonImg);
        
        ImageIcon chatIcon = new ImageIcon(ChatClient.class.getResource("/icons/chat.png"));
        Image chatImg = chatIcon.getImage();
        Image updateChatImg = chatImg.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon updateChatIcon = new ImageIcon(updateChatImg);
        
        ImageIcon exitIcon = new ImageIcon(ChatClient.class.getResource("/icons/exit.png"));
        Image exitImg = exitIcon.getImage();
        Image updateExitImg = exitImg.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon updateExitIcon = new ImageIcon(updateExitImg);

        userListButton.setIcon(updatePersonIcon);
        userListButton.setBorderPainted(false);
        userListButton.setContentAreaFilled(false);
        
        roomListButton.setIcon(updateChatIcon);
        roomListButton.setBorderPainted(false);
        roomListButton.setContentAreaFilled(false);
	}
	
	public void makeUserListPanel() {
		userListPanel = new JPanel();
		userListPanel.setLayout(null);
		contentPane.add(userListPanel);
		userListPanel.setBounds(100, 0, 300, 600);

		UserProfile user1 = new UserProfile("geniuus"); 
		UserProfile user2 = new UserProfile("youngjae");
		UserProfile user3 = new UserProfile("user3");
		UserProfile user4 = new UserProfile("user4");
		UserProfile user5 = new UserProfile("user5");
	
		userListPanel.add(user1);
		user1.setLayout(null);
		user1.setBounds(0,0,300,50);
		user1.add(user1.profilePane);
		
		userListPanel.add(user2);
		user2.setLayout(null);
		user2.setBounds(0,60,300,50);
		user2.add(user2.profilePane);

		userListPanel.add(user3);
		user3.setLayout(null);
		user3.setBounds(0,120,300,50);
		user3.add(user3.profilePane);
		
		userListPanel.add(user4);
		user4.setLayout(null);
		user4.setBounds(0,180,300,50);
		user4.add(user4.profilePane);
		
		userListPanel.add(user5);
		user5.setLayout(null);
		user5.setBounds(0,240,300,50);
		user5.add(user5.profilePane);
	}
	
	public void makeRoomListPanel() {
		roomListPanel = new JPanel();
		roomListPanel.setBounds(100, 0, 300, 600);
		roomListPanel.setLayout(null);
		contentPane.add(roomListPanel);
		
		JLabel lblChatLabel = new JLabel("채팅");
		lblChatLabel.setBounds(20, 20, 50, 40);
		lblChatLabel.setFont(new Font("D2Coding",Font.BOLD, 20));
		roomListPanel.add(lblChatLabel);
		
		// 새로운 채팅 만드는 버튼 만들기 
		JButton makeChatButton = new JButton("");
		makeChatButton.setBounds(230, 20, 50, 40);
		ImageIcon chatIcon = new ImageIcon(ChatClient.class.getResource("/icons/plus_chat.png"));
        Image chatImg = chatIcon.getImage();
        Image updateChatImg = chatImg.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon updateChatIcon = new ImageIcon(updateChatImg);
        makeChatButton.setIcon(updateChatIcon);
        makeChatButton.setBorderPainted(false);
        makeChatButton.setContentAreaFilled(false);
		roomListPanel.add(makeChatButton);
		
		// roomList 보여주기 높이 70부터 
//		UserRoom userRoom = new UserRoom();
//		userRoom.setBounds(5,70,290,50);
//		roomListPanel.add(userRoom);
		
	}
	
	public void addActionListener() {
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userListButton.equals(e.getSource())) {		
					userListPanel.setVisible(true);
					roomListPanel.setVisible(false);
				}
				else if(roomListButton.equals(e.getSource())) {
					userListPanel.setVisible(false);
					roomListPanel.setVisible(true);
				}
				else if(createRoomButton.equals(e.getSource())) {
					
				}
			}
		};
	}
	
	// Server Message를 수신해서 화면에 표시
	class ListenNetwork extends Thread {
		public void run() {
			while (true) {
				try {					
					Object obcm = null;
					String msg = null;
					ChatMsg cm;
					try {
						obcm = ois.readObject();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}
					if (obcm == null)
						break;
					if (obcm instanceof ChatMsg) {
						cm = (ChatMsg) obcm;
						msg = String.format("[%s] %s", cm.getId(), cm.getData());
					} else
						continue;
					switch (cm.getCode()) {
						case "100":
							System.out.println("enter chatting");
							break;
						case "200": // chat message
							AppendText(msg);
							break;
						case "300": // Image 첨부
							AppendText("[" + cm.getId() + "]");
							AppendImage(cm.img);
							break;
					}
				} catch (IOException e) {
					AppendText("ois.readObject() error");
					try {
						ois.close();
						oos.close();
						socket.close();

						break;
					} catch (Exception ee) {
						break;
					} // catch문 끝
				} // 바깥 catch문끝

			}
		}
	}

//	// keyboard enter key 치면 서버로 전송
//	class TextSendAction implements ActionListener {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			// Send button을 누르거나 메시지 입력하고 Enter key 치면
//			if (e.getSource() == btnSend || e.getSource() == txtInput) {
//				String msg = null;
//				// msg = String.format("[%s] %s\n", UserName, txtInput.getText());
//				msg = txtInput.getText();
//				SendMessage(msg);
//				txtInput.setText(""); // 메세지를 보내고 나면 메세지 쓰는창을 비운다.
//				txtInput.requestFocus(); // 메세지를 보내고 커서를 다시 텍스트 필드로 위치시킨다
//				if (msg.contains("/exit")) // 종료 처리
//					System.exit(0);
//			}
//		}
//	}

//	class ImageSendAction implements ActionListener {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			// 액션 이벤트가 sendBtn일때 또는 textField 에세 Enter key 치면
//			if (e.getSource() == imgBtn) {
//				frame = new Frame("이미지첨부");
//				fd = new FileDialog(frame, "이미지 선택", FileDialog.LOAD);
//				// frame.setVisible(true);
//				// fd.setDirectory(".\\");
//				fd.setVisible(true);
//				//System.out.println(fd.getDirectory() + fd.getFile());
//				ChatMsg obcm = new ChatMsg(UserName, "300", "IMG");
//				ImageIcon img = new ImageIcon(fd.getDirectory() + fd.getFile());
//				obcm.setImg(img);
//				SendObject(obcm);
//			}
//		}
//	}

	ImageIcon icon1 = new ImageIcon("src/zzz.png");

	public void AppendIcon(ImageIcon icon) {
		int len = textArea.getDocument().getLength();
		// 끝으로 이동
		textArea.setCaretPosition(len);
		textArea.insertIcon(icon);
	}

	// 화면에 출력
	public void AppendText(String msg) {
		msg = msg.trim(); // 앞뒤 blank와 \n을 제거한다.
		int len = textArea.getDocument().getLength();
		// 끝으로 이동
		textArea.setCaretPosition(len);
		textArea.replaceSelection(msg + "\n");
	}

	public void AppendImage(ImageIcon ori_icon) {
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
		// ImageViewAction viewaction = new ImageViewAction();
		// new_icon.addActionListener(viewaction); // 내부클래스로 액션 리스너를 상속받은 클래스로
	}

	// Windows 처럼 message 제외한 나머지 부분은 NULL 로 만들기 위한 함수
	public byte[] MakePacket(String msg) {
		byte[] packet = new byte[BUF_LEN];
		byte[] bb = null;
		int i;
		for (i = 0; i < BUF_LEN; i++)
			packet[i] = 0;
		try {
			bb = msg.getBytes("euc-kr");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		for (i = 0; i < bb.length; i++)
			packet[i] = bb[i];
		return packet;
	}

	// Server에게 network으로 전송
	public void SendMessage(String msg) {
		try {
			// dos.writeUTF(msg);
//			byte[] bb;
//			bb = MakePacket(msg);
//			dos.write(bb, 0, bb.length);
			ChatMsg obcm = new ChatMsg(UserName, "200", msg);
			oos.writeObject(obcm);
		} catch (IOException e) {
			// AppendText("dos.write() error");
			AppendText("oos.writeObject() error");
			try {
//				dos.close();
//				dis.close();
				ois.close();
				oos.close();
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.exit(0);
			}
		}
	}

	public void SendObject(Object ob) { // 서버로 메세지를 보내는 메소드
		try {
			oos.writeObject(ob);
		} catch (IOException e) {
			// textArea.append("메세지 송신 에러!!\n");
			AppendText("SendObject Error");
		}
	}
}
