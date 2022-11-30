import java.awt.Checkbox;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.InterruptedIOException;
import java.lang.reflect.Array;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import java.awt.GridLayout;

public class UserList extends JDialog{
	public JButton applyButton;
	public String selectedUserList = "";
	public JCheckBox userCheckBox;
	public int num;
	public String variable;
	public StringBuilder sb = new StringBuilder();
	private JLabel label_1;
	public Vector<String> users = new Vector<>();
	public String state = null;
	private JCheckBox [] userCheckBoxs;
	public UserList(Frame frame, String title) {
		
		super(frame,title,true);
		this.setBounds(0,0,400,400);;
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label = new JLabel("채팅 상대를 선택하세요.");
		label.setBounds(10,15,200,30);
		getContentPane().add(label);
		
		label_1 = new JLabel("");
		getContentPane().add(label_1);
			
		applyButton = new JButton("apply");
		applyButton.setBounds(380,210,50,40);
		

//		applyButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(userCheckBox.isSelected())
//			}
//		});
		getContentPane().add(applyButton);
		
        setLocationRelativeTo(null);
		
	}
	
	public void makeCheckbox(Vector<String> users) {
		userCheckBoxs = new JCheckBox[users.size()];
		for(int i=0; i<users.size(); i++) {
			userCheckBoxs[i] = new JCheckBox(users.elementAt(i));
			userCheckBoxs[i].addItemListener(new ItemListener() {			
				@Override
				public void itemStateChanged(ItemEvent e) {		
					if(e.getItem() == userCheckBoxs[0]) {
						System.out.println("0 : " + userCheckBoxs[1]);
					}
					if(e.getItem() == userCheckBoxs[1]) {
						System.out.println("1 : " + userCheckBoxs[1]);
					}
				}
			});
			getContentPane().add(userCheckBoxs[i]);
		}
	}

	public String showDialog() {
		
		makeCheckbox(users);		
		
		setVisible(true);
		if(selectedUserList == null) return null;
		else return selectedUserList;		
	}
	
	public void setUsers(String users) {
		this.users.add(users);			
		
	}
	
}
