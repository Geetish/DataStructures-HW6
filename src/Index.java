/*****************************************************
 * 
 * 95-772 Data Structures for Application Programmers
 * Homework 6: Building Index using BST
 * 
 * Andrew id: gnayak	
 * Name: Geetish Nayak
 * 
 *****************************************************/
import java.io.*;
import java.util.*;
 
public class Index {
	
	
	/* This is a helper method for building index
	 * @param objBST The BST object
	 * 
	 */
	private void buildIndexHelper(String fileName,BST<Word> objBST,boolean isCaseSensitive){
		Scanner scannerObj=null;
		try{
			scannerObj = new Scanner(new File(fileName));
			int countLines=0;
			// This map is used to store the count of the various letters and 
			// their corresponding frequencies
			Map<String,Integer> countStrings = new HashMap<String,Integer>();
			Map<String,HashSet<Integer>> lines = new HashMap<String,HashSet<Integer>>();
			while(scannerObj.hasNext()){
				String line = scannerObj.nextLine();
				if(line!=null){
					countLines++;
					String[] words = line.split("\\W");
					for(String word: words){
						if(word.matches("[A-Za-z]+")){
							word=word.trim();
							if(isCaseSensitive==false){
								word = word.toLowerCase();
							}
							if(countStrings.containsKey(word)){
								int count = countStrings.get(word);
								count++;
								countStrings.put(word.trim(),count);
								HashSet<Integer> ls = lines.get(word);
								ls.add(countLines);
								lines.put(word,ls);
							}
							else{
								countStrings.put(word.trim(),1);
								HashSet<Integer> ls = new HashSet<Integer>();
								ls.add(countLines);
								lines.put(word,ls);
							}
						}
					}
				
				}
			}
			for(String str : countStrings.keySet()){
				Word wordObj= new Word(str,lines.get(str),countStrings.get(str));
				objBST.insert(wordObj);
			}
			
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
			System.exit(0);
		}
		finally{
			scannerObj.close();
		}
	}

	/**
	 * Build a tree giving a file name
	 * 
	 * @param fileName
	 *            - input file name
	 * @return BST object
	 */
	public BST<Word> buildIndex(String fileName) {
		BST<Word> objBST = new BST<Word>();
		buildIndexHelper(fileName,objBST,true);
		return objBST;
		
	}

	/**
	 * Build a tree with a file name and comparator object
	 * 
	 * @param fileName
	 *            - input file name
	 * @param comparator
	 *            - comparator to be used
	 * @return BST object
	 */
	public BST<Word> buildIndex(String fileName, Comparator<Word> comparator) {
		BST<Word> objBST=new BST<Word>(comparator);
		if(comparator!=null && comparator instanceof IgnoreCase){
			// we have to ignore the case of the words in this case
			buildIndexHelper(fileName,objBST,false);
		}
		else{
			buildIndexHelper(fileName,objBST,true);
		}
		return objBST;
	}

	
	
	/**
	 * Build a tree with a given list and comparator
	 * 
	 * @param list
	 *            - ArrayList of words
	 * @param comparator
	 *            - comparator to be used
	 * @return BST object
	 */
	public BST<Word> buildIndex(ArrayList<Word> list,
			Comparator<Word> comparator) {
		BST<Word> objBST=new BST<Word>(comparator);
		for(Word wordObj:list){
			objBST.insert(wordObj);
		}
		return objBST;
	}

	/**
	 * Sort words alphabetically Should keep the state of the tree
	 * 
	 * @param tree
	 *            - BST tree
	 * @return ArrayList of words sorted alphabetically
	 */
	public ArrayList<Word> sortByAlpha(BST<Word> tree) {
		ArrayList<Word> arrayList = new ArrayList<Word>();
		Iterator<Word> iterator = tree.iterator();
		while(iterator.hasNext()){
			arrayList.add(iterator.next());
		}
		// Sort on the basis of alphabets
		Collections.sort(arrayList);
		return arrayList;
	}

	/**
	 * Sort words by frequency Should keep the state of the tree
	 * 
	 * @param tree
	 *            - BST tree
	 * @return ArrayList of words sorted by frequency
	 */
	public ArrayList<Word> sortByFrequency(BST<Word> tree) {
		ArrayList<Word> arrayList = new ArrayList<Word>();
		Iterator<Word> iterator = tree.iterator();
		while(iterator.hasNext()){
			arrayList.add(iterator.next());
		}
		// Sort on the basis of frequency
		Collections.sort(arrayList,new Frequency());
		return arrayList;
	}

	/**
	 * Find all words of the highest frequency
	 * 
	 * @param tree
	 * @return ArrayList of words that have highest frequency
	 */
	public ArrayList<Word> getHighestFrequency(BST<Word> tree) {
		int maxFreq = 0;
		ArrayList<Word> arrayList = new ArrayList<Word>();
		// Get the iterator
		Iterator<Word> iterator = tree.iterator();
		// Find the max frequency of a word
		while(iterator.hasNext()){
			Word tempWord = iterator.next();
			if(maxFreq < tempWord.getFrequency()){
				maxFreq = tempWord.getFrequency();
			}
		}
		// Now at this point we have the max frequency count
		iterator = tree.iterator();
		while(iterator.hasNext()){
			Word tempWord = iterator.next();
			if(tempWord.getFrequency()==maxFreq){
				arrayList.add(tempWord);
			}
		}
		return arrayList;
		
	}

}