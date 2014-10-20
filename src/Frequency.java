/*****************************************************
 * 
 * 95-772 Data Structures for Application Programmers
 * Homework 6: Building Index using BST
 * 
 * Andrew id: gnayak	
 * Name: Geetish Nayak
 * 
 *****************************************************/
import java.util.*;
 
/*
 * This Comparator is used to compare on the basis of the 
 * frequency of the word in the document
 * 
 */
public class Frequency implements Comparator<Word> {

	@Override
	public int compare(Word o1, Word o2) {
		if(o1.getFrequency() < o2.getFrequency()){
			return 1;
		}
		else if(o1.getFrequency() > o2.getFrequency()){
			return -1;
		}
		else{
			return 0;
		}
	}

}