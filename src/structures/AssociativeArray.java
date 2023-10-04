
package structures;

import static java.lang.reflect.Array.newInstance;

/**
 * A basic implementation of Associative Arrays with keys of type K
 * and values of type V. Associative Arrays store key/value pairs
 * and permit you to look up values by key.
 *
 * 
 * @author Kevin Fitzgerald
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key/value pairs).
   */
  int size;

  /**
   * The array of key/value pairs.
   */
  KVPair<K, V> pairs[];

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({ "unchecked" })
  public AssociativeArray() {
    // Creating new arrays is sometimes a PITN.
    this.pairs = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(),
        DEFAULT_CAPACITY);
    this.size = 0;
  } // AssociativeArray()

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this AssociativeArray.
   */
  public AssociativeArray<K, V> clone() {
    AssociativeArray<K, V> newarr = new AssociativeArray<K, V>();
    for(int i = 0; i< this.size; i++){
      newarr.set(this.pairs[i].key,this.pairs[i].value);
    }//for
    return newarr; 
  } // clone()

  /**
   * Convert the array to a string.
   */
  public String toString() {
    String cumResult = "{";
    if (this.size == 0){
      cumResult = cumResult + "}";
      }//if array is empty
    else{
      for(int i = 0; i < this.size - 1; i++){
        cumResult = cumResult + " " + this.pairs[i].key + ": " + this.pairs[i].value + ",";
        }//for
      cumResult = cumResult + " "+ this.pairs[this.size - 1].key + ": " + this.pairs[this.size - 1].value + " }";
      }//else
    return cumResult;
  } // toString()

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Set the value associated with key to value. Future calls to
   * get(key) will return value.
   */
  public void set(K key, V value){
    if(this.hasKey(key)){
      int loc = 0;
      try{
      loc = find(key);
      }catch (KeyNotFoundException e){
      System.out.println("something beyond God has caused this error"); //this will never happen for reasons Java cannot understand
      System.exit(0);
      }
        pairs[loc].value = value;
      }// if key already exists
      else if(this.size == this.pairs.length - 1){
          this.expand();
          pairs[size] = new KVPair<K,V>(key, value);
          size++;
      } // if array is full
      else{
        pairs[size] = new KVPair<K,V>(key, value);
        size++;
      }
  } // set(K,V)

  /**
   * Get the value associated with key.
   *
   * @throws KeyNotFoundException
   *                              when the key does not appear in the associative
   *                              array.
   */
  public V get(K key) throws KeyNotFoundException {

     V val = this.pairs[find(key)].value;
    
    return val;
  } // get(K)

  /**
   * Determine if key appears in the associative array.
   */
  public boolean hasKey(K key) {
    for( int i = 0; i < this.size(); i++){
      if (this.pairs[i].key.toString().equals( key.toString()))
      {return true;}
    }//for
    return false; 
  } // hasKey(K)

  /**
   * Remove the key/value pair associated with a key. Future calls
   * to get(key) will throw an exception. If the key does not appear
   * in the associative array, does nothing.
   */
  public void remove(K key) {
    if (this.hasKey(key)){
      try{
      this.pairs[this.find(key)] = this.pairs[size - 1];
      this.pairs[size-1] = null;
      size--;
        }catch (KeyNotFoundException e){
        System.out.println("something beyond God has caused this error"); //as with set(key) gettng this error is a Very Bad Sign
        System.exit(0);
        }//try/catch
    }// if key exists
  } // remove(K)

  /**
   * Determine how many values are in the associative array.
   */
  public int size() {
    return this.size;
  } // size()

  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  public void expand() {
    this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key.
   * If no such entry is found, throws an exception.
   */
  public int find(K key) throws KeyNotFoundException {

      for( int i = 0; i < this.size(); i++){
        if (this.pairs[i].key.toString().equals( key.toString()))
      {return i;}
      }
      throw new KeyNotFoundException();
    
  } // find(K)

} // class AssociativeArray
