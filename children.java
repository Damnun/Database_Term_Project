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

public class children {

	Connection conn;

	JList childrenNames = new JList();

	JButton bSearch;		// 색인 실행을 위한 버튼
	JButton bSave;			// 저장 실행을 위한 버튼
	JButton bDelete;		// 삭제 실행을 위한 버튼
	JButton bNew;			// 신규 실행을 위한 버튼
	JButton bPrint;			// 출력을 위한 버튼
	JButton bPreview;		// 미리보기를 위한 버튼

	JTextField search;		// 색인을 위한  필드
	JTextField childrenName;
	JTextField sponsorName;
	JTextField age;
	JTextField sex;
	JTextField interest_id;
	JTextField address_id;
	JTextField children_id;

	JLabel list = new JLabel("결연 아동 리스트");

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
		JPanel leftTopPanel2 = new JPanel(new RiverLayout());
		JScrollPane cScroller2 = new JScrollPane(childrenNames);
		cScroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cScroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		childrenNames.setVisibleRowCount(7);
		childrenNames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		childrenNames.setFixedCellWidth(100);
		leftTopPanel.add("br center", list);
		leftTopPanel.add("p center", cScroller2);



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
		age = new JTextField(5);
		childrenName = new JTextField(5);
		interest_id = new JTextField(10);
		address_id = new JTextField(10);
		children_id = new JTextField(3);

		rightTopPanel.add("br center", new JLabel("결연 아동 정보"));
		rightTopPanel.add("p left", new JLabel("이름"));
		rightTopPanel.add("tab", childrenName);

		rightTopPanel.add("", new JLabel("나이"));
		rightTopPanel.add("", age);
		rightTopPanel.add("", new JLabel("성별"));
		rightTopPanel.add("tab", sex);
		rightTopPanel.add("br", new JLabel("취미"));
		rightTopPanel.add("tab", interest_id);
		rightTopPanel.add("", new JLabel("주소"));
		rightTopPanel.add("", address_id);


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

		// ActionListener
		sponsorButtonListener sb = new sponsorButtonListener();
		sponsorButton.addActionListener(sb);

		institutionButtonListener ib = new institutionButtonListener();
		institutionButton.addActionListener(ib);


