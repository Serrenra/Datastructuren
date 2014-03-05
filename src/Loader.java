import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Loader {
	int[][] s;
	
	Loader(String filename) throws IOException {
		this.ReadFile(new File(filename));
	}
	private void ReadFile(File filename) throws IOException {
		s = new int[9][9];
		String str;
		int i = 0;
		BufferedReader in =  new BufferedReader(new FileReader(filename));
		try {
			while((str = in.readLine()) != null){
				if(str.length() != 9 || i > 8) {
					throw new Exception();
				}
				for(int j = 0;j<9;j++) {
					s[i][j] = str.charAt(j);
				}
				i++;
			}
			if(i != 9) throw new Exception();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			System.out.println("Wrong file format. A file should be nine lines with nine numbers per line.");
		}
		finally {
			in.close();
		}	
		
	}
	public int[][] getSudoku() {
		return s;
	}
	//test
}
