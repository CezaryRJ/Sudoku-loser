
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {

	public static void main(String[] args) throws Exception {
		JFrame ramme = new JFrame("Choose an option");
		ramme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ramme.setSize(500, 200);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout());
		JButton velgfil = new JButton("Select a file");
		JButton tastinn = new JButton("Sett up");

		index index = new index();
		Brett brett = new Brett();
		SudokuBeholder svar = new SudokuBeholder();
		velgfil.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser velger = new JFileChooser();
				ramme.setVisible(false);

				if (velger.showOpenDialog(velger) == JFileChooser.APPROVE_OPTION) {

					String ValgtFil = velger.getSelectedFile()
							.getAbsolutePath();
					try {
						brett.lesFil(ValgtFil);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					int ruter = brett.gethoyde() * brett.getlengde();
					GUI gui = new GUI(svar, index, ruter);
					brett.delInnRuter();
					JFrame working = new JFrame("Working");
					JPanel working1 = new JPanel();
					working1.setLayout(new GridLayout());
					
					working.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					working.setVisible(true);
					working.setSize(300,0);
					brett.fyllUtDenneOgResten(brett.brett, svar);
					working.setVisible(false);
					Thread trod = new Thread(gui);
					trod.start();
				}
			}

		});

		tastinn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				JPanel panel = new JPanel();
				panel.setLayout(new FlowLayout());
				JLabel hoyde = new JLabel("Y");
				JTextField ting = new JTextField(5);
				JLabel lengde = new JLabel("X");
				JTextField ting1 = new JTextField(5);
				JButton next = new JButton("Next");
				panel.add(hoyde);
				panel.add(ting);
				panel.add(lengde);
				panel.add(ting1);
				panel.add(next);
				frame.add(panel);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(180, 100);
				ramme.setVisible(false);
				frame.setVisible(true);

				next.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						frame.setVisible(false);
						JFrame frm = new JFrame();
						JPanel brett1 = new JPanel();

						frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						int hoyde1 = Integer.parseInt(ting.getText());
						int lengde1 = Integer.parseInt(ting1.getText());
						int ruter = hoyde1 * lengde1;
						brett1.setLayout(new GridLayout(ruter + 1, ruter));

						JTextField[][] fields = new JTextField[ruter][ruter];
						for (int y = 0; y < ruter; y++) {
							for (int x = 0; x < ruter; x++) {
								fields[y][x] = new JTextField("");
								brett1.add(fields[y][x]);

							}
						}
						brett1.setVisible(true);
						JButton solve = new JButton("SOLVE");
						brett1.add(solve);
						frm.setSize(500, 500);
						frm.add(brett1);
						frm.setVisible(true);
						solve.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								brett1.setVisible(false);
								frm.setVisible(false);
								Skriver skriver = new Skriver();
								try {
									String[][] tall = new String[ruter][ruter];
									for (int y = 0; y < ruter; y++) {
										for (int x = 0; x < ruter; x++) {
											if (fields[y][x].getText().equals("")) {
												tall[y][x] = ".";
												
											} else {
												String tmp = fields[y][x]
														.getText();
											
												tall[y][x] = tmp;
											}
										}
									}
									skriver.lagBrett(tall, hoyde1, lengde1);

									try {
										brett.lesFil("tmp.txt");
									} catch (FileNotFoundException e1) {

										e1.printStackTrace();
									}
									int ruter = brett.gethoyde()
											* brett.getlengde();
									GUI gui = new GUI(svar, index, ruter);
									brett.delInnRuter();
									JFrame working = new JFrame("Working");
									JPanel working1 = new JPanel();
									working1.setLayout(new GridLayout());
									
									working.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
									working.setVisible(true);
									working.setSize(300,0);
									brett.fyllUtDenneOgResten(brett.brett, svar);
									working.setVisible(false);
									Thread trod = new Thread(gui);
									trod.start();
								} catch (FileNotFoundException e1) {

									e1.printStackTrace();
								}

							}
						});

					}
				});

			}
		});

		panel.add(velgfil);
		panel.add(tastinn);
		ramme.add(panel);
		ramme.setVisible(true);

	}
}