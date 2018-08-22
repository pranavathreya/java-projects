package assembler;

import java.util.Hashtable;

public class SymbolTable<K,V> extends Hashtable<Object, Object>{
	
	private static int varCount=0;
	public SymbolTable() {
		super();
	}
	
	//Predefined Symbols
	public SymbolTable(String n) {
		if(n=="predefined") {
			//Ch.6 pg 110
		 	this.put("SP", "0");
			this.put("LCL", "1");
			this.put("ARG", "2");
			this.put("THIS", "3");
			this.put("THAT", "4");
			this.put("R0", "0");
			this.put("R1", "1");
			this.put("R2", "2");
			this.put("R3", "3");
			this.put("R4", "4");
			this.put("R5", "5");
			this.put("R6", "6");
			this.put("R7", "7");
			this.put("R8", "8");
			this.put("R9", "9");
			this.put("R10", "10");
			this.put("R11", "11");
			this.put("R12", "12");
			this.put("R13", "13");
			this.put("R14", "14");
			this.put("R15", "15");
			this.put("SCREEN", "16384");
			this.put("KBD", "24576");
			}
		
		//C-instruction binary translation:
		//COMP command Translations
		else if(n=="cInstructionComp") {
			//When using D register
			this.put("0","0101010");this.put("D-1","0001110");
	        this.put("A-1","0110010");this.put("D+A","0000010");
	        this.put("A-D","0000111");this.put("D-A","0010011");
			this.put("1","0111111");this.put("D&A","0000000");
			this.put("-1","0111010");this.put("D|A","0010101");
	        this.put("D","0001100");
	        this.put("A","0110000");
	        this.put("!D","0001101");
	        this.put("!A","0110001");
	        this.put("-D","0001111");
	        this.put("-A","0110011");
	        this.put("D+1","0011111");
	        this.put("A+1","0110111");
	        
	        //When using main memory
	        this.put("M","1110000");
	        this.put("!M","1110001");
	        this.put("-M","1110011");
	        this.put("M+1","1110111");
	        this.put("M-1","1110010");
	        this.put("D+M","1000010");
	        this.put("D-M","1010011");
	        this.put("M-D","1000111");
	        this.put("D&M","1000000");
	        this.put("D|M","1010101");
		}
		
		else if(n=="cInstructionDestJump"){	     
	        //put all dst posibilities into a HashMap
	        this.put("","000");this.put("M","001");
	        this.put("D","010");this.put("MD","011");
	        this.put("A","100");this.put("AM","101");
	        this.put("AD","110");this.put("AMD","111");

	        //put all jmp posibilities into a HashMap
	        this.put("","000");this.put("JGT","001");
	        this.put("JEQ","010");this.put("JGE","011");
	        this.put("JLT","100");this.put("JNE","101");
	        this.put("JLE","110");this.put("JMP","111");
		}
	}
		
		

	public void addSymbol(String n) {
		this.put(n, Integer.toString(16+varCount));
		varCount++;
	}
	

}
