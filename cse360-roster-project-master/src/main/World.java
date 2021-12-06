package main;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumn;
import javax.xml.crypto.KeySelector.Purpose;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class World extends JFrame implements ActionListener, Observer {

	final Repository repository;
	final JFrame frame;
	final JFileChooser fc;
	final JMenuItem loadRoster;
	final JMenuItem addAttendance;
	final JMenuItem save;
	final JMenuItem dataPlot;
	final JMenuItem aboutUs;
	final JTextArea log;
	final JTable jtable;
	final MyTableModel tableModel;

	public World() {
		fc = new JFileChooser();
		log = new JTextArea();

		repository = new Repository();
		repository.addObserver(this);

		frame = new JFrame("CSE360 Final Project");

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu aboutMenu = new JMenu("About");

		loadRoster = new JMenuItem("Load a Roster");

		addAttendance = new JMenuItem("Add Attendance");
		addAttendance.setEnabled(false);

		save = new JMenuItem("Save");
		save.setEnabled(false);

		dataPlot = new JMenuItem("Plot Data");
		dataPlot.setEnabled(false);
		
		aboutUs = new JMenuItem("About Us");

		loadRoster.addActionListener(this);
		addAttendance.addActionListener(this);
		save.addActionListener(this);
		dataPlot.addActionListener(this);
		aboutUs.addActionListener(this);

		fileMenu.add(loadRoster);
		fileMenu.add(addAttendance);
		fileMenu.add(save);
		fileMenu.add(dataPlot);
		
		aboutMenu.add(aboutUs);

//		JTextArea aboutInfo = new JTextArea("Amar Yadav (ayadav42)" + "\n\nDanielle Dupont" + "\n\nIsaiah Graham", 35, 37);
//		aboutInfo.setEditable(false);
		aboutMenu.addActionListener(this);

		menuBar.add(fileMenu);
		menuBar.add(aboutMenu);

		frame.setJMenuBar(menuBar);
		frame.setSize(300, 400);
		frame.setPreferredSize(new Dimension(300, 400));

		tableModel = new MyTableModel();
		jtable = new JTable(tableModel);
		jtable.setBounds(30, 40, 200, 300);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		setJTableColumnsWidth(jtable, 40);
		JScrollPane sp = new JScrollPane(jtable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jtable.setFillsViewportHeight(true);
		frame.add(sp);
		frame.setVisible(true);

		frame.setSize(500, 650);
		frame.setVisible(true);
	}

	void setJTableColumnsWidth(JTable table, int colPreferredWidth) {
		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth(colPreferredWidth);
		}
	}

	void setJTableColumnsWidth(JTable table, int tablePreferredWidth, double... percentages) {
		double total = 0;
		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			total += percentages[i];
		}

		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth((int) (tablePreferredWidth * (percentages[i] / total)));
		}
	}

	public void populateTable(String[][] tableInfo, String[] headers) {

//		jtable = new JTable(tableInfo, headers);
		tableModel.updateTable(tableInfo, headers);

	}

	public static void main(String[] args) {
		World world = new World();
	}

	@SuppressWarnings({ "deprecation" })
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == loadRoster) {
				File file = pickAFile();
				if (file != null) {
					repository.addStudentsFromRoster(new FileInputStream(file));
				} else {
					JOptionPane.showMessageDialog(null, "No file selected");
				}

			} else if (e.getSource() == addAttendance) {
				JDatePickerImpl datePicker = pickADate();
				Date selectedDate = (Date) datePicker.getModel().getValue();
				if (selectedDate != null) {
					System.out.println("selectedDate: " + selectedDate.toString());
					SimpleDateFormat dt1 = new SimpleDateFormat("MMM dd");
					String dateHeader = dt1.format(selectedDate);
					File file = pickAFile();
					if (file != null) {
						List<String> messages = repository.addAttendance(new FileInputStream(file), dateHeader);
						if (messages.size() > 0) {
//							JOptionPane.showMessageDialog(null, messages);
							infoBox(messages, "");
						} else {
							JOptionPane.showMessageDialog(null, "Some error ocurred");
						}
					} else {
						JOptionPane.showMessageDialog(null, "No file selected");
					}
				} else {
					JOptionPane.showMessageDialog(null, "No date selected");
				}

			} else if (e.getSource() == save) {
				String filename = repository.saveDataToFile();
				if (filename == "") {
					JOptionPane.showMessageDialog(null, "Some error ocurred");
				} else {
					JOptionPane.showMessageDialog(null, "File saved as " + filename);
				}
			} else if (e.getSource() == dataPlot) {
				System.out.println("repository.numOfNewStudents: " + repository.numOfNewStudents);
				SwingUtilities.invokeLater(() -> {
					System.out.println("repository.numOfNewStudents2: " + repository.numOfNewStudents);
					PlotData example = new PlotData("Scatter Chart Example", repository);
					example.setSize(800, 400);
					example.setLocationRelativeTo(null);
//					example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
					example.setVisible(true);
				});
			} else if(e.getSource() == aboutUs) {
				List<String> messages = new ArrayList<>();
				messages.add("\n");
				messages.add("\n");
				messages.add("Amar Yadav (ayadav42 1219650510)");
				messages.add("Danielle Dupont (ayadav42 1219650510)");
				messages.add("Isaiah Graham (ayadav42 1219650510)");
				infoBox(messages, "About Us");
			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	public JDatePickerImpl pickADate() {
		JPanel panel = new JPanel();
//		panel.add(new JButton("Click"));
//		panel.add(new JTextField(20));
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		panel.add(datePanel);
		panel.add(new JLabel("Pick Date"));
		JOptionPane.showMessageDialog(null, panel, "Information", JOptionPane.INFORMATION_MESSAGE);
		return datePicker;
	}

	public void infoBox(List<String> infoMessages, String titleBar) {
//		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
		JFrame f = new JFrame();
		JDialog d = new JDialog(f, titleBar, true);
		d.setLayout(new FlowLayout());
		JButton b = new JButton("OK");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d.setVisible(false);
			}
		});
		for (String str : infoMessages) {
			d.add(new JLabel(str));
		}
		d.add(b);
		d.setSize(300, 300);
		d.setVisible(true);
	}

	public File pickAFile() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV & Text Files", "csv", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			return chooser.getSelectedFile();
		} else {
			return null;
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		String[][] latestTableInfo = repository.getTableData();
//		print2DArrayOfStrings(latestTableInfo);
		String[] headers = new String[repository.csvHeaders.size()];
		for (int index = 0; index < headers.length; index++) {
			headers[index] = repository.csvHeaders.get(index);
		}
		populateTable(latestTableInfo, headers);
		setJTableColumnsWidth(jtable, 80);
		if (repository.students.size() > 0) {
			addAttendance.setEnabled(true);
			save.setEnabled(true);
			if (repository.csvHeaders.size() > 6) {
				dataPlot.setEnabled(true);
			} else {
				dataPlot.setEnabled(false);
			}
		} else {
			addAttendance.setEnabled(false);
			save.setEnabled(false);
		}
	}

	public void print2DArrayOfStrings(String[][] arr) {
		for (String[] row : arr) {
			for (String str : row) {
				System.out.print(str + " ");
//				System.out.print(str.getClass().getName());
			}
			System.out.println();
		}
	}

}
