package final_term_proj;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Position;

import se.datadosen.component.RiverLayout;

public class Donate {
	
	Connection conn;
	JList sponsorNames = new JList();


	
	JButton bSearch;		// 색인 실행을 위한 버튼
	JButton bSave;			// 저장 실행을 위한 버튼
	JButton bDelete;		// 삭제 실행을 위한 버튼
	JButton bNew;			// 신규 실행을 위한 버튼
	JButton bPrint;			// 출력을 위한 버튼
	JButton bPreview;		// 미리보기를 위한 버튼

	JTextField search;		// 색인을 위한  필드
	JTextField sex;
	JTextField age;
	JTextField mail_address;
	JTextField start_date;
	JTextField end_date;
	JTextField phone_number;
	JTextField nickname;
	JTextField donated_money;
	JTextField institution_id;
	JTextField address_id;
	JTextField children_id;
	JTextField error_message;

	JLabel list = new JLabel("후원자 리스트");

	JButton sponsorButton = new JButton("후원자");
	JButton childrenButton = new JButton("결연 아동");
	JButton institutionButton = new JButton("결연 기관");
	JTextField sponsorname;

	// 최상위 프레임
	JFrame frame;
	String frameTitle = "결연아동 후원 데이터베이스 클라이언트";


	public void setUpGUI() {
		
		frame = new JFrame(frameTitle);

		// 후원자의 이름 리스트 (좌측 상단)
		JPanel leftTopPanel = new JPanel(new RiverLayout());

		JScrollPane cScroller = new JScrollPane(sponsorNames);
		cScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sponsorNames.setVisibleRowCount(7);
		sponsorNames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sponsorNames.setFixedCellWidth(120);
		leftTopPanel.add("br center", list);
		leftTopPanel.add("p center", cScroller);



		// 좌측 하단 검색기능
		JPanel leftBottomPanel = new JPanel(new RiverLayout());
		JPanel tmpPanel = new JPanel(new RiverLayout());
		JPanel tmpPanel1 = new JPanel(new RiverLayout());
		JPanel tmpPanel2 = new JPanel(new RiverLayout());
		search = new JTextField(20);
		bSearch = new JButton("검색");
		bPrint = new JButton("출력");
		bPreview = new JButton("미리보기");
		tmpPanel1.add(search);
		tmpPanel2.add(bSearch);
		tmpPanel2.add(bPrint);
		tmpPanel2.add(bPreview);
		tmpPanel.add("center",tmpPanel1);
		tmpPanel.add("br center",tmpPanel2);
		leftBottomPanel.add("center", tmpPanel);

		//우측 하단 패널
		JPanel rightBottomPanel = new JPanel(new RiverLayout());
		tmpPanel = new JPanel(new RiverLayout());
		bSave = new JButton("저장");
		bDelete = new JButton("삭제");
		bNew = new JButton("신규");
		tmpPanel.add(bSave);
		tmpPanel.add("tab", bDelete);
		tmpPanel.add("tab", bNew);
		rightBottomPanel.add("center", tmpPanel);
		rightBottomPanel.add("br", Box.createRigidArea(new Dimension(0,20)));

		// 후원자의 세부 명세 (우측 상단)
		JPanel rightTopPanel = new JPanel(new RiverLayout());
		sponsorname = new JTextField(5);
		sex = new JTextField(2);
		age = new JTextField(2);
		mail_address = new JTextField(10);
		phone_number = new JTextField(10);
		start_date = new JTextField(10);
		end_date = new JTextField(10);
		nickname = new JTextField(5);
		address_id = new JTextField(10);
		institution_id = new JTextField(10);
		donated_money = new JTextField(8);
		children_id = new JTextField(8);
		error_message = new JTextField(30);
		error_message.setVisible(false);
		error_message.setEditable(false);
		
		rightTopPanel.add("br center", new JLabel("후원자 정보"));
		rightTopPanel.add("br left", new JLabel("이름"));
		rightTopPanel.add("tab", sponsorname);
		rightTopPanel.add("left", new JLabel("닉네임"));
		rightTopPanel.add("", nickname);
		rightTopPanel.add("left", new JLabel("나이"));
		rightTopPanel.add("", age);
		rightTopPanel.add("left", new JLabel("성별"));
		rightTopPanel.add("tab", sex);


		rightTopPanel.add("p left", new JLabel("메일 주소"));
		rightTopPanel.add("tab", mail_address);
		rightTopPanel.add("left", new JLabel("연락처"));
		rightTopPanel.add("", phone_number);

		rightTopPanel.add("br left", new JLabel("시작일"));
		rightTopPanel.add("tab", start_date);
		rightTopPanel.add("left", new JLabel("종료일"));
		rightTopPanel.add("", end_date);

		rightTopPanel.add("br left", new JLabel("주소"));
		rightTopPanel.add("tab", address_id);

		rightTopPanel.add("left", new JLabel("기관명"));
		rightTopPanel.add("", institution_id);
		rightTopPanel.add("br left", new JLabel("결연 아동"));
		rightTopPanel.add("tab", children_id);
		rightTopPanel.add("left", new JLabel("총 기부액"));
		rightTopPanel.add("", donated_money);
		rightTopPanel.add("br center",error_message);




		// GUI 배치 topPanel
		JPanel topPanel = new JPanel(new GridLayout(1,3));
		topPanel.add(sponsorButton);
		topPanel.add(childrenButton);
		topPanel.add(institutionButton);

		// GUI 배치 middlePanel
		JPanel middlePanel = new JPanel(new GridLayout(1,2));
		middlePanel.add(leftTopPanel);
		middlePanel.add(rightTopPanel);

		// GUI 배치 bottomPanel
		JPanel bottomPanel = new JPanel(new GridLayout(1,2));
		bottomPanel.add(leftBottomPanel);
		bottomPanel.add(rightBottomPanel);

		// GUI 배치 mainPanel
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);



