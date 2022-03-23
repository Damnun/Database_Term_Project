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


	
	JButton bSearch;		// ���� ������ ���� ��ư
	JButton bSave;			// ���� ������ ���� ��ư
	JButton bDelete;		// ���� ������ ���� ��ư
	JButton bNew;			// �ű� ������ ���� ��ư
	JButton bPrint;			// ����� ���� ��ư
	JButton bPreview;		// �̸����⸦ ���� ��ư

	JTextField search;		// ������ ����  �ʵ�
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

	JLabel list = new JLabel("�Ŀ��� ����Ʈ");

	JButton sponsorButton = new JButton("�Ŀ���");
	JButton childrenButton = new JButton("�Ῥ �Ƶ�");
	JButton institutionButton = new JButton("�Ῥ ���");
	JTextField sponsorname;

	// �ֻ��� ������
	JFrame frame;
	String frameTitle = "�Ῥ�Ƶ� �Ŀ� �����ͺ��̽� Ŭ���̾�Ʈ";


	public void setUpGUI() {
		
		frame = new JFrame(frameTitle);

		// �Ŀ����� �̸� ����Ʈ (���� ���)
		JPanel leftTopPanel = new JPanel(new RiverLayout());

		JScrollPane cScroller = new JScrollPane(sponsorNames);
		cScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sponsorNames.setVisibleRowCount(7);
		sponsorNames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sponsorNames.setFixedCellWidth(120);
		leftTopPanel.add("br center", list);
		leftTopPanel.add("p center", cScroller);



		// ���� �ϴ� �˻����
		JPanel leftBottomPanel = new JPanel(new RiverLayout());
		JPanel tmpPanel = new JPanel(new RiverLayout());
		JPanel tmpPanel1 = new JPanel(new RiverLayout());
		JPanel tmpPanel2 = new JPanel(new RiverLayout());
		search = new JTextField(20);
		bSearch = new JButton("�˻�");
		bPrint = new JButton("���");
		bPreview = new JButton("�̸�����");
		tmpPanel1.add(search);
		tmpPanel2.add(bSearch);
		tmpPanel2.add(bPrint);
		tmpPanel2.add(bPreview);
		tmpPanel.add("center",tmpPanel1);
		tmpPanel.add("br center",tmpPanel2);
		leftBottomPanel.add("center", tmpPanel);

		//���� �ϴ� �г�
		JPanel rightBottomPanel = new JPanel(new RiverLayout());
		tmpPanel = new JPanel(new RiverLayout());
		bSave = new JButton("����");
		bDelete = new JButton("����");
		bNew = new JButton("�ű�");
		tmpPanel.add(bSave);
		tmpPanel.add("tab", bDelete);
		tmpPanel.add("tab", bNew);
		rightBottomPanel.add("center", tmpPanel);
		rightBottomPanel.add("br", Box.createRigidArea(new Dimension(0,20)));

		// �Ŀ����� ���� �� (���� ���)
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
		
		rightTopPanel.add("br center", new JLabel("�Ŀ��� ����"));
		rightTopPanel.add("br left", new JLabel("�̸�"));
		rightTopPanel.add("tab", sponsorname);
		rightTopPanel.add("left", new JLabel("�г���"));
		rightTopPanel.add("", nickname);
		rightTopPanel.add("left", new JLabel("����"));
		rightTopPanel.add("", age);
		rightTopPanel.add("left", new JLabel("����"));
		rightTopPanel.add("tab", sex);


		rightTopPanel.add("p left", new JLabel("���� �ּ�"));
		rightTopPanel.add("tab", mail_address);
		rightTopPanel.add("left", new JLabel("����ó"));
		rightTopPanel.add("", phone_number);

		rightTopPanel.add("br left", new JLabel("������"));
		rightTopPanel.add("tab", start_date);
		rightTopPanel.add("left", new JLabel("������"));
		rightTopPanel.add("", end_date);

		rightTopPanel.add("br left", new JLabel("�ּ�"));
		rightTopPanel.add("tab", address_id);

		rightTopPanel.add("left", new JLabel("�����"));
		rightTopPanel.add("", institution_id);
		rightTopPanel.add("br left", new JLabel("�Ῥ �Ƶ�"));
		rightTopPanel.add("tab", children_id);
		rightTopPanel.add("left", new JLabel("�� ��ξ�"));
		rightTopPanel.add("", donated_money);
		rightTopPanel.add("br center",error_message);




		// GUI ��ġ topPanel
		JPanel topPanel = new JPanel(new GridLayout(1,3));
		topPanel.add(sponsorButton);
		topPanel.add(childrenButton);
		topPanel.add(institutionButton);

		// GUI ��ġ middlePanel
		JPanel middlePanel = new JPanel(new GridLayout(1,2));
		middlePanel.add(leftTopPanel);
		middlePanel.add(rightTopPanel);

		// GUI ��ġ bottomPanel
		JPanel bottomPanel = new JPanel(new GridLayout(1,2));
		bottomPanel.add(leftBottomPanel);
		bottomPanel.add(rightBottomPanel);

		// GUI ��ġ mainPanel
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
		search.addActionListener(l);								// �ؽ�Ʈ �ڽ����� ���� ���� ���� ���� �� ��
		bSearch.addActionListener(l);								// ��ư���� ���� ������ �� ������ �ڵ鷯 ���
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "mite");	// DB �����ϱ�
			prepareList();
		}
		catch (ClassNotFoundException cnfe) {
			System.out.println("JDBC ����̹� Ŭ������ ã�� �� �����ϴ� : " + cnfe.getMessage());
		}
		catch (Exception ex) {
			System.out.println("DB ���� ���� : " + ex.getMessage());
		}
	}


	// ���� ��ư�� ������
	public class SaveButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			try {
				Statement stmt = conn.createStatement();				// SQL ���� �ۼ��� ����  Statement ��ü ����
				stmt.executeUpdate("DELETE FROM sponsor WHERE name = '" +	// ���� ���ڵ� �����ϰ� (name �ʵ�� ������ ���ٰ� ����)
						sponsorname.getText().trim() + "'");

				//address�� �θ� ���̺� �̸� DISTINCT �Է�
				Statement stmt2 = conn.createStatement(); // address id�� ����
				ResultSet ms = stmt2.executeQuery("SELECT * FROM address WHERE address = '" + address_id.getText().trim() +"'");
				ms.next();
				address_id.setText(ms.getString("address_id"));
				
				
				//children�� �θ� ���̺� �̸� DISTINCT �Է�
				Statement stmt3 = conn.createStatement(); // children id�� ����i
				ResultSet ks = stmt3.executeQuery("SELECT * FROM children WHERE name = '" + children_id.getText().trim() +"'");
				ks.next();
				children_id.setText(ks.getString("children_id"));

				Statement stmt4 = conn.createStatement(); // institution id�� ����
				ResultSet ls = stmt4.executeQuery("SELECT * FROM institution WHERE institution_name = '" + institution_id.getText().trim() +"'");
				ls.next();
				institution_id.setText(ls.getString("institution_id"));
			
				
				
				stmt.executeUpdate("INSERT INTO sponsor (children_id, name, age, sex, mail_address, start_date, end_date, phone_number, address_id, donated_money, nickname, institution_id) VALUES ('" +	// �� ���ڵ�� ����
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
				prepareList();											// �ٽ� �ѷ� 
				int index = sponsorNames.getNextMatch(sponsorname.getText().trim(), 0, Position.Bias.Forward);
				sponsorNames.setSelectedIndex(index);

			} catch (SQLException sqlex) {
				System.out.println("SQL ���� : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling ����(SAVE ������) : " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}




	//Sponsor�� ����Ʈ
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
			System.out.println("SQL ���� : " + sqlex.getMessage());
			sqlex.printStackTrace();
		}
	}
	


	// ����Ʈ �ڽ��� �׼��� �߻��ϸ� ó���ϴ� ������
	public class NameListListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent lse) {				// ����Ʈ�� ������ �ٲ𶧸��� ȣ��

			if (!lse.getValueIsAdjusting() && !sponsorNames.isSelectionEmpty()) {  // ���� ������ �� ���� ��쿡 ó��
				try {

					Statement stmt = conn.createStatement();				// SQL ���� ����� ���� Statement ��ü
					ResultSet rs = stmt.executeQuery("SELECT * FROM sponsor WHERE name = '" +
							(String)sponsorNames.getSelectedValue() + "'");
					rs.next();

					Statement stmt2 = conn.createStatement(); // children table�� �����ϱ� ���� stmt
					ResultSet ms = stmt2.executeQuery("SELECT * FROM children WHERE children_id = '" + rs.getString("children_id") +"'");
					ms.next();

					Statement stmt3 = conn.createStatement(); // address table�� �����ϱ� ���� stmt
					ResultSet ks = stmt3.executeQuery("SELECT * FROM address WHERE address_id = '" + rs.getString("address_id") +"'");
					ks.next();

					Statement stmt4 = conn.createStatement(); // institution table�� �����ϱ� ���� stmt
					ResultSet ls = stmt4.executeQuery("SELECT * FROM institution WHERE institution_id = '" + rs.getString("institution_id") +"'");
					ls.next();


					// �������� ���ϵǾ ù��° ������ ��� 
					sponsorname.setText(rs.getString("name"));			// DB���� ���� �� ���� ������ �ý�Ʈ �ڽ� ä��
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
					System.out.println("SQL ���� : " + sqlex.getMessage());
					sqlex.printStackTrace();
				} catch (Exception ex) {
					System.out.println("DB Handling ����(����Ʈ ������) : " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
	}




	// ���� ������Ʈ�� ������
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


	// ���� ��ư�� ������
	public class DeleteButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			try {
				Statement stmt = conn.createStatement();				// SQL ���� �ۼ��� ����  Statement ��ü ����
				stmt.executeUpdate("DELETE FROM sponsor WHERE name = '" +
						sponsorname.getText().trim() + "'");
				stmt.close();
				prepareList();											// ����Ʈ �ڽ� �� ����Ʈ�� �ٽ� ä�� 
			} catch (SQLException sqlex) {
				System.out.println("SQL ���� : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling ����(DELETE ������) : " + ex.getMessage());
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

	// ����� ���� �׼��� �߻��ϸ� ó���ϴ� ������ (print�� preview)
	public class DisplayButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			// DB���� �������� �����͸� rowObjects�� ���·� �����ϰ� �̵��� ����Ʈ�� Printer �Ǵ� Preview�� ���� ��
			ArrayList<RowObjects> rowList = new ArrayList<RowObjects>();	// ����� ����Ʈ
			RowObjects line;												// �ϳ��� ��
			PrintObject word;												// �ϳ��� �ܾ�
			try {
				Statement stmt = conn.createStatement();					// SQL ���� ����� ���� Statement ��ü
				ResultSet rs = stmt.executeQuery("SELECT * FROM sponsor");
				while(rs.next()) {
					line = new RowObjects();								// 5���� �ܾ 1��
					line.add(new PrintObject(rs.getString("name"), 20));
					line.add(new PrintObject(rs.getString("age"), 20));
					line.add(new PrintObject(rs.getString("sex"), 20));
					line.add(new PrintObject(rs.getString("nickname"), 20));
					line.add(new PrintObject(rs.getString("address_id"), 20));
					line.add(new PrintObject(rs.getString("institution_id"), 20));
					rowList.add(line);										// ����ؾ� �� ��ü ����Ʈ�� ����									
				}
				stmt.close();

				// �� �������� Į�� ����� ���� �� �� ������
				line = new RowObjects();									// 5���� �ܾ 1��
				line.add(new PrintObject("�̸�", 20));
				line.add(new PrintObject("����", 20));
				line.add(new PrintObject("����", 20));
				line.add(new PrintObject("�г���", 20));
				line.add(new PrintObject("�ּҹ�ȣ", 20));
				line.add(new PrintObject("�����ȣ", 20));

				if (e.getSource() == bPrint) {
					Printer prt = new Printer(new PrintObject("�Ŀ��ڸ���Ʈ", 50), line, rowList, true);
					prt.print();
				}
				else {
					Preview prv = new Preview(new PrintObject("�Ŀ��ڸ���Ʈ", 50), line, rowList, true);
					prv.preview();
				}

			} catch (SQLException sqlex) {
				System.out.println("SQL ���� : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling ����(����Ʈ ������) : " + ex.getMessage());
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