import java.util.io.*;
import java.util.LinkedList;


public class Adlib_Template{

	private String title; // title of story 
	private LinkedList<String> wordTypes; // e.g noun, verb, name, etc... with order preserved
	private File template;
		
    public Adlib_Template(String story){
    	this.title = story;
		this.wordTypes = new LinkedList<>();
		this.template = createTemplate(title);
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

    public File getTemplate(){
        return template;
    }

	/**
	 * this method takes in a string filename and finds the correct story
	 * it then copies the contents into a template file and converts the user entry fields
	 * to %s for use in game, and populates word types map
	 *
	 * @author Jkon1513
	 * @param filename = name of file minus the extension
	 * @return converted template file
	  */
	
	public File createTemplate(String filename) throws FileNotFoundException {
	    File textFile = new File(filename+".txt");
        Scanner in = new Scanner(textFile);
	    PrintWriter out = new PrintWriter(filename+".tmpl");
	    String regex = "<[a-z]+>"; //macthes word entry tags
        
        
        //scans file line by line, then searches line for entry tags
        while(in.hasNextLine()){
            String line = in.nextLine();
            entryScan(line);
            line = line.replaceAll(regex,"%s");
            out.printf("%s\n",line);
        }
        
        in.close();
        out.close();
        
        return new File(filename+".tmpl");        
    }
   
    /**
     * @param line: a line scanned from the story file being used to create this template
     *
     * this method scans the line for entry tags. id adds the entry type to the linked list
     * duplicates are allowed to perserve order for when prompting user. brackets are 
     * removed before adding to the list
     *
     */
    private void entryScsn(String line){

        Scanner lineScan = new Scanner(line);
        while(lineScan.hasNext()){
            String word = lineScan.next();
            if(word.matches(regex)){
                String type = word.substring(1,word.length()-1);// removes tag brackets
                wordTypes.add(type);
            }
        }
    }




}