		//ActionListener
		childrenButtonListener cb = new childrenButtonListener();
		childrenButton.addActionListener(cb);

		institutionButtonListener ib = new institutionButtonListener();
		institutionButton.addActionListener(ib);

		MySearchListener l = new MySearchListener();
		search.addActionListener(l);								// 텍스트 박스에서 리턴 눌러 색인 시작 할 때
		bSearch.addActionListener(l);								// 버튼으로 색인 시작할 때 동일한 핸들러 사용
		bSave.addActionListener(new SaveButtonListener());
		bDelete.addActionListener(new DeleteButtonListener());
		bNew.addActionListener(new NewButtonListener());
		bPrint.addActionListener(new DisplayButtonListener());
		bPreview.addActionListener(new DisplayButtonListener());
		sponsorNames.addListSelectionListener(new NameListListener());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mainPanel);
		frame.setSize(850,400);
		frame.setVisible(true);

	}

	public void dbConnectionInit() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "mite");	// DB 연결하기
			prepareList();
		}
		catch (ClassNotFoundException cnfe) {
			System.out.println("JDBC 드라이버 클래스를 찾을 수 없습니다 : " + cnfe.getMessage());
		}
		catch (Exception ex) {
			System.out.println("DB 연결 에러 : " + ex.getMessage());
		}
	}


	// 저장 버튼의 리스너
	public class SaveButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			try {
				Statement stmt = conn.createStatement();				// SQL 문을 작성을 위한  Statement 객체 생성
				stmt.executeUpdate("DELETE FROM sponsor WHERE name = '" +	// 현재 레코드 삭제하고 (name 필드는 변함이 없다고 가정)
						sponsorname.getText().trim() + "'");

				//address의 부모 테이블에 미리 DISTINCT 입력
				Statement stmt2 = conn.createStatement(); // address id에 참조
				ResultSet ms = stmt2.executeQuery("SELECT * FROM address WHERE address = '" + address_id.getText().trim() +"'");
				ms.next();
				address_id.setText(ms.getString("address_id"));
				
				
				//children의 부모 테이블에 미리 DISTINCT 입력
				Statement stmt3 = conn.createStatement(); // children id에 참조i
				ResultSet ks = stmt3.executeQuery("SELECT * FROM children WHERE name = '" + children_id.getText().trim() +"'");
				ks.next();
				children_id.setText(ks.getString("children_id"));

				Statement stmt4 = conn.createStatement(); // institution id에 참조
				ResultSet ls = stmt4.executeQuery("SELECT * FROM institution WHERE institution_name = '" + institution_id.getText().trim() +"'");
				ls.next();
				institution_id.setText(ls.getString("institution_id"));
			
				
				
				stmt.executeUpdate("INSERT INTO sponsor (children_id, name, age, sex, mail_address, start_date, end_date, phone_number, address_id, donated_money, nickname, institution_id) VALUES ('" +	// 새 레코드로 변경
						children_id.getText().trim() + "', '" +
						sponsorname.getText().trim() + "', '" +
						age.getText().trim() + "', '" +
						sex.getText().trim() + "', '" +
						mail_address.getText().trim() + "', '" +
						start_date.getText().trim() + "', '" +
						end_date.getText().trim() + "', '" +
						phone_number.getText().trim() + "', '" +
						address_id.getText().trim() + "', '" +
						donated_money.getText().trim() + "', '" +
						nickname.getText().trim() + "', '" +
						institution_id.getText().trim() + "')");
		

				stmt.close();
				prepareList();											// 다시 뿌려 
				int index = sponsorNames.getNextMatch(sponsorname.getText().trim(), 0, Position.Bias.Forward);
				sponsorNames.setSelectedIndex(index);

			} catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling 에러(SAVE 리스너) : " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}




	//Sponsor의 리스트
	public void prepareList() {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery("USE final_term_proj");
			ResultSet rs = stmt.executeQuery("SELECT * FROM sponsor");
			Vector<String> list = new Vector<String>();
			while(rs.next()) {
				list.add(rs.getString("name"));
			}
			stmt.close();
			Collections.sort(list);
			sponsorNames.setListData(list);
			if (!list.isEmpty())
				sponsorNames.setSelectedIndex(0);
		} catch (SQLException sqlex) {
			System.out.println("SQL 에러 : " + sqlex.getMessage());
			sqlex.printStackTrace();
		}
	}
	


	// 리스트 박스에 액션이 발생하면 처리하는 리스너
	public class NameListListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent lse) {				// 리스트의 선택이 바뀔때마다 호출

			if (!lse.getValueIsAdjusting() && !sponsorNames.isSelectionEmpty()) {  // 현재 선택이 다 끝난 경우에 처리
				try {

					Statement stmt = conn.createStatement();				// SQL 문장 만들기 위한 Statement 객체
					ResultSet rs = stmt.executeQuery("SELECT * FROM sponsor WHERE name = '" +
							(String)sponsorNames.getSelectedValue() + "'");
					rs.next();

					Statement stmt2 = conn.createStatement(); // children table에 참조하기 위한 stmt
					ResultSet ms = stmt2.executeQuery("SELECT * FROM children WHERE children_id = '" + rs.getString("children_id") +"'");
					ms.next();

					Statement stmt3 = conn.createStatement(); // address table에 참조하기 위한 stmt
					ResultSet ks = stmt3.executeQuery("SELECT * FROM address WHERE address_id = '" + rs.getString("address_id") +"'");
					ks.next();

					Statement stmt4 = conn.createStatement(); // institution table에 참조하기 위한 stmt
					ResultSet ls = stmt4.executeQuery("SELECT * FROM institution WHERE institution_id = '" + rs.getString("institution_id") +"'");
					ls.next();


					// 여러개가 리턴되어도 첫번째 것으로 사용 
					sponsorname.setText(rs.getString("name"));			// DB에서 리턴 된 값을 가지고 택스트 박스 채움
					age.setText(rs.getString("age"));
					sex.setText(rs.getString("sex"));
					mail_address.setText(rs.getString("mail_address"));	
					start_date.setText(rs.getDate("start_date").toString());
					end_date.setText(rs.getDate("end_date").toString());
					phone_number.setText(rs.getString("phone_number"));
					nickname.setText(rs.getString("nickname"));
					address_id.setText(ks.getString("address"));
					children_id.setText(ms.getString("name"));
					institution_id.setText(ls.getString("institution_name"));
					donated_money.setText(rs.getString("donated_money"));
					stmt.close();


				} catch (SQLException sqlex) {
					System.out.println("SQL 에러 : " + sqlex.getMessage());
					sqlex.printStackTrace();
				} catch (Exception ex) {
					System.out.println("DB Handling 에러(리스트 리스너) : " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
	}




	// 색인 컴포넌트의 리스너
	public class MySearchListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			int index = sponsorNames.getNextMatch(search.getText().trim(), 0, Position.Bias.Forward);
			if (index != -1) {
				sponsorNames.setSelectedIndex(index);
			}
			search.setText("");
		}
	}

	public class childrenButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			System.out.println("sponsor -> children");
			frame.setVisible(false);
			children cj = new children();
			cj.setUpGUI();
			cj.dbConnectionInit();
		}
	}

	public class institutionButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			System.out.println("sponsor -> institution");
			frame.setVisible(false);
			institution it = new institution();
			it.setUpGUI();
			it.dbConnectionInit();
		}
	}


	// 삭제 버튼의 리스너
	public class DeleteButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			try {
				Statement stmt = conn.createStatement();				// SQL 문을 작성을 위한  Statement 객체 생성
				stmt.executeUpdate("DELETE FROM sponsor WHERE name = '" +
						sponsorname.getText().trim() + "'");
				stmt.close();
				prepareList();											// 리스트 박스 새 리스트로 다시 채움 
			} catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling 에러(DELETE 리스너) : " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}


	public class NewButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			sponsorname.setText("");
			age.setText("");
			sex.setText("");
			children_id.setText("");
			mail_address.setText("");
			start_date.setText("");
			end_date.setText("");
			phone_number.setText("");
			address_id.setText("");
			nickname.setText("");
			institution_id.setText("");
			donated_money.setText("");
			sponsorNames.clearSelection();
		}
	}

	// 출력을 위한 액션이 발생하면 처리하는 리스너 (print와 preview)
	public class DisplayButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			// DB에서 가져오는 데이터를 rowObjects의 형태로 저장하고 이들의 리스트를 Printer 또는 Preview로 보내 줌
			ArrayList<RowObjects> rowList = new ArrayList<RowObjects>();	// 행들의 리스트
			RowObjects line;												// 하나의 행
			PrintObject word;												// 하나의 단어
			try {
				Statement stmt = conn.createStatement();					// SQL 문장 만들기 위한 Statement 객체
				ResultSet rs = stmt.executeQuery("SELECT * FROM sponsor");
				while(rs.next()) {
					line = new RowObjects();								// 5개의 단어가 1줄
					line.add(new PrintObject(rs.getString("name"), 20));
					line.add(new PrintObject(rs.getString("age"), 20));
					line.add(new PrintObject(rs.getString("sex"), 20));
					line.add(new PrintObject(rs.getString("nickname"), 20));
					line.add(new PrintObject(rs.getString("address_id"), 20));
					line.add(new PrintObject(rs.getString("institution_id"), 20));
					rowList.add(line);										// 출력해야 될 전체 리스트를 만듬									
				}
				stmt.close();

				// 각 페이지의 칼럼 헤더를 위해 한 줄 만들음
				line = new RowObjects();									// 5개의 단어가 1줄
				line.add(new PrintObject("이름", 20));
				line.add(new PrintObject("나이", 20));
				line.add(new PrintObject("성별", 20));
				line.add(new PrintObject("닉네임", 20));
				line.add(new PrintObject("주소번호", 20));
				line.add(new PrintObject("기관번호", 20));

				if (e.getSource() == bPrint) {
					Printer prt = new Printer(new PrintObject("후원자리스트", 50), line, rowList, true);
					prt.print();
				}
				else {
					Preview prv = new Preview(new PrintObject("후원자리스트", 50), line, rowList, true);
					prv.preview();
				}

			} catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling 에러(리스트 리스너) : " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}


	public static void main(String[] args) {
		Donate client = new Donate();
		client.setUpGUI();
		client.dbConnectionInit();
	}

}