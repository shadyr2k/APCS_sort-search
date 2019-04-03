public class LinkedListOfStrings {
	// What is our underlying data structure...not a Node[]!  A linked list!
	// Well, what does a LinkedList need? 
	Node head;
	public int nodeCount;
	// iteratively traverse the Linked List, printing out the String + " --> "
	// unless it's the last node, in which case print out the String + " --> null"
	public String toString() {
		String listString = "Head: ";
		Node runner = head; // initialize temp Node to head
		while(runner != null) {
			listString += runner.name + " --> ";
			runner = runner.next;
		}
		return listString;
	}
	
	// iteratively traverse the Linked List, counting out the number of Nodes
	// whose String contains str
	public int countNodesWithString(String str) {
		int count = 0;
		Node runner = head;
		while(runner != null) {
			if(runner.name.contains(str)) {
				count++;
			}
			runner = runner.next;
		}
		return count;
	}
	
	// recursively traverse the Linked List, counting out the number of Nodes
	// whose String contains str
	public int recursivelyCountNodesWithString(Node head, String str) {
		if(head.next == null) return head.name.contains(str) ? 1 : 0;
		if(head.next.name.contains(str)) return 1 + recursivelyCountNodesWithString(head.next, str);
		return recursivelyCountNodesWithString(head.next, str);
	}
	
	// Prints the nodes in reverse, iteratively
	public void printReversed(Node head) {
		String s = "";
		Node runner = head;
		while(runner != null){
			s = runner.name + " --> " + s;
			runner = runner.next;
		}
		System.out.print(s);
	}
	
	// Prints the nodes in reverse, recursively
	public void recursivelyPrintReversed(Node head) {
		if(head == null) return;
		recursivelyPrintReversed(head.next);
		System.out.print(head.name + " --> ");
	}
	
	
	
	//********* Some of the LinkedList operations specified by Interface List ********
	// (not all of them, that's why we aren't implementing the actual interface)
	
	
	// Appends the specified element to the end of this list.
	// Returns true if this collection changed as a result of the call. 
	// (Returns false if this collection does not permit duplicates and already contains the specified element.) 
	public boolean add(Node n) {
		if(head == null) {
			head = n;
			nodeCount++;
			return true;
		} else {
			Node runner = head;
			while(runner != null) {
				if(runner.name.contains(n.name)) return false;
				if(runner.next == null) break;
				runner = runner.next;
			}
			runner.next = n;
			nodeCount++;
			return true;
		}
	}
	
	// Inserts the specified element at the specified position in this list.
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public void add(int index, Node n) {
		if(index == size()) add(n);
		else if(index < 0 || index > size()) throw new IndexOutOfBoundsException("true statement incoming");
		else {
			if(index == 0) {
				n.next = head;
				head = n;
				nodeCount++;
			} else {
				int count = 0;
				Node runner = head;
				while(count++ < index - 1) {
					runner = runner.next;
				}
				n.next = runner.next;
				runner.next = n;
				nodeCount++;
			}
		} 
	}
	
	// Removes all of the elements from this list.
	public void clear() {
		head = null;
		nodeCount = 0;
	}
	
	// Returns true if this list contains the specified element.
	public boolean contains(Node n) {
		Node runner = head;
		while(runner != null) {
			if(runner.name.contains(n.name)) return true;
			runner = runner.next;
		}
		return false;
	}
	
	// Returns the element at the specified position in this list
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public Node get(int index) {
		if(index < 0 || index >= size()) throw new IndexOutOfBoundsException("pi");
		else if(index == 0) return head;
		else {
			int count = 0;
			Node runner = head;
			while(count++ < index) {
				runner = runner.next;
			}
			return runner;
		}
	}
	
	// Removes the element at the specified position in this list.
	// Returns the element previously at the specified position
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public Node remove(int index) {
		if(index < 0 || index >= size()) throw new IndexOutOfBoundsException("equals");
		else {
			Node returnThis = head;
			if(index == 0) 
				head = head.next;
			else {
				int count = 0;
				Node runner = head;
				while(count++ < index - 1) 
					runner = runner.next;
				returnThis = runner.next;
				runner.next = runner.next.next;
			}
			nodeCount--;
			return returnThis;
		} 
	}
	
	
	// Removes the first occurrence of the specified element from this list, if it is present.
	// Returns true if this list contained the specified element (or equivalently, if this list changed as a result of the call).
	public boolean remove(Node n) {
		Node runner = head;
		while(runner != null) {
			if(runner.next == null) break;
			if(runner.name.equals(n.name)) {
				head = runner.next;
				nodeCount--;
				return true;
			} else if(runner.next.name.equals(n.name)) {
				runner.next = runner.next.next;
				nodeCount--;
				return true;
			}
			runner = runner.next;
		} 
		return false;
	}
	
	// Replaces the element at the specified position in this list with the specified element.
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public Node set(int index, Node n) {
		Node returnThis = head;
		if(index < 0 || index >= size()) throw new IndexOutOfBoundsException("e");
		else if(index == 0) {
			n.next = head.next;
			head = n;
			return returnThis;
		} else {
			int count = 0;
			
			Node runner = head;
			while(count++ < index - 1) {
				runner = runner.next;
			}
			n.next = runner.next.next;
			returnThis = runner.next;
			runner.next = n;
			return returnThis;
		}
	}
	
	// Returns the number of elements in this collection.
	public int size() {
		return nodeCount;
	}
}
