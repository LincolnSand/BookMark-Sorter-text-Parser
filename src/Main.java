import java.io.Writer;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

public class Main {

    private final String[] keywords = {"haskell", "machine learning", "bios", "operating system", "linux"};
    private String[] keywordFileNames = new String[keywords.length];
    private File[] keywordFiles = new File[keywords.length];
    private File elseFile;
    private final String elseFileName = "src/else.html";
    private final String inputFileName = "src/input.html";

    private Scanner questionScanner;
    {
        try {
            questionScanner = new Scanner(new File(inputFileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main();
    }

    private Main(){

        init();

        try {
            fileCreator();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println("keyline , " + "line , " + stringChecker("keyline", "line"));

        System.out.println();

        readerAndWriter();

        endOfFileAdder();
    }

    private void readerAndWriter(){
        String line = "";
        int lineNumber = 1;
        boolean fitsKeyword = false;
        do{
            fitsKeyword = false;
            line = questionScanner.nextLine();
            //System.out.println(line + " at " + lineNumber);
            ++lineNumber;
            for(int x1 = 0; x1 < keywords.length; ++x1){
                //System.out.println(line +" : " + keywords[x1] + " : " + stringChecker(line, keywords[x1]));
                if(stringChecker(line, keywords[x1])){
                    Writer out;
                    try {
                        out = new FileWriter(keywordFileNames[x1], true);
                        out.write(line + '\n'); //Write the string to the file
                        out.flush();
                        out.close(); //Close the file
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    fitsKeyword = true;
                    break;
                }
            }
            if(!fitsKeyword){
                Writer out;
                try {
                    out = new FileWriter(elseFile, true);
                    out.write(line + '\n'); //Write the string to the file
                    out.flush();
                    out.close(); //Close the file
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }while(!line.equals("End Of File")); 
    }

    private boolean stringChecker(String line, String keyword){
        return (line.toUpperCase()).contains(keyword.toUpperCase()); //keyword in line;
    }

    private void fileCreator() throws IOException {
        String keywordFileName;
        for(int x1 = 0; x1 < keywords.length; ++x1){
            keywordFileName = "src/" + keywords[x1] + ".html";
            File file = new File(keywordFileName);
            if(file.createNewFile()){
                System.out.println(keywordFileName + " File Created");
            }
            else{
                System.out.println("File " + keywordFileName + " already exists");
            }
            keywordFiles[x1] = file;
            keywordFileNames[x1] = keywordFileName;
            //System.out.println("File: " + keywordFileName);
        }

        elseFile = new File(elseFileName);
        if(elseFile.createNewFile()){
            System.out.println(elseFileName + " File Created");
        }
        else{
            System.out.println("File " + elseFileName + " already exists");
        }
    }

    private void init(){
		Writer out;
		try {
			out = new FileWriter(inputFileName, true);
			out.write('\n' + "End Of File" + '\n'); //Write the string to the file
			out.flush();
			out.close(); //Close the file
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void endOfFileAdder(){
		for(String fileName : keywordFileNames){
			Writer out;
			try {
				out = new FileWriter(fileName, true);
				out.write('\n' + "End Of File" + '\n'); //Write the string to the file
				out.flush();
				out.close(); //Close the file
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