		MySearchListener l = new MySearchListener();
		bNew.addActionListener(new NewButtonListener());
		bSave.addActionListener(new SaveButtonListener());
		bDelete.addActionListener(new DeleteButtonListener());
		search.addActionListener(l);								// 텍스트 박스에서 리턴 눌러 색인 시작 할 때
		bSearch.addActionListener(l);								// 버튼으로 색인 시작할 때 동일한 핸들러 사용	
		bPrint.addActionListener(new DisplayButtonListener());
		bPreview.addActionListener(new DisplayButtonListener());
		childrenNames.addListSelectionListener(new NameListListener());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mainPanel);
		frame.setSize(850,400);
		frame.setVisible(true);

	}

	public void dbConnectionInit() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "mite");	// DB 연결하기
			prepareChildList();
		}
		catch (ClassNotFoundException cnfe) {
			System.out.println("JDBC 드라이버 클래스를 찾을 수 없습니다 : " + cnfe.getMessage());
		}
		catch (Exception ex) {
			System.out.println("DB 연결 에러 : " + ex.getMessage());
		}
	}


	// 리스트 박스에 액션이 발생하면 처리하는 리스너
	public class NameListListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent lse) {					// 리스트의 선택이 바뀔때마다 호출
			if (!lse.getValueIsAdjusting() && !childrenNames.isSelectionEmpty()) {  // 현재 선택이 다 끝난 경우에 처리
				try {
					Statement stmt = conn.createStatement();				// SQL 문장 만들기 위한 Statement 객체
					ResultSet rs = stmt.executeQuery("SELECT * FROM children WHERE name = '" +
							(String)childrenNames.getSelectedValue() + "'");
					rs.next();												// 여러개가 리턴되어도 첫번째 것으로 사용 

					Statement stmt2 = conn.createStatement(); // interest table에 참조하기 위한 stmt
					ResultSet ms = stmt2.executeQuery("SELECT * FROM interest WHERE interest_id = '" + rs.getString("interest_id") +"'");
					ms.next();

					Statement stmt3 = conn.createStatement(); // address table에 참조하기 위한 stmt
					ResultSet ks = stmt3.executeQuery("SELECT * FROM address WHERE address_id = '" + rs.getString("address_id") +"'");
					ks.next();

					childrenName.setText(rs.getString("name"));			// DB에서 리턴 된 값을 가지고 택스트 박스 채움
					age.setText(rs.getString("age"));
					sex.setText(rs.getString("sex"));
					address_id.setText(ks.getString("address"));
					interest_id.setText(ms.getString("interest"));
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

	//children의 리스트
	public void prepareChildList() {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery("USE final_term_proj");
			ResultSet rs = stmt.executeQuery("SELECT * FROM children");
			Vector<String> list = new Vector<String>();
			while(rs.next()) {
				list.add(rs.getString("name"));
			}
			stmt.close();
			Collections.sort(list);
			childrenNames.setListData(list);
			if (!list.isEmpty())
				childrenNames.setSelectedIndex(0);
		} catch (SQLException sqlex) {
			System.out.println("SQL 에러 : " + sqlex.getMessage());
			sqlex.printStackTrace();
		}
	}

	// 색인 컴포넌트의 리스너
	public class MySearchListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			int index = childrenNames.getNextMatch(search.getText().trim(), 0, Position.Bias.Forward);
			if (index != -1) {
				childrenNames.setSelectedIndex(index);
			}
			search.setText("");
		}
	}

	public class sponsorButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			System.out.println("children -> sponsor");
			frame.setVisible(false);
			Donate dj = new Donate();
			dj.setUpGUI();
			dj.dbConnectionInit();
		}
	}

	public class institutionButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			System.out.println("children -> institution");
			frame.setVisible(false);
			institution it = new institution();
			it.setUpGUI();
			it.dbConnectionInit();
		}
	}

	public class NewButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			childrenName.setText("");
			age.setText("");
			sex.setText("");
			address_id.setText("");
			interest_id.setText("");
		}
	}

	public class DeleteButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			try {
				Statement stmt = conn.createStatement();				// SQL 문을 작성을 위한  Statement 객체 생성
				stmt.executeUpdate("UPDATE sponsor SET children_id = null WHERE children_id = (SELECT children_id FROM children WHERE name = '" + childrenName.getText().trim()
						+ "')");
				
				stmt.executeUpdate("DELETE FROM children WHERE name = '" +
						childrenName.getText().trim() + "'");
				stmt.close();
				prepareChildList();											// 리스트 박스 새 리스트로 다시 채움 
			} catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling 에러(DELETE 리스너) : " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

	// 저장 버튼의 리스너
	public class SaveButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			try {
				Statement stmt = conn.createStatement();				// SQL 문을 작성을 위한  Statement 객체 생성

				stmt.executeUpdate("UPDATE sponsor SET children_id = null WHERE children_id = (SELECT children_id FROM children WHERE name = '" + childrenName.getText().trim()
						+ "')");


				stmt.executeUpdate("DELETE FROM children WHERE name = '" +	// 현재 레코드 삭제하고 (name 필드는 변함이 없다고 가정)
						childrenName.getText().trim() + "'");


				//address의 부모 테이블에 미리 DISTINCT 입력
				stmt.executeUpdate("INSERT INTO address (address) SELECT DISTINCT '" + address_id.getText().trim() +"'" +" FROM children");			

				Statement stmt2 = conn.createStatement(); // address id에 참조
				System.out.println(address_id.getText());
				ResultSet ms = stmt2.executeQuery("SELECT * FROM address WHERE address = '" + address_id.getText().trim() +"'");
				ms.next();
				address_id.setText(ms.getString("address_id"));
				System.out.println();


				//children의 부모 테이블에 미리 DISTINCT 입력
				stmt.executeUpdate("INSERT INTO interest (interest) SELECT DISTINCT '" + interest_id.getText().trim() +"'" +" FROM children");	
				System.out.println(interest_id.getText());
				Statement stmt3 = conn.createStatement(); // children id에 참조
				ResultSet ks = stmt3.executeQuery("SELECT * FROM interest WHERE interest = '" + interest_id.getText().trim() +"'");
				ks.next();
				interest_id.setText(ks.getString("interest_id"));


				stmt.executeUpdate("INSERT INTO children (name, age, sex, address_id, interest_id) VALUES ('" +	// 새 레코드로 변경
						childrenName.getText().trim() + "', '" +
						age.getText().trim() + "', '" +
						sex.getText().trim() + "', '" +			
						address_id.getText().trim() + "', '" +
						interest_id.getText().trim() + "')");



				stmt.close();
				prepareChildList();											// 다시 뿌려 
				int index = childrenNames.getNextMatch(childrenName.getText().trim(), 0, Position.Bias.Forward);
				childrenNames.setSelectedIndex(index);

			} catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling 에러(SAVE 리스너) : " + ex.getMessage());
				ex.printStackTrace();
			}
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
				ResultSet rs = stmt.executeQuery("SELECT * FROM children");
				while(rs.next()) {
					line = new RowObjects();								// 5개의 단어가 1줄
					line.add(new PrintObject(rs.getString("name"), 20));
					line.add(new PrintObject(rs.getString("age"), 20));
					line.add(new PrintObject(rs.getString("sex"), 20));
					line.add(new PrintObject(rs.getString("interest_id"), 20));
					line.add(new PrintObject(rs.getString("children_id"), 20));
					line.add(new PrintObject(rs.getString("address_id"), 20));


					rowList.add(line);										// 출력해야 될 전체 리스트를 만듬									
				}
				stmt.close();

				// 각 페이지의 칼럼 헤더를 위해 한 줄 만들음
				line = new RowObjects();									// 5개의 단어가 1줄
				line.add(new PrintObject("이름", 20));
				line.add(new PrintObject("나이", 20));
				line.add(new PrintObject("성별", 20));
				line.add(new PrintObject("취미번호", 20));
				line.add(new PrintObject("아동번호", 20));
				line.add(new PrintObject("주소번호", 20));

				if (e.getSource() == bPrint) {
					Printer prt = new Printer(new PrintObject("결연아동 리스트", 50), line, rowList, true);
					prt.print();
				}
				else {
					Preview prv = new Preview(new PrintObject("결연아동 리스트", 50), line, rowList, true);
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
		children client = new children();
		client.setUpGUI();
		client.dbConnectionInit();
	}

}