import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class Window {
	ArrayList<User> userList = new ArrayList<User>();

	// �¼����������ӿ� ����
	JFrame selectSeatFrame;
	ArrayList<JButton> seatBtnList = new ArrayList<JButton>();		// ��ư ������ ArrayList
	int selectSeatIndex = -1;										// ���õ� �¼� �ε��� �����

	// �α��������ӿ� ����
	JFrame loginFrame;
	JLabel login_idLabel;
	JTextField login_idTextField;
	JLabel login_pwLabel;
	JTextField login_pwTextField;
	JButton login_loginBtn;
	JButton login_addUserBtn;
	JButton login_nonUserBtn;
	JButton login_findUserBtn;

	// ȸ������ �����ӿ� ����
	JFrame addUserFrame;
	JLabel addUser_nameLabel;
	JLabel addUser_idLabel;
	JLabel addUser_pwLabel;
	JLabel addUser_birthLabel;
	JTextField addUser_nameField;
	JTextField addUser_idField;
	JTextField addUser_pwField;
	JTextField addUser_birthField;
	JButton addUser_okBtn;
	JButton addUser_cancelBtn;
	// ȸ������ ���� ��¿� ���̾�α�
	ErrorDialog errorDialog = new ErrorDialog();

	// �ð����� �����ӿ� ����
	JFrame selectTimeFrame;
	boolean user = false;
	// �ð����� �г�
	JPanel selectTimePanel;
	int time_count = 1;
	JLabel selectSeatLabel;
	JLabel timeCountLabel;
	Button timePlusBtn;
	Button timeMinusBtn;
	// �ð� ���� �г�
	JPanel PayPanel;
	JButton payOkBtn;
	JButton payCancelBtn;
	JLabel hourLabel;
	JLabel countLabel;
	JLabel totalLabel;
	JLabel infoMemLabel;
	JLabel infoNonMemLabel;
	JLabel totalFareLabel;
	int fare = 0;

	// ȸ��ã�� ������ ����
	JFrame findUserFrame;
	// �̸����� ã��
	JPanel findUserPanel;
	JLabel findUser_nameLabel;
	JTextField findUser_nameField;
	JButton findUser_findButton;
	JLabel findUser_findUserInfoLabel;
	JButton findUser_okBtn;
	// ����, Ż��
	JPanel findUser_afterPanel;
	JLabel findUser_findUserAfterLabel;
	JButton findUser_findUsermodifyBtn;				
	JButton findUser_findUserwithdrawBtn;
	
	// ȸ�� ���� ������ ����
	int findUserIndex = 0;
	JFrame modifyUserFrame;
	JLabel modify_nameLabel;
	JLabel modify_idLabel;
	JLabel modify_pwLabel;
	JLabel modify_pwInfoLabel;
	JLabel modify_newpwLabel;
	JLabel modify_newpwInfoLabel;
	JLabel modify_birthLabel;
	JTextField modify_nameField;
	JTextField modify_idField;
	JTextField modify_pwField;
	JTextField modify_birthField;
	JTextField modify_newpwField;
	JButton modify_okBtn;
	JButton modify_cancelBtn;	
	
	// ȸ�� Ż�� Ȯ��â ������
	JFrame withdrawUserFrame;
	JLabel withdraw_infoLabel;
	JLabel withdraw_pwLabel;
	JTextField withdraw_pwField;
	JButton withdraw_okBtn;
	JButton withdraw_cancelBtn;
	
	// �¼� ������� ������
	JFrame seatEndFrame;
	JLabel seatEnd_seatIndexLabel;
	JLabel seatEnd_infoLabel;
	JButton seatEnd_okBtn;
	JButton seatEnd_cancelBtn;
	public Window()
	{
		ReadUserData();
		// �¼� ���� ������ ����
		selectSeatFrame = new JFrame();
		selectSeatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		selectSeatFrame.setTitle("�¼�����");
		selectSeatFrame.setSize(600,600);
		selectSeatFrame.setLocation(300,100);
		selectSeatFrame.setLayout(null);
		// ��ư, ���̾ƿ��� �г� ����
		JPanel seatPanel = new JPanel();
		seatPanel.setBorder(BorderFactory.createTitledBorder("�¼� ��ȸ"));
		seatPanel.setSize(580,560);
		seatPanel.setLayout(new GridLayout(5,6,8,8));
		selectSeatFrame.add(seatPanel);
		for(int i=0; i<30; i++)
		{
			JButton btn = new JButton();
			btn.setText(Integer.toString(i+1));
			btn.setName(Integer.toString(i));
			btn.addActionListener(new SeatBtnActionListener());
			seatPanel.add(btn);
			seatBtnList.add(btn);
		}
		selectSeatFrame.setVisible(true);

		// �α��� ������ ����
		loginFrame = new JFrame();
		loginFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		loginFrame.setTitle("�α���");
		loginFrame.setSize(500,300);
		loginFrame.setLocation(500,200);
		loginFrame.setLayout(null);
		// ���̵� �Է�â
		login_idLabel = new JLabel("ID");
		login_idLabel.setSize(60,80);
		login_idLabel.setLocation(125,30);
		loginFrame.add(login_idLabel);
		login_idTextField = new JTextField();
		login_idTextField.setSize(200,40);
		login_idTextField.setLocation(180,50);
		loginFrame.add(login_idTextField);
		// �н����� �Է�â
		login_pwLabel = new JLabel("PassWord");
		login_pwLabel.setSize(60,80);
		login_pwLabel.setLocation(100,100);
		loginFrame.add(login_pwLabel);
		login_pwTextField = new JTextField();
		login_pwTextField.setSize(200, 40);
		login_pwTextField.setLocation(180, 120);
		loginFrame.add(login_pwTextField);
		// �α��� ��ư ����
		login_loginBtn = new JButton("�α���");
		login_loginBtn.setSize(90,30);
		login_loginBtn.setLocation(30, 190);
		login_loginBtn.addActionListener(new LoginBtnActionListener());
		loginFrame.add(login_loginBtn);
		// ȸ������ ��ư ����
		login_addUserBtn = new JButton("ȸ������");
		login_addUserBtn.setSize(90,30);
		login_addUserBtn.setLocation(140, 190);
		login_addUserBtn.addActionListener(new AddUserBtnActionListener());
		loginFrame.add(login_addUserBtn);
		// ȸ��ã�� ��ư ����
		login_findUserBtn = new JButton("ȸ��ã��");
		login_findUserBtn.setSize(90,30);
		login_findUserBtn.setLocation(250, 190);
		login_findUserBtn.addActionListener(new FindUserBtnActionListener());
		loginFrame.add(login_findUserBtn);
		// ��ȸ�� ��ư ����
		login_nonUserBtn = new JButton("��ȸ��");
		login_nonUserBtn.setSize(90,30);
		login_nonUserBtn.setLocation(360, 190);
		login_nonUserBtn.addActionListener(new NonUserBtnActionListener());
		loginFrame.add(login_nonUserBtn);

		loginFrame.setVisible(false);

		// ȸ������ ������ ����
		addUserFrame = new JFrame();
		addUserFrame.setTitle("ȸ������");
		addUserFrame.setSize(700,400);
		addUserFrame.setLocation(400,150);
		addUserFrame.setLayout(null);
		// �̸��Է� ĭ
		addUser_nameLabel = new JLabel("�̸�");
		addUser_nameLabel.setSize(60,80);
		addUser_nameLabel.setLocation(125, 30);
		addUserFrame.add(addUser_nameLabel);
		addUser_nameField = new JTextField("");
		addUser_nameField.setSize(200, 40);
		addUser_nameField.setLocation(180, 50);
		addUserFrame.add(addUser_nameField);
		// id �Է� ĭ
		addUser_idLabel = new JLabel("ID");
		addUser_idLabel.setSize(60,80);
		addUser_idLabel.setLocation(130, 100);
		addUserFrame.add(addUser_idLabel);
		addUser_idField = new JTextField("");
		addUser_idField.setSize(200, 40);
		addUser_idField.setLocation(180, 120);
		addUserFrame.add(addUser_idField);
		// pw �Է� ĭ
		addUser_pwLabel = new JLabel("PassWord");
		addUser_pwLabel.setSize(60,80);
		addUser_pwLabel.setLocation(105, 170);
		addUserFrame.add(addUser_pwLabel);
		addUser_pwField = new JTextField("");
		addUser_pwField.setSize(200, 40);
		addUser_pwField.setLocation(180, 190);
		addUserFrame.add(addUser_pwField);
		// ���� �Է� ĭ
		addUser_birthLabel = new JLabel("�������");
		addUser_birthLabel.setSize(60,80);
		addUser_birthLabel.setLocation(110, 240);
		addUserFrame.add(addUser_birthLabel);
		addUser_birthField = new JTextField("");
		addUser_birthField.setSize(200, 40);
		addUser_birthField.setLocation(180, 260);
		addUserFrame.add(addUser_birthField);
		// Ȯ�� ��ư
		addUser_okBtn = new JButton("Ȯ��");
		addUser_okBtn.setSize(100, 30);
		addUser_okBtn.setLocation(500, 120);
		addUser_okBtn.addActionListener(new AddUserOKBtnActionListener());
		addUserFrame.add(addUser_okBtn);
		// ��� ��ư
		addUser_cancelBtn = new JButton("���");
		addUser_cancelBtn.setSize(100, 30);
		addUser_cancelBtn.setLocation(500, 200);
		addUser_cancelBtn.addActionListener(new AddUserCancelActionListener());
		addUserFrame.add(addUser_cancelBtn);

		addUserFrame.setVisible(false);

		// �ð����� ������ ����
		selectTimeFrame = new JFrame();
		selectTimeFrame.setTitle("�ð� ����");
		selectTimeFrame.setSize(240,600);
		selectTimeFrame.setLocation(450,100);
		selectTimeFrame.setLayout(null);
		// �ð����� �г� ����
		selectTimePanel = new JPanel();
		selectTimePanel.setBorder(new TitledBorder("�¼� �� �ð� ����"));
		selectTimePanel.setLayout(null);		
		selectTimePanel.setSize(225, 300);
		selectTimePanel.setLocation(0,30);
		// ������ �¼� ��ȣ ��
		selectSeatLabel = new JLabel();
		selectSeatLabel.setSize(200,30);
		selectSeatLabel.setLocation(20,70);
		selectTimePanel.add(selectSeatLabel);
		// �ð� ���� ��ư
		timeMinusBtn = new Button("-");
		timeMinusBtn.setSize(30,30);
		timeMinusBtn.setLocation(30,120);
		// ���� ��ư Ŭ���� �׼Ǹ�����
		timeMinusBtn.addActionListener(new TimeCountBtnActionListener());
		selectTimePanel.add(timeMinusBtn);
		// �ð� ���� ��ư
		timePlusBtn = new Button("+");
		timePlusBtn.setSize(30,30);
		timePlusBtn.setLocation(165,120);
		// ���� ��ư Ŭ���� �׼Ǹ�����
		timePlusBtn.addActionListener(new TimeCountBtnActionListener());
		selectTimePanel.add(timePlusBtn);
		// �ð� �ؽ�Ʈ
		hourLabel = new JLabel();
		hourLabel.setSize(100,30);
		hourLabel.setLocation(100, 100);
		hourLabel.setText("�ð�");
		selectTimePanel.add(hourLabel);
		countLabel = new JLabel();
		countLabel.setSize(100, 30);
		countLabel.setLocation(110, 120);
		countLabel.setText(Integer.toString(time_count));
		selectTimePanel.add(countLabel);
		// �� �ð� ����
		totalLabel = new JLabel();
		totalLabel.setSize(180, 30);
		totalLabel.setLocation(30, 160);
		totalLabel.setText("��  "+Integer.toString(time_count)+"�ð��� ���� �ϼ̽��ϴ�.");
		selectTimePanel.add(totalLabel);
		// ȸ�� ��ȸ�� ���� ����
		infoMemLabel = new JLabel();
		infoMemLabel.setSize(180, 15);
		infoMemLabel.setLocation(20,230);
		infoMemLabel.setText("-ȸ���� ���, �ð��� 1000��");
		selectTimePanel.add(infoMemLabel);
		infoNonMemLabel = new JLabel();
		infoNonMemLabel.setSize(180, 15);
		infoNonMemLabel.setLocation(20,250);
		infoNonMemLabel.setText("-��ȸ���� ���, �ð��� 1200��");
		selectTimePanel.add(infoNonMemLabel);

		selectTimeFrame.add(selectTimePanel);

		// �ð� ���� �г� ����
		PayPanel = new JPanel();
		PayPanel.setBorder(new TitledBorder("�� ���� �ݾ�"));
		PayPanel.setLayout(null);		
		PayPanel.setSize(225,120);
		PayPanel.setLocation(0, 340);

		totalFareLabel = new JLabel();
		totalFareLabel.setSize(160, 30);
		totalFareLabel.setLocation(40, 50);
		if(user)
			fare = time_count * 1000;
		else 
			fare = time_count * 1200;
		totalFareLabel.setText("�� �ݾ��� " + fare +"�� �Դϴ�.");
		PayPanel.add(totalFareLabel);

		selectTimeFrame.add(PayPanel);

		// �ð� ���� ��ư �г� ����
		payOkBtn = new JButton("Ȯ��");
		payOkBtn.setSize(80,40);
		payOkBtn.setLocation(20,480);
		payOkBtn.addActionListener(new SelectTimeActionListener());
		selectTimeFrame.add(payOkBtn);			// ��ư�� ����Ʈ�ҿ� �����Ѵ�.


		payCancelBtn = new JButton("���");
		payCancelBtn.setSize(80,40);
		payCancelBtn.setLocation(120,480);
		payCancelBtn.addActionListener(new SelectTimeActionListener());
		selectTimeFrame.add(payCancelBtn);			// ��ư�� ����Ʈ�ҿ� �����Ѵ�.

		selectTimeFrame.setVisible(false);
		
		// ȸ�� ã�� ������ ����
		findUserFrame = new JFrame();
		findUserFrame.setTitle("ȸ�� ã��");
		findUserFrame.setSize(500, 470);
		findUserFrame.setLocation(450,130);
		findUserFrame.setLayout(null);
		// �̸����� ã�� �г� ����
		findUserPanel = new JPanel();
		findUserPanel.setBorder(new TitledBorder("ã���� �̸��� �Է��ϼ���"));
		findUserPanel.setSize(480,220);
		findUserPanel.setLocation(0, 20);
		findUserPanel.setLayout(null);
		findUserFrame.add(findUserPanel);
		// �̸� �Է¶� ����
		findUser_nameLabel = new JLabel("�̸�");
		findUser_nameLabel.setSize(40,40);
		findUser_nameLabel.setLocation(110,40);
		findUserPanel.add(findUser_nameLabel);
		
		findUser_nameField = new JTextField();
		findUser_nameField.setSize(130,30);
		findUser_nameField.setLocation(180, 45);
		findUserPanel.add(findUser_nameField);
		
		findUser_findButton = new JButton("�˻�");
		findUser_findButton.setSize(80,30);
		findUser_findButton.setLocation(350, 45);
		findUser_findButton.addActionListener(new FindUserFindBtnActionListener());
		findUserPanel.add(findUser_findButton);
		// �˻��� ���̵� ���� ��
		findUser_findUserInfoLabel = new JLabel();
		findUser_findUserInfoLabel.setSize(480,30);
		findUser_findUserInfoLabel.setLocation(150, 100);
		findUserPanel.add(findUser_findUserInfoLabel);
		
		// �˻� ������ ���;� ��
		findUser_okBtn = new JButton("Ȯ��");
		findUser_okBtn.setSize(80,30);
		findUser_okBtn.setLocation(200, 150);
		findUser_okBtn.addActionListener(new FindUserAfterBtnActionListener());
		findUserPanel.add(findUser_okBtn);
		findUser_okBtn.setVisible(false);
		
		// �˻� ������ ���� ����, Ż���ư ��Ƶ� �г�
		findUser_afterPanel = new JPanel();
		findUser_afterPanel.setBorder(new TitledBorder("�ΰ����"));
		findUser_afterPanel.setSize(480,160);
		findUser_afterPanel.setLocation(0, 250);
		findUser_afterPanel.setLayout(null);
		findUserFrame.add(findUser_afterPanel);
		// ����,Ż�� ��, ��ư ����
		findUser_findUserAfterLabel = new JLabel("���� �Ǵ� Ż�� ��� �����մϴ�.");
		findUser_findUserAfterLabel.setSize(480,30);
		findUser_findUserAfterLabel.setLocation(150,40);
		findUser_afterPanel.add(findUser_findUserAfterLabel);
		
		findUser_findUsermodifyBtn = new JButton("����");
		findUser_findUsermodifyBtn.setSize(80,30);
		findUser_findUsermodifyBtn.setLocation(130,90);
		findUser_findUsermodifyBtn.addActionListener(new FindUserAfterBtnActionListener());
		findUser_afterPanel.add(findUser_findUsermodifyBtn);
		
		findUser_findUserwithdrawBtn = new JButton("Ż��");
		findUser_findUserwithdrawBtn.setSize(80,30);
		findUser_findUserwithdrawBtn.setLocation(280,90);
		findUser_findUserwithdrawBtn.addActionListener(new FindUserAfterBtnActionListener());
		findUser_afterPanel.add(findUser_findUserwithdrawBtn);
		//�˻� ������ �������� �����ؾ���
		findUser_afterPanel.setVisible(false);
		
		findUserFrame.setVisible(false);		
		
		// ȸ�� ���� ���� ������ ����
		modifyUserFrame = new JFrame();
		modifyUserFrame.setTitle("���� ����");
		modifyUserFrame.setSize(700,500);
		modifyUserFrame.setLocation(400, 120);
		modifyUserFrame.setLayout(null);
		
		// �̸��Է� ĭ
		modify_nameLabel = new JLabel("�̸�");
		modify_nameLabel.setSize(60,80);
		modify_nameLabel.setLocation(125, 30);
		modifyUserFrame.add(modify_nameLabel);
		modify_nameField = new JTextField("");
		modify_nameField.setSize(200, 40);
		modify_nameField.setLocation(180, 50);
		modify_nameField.setEditable(false);
		modifyUserFrame.add(modify_nameField);
		// id �Է� ĭ
		modify_idLabel = new JLabel("ID");
		modify_idLabel.setSize(60,80);
		modify_idLabel.setLocation(130, 110);
		modifyUserFrame.add(modify_idLabel);
		modify_idField = new JTextField("");
		modify_idField.setSize(200, 40);
		modify_idField.setLocation(180, 130);
		modify_idField.setEditable(false);
		modifyUserFrame.add(modify_idField);
		// pw �Է� ĭ
		modify_pwLabel = new JLabel("PassWord");
		modify_pwLabel.setSize(60,80);
		modify_pwLabel.setLocation(105, 190);
		modifyUserFrame.add(modify_pwLabel);
		modify_pwField = new JTextField("");
		modify_pwField.setSize(200, 40);
		modify_pwField.setLocation(180, 210);
		modifyUserFrame.add(modify_pwField);
		modify_pwInfoLabel = new JLabel("�� ���� ��й�ȣ�� �Է��ϼ���.(Ʋ���� ���� �Ұ�)");
		modify_pwInfoLabel.setSize(330,80);
		modify_pwInfoLabel.setLocation(100, 230);
		modifyUserFrame.add(modify_pwInfoLabel);
		// ���ο� pw �Է� ĭ
		modify_newpwLabel = new JLabel("New PW");
		modify_newpwLabel.setSize(60,80);
		modify_newpwLabel.setLocation(110, 270);
		modifyUserFrame.add(modify_newpwLabel);
		modify_newpwField = new JTextField("");
		modify_newpwField.setSize(200, 40);
		modify_newpwField.setLocation(180, 290);
		modifyUserFrame.add(modify_newpwField);
		modify_newpwInfoLabel = new JLabel("�� ������ ���Ͻø� ���ο� ��й�ȣ�� �Է��ϼ���.");
		modify_newpwInfoLabel.setSize(330,80);
		modify_newpwInfoLabel.setLocation(100, 310);
		modifyUserFrame.add(modify_newpwInfoLabel);
		// ���� �Է� ĭ
		modify_birthLabel = new JLabel("�������");
		modify_birthLabel.setSize(60,80);
		modify_birthLabel.setLocation(110, 350);
		modifyUserFrame.add(modify_birthLabel);
		modify_birthField = new JTextField("");
		modify_birthField.setSize(200, 40);
		modify_birthField.setLocation(180, 370);
		modifyUserFrame.add(modify_birthField);
		// Ȯ�� ��ư
		modify_okBtn = new JButton("����");
		modify_okBtn.setSize(100, 30);
		modify_okBtn.setLocation(500, 175);
		modify_okBtn.addActionListener(new ModifyBtnActionListener());
		modifyUserFrame.add(modify_okBtn);
		// ��� ��ư
		modify_cancelBtn = new JButton("���");
		modify_cancelBtn.setSize(100, 30);
		modify_cancelBtn.setLocation(500, 255);
		modify_cancelBtn.addActionListener(new ModifyBtnActionListener());
		modifyUserFrame.add(modify_cancelBtn);

		modifyUserFrame.setVisible(false);
		
		// Ż�� Ȯ��â ������
		withdrawUserFrame = new JFrame();
		withdrawUserFrame.setTitle("Ż��");
		withdrawUserFrame.setSize(400,230);
		withdrawUserFrame.setLocation(450, 300);
		withdrawUserFrame.setLayout(null);
		
		withdraw_infoLabel = new JLabel("Ż���Ͻ÷��� ��й�ȣ�� �Է����ּ���.");
		withdraw_infoLabel.setSize(300,30);
		withdraw_infoLabel.setLocation(80, 15);
		withdrawUserFrame.add(withdraw_infoLabel);
		
		withdraw_pwLabel = new JLabel("PassWord");
		withdraw_pwLabel.setSize(300,30);
		withdraw_pwLabel.setLocation(50, 70);
		withdrawUserFrame.add(withdraw_pwLabel);
		
		withdraw_pwField = new JTextField();
		withdraw_pwField.setSize(200,30);
		withdraw_pwField.setLocation(130, 70);
		withdrawUserFrame.add(withdraw_pwField);
		
		withdraw_okBtn = new JButton("Ȯ��");
		withdraw_okBtn.setSize(80	,30);
		withdraw_okBtn.setLocation(100, 130);
		withdraw_okBtn.addActionListener(new WithdrawBtnActionListener());
		withdrawUserFrame.add(withdraw_okBtn);
		
		withdraw_cancelBtn = new JButton("���");
		withdraw_cancelBtn.setSize(80	,30);
		withdraw_cancelBtn.setLocation(220, 130);
		withdraw_cancelBtn.addActionListener(new WithdrawBtnActionListener());
		withdrawUserFrame.add(withdraw_cancelBtn);
		
		withdrawUserFrame.setVisible(false);
		
		// �������â ������ ����
		seatEndFrame = new JFrame();
		seatEndFrame.setTitle("�������");
		seatEndFrame.setSize(400,230);
		seatEndFrame.setLocation(450, 300);
		seatEndFrame.setLayout(null);
		
		seatEnd_seatIndexLabel = new JLabel();
		seatEnd_seatIndexLabel.setSize(200,80);
		seatEnd_seatIndexLabel.setLocation(122,10);
		seatEndFrame.add(seatEnd_seatIndexLabel);
		
		seatEnd_infoLabel = new JLabel("����� ���� �Ͻðڽ��ϱ�?");
		seatEnd_infoLabel.setSize(200,80);
		seatEnd_infoLabel.setLocation(120,40);
		seatEndFrame.add(seatEnd_infoLabel);
		
		seatEnd_okBtn = new JButton("Ȯ��");
		seatEnd_okBtn.setSize(80,30);
		seatEnd_okBtn.setLocation(90,120);
		seatEnd_okBtn.addActionListener(new SeatEndBtnActionListener());		
		seatEndFrame.add(seatEnd_okBtn);
		
		seatEnd_cancelBtn = new JButton("���");
		seatEnd_cancelBtn.setSize(80,30);
		seatEnd_cancelBtn.setLocation(220,120);
		seatEnd_cancelBtn.addActionListener(new SeatEndBtnActionListener());		
		seatEndFrame.add(seatEnd_cancelBtn);
		
		seatEndFrame.setVisible(false);
	}
	void SaveUserData()
	{
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		try{
			fout = new FileOutputStream("userlist.dat");
			oos = new ObjectOutputStream(fout);
			ArrayList<User> list = new ArrayList<User>();
			list.addAll(userList);
			oos.writeObject(list);//���� �Է��� ��Ģ�� ���� �ι� �� ����.
			oos.reset();
			oos.writeObject(list);
			oos.reset();
			
			System.out.println("����Ǿ����ϴ�.");
			
		}catch(Exception ex){
		}finally{
			try{
				oos.close();
				fout.close();
			}catch(IOException ioe){}
		} // finally
	}
	void ReadUserData()
	{
		FileInputStream fin = null;
		ObjectInputStream ois = null;

		try{
			fin = new FileInputStream("userlist.dat");
			ois = new ObjectInputStream(fin);
			 
			//userList.clear();
			userList = (ArrayList)ois.readObject();
			
		}catch(Exception ex){
		}finally{
			try{
				ois.close();
				fin.close();
			}catch(IOException ioe){}
		} // finally
	}
	class SeatBtnActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			if(b.getBackground().equals(Color.yellow))
			{
				selectSeatIndex = Integer.parseInt(b.getName());
				seatEnd_seatIndexLabel.setText("������ �¼��� "+(selectSeatIndex+1)+"�� �Դϴ�.");
				seatEndFrame.setVisible(true);
			}
			else
			{
				//b.setBackground(Color.cyan);
				// ���� Ŭ���ߴ� �¼� ��׶��� ���� �������
				//if(selectSeatIndex != -1)
					//seatBtnList.get(selectSeatIndex).setBackground(null);
				// Ŭ���� �¼� �ε��� ����
				selectSeatIndex = Integer.parseInt(b.getName());
				loginFrame.setVisible(true);
			}
		}
	}
	class LoginBtnActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			boolean find = false;
			if(login_idTextField.getText().length() != 0 && login_pwTextField.getText().length() != 0)
			{
				for(int i=0; i<userList.size(); i++)
				{
					if(userList.get(i).getID().equals(login_idTextField.getText()) && 
							userList.get(i).getPassword().equals(login_pwTextField.getText()))
					{
						find = true;
						user = true;
						findUserIndex = i;
						login_idTextField.setText("");
						login_pwTextField.setText("");
						selectTimeFrame.setVisible(true);
						
						selectSeatLabel.setText("������ �¼��� "+(selectSeatIndex+1)+"�� �Դϴ�.");
						countLabel.setText(Integer.toString(time_count));	
						totalLabel.setText("��  "+Integer.toString(time_count)+"�ð��� ���� �ϼ̽��ϴ�.");
						if(user)				// ȸ���� ��
						{
							fare = time_count *1000;
						}
						else
							fare = time_count * 1200;
						totalFareLabel.setText("�� �ݾ��� " + fare +"�� �Դϴ�.");
					}
				}
				if(!find)
				{
					errorDialog.setVisible(true);
					errorDialog.SetStr("ȸ�� ������ �������� �ʽ��ϴ�.");
				}
			}
			else if(login_idTextField.getText().length() == 0 &&  login_pwTextField.getText().length() == 0)
			{
				errorDialog.setVisible(true);
				errorDialog.SetStr("��� �׸��� �Էµ��� �ʾҽ��ϴ�.");
			}
			
		}
	}
	class AddUserBtnActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			addUserFrame.setVisible(true);
		}
	}
	class FindUserBtnActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			findUserFrame.setVisible(true);
		}
	}
	class NonUserBtnActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			user = false;
			selectTimeFrame.setVisible(true);
			selectSeatLabel.setText("������ �¼��� "+(selectSeatIndex+1)+"�� �Դϴ�.");
			countLabel.setText(Integer.toString(time_count));	
			totalLabel.setText("��  "+Integer.toString(time_count)+"�ð��� ���� �ϼ̽��ϴ�.");
			if(user)				// ȸ���� ��
			{
				fare = time_count *1000;
			}
			else
				fare = time_count * 1200;
			totalFareLabel.setText("�� �ݾ��� " + fare +"�� �Դϴ�.");
		}
	}
	// ȸ������ Ȯ�� ��ư �׼� ������
	class AddUserOKBtnActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			// Ȯ�ι�ư �������� �����Ұ͵� (�������� �Է��Ѱɷ� ����)
			// �ؽ�Ʈ�ʵ� ��������� ����ó��
			if(addUser_nameField.getText().length()!=0 && addUser_idField.getText().length()!=0 &&
					addUser_pwField.getText().length()!=0 && addUser_birthField.getText().length()!=0)
			{
				boolean repetition = false;
				for(int i=0; i<userList.size(); i++)
				{
					// ȸ������ ID �ߺ�üũ
					if(userList.get(i).getID().equals(addUser_idField.getText()))
					{
						repetition = true;
						errorDialog.setVisible(true);
						errorDialog.SetStr("���� ID�� �ֽ��ϴ�.");
					}
				}
				if(!repetition)
				{
					// new User ����
					User u = new User(addUser_nameField.getText(), addUser_idField.getText(), addUser_pwField.getText(), addUser_birthField.getText());
					userList.add(u);	
					SaveUserData();
					ReadUserData();
					addUserFrame.setVisible(false);
					addUser_nameField.setText("");
					addUser_idField.setText("");
					addUser_pwField.setText("");
					addUser_birthField.setText("");
				}
			}
			else
			{
				errorDialog.setVisible(true);
				errorDialog.SetStr("��� �׸��� ���õ��� �ʾҽ��ϴ�.");
			}
		}
	}

	// ȸ������ ��� ��ư �׼� ������
	class AddUserCancelActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			addUser_nameField.setText("");
			addUser_idField.setText("");
			addUser_pwField.setText("");
			addUser_birthField.setText("");
			addUserFrame.setVisible(false);
		}
	}
	// Error �޽��� â
	class ErrorDialog extends JDialog{
		JLabel error_label;
		ErrorDialog(){
			setTitle("Error");				
			setSize(400, 200);
			setLocation(550, 250);
			setVisible(false);
			setLayout(null);

			// ��������
			error_label = new JLabel();

			add(error_label);	
			error_label.setSize(200,80);
			error_label.setLocation(105,20);

			// Ȯ�� ��ư ����
			JButton b1 = new JButton("Ȯ��");
			add(b1);			// ��ư�� ����Ʈ�ҿ� �����Ѵ�.
			b1.setSize(100,30);
			b1.setLocation(150, 100);
			// Ȯ�ο� �׼Ǹ����� ���
			b1.addActionListener(new ErrorOKBtnActionListener());
		}
		void SetStr(String str)
		{
			error_label.setText(str);
		}
	}
	// Error Ȯ�� ��ư �׼� ������
	class ErrorOKBtnActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			errorDialog.setVisible(false);	
		}
	}
	// �ð� ���� Ȯ�� ��ҹ�ư
	class SelectTimeActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if(o.equals(payOkBtn))
			{
				seatBtnList.get(selectSeatIndex).setBackground(Color.yellow);
				if(user)
					seatBtnList.get(selectSeatIndex).setText("<html>"+userList.get(findUserIndex).getName()+"��<br>�����<br>"+time_count+"�ð�</html>");
				else
					seatBtnList.get(selectSeatIndex).setText("<html>�����<br>"+time_count+"�ð�</html>");
				time_count = 1;
				loginFrame.setVisible(false);
				selectTimeFrame.setVisible(false);
			}
			else
			{
				time_count = 1;
				selectTimeFrame.setVisible(false);
			}
		}
	}
	// �ð� ���� ��ư �׼� ������
	class TimeCountBtnActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if(o.equals(timePlusBtn))
			{
				time_count++;
			}
			else if(o.equals(timeMinusBtn))
			{
				time_count--;
				if(time_count<1)		// �ּҽð� 1�ð� ���Ϸ� �������� �ʵ���
					time_count=1;
			}
			countLabel.setText(Integer.toString(time_count));	
			totalLabel.setText("��  "+Integer.toString(time_count)+"�ð��� ���� �ϼ̽��ϴ�.");
			if(user)				// ȸ���� ��
			{
				fare = time_count *1000;
			}
			else
				fare = time_count * 1200;
			totalFareLabel.setText("�� �ݾ��� " + fare +"�� �Դϴ�.");
		}
	}
	class FindUserFindBtnActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			boolean findName = false;
			if(findUser_nameField.getText().length() != 0)
			{
				for(int i=0; i<userList.size(); i++)
				{
					if(userList.get(i).getName().equals(findUser_nameField.getText()))		// �Է��� �̸��� �������� ����Ʈ�� ���� �̸��� �������
					{
						findName = true;
						findUser_okBtn.setVisible(true);
						findUserIndex = i;			// userLIst���� ã�� ������ �ε��� ����(����,Ż�� ����� ���ؼ�)
						findUser_findUserInfoLabel.setText(userList.get(i).getName()+"���� ���̵�� "+userList.get(i).getID()+"�Դϴ�.");
						findUser_afterPanel.setVisible(true);
					}
				}
			}
			if(!findName)
			{
				findUser_findUserInfoLabel.setText("           �˻������ �����ϴ�.");
				findUser_afterPanel.setVisible(false);
				findUser_okBtn.setVisible(false);
			}
			
			findUser_nameField.setText("");
		}
	}
	class FindUserAfterBtnActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if(o.equals(findUser_okBtn))		// Ȯ�ι�ư
			{
				findUser_findUserInfoLabel.setText("");
				findUser_afterPanel.setVisible(false);
				findUser_okBtn.setVisible(false);
				findUserFrame.setVisible(false);
			}
			else if(o.equals(findUser_findUsermodifyBtn))		// ������ư
			{
				findUser_findUserInfoLabel.setText("");
				findUser_afterPanel.setVisible(false);
				findUser_okBtn.setVisible(false);
				modifyUserFrame.setVisible(true);
				modify_nameField.setText(userList.get(findUserIndex).getName());
				modify_idField.setText(userList.get(findUserIndex).getID());
			}
			else if(o.equals(findUser_findUserwithdrawBtn))		// Ż���ư
			{
				findUser_findUserInfoLabel.setText("");
				findUser_afterPanel.setVisible(false);
				findUser_okBtn.setVisible(false);
				withdrawUserFrame.setVisible(true);
			}
		}
	}
	class ModifyBtnActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if(o.equals(modify_okBtn))
			{
				if(modify_pwField.getText().equals(userList.get(findUserIndex).getPassword()))	//ã�� ���������� �н������ �Է��� �н����� �������
				{
					if(modify_newpwField.getText().length() != 0)		// ���ο� �н����尡 �����ܿ�
					{
						userList.get(findUserIndex).setPassword(modify_newpwField.getText());
					}
					if(modify_birthField.getText().length() != 0)				// ����ĭ�� �Է°��� �������
					{
						userList.get(findUserIndex).setBirth_date(modify_birthField.getText());
					}
					modify_pwField.setText("");
					modify_newpwField.setText("");
					modify_birthField.setText("");
					modifyUserFrame.setVisible(false);
				}
				else		// �н����� ���� ������� �޽���â�� �����
				{
					errorDialog.setVisible(true);
					errorDialog.SetStr("�н����尡 ���� �ʽ��ϴ�.");
				}
			}
			else			// ���Ű ������ ��� â �ݱ�
			{
				modify_pwField.setText("");
				modify_newpwField.setText("");
				modify_birthField.setText("");
				modifyUserFrame.setVisible(false);				
			}
		}
	}
	class WithdrawBtnActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if(o.equals(withdraw_okBtn))
			{
				//Ż��
				if(withdraw_pwField.getText().length() != 0)
				{
					if(withdraw_pwField.getText().equals(userList.get(findUserIndex).getPassword()))
					{
						userList.remove(findUserIndex);
						withdraw_pwField.setText("");
						findUserIndex = -1;
						withdrawUserFrame.setVisible(false);
					}
					else
					{
						errorDialog.setVisible(true);
						errorDialog.SetStr("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
					}
				}
			}
			else if(o.equals(withdraw_cancelBtn))
			{
				withdraw_pwField.setText("");
				withdrawUserFrame.setVisible(false);
			}
		}
	}
	class SeatEndBtnActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if(o.equals(seatEnd_okBtn))
			{
				seatBtnList.get(selectSeatIndex).setText(Integer.toString(selectSeatIndex+1));
				seatBtnList.get(selectSeatIndex).setBackground(null);
			}
			seatEndFrame.setVisible(false);
		}
	}
}
