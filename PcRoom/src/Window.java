import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class Window {
	ArrayList<User> userList = new ArrayList<User>();

	// 좌석선택프레임용 변수
	JFrame selectSeatFrame;
	ArrayList<JButton> seatBtnList = new ArrayList<JButton>();		// 버튼 관리할 ArrayList
	int selectSeatIndex = -1;										// 선택된 좌석 인덱스 저장용

	// 로그인프레임용 변수
	JFrame loginFrame;
	JLabel login_idLabel;
	JTextField login_idTextField;
	JLabel login_pwLabel;
	JTextField login_pwTextField;
	JButton login_loginBtn;
	JButton login_addUserBtn;
	JButton login_nonUserBtn;
	JButton login_findUserBtn;

	// 회원가입 프레임용 변수
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
	// 회원가입 에러 출력용 다이얼로그
	ErrorDialog errorDialog = new ErrorDialog();

	// 시간선택 프레임용 변수
	JFrame selectTimeFrame;
	boolean user = false;
	// 시간선택 패널
	JPanel selectTimePanel;
	int time_count = 1;
	JLabel selectSeatLabel;
	JLabel timeCountLabel;
	Button timePlusBtn;
	Button timeMinusBtn;
	// 시간 결재 패널
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

	// 회원찾기 프레임 변수
	JFrame findUserFrame;
	// 이름으로 찾기
	JPanel findUserPanel;
	JLabel findUser_nameLabel;
	JTextField findUser_nameField;
	JButton findUser_findButton;
	JLabel findUser_findUserInfoLabel;
	JButton findUser_okBtn;
	// 수정, 탈퇴
	JPanel findUser_afterPanel;
	JLabel findUser_findUserAfterLabel;
	JButton findUser_findUsermodifyBtn;				
	JButton findUser_findUserwithdrawBtn;
	
	// 회원 수정 프레임 변수
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
	
	// 회원 탈퇴 확인창 프레임
	JFrame withdrawUserFrame;
	JLabel withdraw_infoLabel;
	JLabel withdraw_pwLabel;
	JTextField withdraw_pwField;
	JButton withdraw_okBtn;
	JButton withdraw_cancelBtn;
	
	// 좌석 사용종료 프레임
	JFrame seatEndFrame;
	JLabel seatEnd_seatIndexLabel;
	JLabel seatEnd_infoLabel;
	JButton seatEnd_okBtn;
	JButton seatEnd_cancelBtn;
	public Window()
	{
		ReadUserData();
		// 좌석 선택 프레임 설정
		selectSeatFrame = new JFrame();
		selectSeatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		selectSeatFrame.setTitle("좌석선택");
		selectSeatFrame.setSize(600,600);
		selectSeatFrame.setLocation(300,100);
		selectSeatFrame.setLayout(null);
		// 버튼, 레이아웃용 패널 선언
		JPanel seatPanel = new JPanel();
		seatPanel.setBorder(BorderFactory.createTitledBorder("좌석 조회"));
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

		// 로그인 프레임 설정
		loginFrame = new JFrame();
		loginFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		loginFrame.setTitle("로그인");
		loginFrame.setSize(500,300);
		loginFrame.setLocation(500,200);
		loginFrame.setLayout(null);
		// 아이디 입력창
		login_idLabel = new JLabel("ID");
		login_idLabel.setSize(60,80);
		login_idLabel.setLocation(125,30);
		loginFrame.add(login_idLabel);
		login_idTextField = new JTextField();
		login_idTextField.setSize(200,40);
		login_idTextField.setLocation(180,50);
		loginFrame.add(login_idTextField);
		// 패스워드 입력창
		login_pwLabel = new JLabel("PassWord");
		login_pwLabel.setSize(60,80);
		login_pwLabel.setLocation(100,100);
		loginFrame.add(login_pwLabel);
		login_pwTextField = new JTextField();
		login_pwTextField.setSize(200, 40);
		login_pwTextField.setLocation(180, 120);
		loginFrame.add(login_pwTextField);
		// 로그인 버튼 설정
		login_loginBtn = new JButton("로그인");
		login_loginBtn.setSize(90,30);
		login_loginBtn.setLocation(30, 190);
		login_loginBtn.addActionListener(new LoginBtnActionListener());
		loginFrame.add(login_loginBtn);
		// 회원가입 버튼 설정
		login_addUserBtn = new JButton("회원가입");
		login_addUserBtn.setSize(90,30);
		login_addUserBtn.setLocation(140, 190);
		login_addUserBtn.addActionListener(new AddUserBtnActionListener());
		loginFrame.add(login_addUserBtn);
		// 회원찾기 버튼 설정
		login_findUserBtn = new JButton("회원찾기");
		login_findUserBtn.setSize(90,30);
		login_findUserBtn.setLocation(250, 190);
		login_findUserBtn.addActionListener(new FindUserBtnActionListener());
		loginFrame.add(login_findUserBtn);
		// 비회원 버튼 설정
		login_nonUserBtn = new JButton("비회원");
		login_nonUserBtn.setSize(90,30);
		login_nonUserBtn.setLocation(360, 190);
		login_nonUserBtn.addActionListener(new NonUserBtnActionListener());
		loginFrame.add(login_nonUserBtn);

		loginFrame.setVisible(false);

		// 회원가입 프레임 설정
		addUserFrame = new JFrame();
		addUserFrame.setTitle("회원가입");
		addUserFrame.setSize(700,400);
		addUserFrame.setLocation(400,150);
		addUserFrame.setLayout(null);
		// 이름입력 칸
		addUser_nameLabel = new JLabel("이름");
		addUser_nameLabel.setSize(60,80);
		addUser_nameLabel.setLocation(125, 30);
		addUserFrame.add(addUser_nameLabel);
		addUser_nameField = new JTextField("");
		addUser_nameField.setSize(200, 40);
		addUser_nameField.setLocation(180, 50);
		addUserFrame.add(addUser_nameField);
		// id 입력 칸
		addUser_idLabel = new JLabel("ID");
		addUser_idLabel.setSize(60,80);
		addUser_idLabel.setLocation(130, 100);
		addUserFrame.add(addUser_idLabel);
		addUser_idField = new JTextField("");
		addUser_idField.setSize(200, 40);
		addUser_idField.setLocation(180, 120);
		addUserFrame.add(addUser_idField);
		// pw 입력 칸
		addUser_pwLabel = new JLabel("PassWord");
		addUser_pwLabel.setSize(60,80);
		addUser_pwLabel.setLocation(105, 170);
		addUserFrame.add(addUser_pwLabel);
		addUser_pwField = new JTextField("");
		addUser_pwField.setSize(200, 40);
		addUser_pwField.setLocation(180, 190);
		addUserFrame.add(addUser_pwField);
		// 생일 입력 칸
		addUser_birthLabel = new JLabel("생년월일");
		addUser_birthLabel.setSize(60,80);
		addUser_birthLabel.setLocation(110, 240);
		addUserFrame.add(addUser_birthLabel);
		addUser_birthField = new JTextField("");
		addUser_birthField.setSize(200, 40);
		addUser_birthField.setLocation(180, 260);
		addUserFrame.add(addUser_birthField);
		// 확인 버튼
		addUser_okBtn = new JButton("확인");
		addUser_okBtn.setSize(100, 30);
		addUser_okBtn.setLocation(500, 120);
		addUser_okBtn.addActionListener(new AddUserOKBtnActionListener());
		addUserFrame.add(addUser_okBtn);
		// 취소 버튼
		addUser_cancelBtn = new JButton("취소");
		addUser_cancelBtn.setSize(100, 30);
		addUser_cancelBtn.setLocation(500, 200);
		addUser_cancelBtn.addActionListener(new AddUserCancelActionListener());
		addUserFrame.add(addUser_cancelBtn);

		addUserFrame.setVisible(false);

		// 시간선택 프레임 설정
		selectTimeFrame = new JFrame();
		selectTimeFrame.setTitle("시간 선택");
		selectTimeFrame.setSize(240,600);
		selectTimeFrame.setLocation(450,100);
		selectTimeFrame.setLayout(null);
		// 시간선택 패널 설정
		selectTimePanel = new JPanel();
		selectTimePanel.setBorder(new TitledBorder("좌석 및 시간 선택"));
		selectTimePanel.setLayout(null);		
		selectTimePanel.setSize(225, 300);
		selectTimePanel.setLocation(0,30);
		// 선택한 좌석 번호 라벨
		selectSeatLabel = new JLabel();
		selectSeatLabel.setSize(200,30);
		selectSeatLabel.setLocation(20,70);
		selectTimePanel.add(selectSeatLabel);
		// 시간 감소 버튼
		timeMinusBtn = new Button("-");
		timeMinusBtn.setSize(30,30);
		timeMinusBtn.setLocation(30,120);
		// 감소 버튼 클릭시 액션리스너
		timeMinusBtn.addActionListener(new TimeCountBtnActionListener());
		selectTimePanel.add(timeMinusBtn);
		// 시간 증가 버튼
		timePlusBtn = new Button("+");
		timePlusBtn.setSize(30,30);
		timePlusBtn.setLocation(165,120);
		// 증가 버튼 클릭시 액션리스너
		timePlusBtn.addActionListener(new TimeCountBtnActionListener());
		selectTimePanel.add(timePlusBtn);
		// 시간 텍스트
		hourLabel = new JLabel();
		hourLabel.setSize(100,30);
		hourLabel.setLocation(100, 100);
		hourLabel.setText("시간");
		selectTimePanel.add(hourLabel);
		countLabel = new JLabel();
		countLabel.setSize(100, 30);
		countLabel.setLocation(110, 120);
		countLabel.setText(Integer.toString(time_count));
		selectTimePanel.add(countLabel);
		// 총 시간 선택
		totalLabel = new JLabel();
		totalLabel.setSize(180, 30);
		totalLabel.setLocation(30, 160);
		totalLabel.setText("총  "+Integer.toString(time_count)+"시간을 선택 하셨습니다.");
		selectTimePanel.add(totalLabel);
		// 회원 비회원 가격 정보
		infoMemLabel = new JLabel();
		infoMemLabel.setSize(180, 15);
		infoMemLabel.setLocation(20,230);
		infoMemLabel.setText("-회원일 경우, 시간당 1000원");
		selectTimePanel.add(infoMemLabel);
		infoNonMemLabel = new JLabel();
		infoNonMemLabel.setSize(180, 15);
		infoNonMemLabel.setLocation(20,250);
		infoNonMemLabel.setText("-비회원일 경우, 시간당 1200원");
		selectTimePanel.add(infoNonMemLabel);

		selectTimeFrame.add(selectTimePanel);

		// 시간 결제 패널 설정
		PayPanel = new JPanel();
		PayPanel.setBorder(new TitledBorder("총 결제 금액"));
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
		totalFareLabel.setText("총 금액은 " + fare +"원 입니다.");
		PayPanel.add(totalFareLabel);

		selectTimeFrame.add(PayPanel);

		// 시간 결제 버튼 패널 설정
		payOkBtn = new JButton("확인");
		payOkBtn.setSize(80,40);
		payOkBtn.setLocation(20,480);
		payOkBtn.addActionListener(new SelectTimeActionListener());
		selectTimeFrame.add(payOkBtn);			// 버튼을 컨텐트팬에 부착한다.


		payCancelBtn = new JButton("취소");
		payCancelBtn.setSize(80,40);
		payCancelBtn.setLocation(120,480);
		payCancelBtn.addActionListener(new SelectTimeActionListener());
		selectTimeFrame.add(payCancelBtn);			// 버튼을 컨텐트팬에 부착한다.

		selectTimeFrame.setVisible(false);
		
		// 회원 찾기 프레임 설정
		findUserFrame = new JFrame();
		findUserFrame.setTitle("회원 찾기");
		findUserFrame.setSize(500, 470);
		findUserFrame.setLocation(450,130);
		findUserFrame.setLayout(null);
		// 이름으로 찾기 패널 설정
		findUserPanel = new JPanel();
		findUserPanel.setBorder(new TitledBorder("찾으실 이름을 입력하세요"));
		findUserPanel.setSize(480,220);
		findUserPanel.setLocation(0, 20);
		findUserPanel.setLayout(null);
		findUserFrame.add(findUserPanel);
		// 이름 입력란 설정
		findUser_nameLabel = new JLabel("이름");
		findUser_nameLabel.setSize(40,40);
		findUser_nameLabel.setLocation(110,40);
		findUserPanel.add(findUser_nameLabel);
		
		findUser_nameField = new JTextField();
		findUser_nameField.setSize(130,30);
		findUser_nameField.setLocation(180, 45);
		findUserPanel.add(findUser_nameField);
		
		findUser_findButton = new JButton("검색");
		findUser_findButton.setSize(80,30);
		findUser_findButton.setLocation(350, 45);
		findUser_findButton.addActionListener(new FindUserFindBtnActionListener());
		findUserPanel.add(findUser_findButton);
		// 검색된 아이디 나올 라벨
		findUser_findUserInfoLabel = new JLabel();
		findUser_findUserInfoLabel.setSize(480,30);
		findUser_findUserInfoLabel.setLocation(150, 100);
		findUserPanel.add(findUser_findUserInfoLabel);
		
		// 검색 성공시 나와야 함
		findUser_okBtn = new JButton("확인");
		findUser_okBtn.setSize(80,30);
		findUser_okBtn.setLocation(200, 150);
		findUser_okBtn.addActionListener(new FindUserAfterBtnActionListener());
		findUserPanel.add(findUser_okBtn);
		findUser_okBtn.setVisible(false);
		
		// 검색 성공시 나올 수정, 탈퇴버튼 담아둘 패널
		findUser_afterPanel = new JPanel();
		findUser_afterPanel.setBorder(new TitledBorder("부가기능"));
		findUser_afterPanel.setSize(480,160);
		findUser_afterPanel.setLocation(0, 250);
		findUser_afterPanel.setLayout(null);
		findUserFrame.add(findUser_afterPanel);
		// 수정,탈퇴 라벨, 버튼 설정
		findUser_findUserAfterLabel = new JLabel("수정 또는 탈퇴 사용 가능합니다.");
		findUser_findUserAfterLabel.setSize(480,30);
		findUser_findUserAfterLabel.setLocation(150,40);
		findUser_afterPanel.add(findUser_findUserAfterLabel);
		
		findUser_findUsermodifyBtn = new JButton("수정");
		findUser_findUsermodifyBtn.setSize(80,30);
		findUser_findUsermodifyBtn.setLocation(130,90);
		findUser_findUsermodifyBtn.addActionListener(new FindUserAfterBtnActionListener());
		findUser_afterPanel.add(findUser_findUsermodifyBtn);
		
		findUser_findUserwithdrawBtn = new JButton("탈퇴");
		findUser_findUserwithdrawBtn.setSize(80,30);
		findUser_findUserwithdrawBtn.setLocation(280,90);
		findUser_findUserwithdrawBtn.addActionListener(new FindUserAfterBtnActionListener());
		findUser_afterPanel.add(findUser_findUserwithdrawBtn);
		//검색 성공시 나오도록 설정해야함
		findUser_afterPanel.setVisible(false);
		
		findUserFrame.setVisible(false);		
		
		// 회원 정보 수정 프레임 설정
		modifyUserFrame = new JFrame();
		modifyUserFrame.setTitle("정보 수정");
		modifyUserFrame.setSize(700,500);
		modifyUserFrame.setLocation(400, 120);
		modifyUserFrame.setLayout(null);
		
		// 이름입력 칸
		modify_nameLabel = new JLabel("이름");
		modify_nameLabel.setSize(60,80);
		modify_nameLabel.setLocation(125, 30);
		modifyUserFrame.add(modify_nameLabel);
		modify_nameField = new JTextField("");
		modify_nameField.setSize(200, 40);
		modify_nameField.setLocation(180, 50);
		modify_nameField.setEditable(false);
		modifyUserFrame.add(modify_nameField);
		// id 입력 칸
		modify_idLabel = new JLabel("ID");
		modify_idLabel.setSize(60,80);
		modify_idLabel.setLocation(130, 110);
		modifyUserFrame.add(modify_idLabel);
		modify_idField = new JTextField("");
		modify_idField.setSize(200, 40);
		modify_idField.setLocation(180, 130);
		modify_idField.setEditable(false);
		modifyUserFrame.add(modify_idField);
		// pw 입력 칸
		modify_pwLabel = new JLabel("PassWord");
		modify_pwLabel.setSize(60,80);
		modify_pwLabel.setLocation(105, 190);
		modifyUserFrame.add(modify_pwLabel);
		modify_pwField = new JTextField("");
		modify_pwField.setSize(200, 40);
		modify_pwField.setLocation(180, 210);
		modifyUserFrame.add(modify_pwField);
		modify_pwInfoLabel = new JLabel("※ 현재 비밀번호를 입력하세요.(틀릴시 수정 불가)");
		modify_pwInfoLabel.setSize(330,80);
		modify_pwInfoLabel.setLocation(100, 230);
		modifyUserFrame.add(modify_pwInfoLabel);
		// 새로운 pw 입력 칸
		modify_newpwLabel = new JLabel("New PW");
		modify_newpwLabel.setSize(60,80);
		modify_newpwLabel.setLocation(110, 270);
		modifyUserFrame.add(modify_newpwLabel);
		modify_newpwField = new JTextField("");
		modify_newpwField.setSize(200, 40);
		modify_newpwField.setLocation(180, 290);
		modifyUserFrame.add(modify_newpwField);
		modify_newpwInfoLabel = new JLabel("※ 수정을 원하시면 새로운 비밀번호를 입력하세요.");
		modify_newpwInfoLabel.setSize(330,80);
		modify_newpwInfoLabel.setLocation(100, 310);
		modifyUserFrame.add(modify_newpwInfoLabel);
		// 생일 입력 칸
		modify_birthLabel = new JLabel("생년월일");
		modify_birthLabel.setSize(60,80);
		modify_birthLabel.setLocation(110, 350);
		modifyUserFrame.add(modify_birthLabel);
		modify_birthField = new JTextField("");
		modify_birthField.setSize(200, 40);
		modify_birthField.setLocation(180, 370);
		modifyUserFrame.add(modify_birthField);
		// 확인 버튼
		modify_okBtn = new JButton("수정");
		modify_okBtn.setSize(100, 30);
		modify_okBtn.setLocation(500, 175);
		modify_okBtn.addActionListener(new ModifyBtnActionListener());
		modifyUserFrame.add(modify_okBtn);
		// 취소 버튼
		modify_cancelBtn = new JButton("취소");
		modify_cancelBtn.setSize(100, 30);
		modify_cancelBtn.setLocation(500, 255);
		modify_cancelBtn.addActionListener(new ModifyBtnActionListener());
		modifyUserFrame.add(modify_cancelBtn);

		modifyUserFrame.setVisible(false);
		
		// 탈퇴 확인창 프레임
		withdrawUserFrame = new JFrame();
		withdrawUserFrame.setTitle("탈퇴");
		withdrawUserFrame.setSize(400,230);
		withdrawUserFrame.setLocation(450, 300);
		withdrawUserFrame.setLayout(null);
		
		withdraw_infoLabel = new JLabel("탈퇴하시려면 비밀번호를 입력해주세요.");
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
		
		withdraw_okBtn = new JButton("확인");
		withdraw_okBtn.setSize(80	,30);
		withdraw_okBtn.setLocation(100, 130);
		withdraw_okBtn.addActionListener(new WithdrawBtnActionListener());
		withdrawUserFrame.add(withdraw_okBtn);
		
		withdraw_cancelBtn = new JButton("취소");
		withdraw_cancelBtn.setSize(80	,30);
		withdraw_cancelBtn.setLocation(220, 130);
		withdraw_cancelBtn.addActionListener(new WithdrawBtnActionListener());
		withdrawUserFrame.add(withdraw_cancelBtn);
		
		withdrawUserFrame.setVisible(false);
		
		// 사용종료창 프레임 설정
		seatEndFrame = new JFrame();
		seatEndFrame.setTitle("사용종료");
		seatEndFrame.setSize(400,230);
		seatEndFrame.setLocation(450, 300);
		seatEndFrame.setLayout(null);
		
		seatEnd_seatIndexLabel = new JLabel();
		seatEnd_seatIndexLabel.setSize(200,80);
		seatEnd_seatIndexLabel.setLocation(122,10);
		seatEndFrame.add(seatEnd_seatIndexLabel);
		
		seatEnd_infoLabel = new JLabel("사용을 종료 하시겠습니까?");
		seatEnd_infoLabel.setSize(200,80);
		seatEnd_infoLabel.setLocation(120,40);
		seatEndFrame.add(seatEnd_infoLabel);
		
		seatEnd_okBtn = new JButton("확인");
		seatEnd_okBtn.setSize(80,30);
		seatEnd_okBtn.setLocation(90,120);
		seatEnd_okBtn.addActionListener(new SeatEndBtnActionListener());		
		seatEndFrame.add(seatEnd_okBtn);
		
		seatEnd_cancelBtn = new JButton("취소");
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
			oos.writeObject(list);//파일 입력의 법칙에 따라 두번 한 것임.
			oos.reset();
			oos.writeObject(list);
			oos.reset();
			
			System.out.println("저장되었습니다.");
			
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
				seatEnd_seatIndexLabel.setText("선택한 좌석은 "+(selectSeatIndex+1)+"번 입니다.");
				seatEndFrame.setVisible(true);
			}
			else
			{
				//b.setBackground(Color.cyan);
				// 전에 클릭했던 좌석 백그라운드 색깔 원래대로
				//if(selectSeatIndex != -1)
					//seatBtnList.get(selectSeatIndex).setBackground(null);
				// 클릭한 좌석 인덱스 저장
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
						
						selectSeatLabel.setText("선택한 좌석은 "+(selectSeatIndex+1)+"번 입니다.");
						countLabel.setText(Integer.toString(time_count));	
						totalLabel.setText("총  "+Integer.toString(time_count)+"시간을 선택 하셨습니다.");
						if(user)				// 회원일 때
						{
							fare = time_count *1000;
						}
						else
							fare = time_count * 1200;
						totalFareLabel.setText("총 금액은 " + fare +"원 입니다.");
					}
				}
				if(!find)
				{
					errorDialog.setVisible(true);
					errorDialog.SetStr("회원 정보가 존재하지 않습니다.");
				}
			}
			else if(login_idTextField.getText().length() == 0 &&  login_pwTextField.getText().length() == 0)
			{
				errorDialog.setVisible(true);
				errorDialog.SetStr("모든 항목이 입력되지 않았습니다.");
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
			selectSeatLabel.setText("선택한 좌석은 "+(selectSeatIndex+1)+"번 입니다.");
			countLabel.setText(Integer.toString(time_count));	
			totalLabel.setText("총  "+Integer.toString(time_count)+"시간을 선택 하셨습니다.");
			if(user)				// 회원일 때
			{
				fare = time_count *1000;
			}
			else
				fare = time_count * 1200;
			totalFareLabel.setText("총 금액은 " + fare +"원 입니다.");
		}
	}
	// 회원가입 확인 버튼 액션 리스너
	class AddUserOKBtnActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			// 확인버튼 눌렀을때 동작할것들 (유저정보 입력한걸로 토대로)
			// 텍스트필드 비어있을때 예외처리
			if(addUser_nameField.getText().length()!=0 && addUser_idField.getText().length()!=0 &&
					addUser_pwField.getText().length()!=0 && addUser_birthField.getText().length()!=0)
			{
				boolean repetition = false;
				for(int i=0; i<userList.size(); i++)
				{
					// 회원가입 ID 중복체크
					if(userList.get(i).getID().equals(addUser_idField.getText()))
					{
						repetition = true;
						errorDialog.setVisible(true);
						errorDialog.SetStr("같은 ID가 있습니다.");
					}
				}
				if(!repetition)
				{
					// new User 생성
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
				errorDialog.SetStr("모든 항목이 선택되지 않았습니다.");
			}
		}
	}

	// 회원가입 취소 버튼 액션 리스너
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
	// Error 메시지 창
	class ErrorDialog extends JDialog{
		JLabel error_label;
		ErrorDialog(){
			setTitle("Error");				
			setSize(400, 200);
			setLocation(550, 250);
			setVisible(false);
			setLayout(null);

			// 에러내용
			error_label = new JLabel();

			add(error_label);	
			error_label.setSize(200,80);
			error_label.setLocation(105,20);

			// 확인 버튼 생성
			JButton b1 = new JButton("확인");
			add(b1);			// 버튼을 컨텐트팬에 부착한다.
			b1.setSize(100,30);
			b1.setLocation(150, 100);
			// 확인용 액션리스너 등록
			b1.addActionListener(new ErrorOKBtnActionListener());
		}
		void SetStr(String str)
		{
			error_label.setText(str);
		}
	}
	// Error 확인 버튼 액션 리스너
	class ErrorOKBtnActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			errorDialog.setVisible(false);	
		}
	}
	// 시간 선택 확인 취소버튼
	class SelectTimeActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if(o.equals(payOkBtn))
			{
				seatBtnList.get(selectSeatIndex).setBackground(Color.yellow);
				if(user)
					seatBtnList.get(selectSeatIndex).setText("<html>"+userList.get(findUserIndex).getName()+"님<br>사용중<br>"+time_count+"시간</html>");
				else
					seatBtnList.get(selectSeatIndex).setText("<html>사용중<br>"+time_count+"시간</html>");
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
	// 시간 선택 버튼 액션 리스너
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
				if(time_count<1)		// 최소시간 1시간 이하로 내려가지 않도록
					time_count=1;
			}
			countLabel.setText(Integer.toString(time_count));	
			totalLabel.setText("총  "+Integer.toString(time_count)+"시간을 선택 하셨습니다.");
			if(user)				// 회원일 때
			{
				fare = time_count *1000;
			}
			else
				fare = time_count * 1200;
			totalFareLabel.setText("총 금액은 " + fare +"원 입니다.");
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
					if(userList.get(i).getName().equals(findUser_nameField.getText()))		// 입력한 이름과 유저정보 리스트에 같은 이름이 있을경우
					{
						findName = true;
						findUser_okBtn.setVisible(true);
						findUserIndex = i;			// userLIst에서 찾은 정보의 인덱스 저장(수정,탈퇴 사용을 위해서)
						findUser_findUserInfoLabel.setText(userList.get(i).getName()+"님의 아이디는 "+userList.get(i).getID()+"입니다.");
						findUser_afterPanel.setVisible(true);
					}
				}
			}
			if(!findName)
			{
				findUser_findUserInfoLabel.setText("           검색결과가 없습니다.");
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
			if(o.equals(findUser_okBtn))		// 확인버튼
			{
				findUser_findUserInfoLabel.setText("");
				findUser_afterPanel.setVisible(false);
				findUser_okBtn.setVisible(false);
				findUserFrame.setVisible(false);
			}
			else if(o.equals(findUser_findUsermodifyBtn))		// 수정버튼
			{
				findUser_findUserInfoLabel.setText("");
				findUser_afterPanel.setVisible(false);
				findUser_okBtn.setVisible(false);
				modifyUserFrame.setVisible(true);
				modify_nameField.setText(userList.get(findUserIndex).getName());
				modify_idField.setText(userList.get(findUserIndex).getID());
			}
			else if(o.equals(findUser_findUserwithdrawBtn))		// 탈퇴버튼
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
				if(modify_pwField.getText().equals(userList.get(findUserIndex).getPassword()))	//찾은 유저정보의 패스워드와 입력한 패스워드 같을경우
				{
					if(modify_newpwField.getText().length() != 0)		// 새로운 패스워드가 있을겨우
					{
						userList.get(findUserIndex).setPassword(modify_newpwField.getText());
					}
					if(modify_birthField.getText().length() != 0)				// 생일칸에 입력값이 있을경우
					{
						userList.get(findUserIndex).setBirth_date(modify_birthField.getText());
					}
					modify_pwField.setText("");
					modify_newpwField.setText("");
					modify_birthField.setText("");
					modifyUserFrame.setVisible(false);
				}
				else		// 패스워드 맞지 않을경우 메시지창을 띄워줌
				{
					errorDialog.setVisible(true);
					errorDialog.SetStr("패스워드가 맞지 않습니다.");
				}
			}
			else			// 취소키 눌렀을 경우 창 닫기
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
				//탈퇴
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
						errorDialog.SetStr("비밀번호가 틀렸습니다.");
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
