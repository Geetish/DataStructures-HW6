/*****************************************************
 *  
 * 95-772 Data Structures for Application Programmers
 * Homework 6: Building Index using BST
 * 
 * Andrew id:gnayak	
 * Name: Geetish Nayak
 * 
 *****************************************************/
import java.util.*;

public class Word implements Comparable<Word> {
	private String word;
	private Set<Integer> index; // word's line number in the source text
	private int frequency; // counts occurrences of the word
	
	/*
	 * Constructor
	 * @param word The word 
	 * @param index The set of lines where this word is present
	 * @param frequecy The frequency of the word in the document
	 */
	public Word(String word, Set<Integer> index,int frequecy){
		this.word = word;
		this.index = index;
		this.frequency = frequecy;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Word word) {
		return getWord().compareTo(word.getWord());
	}
	
	/*
	 * 
	 *  Overriding equals
	 */
	@Override
	public boolean equals(Object word){
		if(word==null){
			return false;
		}
		if(word==this){
			return true;
		}
		if(!(word instanceof Word)){
			return false;
		}
		Word tempWord = (Word) word;
		if(this.word.equals(tempWord.word)){
			return true;
		}
		else{
			return false;
		}
	}
	
	/*
	 * Override Hashcode
	 * 
	 * 
	 */
	@Override
	public int hashCode(){
		return this.word.hashCode();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.word + "  " + this.frequency + "  "+ index.toString(); 
	}
	
	
	/*
	 * Getter and Setters for the variables
	 */
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public Set<Integer> getIndex() {
		return index;
	}
	public void setIndex(Set<Integer> index) {
		this.index = index;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	
}