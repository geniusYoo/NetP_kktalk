
// ChatMsg.java 채팅 메시지 ObjectStream 용.
import java.io.Serializable;
import java.sql.Date;

import javax.swing.ImageIcon;

class ChatMsg implements Serializable {
	private static final long serialVersionUID = 1L;
	public String id;
	public String code; 
	public String data;
	public String room_id;
	public ImageIcon img;
	public String userlist;
	public Date date;
	
	public ChatMsg(String id, String code, String room_id, String userlist, String msg) {
		this.id = id;
		this.code = code;
		this.room_id = room_id;
		this.userlist = userlist;
		this.data = msg;
	}
	
	public String getUserList() {
		return userlist;
	}
	
	public void setUserList(String userList) {
		this.userlist = userList;
	}
	
	public String getRoomId() {
		return room_id;
	}

	public void setRoomId(String room_id) {
		this.room_id = room_id;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ImageIcon getImg() {
		return img;
	}
	public void setImg(ImageIcon img) {
		this.img = img;
	}

}