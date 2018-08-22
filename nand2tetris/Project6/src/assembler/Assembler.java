package assembler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import assembler.ReaderWriter; 
import assembler.CommandClassifier; 
import assembler.SymbolTable;

public class Assembler {
	

	
	public static void main(String[] args) {
		
		
		 //First pass
		 List list1 = ReaderWriter.readFileInList(args[0]);
		 
		 //Prepopulate symbol table
		 SymbolTable preDefTable = new SymbolTable("predefined");
		 SymbolTable cInstTable = new SymbolTable("cInstructionComp");
		 SymbolTable destJumpTable = new SymbolTable("cInstructionDestJump");
		 SymbolTable labelTable = new SymbolTable(null);
		 
		 int actLine=0;
		 
		 for(int i=0; i< list1.size(); i++) {
		 	//Read ASM file line
			String currLine = list1.get(i).toString();
			
			//Getting rid of comments and blank spaces.
			currLine = CommandClassifier.strip(currLine);
			//System.out.println(currLine);
			//If line is a label, add to table with value=line number 
			if(!currLine.matches("")) {
				actLine++;
				//System.out.println(currLine);
				if( currLine.startsWith("(") ) {
					actLine=actLine-1;
					//System.out.println(currLine);
					currLine = currLine.substring(1,currLine.indexOf(")"));
					labelTable.put(currLine, actLine);  
				}
			}	
		 
		 }
		 
		//System.out.println(labelTable); 
		ReaderWriter.writeListToFile(list1, args, preDefTable, labelTable, cInstTable, destJumpTable);
		
		
	}
}
	

