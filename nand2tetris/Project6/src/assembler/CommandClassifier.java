package assembler;

import java.io.BufferedWriter;

public class CommandClassifier {
	
	//Remove comments and spaces from a given line.
    public static String strip(String command) {
    		String strippedStr = "";
    		int offset = command.indexOf("//");

    		if (-1 != offset) {
    		    command = command.substring(0, offset);
    		}
    		String[] arr1 = command.split(" ");

    		for(int i=0; i<arr1.length; i++) {
    			if(arr1[i]!=" ") strippedStr+=arr1[i];
    		}
    		return strippedStr;
    }
	
    
	public static String leftZeroFill(String binValue, int bits){

        for (int i = binValue.length(); i < bits; i++){
            binValue = "0" + binValue;
        }

        return binValue;
    }
	
	public static String commandClassifier(String currLine, SymbolTable st, SymbolTable lt, SymbolTable ci, SymbolTable djt, BufferedWriter bw, int varCount) {
		
		//Ignore comments
		if(!currLine.startsWith("/")) {
			currLine = CommandClassifier.strip(currLine);
			
			//If A-Command
			if(currLine.startsWith("@")) {

				String valueStr =currLine.substring(1);		
				
				//Check if it is a VALUE on the table
				if(st.containsValue(valueStr)){
					String binValue = Integer.toBinaryString
							((Integer.parseInt(valueStr)));
					
					currLine = "0"+ leftZeroFill(binValue, 15);
				}
				//Check if symbol is predefined:;
				else if(st.containsKey(valueStr)) {
					String binValue = Integer.toBinaryString(Integer.
							parseInt(st.get(valueStr).toString()));
					
					currLine = "0"+ leftZeroFill(binValue, 15);
				}
				
				//Check if symbol is a label:
				else if(lt.containsKey(valueStr)) {
					String binValue = Integer.toBinaryString(Integer.
							parseInt(lt.get(valueStr).toString()));
					
					currLine = "0"+ leftZeroFill(binValue, 15);
				}

				
				//If none of the above, create a new symbol:
				else {
					if(valueStr.matches("-?\\d+(\\.\\d+)?")) {
						
						st.put("R"+valueStr, Integer.parseInt(valueStr));
						//System.out.println(st.get("R"+valueStr));
						valueStr="R"+valueStr;
					}
					else{
						st.addSymbol(valueStr);
					}	
					String binValue = Integer.toBinaryString(Integer.
								parseInt(st.get(valueStr).toString()));
						
					//Increment the address 16+i, when
					//an undefined symbol occurs.
					currLine = "0"+leftZeroFill(binValue,15);
					
				}
			return currLine+"\n";	
			}//End of A-Command Test
			
			
			
			//If C-Command
			String dest="";
			String comp="";
			String jump="";
			if(currLine.contains("=") || currLine.contains(";")) {
				//dest=comp;jump
				if(currLine.contains("=") && currLine.contains(";")) {
					dest=(currLine.substring(0,currLine.indexOf("=")));
					
					comp=(currLine.substring(currLine.
							indexOf("=")+1,currLine.indexOf(";")));
					
					jump=(currLine.substring(currLine.indexOf(";")+1));
					}
				//comp;jump
				else if(currLine.contains(";")) {
					comp=(currLine.substring(0,currLine.indexOf(";")));
					jump=(currLine.substring(currLine.indexOf(";")+1));					
				}
				//dest=comp
				else if(currLine.contains("=")) {
					
					dest=(currLine.substring(0,currLine.indexOf("=")));
					comp=(currLine.substring(currLine.indexOf("=")+1));
				}
				//dest
				else {
					dest=currLine;
				}
				
			currLine = "111"+ci.get(comp)+djt.get(dest)+djt.get(jump);

			//System.out.println(currLine = "111"
			//	+"|"+ci.get(comp)+"|"+djt.get(dest)+"|"+djt.get(jump));
			
			return currLine+"\n";	
			}
			 
		 }
	return "";
	}

}
