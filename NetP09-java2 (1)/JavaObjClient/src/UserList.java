import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class UserList extends JFrame{

	public UserList() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		setContentPane(panel);
		
		JLabel label = new JLabel("ä�� ��븦 �����ϼ���.");
		label.setBounds(10,15,200,30);
		panel.add(label);
		
		JCheckBox userCheckBox1 = new JCheckBox("user1");
		userCheckBox1.setBounds(0, 50, 70, 30);
		panel.add(userCheckBox1);
		
		JCheckBox userCheckBox2 = new JCheckBox("user2");
		userCheckBox2.setBounds(0, 90, 70, 30);
		panel.add(userCheckBox2);
		
		JCheckBox userCheckBox3 = new JCheckBox("user3");
		userCheckBox3.setBounds(0, 130, 70, 30);
		panel.add(userCheckBox3);
		
		JCheckBox userCheckBox4 = new JCheckBox("user4");
		userCheckBox4.setBounds(0, 170, 70, 30);
		panel.add(userCheckBox4);
		
		JCheckBox userCheckBox5 = new JCheckBox("user5");
		userCheckBox5.setBounds(0, 210, 70, 30);
		panel.add(userCheckBox5);
		
		JButton applyButton = new JButton("apply");
		applyButton.setBounds(380,210,50,40);
		panel.add(applyButton);
		
	}
}