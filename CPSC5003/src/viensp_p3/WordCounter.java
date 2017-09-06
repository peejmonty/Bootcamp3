package viensp_p3;

/**
 * Word counter class that increments words as they are 
 * added to the Hash Table and decrements words as 
 * they are being removed.
 * @author Phillip J Viens
 *
 */
public class WordCounter {
	private int capacity;
	private int uniqueWordCount = 0;
	private int totalWordCount = 0;
	private Bucket[] hash;
	
	/**
	 * HashBucket class.
	 * @author Phillip J Viens
	 *
	 */
	private static class Bucket {
		private String word;
		private int count;
		private Bucket next;
		
		/**
		 * Bucket constructor
		 * @param word TODO
		 * @param count
		 * @param next
		 */
		public Bucket (String word) {
			this.word = word;
			this.count = 1;
			next = null;
		}
	}
	
	/**
	 * WordCount constructor
	 * @param capacity capacity of the array.
	 */
	public WordCounter(int capacity) {
		this.capacity = capacity;
		hash = new Bucket[capacity];
	}
	
	/**
	 * returns capacity of of the HashTalbe
	 * @return capacity specified by user.
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * gets the unique word count for the HashTable
	 * @return unique word count.
	 */
	public int getUniqueWordCount() {
		return uniqueWordCount;
		
	}
	
	/**
	 * returns the total number of words in the Hash Table
	 * @return totalWordCount
	 */
	public int getTotalWordCount() {
		return totalWordCount;
	}
	
	/**
	 * returns true if Empty
	 * @return true if empty false otherwise.
	 */
	public boolean isEmpty() {
		return totalWordCount == 0;
	}
	
	/**
	 * increments the word counts as both unique and duplicate words are added.
	 * @param word word that is added to the Hash Table.
	 * @return count per hash index.
	 */
	public int incrementWordCount(String word) {
		String theWord = word.toLowerCase();
		int index = hashEquation(theWord);
		
		if (hash[index] == null) {
			hash[index] = new Bucket(word);
			uniqueWordCount++;
			totalWordCount++;
			return hash[index].count;
			}	
		else{
			if (hash[index].word.toLowerCase().hashCode() 
					== word.toLowerCase().hashCode()) {
				hash[index].count++;
				totalWordCount++;
				return hash[index].count;
			}
					
			else {
				Bucket head = hash[index];
					while(head.next != null 
							&& head.next.word.toLowerCase().hashCode() 
							!= theWord.hashCode()) {
						head = head.next;
					}
					if (head.next != null 
							&& head.next.word.toLowerCase().hashCode() 
							== theWord.hashCode()) {
						totalWordCount++;
						head.next.count++;
						return head.next.count;
					}
					else{
						//head.count++;
						head.next = new Bucket(word);
						uniqueWordCount++;
						totalWordCount++;
						return 1;
					}
				
					//totalWordCount++;
				}
			}
	}
	
	/**
	 * hash equation for the index of words.
	 * @param word that is being added to the hash table.
	 * @return returns the index for the word to be placed.
	 */
	private int hashEquation(String word) {
		int hashCode = (word.hashCode() % hash.length + hash.length) % hash.length;
		return hashCode;
	}
	
	/**
	 * returns the count for word in given text
	 * @param word specified word to be found and count
	 * @return number of times word is found 0 if word doesn't exist.
	 */
	public int getWordCount(String word) {
		int index = hashEquation(word.toLowerCase());		
		if(hash[index] == null) {
			return 0;
		}
		else {
			Bucket head = hash[index];
			if(hash[index].word.toLowerCase().hashCode() == word.toLowerCase().hashCode()){
				return hash[index].count;
			}
			else {
				while(head.next != null 
						&& head.next.word.toLowerCase().hashCode() 
						!= word.toLowerCase().hashCode()) {
					head = head.next;
				}
				if(head.next != null 
						&& head.next.word.toLowerCase().hashCode() 
						== word.toLowerCase().hashCode()){
					return head.next.count;
				}
				else { 
					return 0;
				}
			}
		}
	}
	
	/**
	 * removes common words specified by the common word list. Decrements both Unique word
	 * and total word count as words are removed.
	 * @param word that is being removed
	 */
	public void removeWord(String word) {
		
		int index = hashEquation(word.toLowerCase());
		Bucket head = hash[index];
		if(hash[index] != null) {
			if(hash[index].word.toLowerCase().hashCode() == word.toLowerCase().hashCode()){
				totalWordCount -= hash[index].count;
				uniqueWordCount--;
				
				hash[index] = hash[index].next;
			}
			else {
				while(head.next != null && head.next.word.toLowerCase().hashCode() != word.toLowerCase().hashCode()){
					head = head.next;				
				}
			
			// Either we have found the word or not
			
				if(head.next != null
						&& head.next.word.toLowerCase().hashCode() 
						== word.toLowerCase().hashCode()) {
					// Found the right word
					//totalWordCount -=count;
			
					totalWordCount -= head.next.count;
					uniqueWordCount--;
					head.next = head.next.next;
				}
			}
		}
	}
}		

