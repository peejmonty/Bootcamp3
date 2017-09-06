package viensp_p3;

/**
 * Word counter class that increments words as they are added to the Hash Table
 * and decrements words as they are being removed.
 * 
 * @author Phillip J Viens
 *
 */
public class WordCounter {
	private int capacity;
	private int uniqueWordCount = 0;
	private int totalWordCount = 0;
	private Bucket[] hash;

	/**
	 * HashBucket class. Class that constructs 
	 * a Node in the Hash Table
	 * @author Phillip J Viens
	 */
	private static class Bucket {
		private String word;
		private int count;
		private Bucket next;

		/**
		 * Bucket constructor
		 * @param word word that is being added to the bucket
		 */
		public Bucket(String word) {
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

		// If the bucket at the given index is null
		if (hash[index] == null) {

			// create a new bucket and increment the counts
			uniqueWordCount++;
			totalWordCount++;
			hash[index] = new Bucket(word);
			return hash[index].count;
		}
		
		// if the bucket at given index is not empty
		else {

			// if the word in the first node is the same as
			// the given word
			if (hash[index].word.toLowerCase().hashCode()
					== word.toLowerCase().hashCode()) {
				
				totalWordCount++;
				hash[index].count++;
				return hash[index].count;
			}

			// if the word in the first node is not the same
			// traverse the list until you've reached the end or until
			// you've found the same word.
			else {
				Bucket head = hash[index];
				while (head.next != null 
						&& head.next.word.toLowerCase().hashCode() 
						!= theWord.hashCode()) {
					head = head.next;
				}

				// The given word has been found
				// Just increment the lists count and totalWordCount.
				if (head.next != null 
						&& head.next.word.toLowerCase().hashCode() 
						== theWord.hashCode()) {
					totalWordCount++;
					head.next.count++;
					return head.next.count;
				}

				// If the end of the list has been reached
				// create new bucket and increment unique and
				// total word counts.
				else {
					head.next = new Bucket(word);
					uniqueWordCount++;
					totalWordCount++;
					return head.count;
				}
			}
		}
	}

	/**
	 * hash equation for the index of words.
	 * @param word that is being added to the hash table.
	 * @return returns the index for the word to be placed.
	 */
	private int hashEquation(String word) {
		int hashCode = (word.hashCode() 
				% hash.length + hash.length) % hash.length;
		return hashCode;
	}

	/**
	 * returns the count for word in given text
	 * @param word specified word to be found and count
	 * @return number of times word is found 0 if word doesn't exist.
	 */
	public int getWordCount(String word) {
		int index = hashEquation(word.toLowerCase());

		// if the bucket is empty return 0
		if (hash[index] == null) {
			return 0;
		}

		// if it's not empty
		else {
			Bucket head = hash[index];

			// if the first word in the list is equal to the given word
			// return count for the word.
			if (hash[index].word.toLowerCase().hashCode() 
					== word.toLowerCase().hashCode()) {
				return hash[index].count;
			}

			// if the first word is not equal to the given word
			// traverse the list while the hash[index].next is not null
			// and the word is not found
			else {
				while (head.next != null 
						&& head.next.word.toLowerCase().hashCode() 
						!= word.toLowerCase().hashCode()) {
					head = head.next;
				}

				// if the word is found return the words count
				if (head.next != null 
						&& head.next.word.toLowerCase().hashCode() 
						== word.toLowerCase().hashCode()) {
					return head.next.count;
				}

				// else return 0.
				else {
					return 0;
				}
			}
		}
	}

	/**
	 * removes common words specified by the common word list. Decrements both
	 * Unique word and total word count as words are removed.
	 * @param word that is being removed
	 */
	public void removeWord(String word) {
		int index = hashEquation(word.toLowerCase());
		Bucket head = hash[index];
		
		// if the hashTable at the index is not null
		if (hash[index] != null) {

			//checks to see if the first word in the list is 
			//equal to the given word.
			//if so decrement the totalWordCount 
			//and the unique word count and delete the node 
			//from the list
			if (hash[index].word.toLowerCase().hashCode() 
					== word.toLowerCase().hashCode()) {
				totalWordCount -= hash[index].count;
				uniqueWordCount--;
				hash[index] = hash[index].next;
			}

			// While the list is not empty and the word has not been found
			// traverse the list
			else {
				while (head.next != null 
						&& head.next.word.toLowerCase().hashCode() 
						!= word.toLowerCase().hashCode()) {
					head = head.next;
				}

				// if the word has been found decrement the counts and delete
				// the node.
				if (head.next != null 
						&& head.next.word.toLowerCase().hashCode() 
						== word.toLowerCase().hashCode()) {
					totalWordCount -= head.next.count;
					uniqueWordCount--;
					head.next = head.next.next;
				}
			}
		}
	}
}
