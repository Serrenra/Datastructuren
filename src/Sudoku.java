import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Sudoku {
	JTable table;
	int[][] sudoku;

	public static void main(String[] args) {
		new Sudoku();
	}

	public Sudoku() {
		sudoku = new int[9][9];
		final int[][] mogelijkeWaardes = new int[9][9];
		for(int[] x: mogelijkeWaardes) {
			//Alle mogelijke waardes zijn bij instantieren 511. Dus 111111111.
			Arrays.fill(x, 511);
		}
		JFrame frame = new JFrame("Sudoku");
		final JPanel panel = new JPanel();
		DefaultTableModel model = new DefaultTableModel(9, 9);
		table = new JTable(model);
		table.setColumnSelectionAllowed(true);
		table.setRowHeight(44);

		JButton Solve = new JButton("Solve");
		panel.add(Solve, BorderLayout.CENTER);
		//Nieuwe load button.
		JButton Load = new JButton("Load");
		panel.add(Load, BorderLayout.CENTER);
		JScrollPane pane = new JScrollPane(table);
		panel.add(pane);
		frame.add(panel);
		frame.setSize(700, 700);
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JFileChooser fc = new JFileChooser();
		frame.setVisible(true);

		Solve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int row = 0; row < 9; row++) {
					for (int column = 0; column < 9; column++) {
						Integer Value = (Integer) table.getModel().getValueAt(row, column);
						if (Value == null || Value == 0) {
							sudoku[row][column] = 0;
						} else if(Value > 0 && Value <= 9){
							//Er is al bepaald wat dit veld is dus mogelijkeWaardes zijn er niet meer.
							mogelijkeWaardes[row][column] = 0;
							sudoku[row][column] = Value;
						}
					}
				}
				
			}

		});
		//Load laat de file opener zien en update de GUI.
		Load.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(panel);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						sudoku = ReadFile(file);
						for(int i = 0;i<sudoku.length;i++) {
							for(int j = 0;j<sudoku[i].length;j++) {
								table.getModel().setValueAt(sudoku[i][j], i, j);
							}
							System.out.println();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			}	
		});
	}
	//Deze ipv Loader.class
	//Geeft int[][] terug van File.
	private int[][] ReadFile(File filename) throws IOException {
		int[][] s = new int[9][9];
		String str;
		int i = 0;
		BufferedReader in = null;
		try {
			in =  new BufferedReader(new FileReader(filename));
			while((str = in.readLine()) != null){
				if(str.length() != 9 || i > 8) {
					throw new Exception();
				}
				for(int j = 0;j<9;j++) {
					s[i][j] = Character.getNumericValue(str.charAt(j)); 
				}
				i++;
			}
			if(i != 9) throw new Exception();
		} catch(IOException e) {
			System.out.println("File error, does it exist?");
		} catch(Exception e) {
			System.out.println("Wrong file format. A file should be nine lines with nine numbers per line.");
		}
		finally {
			if(in!=null) {
				in.close();
			}
		}
		return s;
	}
}
