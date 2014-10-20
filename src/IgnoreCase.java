/*****************************************************
 * 
 * 95-772 Data Structures for Application Programmers
 * Homework 6: Building Index using BST
 * 
 * Andrew id:
 * Name:
 * 
 *****************************************************/
import java.util.*;
 

/*
 * This Comparator is used to compare on the basis of the 
 * alphabets in the word without considering the case of the 
 * words
 * 
 */
public class IgnoreCase implements Comparator<Word> {

	@Override
	public int compare(Word o1, Word o2) {
		// Convert the words into lower case
		String word1 = o1.getWord().toLowerCase();
		String word2 = o2.getWord().toLowerCase();
		return word1.compareTo(word2);
	}

}