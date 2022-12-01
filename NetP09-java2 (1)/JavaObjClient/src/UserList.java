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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
	public JCheckBox userCheckBox;
	public int num;
	public String variable;
	public StringBuilder sb = new StringBuilder();
	public String selectedUserList;
	public JCheckBox [] userCheckBoxs;
	private JLabel label_1;
	public Vector<String> users = new Vector<>();
	public Vector<String> selectedUsers = new Vector<>();
	public String state = null;
	public UserList(Frame frame, String title) {
		
		super(frame,title,true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(0,0,400,400);;
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label = new JLabel("채팅 상대를 선택하세요.");
		label.setBounds(10,15,200,30);
		getContentPane().add(label);
		
		label_1 = new JLabel("");
		getContentPane().add(label_1);
			
		applyButton = new JButton("apply");
		applyButton.setBounds(380,210,50,40);
		

		applyButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				sb.append(userCheckBoxs[0].getText()); // 자신은 방에 무조건 포함되어야 하므로
				selectedUserList = sb.toString();
				setVisible(false);
				
			}
		});
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
					if(e.getStateChange() == ItemEvent.SELECTED) { // select 되면 stringbuilder에 체크박스의 username append
						for(int k=0; k<users.size(); k++) {
							if(e.getItem() == userCheckBoxs[k]) {
								sb.append(userCheckBoxs[k].getText());
								sb.append(" ");
							}
						}
						System.out.println("selected / sb = " + sb.toString());
					}
					else { // 체크박스가 해제되면 sb에서 제거
						JCheckBox box = (JCheckBox) e.getItem();
						int index = sb.indexOf(box.getText());
						sb.delete(index, index + box.getText().length()+1); // 뒤에 띄어쓰기까지 제거하기 위해 +1 
						System.out.println("detached / sb = " + sb.toString());

					}
					
				}
			});
			getContentPane().add(userCheckBoxs[i]);
			userCheckBoxs[0].setEnabled(false);
		}
	}

	public String showDialog() {
		makeCheckbox(users);
		setVisible(true);

		if(selectedUserList == null) return null;
		else return selectedUserList;		
	}
	
	public void setUsers(Vector<String> users) {	
		this.users.clear();
		for(int i=0; i<users.size(); i++) {				
			this.users.add(users.elementAt(i));
		}
	}
	
}
