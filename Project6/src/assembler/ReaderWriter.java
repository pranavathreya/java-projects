package assembler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class ReaderWriter {
	

	public static List<String> readFileInList(String fileName){
		
		List<String> lines = Collections.emptyList();
		try{
			lines =
				Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
			}

		catch (IOException e){
	
		// do something
		e.printStackTrace();
		}
		return lines; 
	}

	public static void writeListToFile(List<String> list1, String[] args, SymbolTable st, SymbolTable lt, SymbolTable ci, SymbolTable djt) {
		BufferedWriter bw = null;
		FileWriter fw = null;
	
		try {
			args[0]=args[0].substring(0,args[0].indexOf("."));
			fw = new FileWriter(args[0]+".hack");
			bw = new BufferedWriter(fw);
			
			
			
			 //Second pass
			 for(int i=0; i<list1.size(); i++) {
			 	//Read ASM file line
				String currLine = list1.get(i).toString();
				currLine = CommandClassifier.commandClassifier(currLine, st, lt, ci, djt, bw, 0);
				bw.write(currLine);				
				
				 }
			 
		}
		catch (IOException e) {;
				 e.printStackTrace();
		}
		
		finally {
				try {
					if (bw != null)
						bw.close();
					if (fw != null)
						fw.close();
				} 
				catch (IOException ex) {
					ex.printStackTrace();
				}
		}
	
	}

}


