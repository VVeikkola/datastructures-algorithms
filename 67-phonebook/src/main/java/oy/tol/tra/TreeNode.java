package oy.tol.tra;

import java.util.concurrent.atomic.AtomicInteger;

public class TreeNode<K extends Comparable<K>, V> {

    private int hash;
    private Pair<K, V> keyValue;
    private TreeNode<K, V> childR;
    private TreeNode<K, V> childL;
    private LinkedlistImplementation<Pair<K, V>> collisionChain = null;

    static int addDepth = 0;
    static int collisionChainpituus = 0;


    public TreeNode(K key, V value) {
        if (key == null || value == null) throw new IllegalArgumentException();
        keyValue = new Pair<K, V>(key, value);
        this.childR = null;
        this.childL = null;
        hash = key.hashCode();
    }

    public int insert(K key, V value, int keySearch) {
        if (key == null || value == null) throw new IllegalArgumentException();
        int added = 0;
        if (keySearch < this.hash) {
            if (childL == null) {
                childL = new TreeNode(key, value);
                TreeNode.addDepth += 1;
                added = 1;
            } else {
                TreeNode.addDepth += 1;
                added = childL.insert(key, value, keySearch);
            }
        } else if (keySearch > this.hash) {
            if (childR == null) {
                childR = new TreeNode(key, value);
                TreeNode.addDepth += 1;
                added = 1;
            } else {
                TreeNode.addDepth += 1;
                added = childR.insert(key, value, keySearch);
            }
        } else {
            if (keyValue.getKey().equals(key)) {
                keyValue.setvalue(value);
            } else {
                if (collisionChain == null) {
                    collisionChain = new LinkedlistImplementation<Pair<K, V>>();
                    collisionChain.add(new Pair<K, V>(key, value));
                    added = 1;
                    TreeNode.collisionChainpituus = 1;
                } else {
                    Pair<K, V> search = new Pair<K,V>(key, value);
                    int index = collisionChain.indexOf(search);
                    if (index < 0) {
                        collisionChain.add(new Pair<K, V>(key, value));
                        added = 1;
                    } else {
                        collisionChain.remove(index);
                        collisionChain.add(new Pair<K, V>(key, value));
                    }
                    TreeNode.collisionChainpituus = collisionChain.size();
                }
            }
        }
        return added;
    }

    public V find(K key, int finder) {
        V result = null;
        if (finder < this.hash) {
            if (childL != null) {
                result = childL.find(key, finder);
            }
        } else if (finder > this.hash) {
            if (childR != null) {
                result = childR.find(key, finder);
            }
        } else {
            if (keyValue.getKey().equals(key)) {
                return (V) keyValue.getValue();
            } else {
                if (collisionChain != null) {
                    Pair<K, V> search = new Pair<K, V>(key, null);
                    int index = collisionChain.indexOf(search);
                    if (index >= 0) {
                        return collisionChain.get(index).getValue();
                    }
                }
            }
        }
        return result;
    }

    public void toSortedArray(Pair<K, V>[] returnArray, AtomicInteger add) {
        if (childL != null) {
            childL.toSortedArray(returnArray, add);
        }
        returnArray[add.getAndIncrement()] = keyValue;
        if (null != collisionChain) {
            for (int index = 0; index < collisionChain.size(); index++) {
                Pair<K, V> findePair = collisionChain.get(index);
                if (findePair != null) {
                    returnArray[add.getAndIncrement()] = new Pair<>(findePair.getKey(), findePair.getValue());
                }
            }
        }
        if (childR != null) {
            childR.toSortedArray(returnArray, add);
        }
    }
}
