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

	JButton bSearch;		// ���� ������ ���� ��ư
	JButton bSave;			// ���� ������ ���� ��ư
	JButton bDelete;		// ���� ������ ���� ��ư
	JButton bNew;			// �ű� ������ ���� ��ư
	JButton bPrint;			// ����� ���� ��ư
	JButton bPreview;		// �̸����⸦ ���� ��ư

	JTextField search;		// ������ ����  �ʵ�
	JTextField childrenName;
	JTextField sponsorName;
	JTextField age;
	JTextField sex;
	JTextField interest_id;
	JTextField address_id;
	JTextField children_id;

	JLabel list = new JLabel("�Ῥ �Ƶ� ����Ʈ");

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
		JPanel leftTopPanel2 = new JPanel(new RiverLayout());
		JScrollPane cScroller2 = new JScrollPane(childrenNames);
		cScroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cScroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		childrenNames.setVisibleRowCount(7);
		childrenNames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		childrenNames.setFixedCellWidth(100);
		leftTopPanel.add("br center", list);
		leftTopPanel.add("p center", cScroller2);



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
		age = new JTextField(5);
		childrenName = new JTextField(5);
		interest_id = new JTextField(10);
		address_id = new JTextField(10);
		children_id = new JTextField(3);

		rightTopPanel.add("br center", new JLabel("�Ῥ �Ƶ� ����"));
		rightTopPanel.add("p left", new JLabel("�̸�"));
		rightTopPanel.add("tab", childrenName);

		rightTopPanel.add("", new JLabel("����"));
		rightTopPanel.add("", age);
		rightTopPanel.add("", new JLabel("����"));
		rightTopPanel.add("tab", sex);
		rightTopPanel.add("br", new JLabel("���"));
		rightTopPanel.add("tab", interest_id);
		rightTopPanel.add("", new JLabel("�ּ�"));
		rightTopPanel.add("", address_id);


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

		// ActionListener
		sponsorButtonListener sb = new sponsorButtonListener();
		sponsorButton.addActionListener(sb);

		institutionButtonListener ib = new institutionButtonListener();
		institutionButton.addActionListener(ib);


		MySearchListener l = new MySearchListener();
		bNew.addActionListener(new NewButtonListener());
		bSave.addActionListener(new SaveButtonListener());
		bDelete.addActionListener(new DeleteButtonListener());
		search.addActionListener(l);								// �ؽ�Ʈ �ڽ����� ���� ���� ���� ���� �� ��
		bSearch.addActionListener(l);								// ��ư���� ���� ������ �� ������ �ڵ鷯 ���	
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "mite");	// DB �����ϱ�
			prepareChildList();
		}
		catch (ClassNotFoundException cnfe) {
			System.out.println("JDBC ����̹� Ŭ������ ã�� �� �����ϴ� : " + cnfe.getMessage());
		}
		catch (Exception ex) {
			System.out.println("DB ���� ���� : " + ex.getMessage());
		}
	}


	// ����Ʈ �ڽ��� �׼��� �߻��ϸ� ó���ϴ� ������
	public class NameListListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent lse) {					// ����Ʈ�� ������ �ٲ𶧸��� ȣ��
			if (!lse.getValueIsAdjusting() && !childrenNames.isSelectionEmpty()) {  // ���� ������ �� ���� ��쿡 ó��
				try {
					Statement stmt = conn.createStatement();				// SQL ���� ����� ���� Statement ��ü
					ResultSet rs = stmt.executeQuery("SELECT * FROM children WHERE name = '" +
							(String)childrenNames.getSelectedValue() + "'");
					rs.next();												// �������� ���ϵǾ ù��° ������ ��� 

					Statement stmt2 = conn.createStatement(); // interest table�� �����ϱ� ���� stmt
					ResultSet ms = stmt2.executeQuery("SELECT * FROM interest WHERE interest_id = '" + rs.getString("interest_id") +"'");
					ms.next();

					Statement stmt3 = conn.createStatement(); // address table�� �����ϱ� ���� stmt
					ResultSet ks = stmt3.executeQuery("SELECT * FROM address WHERE address_id = '" + rs.getString("address_id") +"'");
					ks.next();

					childrenName.setText(rs.getString("name"));			// DB���� ���� �� ���� ������ �ý�Ʈ �ڽ� ä��
					age.setText(rs.getString("age"));
					sex.setText(rs.getString("sex"));
					address_id.setText(ks.getString("address"));
					interest_id.setText(ms.getString("interest"));
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

	//children�� ����Ʈ
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
			System.out.println("SQL ���� : " + sqlex.getMessage());
			sqlex.printStackTrace();
		}
	}

	// ���� ������Ʈ�� ������
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
				Statement stmt = conn.createStatement();				// SQL ���� �ۼ��� ����  Statement ��ü ����
				stmt.executeUpdate("UPDATE sponsor SET children_id = null WHERE children_id = (SELECT children_id FROM children WHERE name = '" + childrenName.getText().trim()
						+ "')");
				
				stmt.executeUpdate("DELETE FROM children WHERE name = '" +
						childrenName.getText().trim() + "'");
				stmt.close();
				prepareChildList();											// ����Ʈ �ڽ� �� ����Ʈ�� �ٽ� ä�� 
			} catch (SQLException sqlex) {
				System.out.println("SQL ���� : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling ����(DELETE ������) : " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

	// ���� ��ư�� ������
	public class SaveButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			try {
				Statement stmt = conn.createStatement();				// SQL ���� �ۼ��� ����  Statement ��ü ����

				stmt.executeUpdate("UPDATE sponsor SET children_id = null WHERE children_id = (SELECT children_id FROM children WHERE name = '" + childrenName.getText().trim()
						+ "')");


				stmt.executeUpdate("DELETE FROM children WHERE name = '" +	// ���� ���ڵ� �����ϰ� (name �ʵ�� ������ ���ٰ� ����)
						childrenName.getText().trim() + "'");


				//address�� �θ� ���̺� �̸� DISTINCT �Է�
				stmt.executeUpdate("INSERT INTO address (address) SELECT DISTINCT '" + address_id.getText().trim() +"'" +" FROM children");			

				Statement stmt2 = conn.createStatement(); // address id�� ����
				System.out.println(address_id.getText());
				ResultSet ms = stmt2.executeQuery("SELECT * FROM address WHERE address = '" + address_id.getText().trim() +"'");
				ms.next();
				address_id.setText(ms.getString("address_id"));
				System.out.println();


				//children�� �θ� ���̺� �̸� DISTINCT �Է�
				stmt.executeUpdate("INSERT INTO interest (interest) SELECT DISTINCT '" + interest_id.getText().trim() +"'" +" FROM children");	
				System.out.println(interest_id.getText());
				Statement stmt3 = conn.createStatement(); // children id�� ����
				ResultSet ks = stmt3.executeQuery("SELECT * FROM interest WHERE interest = '" + interest_id.getText().trim() +"'");
				ks.next();
				interest_id.setText(ks.getString("interest_id"));


				stmt.executeUpdate("INSERT INTO children (name, age, sex, address_id, interest_id) VALUES ('" +	// �� ���ڵ�� ����
						childrenName.getText().trim() + "', '" +
						age.getText().trim() + "', '" +
						sex.getText().trim() + "', '" +			
						address_id.getText().trim() + "', '" +
						interest_id.getText().trim() + "')");



				stmt.close();
				prepareChildList();											// �ٽ� �ѷ� 
				int index = childrenNames.getNextMatch(childrenName.getText().trim(), 0, Position.Bias.Forward);
				childrenNames.setSelectedIndex(index);

			} catch (SQLException sqlex) {
				System.out.println("SQL ���� : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling ����(SAVE ������) : " + ex.getMessage());
				ex.printStackTrace();
			}
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
				ResultSet rs = stmt.executeQuery("SELECT * FROM children");
				while(rs.next()) {
					line = new RowObjects();								// 5���� �ܾ 1��
					line.add(new PrintObject(rs.getString("name"), 20));
					line.add(new PrintObject(rs.getString("age"), 20));
					line.add(new PrintObject(rs.getString("sex"), 20));
					line.add(new PrintObject(rs.getString("interest_id"), 20));
					line.add(new PrintObject(rs.getString("children_id"), 20));
					line.add(new PrintObject(rs.getString("address_id"), 20));


					rowList.add(line);										// ����ؾ� �� ��ü ����Ʈ�� ����									
				}
				stmt.close();

				// �� �������� Į�� ����� ���� �� �� ������
				line = new RowObjects();									// 5���� �ܾ 1��
				line.add(new PrintObject("�̸�", 20));
				line.add(new PrintObject("����", 20));
				line.add(new PrintObject("����", 20));
				line.add(new PrintObject("��̹�ȣ", 20));
				line.add(new PrintObject("�Ƶ���ȣ", 20));
				line.add(new PrintObject("�ּҹ�ȣ", 20));

				if (e.getSource() == bPrint) {
					Printer prt = new Printer(new PrintObject("�Ῥ�Ƶ� ����Ʈ", 50), line, rowList, true);
					prt.print();
				}
				else {
					Preview prv = new Preview(new PrintObject("�Ῥ�Ƶ� ����Ʈ", 50), line, rowList, true);
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
		children client = new children();
		client.setUpGUI();
		client.dbConnectionInit();
	}

}