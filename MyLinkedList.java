
/**
 * LinkedList class implements a doubly-linked list.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType>
{
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList( )
    {
        clear( );
    }
    
    /**
     * Change the size of this collection to zero.
     */
    public void clear( )
    {
        beginMarker = new Node<AnyType>( null, null, null );
        endMarker = new Node<AnyType>( null, beginMarker, null );
        beginMarker.next = endMarker;
        
        theSize = 0;
    }
    
    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }
    
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }
    
    /**
     * Adds an item to this collection, at the end.
     * @param x any object.
     * @return true.
     */
    public boolean add( AnyType x )
    {
        add( size( ), x );   
        return true;         
    }
    
    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     * @param x any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );
    }
    
    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */    
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<AnyType>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;         
        theSize++;
    }   
    
    
    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }
        
    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;
        
        p.data = newVal;   
        return oldVal;
    }
    
    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     * @param idx index to search at.
     * @return internal node corrsponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corrsponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */    
    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;
        
        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );
            
        if( idx < size( ) / 2 )
        {
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;            
        }
        else
        {
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        } 
        
        return p;
    }
    
    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }
    
    /**
     * Removes the object contained in Node p.
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        
        return p.data;
    }
    
    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );
        
        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );
    
        return new String( sb );
    }

    public void swapNodes(int idx1, int idx2){
        
        if (idx1 < 0 || idx1 >= size() || idx2 < 0 || idx2 >=size())
            throw new IndexOutOfBoundsException("Index 1: " + idx1 + " Index 2: " + idx2 + " size: " + size());

        Node<AnyType> currX = getNode(idx1);
        Node<AnyType> currY = getNode(idx2);
        
        Node<AnyType> nextX = currX.next;
        Node<AnyType> prevX = currX.prev;
    
        currX.next = currY.next;
        currY.next.prev = currX;
    
        currY.next = nextX;
        nextX.prev = currY;
    
        currX.prev = currY.prev;
        currY.prev.next = currX;
    
        currY.prev = prevX;
        prevX.next = currY;
        
    }
    
    public void shiftNodes(int n){
        
        if (n < 0){
            n = -n;
            n = n % size();
            n = -(n - 5);
        }
        else
            n = n % size();

        if (n == 0)
        return;

        Node<AnyType> temp1, beg = beginMarker.next;
        temp1 = beg = beginMarker.next;

        for(int i=1; i<=n; i++)
            beg = beg.next;

        Node<AnyType> temp2 = beg.prev;
        beg.prev = beginMarker;
        beginMarker.next = beg;

        Node<AnyType> end = endMarker.prev;
        end.next = temp1;
        temp1.prev = end;

        temp2.next = endMarker;
        endMarker.prev = temp2;
    }

    public void erase(int idx, int n){
        Node<AnyType> temp = getNode(idx);
        if ((idx < 0) || (idx + n > size())){
            throw new IndexOutOfBoundsException("getNode index: " + idx + "; size: " + size( ) + "; n: " + n);
        }

        for(int i = 1; i <=n; i++){
            Node<AnyType> next = temp.next;
            remove(temp);
            temp = next;
        }
    }

    public void insertList(MyLinkedList<AnyType> lst2, int idx){

        Node<AnyType> temp;

        if((idx < 0) || (idx > size()))
            throw new IndexOutOfBoundsException("getNode index: " + idx + "; size: " + size());
        
        // Inserting to the end of the list
        else if (idx == size())
            temp = endMarker;

        else
            temp = getNode(idx);
        
        temp.prev.next = lst2.beginMarker.next;
        lst2.beginMarker.next.prev = temp.prev;

        lst2.endMarker.prev.next = temp;
        temp.prev = lst2.endMarker.prev;

        theSize += lst2.size();

    }
    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private boolean okToRemove = false;
        
        public boolean hasNext( )
        {
            return current != endMarker;
        }
        
        public AnyType next( )
        {
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( ); 
                   
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }
        
        public void remove( )
        {
            if( !okToRemove )
                throw new IllegalStateException( );
                
            MyLinkedList.this.remove( current.prev );
            okToRemove = false;       
        }
    }
    
    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }
        
        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }
    
    private int theSize;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;

    public void testConnections(){

        System.out.println("Forward Pass");
        Node<AnyType> temp = getNode(0);
        for(int i = 1; i <= size(); i++){
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.print("END \n");

        System.out.println("Backward Pass");
        temp = getNode(size() - 1);
        for(int i = 1; i <= size(); i++){
            System.out.print(temp.data + "->");
            temp = temp.prev;
        }
        System.out.print("BEGINNING \n");
    }
}

class TestLinkedList
{
    public static void main( String [ ] args )
    {
        MyLinkedList<Integer> lst = new MyLinkedList<Integer>( );
        
        // Adding elements into linked list lst
        for( int i = 1; i <= 5; i++ )
            lst.add( i );

        System.out.println(lst);

        // Using the swapNodes method
        lst.swapNodes(2, 4);
        System.out.println(lst);
        // lst.TestConnections() checks if all the node links are correct by printing all forward links and all backward links
        lst.testConnections();

        // Using the shiftNodes method
        lst.shiftNodes(-3);
        System.out.println(lst);
        lst.testConnections();

        // Using the erase method
        lst.erase(2, 2);
        System.out.println(lst);
        lst.testConnections();

        // Making a new linked list lst2 and adding elements to it
        MyLinkedList<Integer> lst2 = new MyLinkedList<>( );
        for( int i = 7; i <= 9; i++ )
            lst2.add( i );
        System.out.println(lst2);

        // Using the insertList method to insert linked list lst2 into linked list lst
        lst.insertList(lst2, 0);
        System.out.println(lst);
        lst.testConnections();
        
    }
}