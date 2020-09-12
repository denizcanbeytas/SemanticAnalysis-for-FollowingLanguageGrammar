package sample;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Pattern;





public class Main {
   

   public static void main(String[] args) throws IOException {
       
	   String[] string;
		BufferedReader bufReader;
        String Input = args[0];
        
       
	   try {
            Path path = Paths.get(Input);
            
			int lineCount = (int) Files.lines(path).count();
            string = new String;
			String[lineCount];
            
			bufreader = new BufferedReader;
			BufferedReader(new FileReader(Input));
            
			for (int i = 0; i < lineCount; i++){
                string[i] = bufReadereader.readLine();
            }
            bufReader.close();
            
			for (int i = 0; i < string.length; i++){
                parsing(string[i], i+1);
            }
            System.out.println("\n\n" + (lineCount+1) + ": EOS");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static final Pattern numberCheck ;
	Pattern numberCheck = Pattern.compile("-?\\d+(\\.\\d+)?");
    
	private static final Pattern letterCheck ;
	Pattern letterCheck = Pattern.compile("[a-zA-Z]");
    
	private static final Pattern multip ;
	Pattern multip = Pattern.compile("[\\-*/]");
    
	private static final Pattern add ;
	Pattern add = Pattern.compile("[+-]+");

    private static void parsing(String string, int lineCount){
        String[] parsing = string.split(" ");
       
	   if(!(Objects.equals(Character.toString(parsing[parsing.length-1].charAt((parsing[parsing.length-1].length())-1)), ";"))){
            System.out.println("line isn't end with semicolon");
            return;
        }
        
		for(int i = 0; i < parsing.length; i++){
            
            if(i == parsing.length-1){
                if(parsing[i].length() <= 1){
                    System.out.println("false language");
                    return;
                }
                String[] x = parsing[i].split(";");
                parsing[i] = declare(x[0]);
            }
            else
                parsing[i] = declare(parsing[i]);
            if(Objects.equals(parsing[i], "null")){
                System.out.println("Empty or false language");
                return;
            }
        }
        if (checkLang(parsing)) {
            printSourceCode(string,parsing,lineCount);
            printSyntaxTree(string,parsing);
        }else {
            System.out.println("Wrong Grammar!!");
            return;
        }
    }

    private static String declare(String string){
        
		if(Objects.equals(string, "read") || Objects.equals(string, "write")){
            return string;
        }
        if(letCheck.matcher(String.valueOf(string.charAt(0))).matches()){
           for (int i = 0; i < string.length(); i++){
           if(!letCheck.matcher(String.valueOf(string.charAt(i))).matches()){
               return "null";
                }
            }
            return "id";
        }
        if(numCheck.matcher(string).matches()){
            return "number";
        }if(Objects.equals(string, ":=")){
            return "asgn";
        }if(multi.matcher(string).matches()){
            return "multip";
        }if(add.matcher(string).matches()){
            return "add";
        }else if(Objects.equals(string, ";")){
            return "eos";
        }
        return "null";
    }

    private static boolean checkLang(String[] lang){
       
	   if(Objects.equals(lang[0], "write") || Objects.equals(lang[0], "read")){
           
		   if(lang.length != 2){
                return false;
            } if (Objects.equals(lang[1], "id")){
                return true;
            }
        }
        if(lang.length > 2 && Objects.equals(lang[0], "id") && Objects.equals(lang[1], "asgn") && (Objects.equals(lang[2], "id") || Objects.equals(lang[2], "num")))
		{
           
		   if(lang.length == 3) 
				return true;
           
		   if(lang.length >= 5){
             if(Objects.equals(lang[3], "multi") || Objects.equals(lang[3], "add")){
               if (Objects.equals(lang[4], "id") || Objects.equals(lang[4], "num")){
                 if(lang.length == 5) 
					 return true;
                    if(lang.length == 7){
                       if(Objects.equals(lang[5], "multi") && Objects.equals(lang[3], "add")){
                          return Objects.equals(lang[6], "id") || Objects.equals(lang[6], "num");
                            }  }
                    }
                }
            }
        }
        return false;
    }

    private static void print(String string, String[] parsing, int lineCount){
        String[] stringNew = string.split(" ");
        System.out.println("\n" + lineCount + ": " + string);
        for (int i = 0; i < stringNew.length; i++) {
            if (stringNew.length - 1 == i) {
                String[] semicolon = stringNew[i].split(";");
                
				if(Objects.equals(parsing[i], "id"))  {
				System.out.println("\t" + lineCount + ": ID, name= " + semicolon[0]);}
                
				else if(Objects.equals(parsing[i], "num"))   
					System.out.println("\t" + lineCount + ": NUM, val= " + semicolon[0]);
                System.out.println("\t" + lineCount +": ;");
            }else {
                if(Objects.equals(parsing[i], "id"))  
					System.out.println("\t" + lineCount + ": ID, name= " + stringNew[i]);
                
				else if(Objects.equals(parsing[i], "num"))    
					System.out.println("\t" + lineCount +": NUM, val= " + stringNew[i]);
                
				else if(Objects.equals(parsing[i], "write") || Objects.equals(parsing[i], "read"))   
					System.out.println("\t" + lineCount + ": reserved word: " + stringNew[i]);
               
			   else System.out.println("\t" + lineCount +": " + stringNew[i]);
            }
        }
    }

    private static void syntaxTree(String string, String[] parsing){
       
	   String[] stringNew ;
	    stringNew = string.split(" ");
       
	   String[] semicolon ;
		semicolon = stringNew[stringNew.length-1].split(";");
       
	   int x = 1;
        System.out.println("\nSyntax Tree:");

        if(Objects.equals(parsing[0], "read")){
            System.out.println("Read: " + semicolon[0]);
            return;
        }

        if(Objects.equals(parsing[0], "write")){
            System.out.println("Write");
            System.out.println("\tId: " + semicolon[0]);
            return;
        }

        if(Objects.equals(parsing[1], "asgn")){
            System.out.println("Assign to: " + stringNew[0]);
           
            
           
        }
    }
}
