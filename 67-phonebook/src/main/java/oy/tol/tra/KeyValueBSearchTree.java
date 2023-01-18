package oy.tol.tra;

import java.util.concurrent.atomic.AtomicInteger;

public class KeyValueBSearchTree<K extends Comparable<K>,V> implements Dictionary<K, V> {

    // This is the BST implementation, KeyValueHashTable has the hash table implementation
    private TreeNode<K, V> root;
    private int count;
    private int depth;
    private int collisionLenght;

    public KeyValueBSearchTree(){
        root = null;
        count = 0;
        depth = 0;
        collisionLenght = 0;
    }

    @Override
    public Type getType() {
       return Type.BST;
    }
 
    @Override
    public int size() {

        return count;
    }

    /**
     * Prints out the statistics of the tree structure usage.
     * Here you should print out member variable information which tell something about
     * your implementation.
     * <p>
     * For example, if you implement this using a hash table, update member variables of the class
     * (int counters) in add(K) whenever a collision happen. Then print this counter value here. 
     * You will then see if you have too many collisions. It will tell you that your hash function
     * is good or bad (too much collisions against data size).
     */
    @Override
    public String getStatus() {
        // TODO: Implement this!
        StringBuilder builder = new StringBuilder();
        builder.append(
            "Treenodes: " + count + "\n" +
            "Max depth of tree: " + depth + "\n" +
            "Collision lenght: " + collisionLenght + "\n"
        );
        return builder.toString();
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        // TODO: Implement this!
        boolean print = false;
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        try {

            if (root == null) {
                root = new TreeNode<K, V>(key, value);
                count++;
                depth = 1;
                collisionLenght = 0;
                print = true;

            } else {
                TreeNode.addDepth = 1;
                collisionLenght = 0;
                int added = root.insert(key, value, key.hashCode());
                if (added > 0) {
                    depth = Math.max(TreeNode.addDepth, depth);
                    count++;
                    print = true;
                }
                collisionLenght = Math.max(collisionLenght, TreeNode.collisionChainpituus);

            }
        } catch (Exception e) {
            throw new OutOfMemoryError();
        }
        return print;
    }


    @Override
    public V find(K key) throws IllegalArgumentException {
        if (key == null){
            throw new IllegalArgumentException();
        }
        if (root == null){
            return null;
        
        }
        return root.find(key, key.hashCode());

    }
    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
        // TODO: Implement this (if needed)!
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pair<K,V> [] toSortedArray() {
        if (root == null){
            return null;
        }
        Pair<K, V>[] array = (Pair<K, V>[]) new Pair[count];
        AtomicInteger add = new AtomicInteger(0);
        root.toSortedArray(array, add);
        Algorithms.fastSort(array);
        return array;

    }
    
      @Override
      public void compress() throws OutOfMemoryError {
        // TODO: Implement this (if needed)!
    }
   
}
