
public class BinarySearchTree {

	private Node root;
	private int size; // Size of tree would be the # of unique words 
	private int totalWordCount; // total words in file 
	
	/**
	 * If a WordFrequency object already exists
	 * in the tree for the parameter word,
	 * then increase the count of that WordFrequency
	 * object. Otherwise, add a WordFrequency
	 * object for the word to the binary search tree.
	 * @param word
	 */
	public void put(String word) {
		if(root == null) {
			size++;
			root = new Node(new WordFrequency(word));
		}
		Node runner = root;
		while(true){
			if(runner.item.getWord().compareTo(word) == 0) {
				runner.item.increaseCount(); totalWordCount++;
				return;
			} else if(runner.item.getWord().compareTo(word) > 0){
				if(runner.right == null) {
					runner.right = new Node(new WordFrequency(word));
					size++; totalWordCount++;
					return;
				}
				else runner = runner.right;
			} else {
				if(runner.left == null) {
					runner.left = new Node(new WordFrequency(word));
					size++; totalWordCount++;
					return;
				}
				else runner = runner.left;
			}
		}
	}

	/**
	 * If a WordFrequency object with the
	 * given word exists in the tree, return
	 * the frequency of the word.
	 * Otherwise, 0 is returned.
	 * @param word
	 * @return the frequency of the word
	 */
	public int getFrequency(String word) {
		Node runner = root;
		while(runner != null){
			if(runner.item.getWord().compareTo(word) > 0)
				runner = runner.right;
			else if(runner.item.getWord().compareTo(word) < 0)
				runner = runner.left;
			else if(runner.item.getWord().compareTo(word) == 0)
				return runner.item.getCount();
		}
		return 0;
	}

	
	/**
	 * Prints all of the WordFrequency objects
	 * in the tree that fall between
	 * startWord (inclusive) and endWord
	 * (not including endWord).
	 * Prints one per line.
	 * @param startWord the first word in the range
	 * @param endWord the last word in the range
	 */
	public void printRange(String startWord, String endWord) {
		printRange(root, startWord, endWord); //*** Recursive call.  this is helper
	}
	
	// Recursive method to print range.
	public void printRange(Node n, String startWord, String endWord) {
		if (n == null)
			return;
		if (n.item.getWord().compareTo(startWord) >= 0 && n.item.getWord().compareTo(endWord) < 0) {
			System.out.print(n.item.getWord() + " ");
			printRange(n.left, startWord, endWord);
			printRange(n.right, startWord, endWord);
		}
		if(n.item.getWord().compareTo(startWord) < 0)
			printRange(n.left, startWord, endWord);
		if(n.item.getWord().compareTo(endWord) > 0)
			printRange(n.right, startWord, endWord);
	}
	
	/**
	 * Returns the number of WordFrequency objects in the tree.
	 * @return the number of WordFrequency objects in the tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Returns the sum of all the word frequencies
	 * @return the sum of all the word frequencies
	 */
	public int getTotalWordCount() {
		return totalWordCount;
	}
	
	private static class Node {
		public WordFrequency item;
		public Node left;
		public Node right;
		
		public Node(WordFrequency item) {
			this.item = item;
		}
		
	}
}
