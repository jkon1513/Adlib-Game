package Madlibs.Game;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.List;

public class Adlib_Template{

	private String title;
	private String author;
	private LinkedList<String> wordTypes;
	private String template; // template to be filled by players
		
    public Adlib_Template(String story)throws FileNotFoundException {
    	this.title = story;
		this.wordTypes = new LinkedList<>();
		this.template = createTemplate(title);
		//add logic for author
	}
    
	public String getTitle(){
		return title;
	}

	public LinkedList<String> getWordTypes(){
		return wordTypes;
	}

	public int getEntryCount(){
		return wordTypes.size();
	}

    public String toString(){
        return template;
    }

    public String getAuthor() {
        return author;
    }

    public void populate (List <String> list){
        String wordTag = "<\\w+>";
        for(int i = 0; i < list.size(); i++) {
            template = template.replaceFirst(wordTag, list.get(i));
        }

    }
    /**
	 * this method takes in a string filename and finds the correct story
	 * it then converts the contents into a string
	 *
	 * @author Jkon1513
	 * @param filename = name of file minus the extension
	 * @return string containing story template
	  */
	//change file path for prod version
	private String createTemplate(String filename) throws FileNotFoundException {
	    File textFile = new File("./src/Madlibs/Testing/"+ filename + ".txt");
        Scanner in = new Scanner(textFile);
        StringBuilder prototype = new StringBuilder();

        while(in.hasNextLine()){
            String line = in.nextLine();
            handleEntryTags(line);
            prototype.append(line).append("\n");
        }
        in.close();

        return prototype.toString().trim();
    }
   
    /**
     * this method scans a given line and finds entry tags. it adds the wordtype of the entry tag
     * to the wordtypes list.
     *
     * @param line: a single line of text from the story being made into the template
     */
    private void handleEntryTags(String line){
        Scanner lineScan = new Scanner(line);

        while(lineScan.hasNext()){
            String word = lineScan.next();

            if(isEntryTag(word)){
                extractWordType(word);
           }
        }
    }

    private boolean isEntryTag(String word){
        return word.matches("<[A-Za-z]+>(ed|ing|'s|s)?[.,!?';]?");

    }

    private void extractWordType(String entryTag) {

        boolean hasSuffix = entryTag.lastIndexOf('>') < entryTag.length() - 1;

        if(!hasSuffix) {
            String wordType = entryTag.substring(1, entryTag.length()-1); // removes tag brackets
            wordTypes.add(wordType);
        }
        else {
            String wordType = entryTag.substring(1,entryTag.lastIndexOf('>'));
            wordTypes.add(wordType);
        }
    }
}
