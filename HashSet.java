import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.*;

public class HashSet<AnyType> extends AbstractCollection<AnyType> implements Set<AnyType>{
    /**
    * Construct an empty HashSet.
    */
    public HashSet( ) {
        allocateArray( DEFAULT_TABLE_SIZE );
        clear( );
    }

    /**
    * Construct a HashSet from any collection.
    */
    public HashSet( Collection<? extends AnyType> other ) {
        allocateArray( nextPrime( other.size( ) * 2 ) );
        clear( );
        for( AnyType val : other )
            add( val );
    }

    private int nextPrime(int i) {
        return 0;
    }

    /**
    * this inner class is needed to encapsulate the element
    * and provide the flag field required by the Hash Table
    */
    
    private static class HashEntry{
        public Object element; // the element
        public boolean isActive; // false if marked deleted
        public HashEntry( Object e ){
            this( e, true );
        }
        public HashEntry( Object e, boolean i ){
            element = e;
            isActive = i;
        }
    }

    /**
    * Internal method to allocate array.
    * @param arraySize the size of the array.
    */
    private void allocateArray( int arraySize ) {
        array = new HashEntry[ nextPrime( arraySize ) ];
    }

    /**
    * Method that performs quadratic probing resolution.
    * @param x the item to search for.
    * @return the position where the search terminates.
    */
    private int findPos( Object x ) {
        int offset = 1;
        int currentPos = ( x == null ) ? 0 :
        Math.abs( x.hashCode( ) % array.length );
        while( array[ currentPos ] != null ) {
        if( x == null ) {
        if( array[ currentPos ].element == null )
        break;
        }
        else if( x.equals( array[ currentPos ].element ) )
        break;
        currentPos += offset; // Compute ith probe
        offset += 2;
        if( currentPos >= array.length ) // Implement the mod
        currentPos -= array.length;
        }
        return currentPos;
    }
    /**
    * Tests if some item is in this collection.
    * @param x any object.
    * @return true if this collection contains an item equal to x.
    */
    public boolean contains( Object x ){
        return isActive( array, findPos( x ) );
    }

    /**
    * Tests if item in pos is active.
    * @param pos a position in the hash table.
    * @param arr the HashEntry array (can be oldArray during rehash).
    * @return true if this position is active.
    */
    private static boolean isActive( HashEntry [ ] arr, int pos ){
        return arr[ pos ] != null && arr[ pos ].isActive;
    }

    @Override
    public Iterator<AnyType> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }
}