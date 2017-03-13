import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements Runnable {
	index index;
	int ruter;
	SudokuBeholder svar;

	GUI(SudokuBeholder svar, index a, int ruter) {
		index = a;
		this.ruter = ruter;
		this.svar = svar;

	}

	public void run() {

		if (svar.antallsvar > 0) {
			JPanel panel = new JPanel();
			JFrame vindu = new JFrame("Solutions");
			vindu.setSize(500, 500);

			vindu.add(panel);

			vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			panel.setLayout(new GridLayout(ruter + 1, ruter));
			JButton[][] knapper = new JButton[ruter][ruter];

			int[][] tmp = svar.taUt(index.getindex());
			for (int y = 0; y < ruter; y++) {
				for (int x = 0; x < ruter; x++) {
					String a = Integer.toString(tmp[y][x]);
					JButton tmp1 = new JButton(a);
					tmp1.setFont(new Font("Arial", Font.BOLD, 40));

					knapper[y][x] = tmp1;
					panel.add(knapper[y][x]);
				}

			}

			JButton forrige = new JButton("<");
			JButton neste = new JButton(">");
			JButton visindex = new JButton("<html>Solution index :" + "<br>"
					+ Integer.toString(index.getindex()) + "</html>");
			JButton antallsvar = new JButton("<html>Solutions found :" + "<br>"
					+ Long.toString(svar.antallsvar()) + "</html>");
			forrige.setFont(new Font("Arial", Font.PLAIN, 20));
			neste.setFont(new Font("Arial", Font.PLAIN, 20));

			neste.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					if (index.getindex() < svar.antallsvar() - 1) {
						index.pluss();
						visindex.setText("<html>Solution index :" + "<br>"
								+ Integer.toString(index.getindex())
								+ "</html>");
						int[][] tmp = svar.taUt(index.getindex());
						for (int y = 0; y < ruter; y++) {
							for (int x = 0; x < ruter; x++) {
								String a = Integer.toString(tmp[y][x]);

								knapper[y][x].setText(a);

							}

						}

					}
				}
			});
			forrige.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if (index.getindex() > 0) {
						index.minus();
						visindex.setText("<html>Solution index :" + "<br>"
								+ Integer.toString(index.getindex())
								+ "</html>");
						int[][] tmp = svar.taUt(index.getindex());
						for (int y = 0; y < ruter; y++) {
							for (int x = 0; x < ruter; x++) {
								String a = Integer.toString(tmp[y][x]);

								knapper[y][x].setText(a);

							}

						}

					}
				}

			});

			panel.add(forrige);
			panel.add(visindex);

			for (int i = 0; i < ruter - 4; i++) {

				panel.add(new JButton(""));
			}
			panel.add(antallsvar);
			panel.add(neste);

			vindu.setVisible(true);

		} else {
			JFrame vindu = new JFrame("NOPE");
			JPanel panel = new JPanel();
			JLabel tekts = new JLabel("No solutions found");
			vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			vindu.setSize(300,90);
			panel.add(tekts);
			vindu.add(panel);
			vindu.setVisible(true);

		}
	}

}
