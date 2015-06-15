import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable{
	
	private String Name;					// user �̸�
	private String ID;						// user ID
	private String Password; 			// user �н�����
	private String birth_date;						// user �������
	
	
	// �ƹ��͵� ���� �ٷ� ����(default ������)
	public User() {
		this.setName(null);
		this.setID(null);
		this.setPassword(null);
		this.setBirth_date(null);
	}

	// ���ڰ����� �ٷ� ����
	public User(String Name, String ID, String Password, String birth) {
		this.setName(Name);
		this.setID(ID);
		this.setPassword(Password);
		this.setBirth_date(birth);
	}//using field
	
	
	// getter, setter => ���ȼ�
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
