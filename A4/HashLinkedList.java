package hashMap;


public class HashLinkedList<K,V>{
	/*
	 * Fields
	 */
	private HashNode<K,V> head;

	private Integer size;

	/*
	 * Constructor
	 */

	HashLinkedList(){
		head = null;
		size = 0;
	}


	/*
	 *Add (Hash)node at the front of the linked list
	 */

	public void add(K key, V value){
		
		// ADD CODE BELOW HERE
		HashNode<K, V> temp = new HashNode<K,V>(key, value);

		if(this.isEmpty()) {
			head = temp;
			temp.next = null;
			size++;
		} else {
			temp.next = this.head;
			head = temp;
			size++;
		}
	

		// ADD CODE ABOVE HERE
	}

	/*
	 * Get Hash(node) by key
	 * returns the node with key
	 */

	public HashNode<K,V> getListNode(K key){
		// ADD CODE BELOW HERE
		
		HashNode<K,V> token = this.getHead();
		if(this.isEmpty()) {
			return null;
		}
		while(!token.getKey().equals(key) && token.getNext() != null) {
			token = token.getNext();
		}
		if(token.getKey().equals(key)) {
			return token;
		} else {
			return null;
		}

		// ADD CODE ABOVE HERE
	}


	/*
	 * Remove the head node of the list
	 * Note: Used by remove method and next method of hash table Iterator
	 */

	public HashNode<K,V> removeFirst(){
		// ADD CODE BELOW HERE

		if(this.isEmpty()) {
			return null;		
		} else {
			HashNode<K, V> token = this.getHead();

			token = this.getHead();

			this.head = head.getNext();
			
			token.next = null;
			this.size--;
			
			return token;
		}

		// ADD CODE ABOVE HERE
	}

	/*
	 * Remove Node by key from linked list 
	 */

	public HashNode<K,V> remove(K key){
		// ADD CODE BELOW HERE
		if(this.isEmpty()) {
			return null;
		}

		HashNode<K, V> token = this.getHead();
		HashNode<K, V> rmvNode;
		
		if(token.getKey().equals(key)) {

			return removeFirst();
			
		} else {
			while(!token.getNext().getKey().equals(key) && token.getNext() != null) {
				token = token.getNext();
			}
			if(token.getNext().getKey().equals(key)) {
				rmvNode = token.getNext();
				token.next = rmvNode.getNext();
				rmvNode.next = null;
				this.size--;


				return rmvNode;
			} else {
				return null;
			}
		}

		// ADD CODE ABOVE HERE

		
	}

	/*
	 * Delete the whole linked list
	 */
	public void clear(){
		head = null;
		size = 0;
	}
	/*
	 * Check if the list is empty
	 */

	boolean isEmpty(){
		return size == 0? true:false;
	}

	int size(){
		return this.size;
	}

	//ADD YOUR HELPER  METHODS BELOW THIS

	public HashNode<K, V> getHead() {
		return this.head;
	}

	public HashLinkedList<K,V> replicateList (HashLinkedList<K,V> list){
		
		HashLinkedList<K,V>  tempList= new HashLinkedList<K,V>();
		HashNode<K,V> copyNode = list.getHead();
		HashNode <K,V> node;
		
		while(copyNode != null){
			
	        node = copyNode.clone(copyNode);  
	        tempList.add(node.getKey(), node.getValue());
			copyNode= copyNode.getNext();
		}
		
		return tempList;
	
	}





	//ADD YOUR HELPER METHODS ABOVE THIS


}
