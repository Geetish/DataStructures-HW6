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
 * This Comparator is basically used to compare
 * first on the basis of alphabets and then
 * on the basis of frequency 
 * 
 */
public class AlphaFreq implements Comparator<Word> {

	@Override
	public int compare(Word arg0, Word arg1) {
		
		int alphabetResult = arg0.getWord().compareTo(arg1.getWord());
		if(alphabetResult!=0){
			return alphabetResult;
		}
		else{
			if(arg0.getFrequency() < arg1.getFrequency())
				return 1;
			if(arg0.getFrequency() > arg1.getFrequency())
				return -1;
			else return 0;
		}
	}

}