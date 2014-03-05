import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Sudoku {
JTable table;
public static void main(String[] args) {
new Sudoku();
}

public Sudoku() {
final ArrayList<Integer>[][] sudokuStart = new ArrayList[9][9];
JFrame frame = new JFrame("Sudoku");
JPanel panel = new JPanel();
DefaultTableModel model = new DefaultTableModel(9, 9);
table = new JTable(model);
table.setColumnSelectionAllowed(true);
table.setRowHeight(44);



JButton Solve = new JButton("Solve");
panel.add(Solve,BorderLayout.CENTER);
JScrollPane pane = new JScrollPane(table);
panel.add(pane);
frame.add(panel);
frame.setSize(700,700);
frame.setUndecorated(true);
frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

frame.setVisible(true);

final ArrayList<Integer> Null = new ArrayList<Integer>(); 
	   Null.add(1);
	   Null.add(2);
	   Null.add(3);
	   Null.add(4);
	   Null.add(5);
	   Null.add(6);
	   Null.add(7);
	   Null.add(8);
	   Null.add(9);
	

Solve.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
    	for(int row = 0; row < 9; row++){
    		for(int column = 0; column < 9; column++){
    			Object Value = table.getModel().getValueAt(row, column);
    			if(Value == null){
    				sudokuStart[row][column] = Null;
    			}
    			else{
    				sudokuStart[row][column]=(ArrayList<Integer>) Value;
    			}
    		}
    	}
  
    }

});

}


}

