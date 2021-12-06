package main;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class PlotData extends JFrame {

//	private static final long serialVersionUID = 6294689542092367723L;
	private Repository repository;

	public PlotData(String title, Repository repository) {
		super(title);

		this.repository = repository;
		System.out.println("repository.numOfNewStudents2: " + this.repository.numOfNewStudents);
		// Create dataset
		XYDataset dataset = createDataset();

		// Create chart
		JFreeChart chart = ChartFactory.createScatterPlot("Student Attendance", "Percentage of Attendance",
				"Number of Students", dataset, PlotOrientation.VERTICAL, true, true, false);

		// Changes background color
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(new Color(255, 228, 196));

		// Create Panel
		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);
	}

	public double calculatePercentAttendance(double time) {
		System.out.println("Time: " + time);
		double percentAttendance;
		if (time >= 75) {
			percentAttendance = 10.0;
		} else {
			System.out.println("calculation:" + time / 75 * 10);
			percentAttendance = time / 75 * 10;
			System.out.println("percent attendance1: " + percentAttendance);
			if ((percentAttendance - (int) percentAttendance) < 0.25) {
				percentAttendance = (int) percentAttendance;
			} else if (0.25 <= (percentAttendance - (int) percentAttendance)
					&& (percentAttendance - (int) percentAttendance) < 0.75) {
				percentAttendance = (int) percentAttendance + 0.5;
			} else {
				percentAttendance = Math.ceil(percentAttendance);
			}
		}
		System.out.println("percent attendance: " + percentAttendance);
		return percentAttendance;
	}

	private XYDataset createDataset2() {
		XYSeriesCollection dataset = new XYSeriesCollection();
		// XYSeries series1 = new XYSeries("Boys"); example taken from the link that you
		// gave
		// here you have to read data from the file about which you was talking. one by
		// one
		// then as shown in the example, add in the series. and then do create a final
		// dataset.
		// series1.add(1, 72.9); like this
		// and finally,
		// dataset.addSeries(series1);
		// at the end return this dataset

		String date;
		XYSeriesCollection AttendancePercent = new XYSeriesCollection();
		List<Student> StudentArray = repository.students;
		List<String> csvHeaders = repository.csvHeaders;

		for (int index = 6; index < csvHeaders.size(); index++) {
			XYSeries newSeries = new XYSeries(csvHeaders.get(index));
			double count[] = new double[101];

			for (Student student : StudentArray) {
				String str[] = student.toString().split(",");
				date = csvHeaders.get(index);
				if (index < str.length) {
					double time = Double.parseDouble(str[index]);
					double percentAttendance = calculatePercentAttendance(time);
					int percent = (int) (percentAttendance * 10); // 7.5657 -> 75
					count[percent]++;
				}
			}

			System.out.println("count: " + count);

			for (double d : count) {
				if (d != 0.0) {
					System.out.print(d + " ");
				}
			}
			System.out.println("");
			for (int j = 0; j < count.length; j = j + 5) {
				if (count[j] == 0) {

				} else {
					newSeries.add(((double) j) / 10, count[j]);
					System.out.println("x-value: " + j / 10);
					System.out.println("y-value: " + count[j]);
				}
			}

			dataset.addSeries(newSeries);
		}

//		dateList : { [0.0,0.0,1.0,3.0,....(100 doubles)],  [0.0,2.0,1.0,3.0,....(100 doubles)]}

//		for (int i = 6; i < csvHeaders.size(); i++) {
//			
//
//		}
		return dataset;
	}

	private XYDataset createDataset() {
		XYSeriesCollection dataset = new XYSeriesCollection();
		// XYSeries series1 = new XYSeries("Boys"); example taken from the link that you
		// gave
		// here you have to read data from the file about which you was talking. one by
		// one
		// then as shown in the example, add in the series. and then do create a final
		// dataset.
		// series1.add(1, 72.9); like this
		// and finally,
		// dataset.addSeries(series1);
		// at the end return this dataset

		String date;
		XYSeriesCollection AttendancePercent = new XYSeriesCollection();
		List<Student> StudentArray = repository.students;
		List<String> csvHeaders = repository.csvHeaders;

		List<double[]> list = new ArrayList<double[]>();
		for (String header : csvHeaders) {
			list.add(new double[101]);
		}
		System.out.println("list size: " + list.size());

		for (Student student : StudentArray) {
			String str[] = student.toString().split(",");
			for (int index = 6; index < str.length; index++) {
				System.out.println("list size: " + list.size());
				double[] count = list.get(index); // 6(Nov 20), 7(Nov 21)

				double time = Double.parseDouble(str[index]);
				double percentAttendance = calculatePercentAttendance(time);
				int percent = (int) (percentAttendance * 10); // 7.5657 -> 75

				count[percent]++;
			}
		}

		for (int j = 6; j < list.size(); j++) {
			XYSeries newSeries = new XYSeries(csvHeaders.get(j));
			double[] count = list.get(j); // [1.0, 2.0, ..] Nov 20
			System.out.println("count for " + csvHeaders.get(j));
			for (int k = 0; k < count.length; k = k + 5) {
				System.out.print(count[k] + " ");
				if (count[k] != 0) {
					newSeries.add(((double) k) / 10, count[k]);
					System.out.println("x-value: " + k / 10);
					System.out.println("y-value: " + count[k]);
				}
			}
			System.out.println("");
			dataset.addSeries(newSeries);
		}

		return dataset;
	}

}
