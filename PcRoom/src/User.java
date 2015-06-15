import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable{
	
	private String Name;					// user 이름
	private String ID;						// user ID
	private String Password; 			// user 패스워드
	private String birth_date;						// user 생년월일
	
	
	// 아무것도 없이 바로 생성(default 생성자)
	public User() {
		this.setName(null);
		this.setID(null);
		this.setPassword(null);
		this.setBirth_date(null);
	}

	// 인자값으로 바로 생성
	public User(String Name, String ID, String Password, String birth) {
		this.setName(Name);
		this.setID(ID);
		this.setPassword(Password);
		this.setBirth_date(birth);
	}//using field
	
	
	// getter, setter => 보안성
	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}


	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}


	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}


	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth) {
		this.birth_date = birth;
	}
	
}
