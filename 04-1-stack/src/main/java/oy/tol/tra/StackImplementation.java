package oy.tol.tra;


/**
 * An implementation of the StackInterface.
 * <p>
 * TODO: Students, implement this so that the tests pass.
 * 
 * Note that you need to implement construtor(s) for your concrete StackImplementation, which
 * allocates the internal Object array for the Stack:
 * - a default constructor, calling the StackImplementation(int size) with value of 10.
 * - StackImplementation(int size), which allocates an array of Object's with size.
 *  -- remember to maintain the capacity and/or currentIndex when the stack is manipulated.
 */
public class StackImplementation<E> implements StackInterface<E> {

   // TODO: Add member variables needed.
   // Do not use constant values in code, e.g. 10. Instead, define a constant for that. For example:
   // private static final int MY_CONSTANT_VARIABLE = 10;
   
   private Object [] itemArray;
   private int currentIndex;
   private int capacity;

   /**
    * Allocates a stack with a default capacity.
    * @throws StackAllocationException
    */
   public StackImplementation() throws StackAllocationException {
      // TODO: call the constructor with size parameter with default size (see member variable!).
      this(10);
   }

   /** TODO: Implement so that
    * - if the size is less than 2, throw StackAllocationException
    * - if the allocation of the array throws with Java exception,
    *   throw StackAllocationException.
    * @param capacity The capacity of the stack.
    * @throws StackAllocationException If cannot allocate room for the internal array.
    */
   public StackImplementation(int capacity) throws StackAllocationException {

      if(capacity < 2){
         throw new StackAllocationException("Stack size should be more than 1");
      }
      try{
         itemArray = new Object[capacity]; 
         this.capacity = capacity;
         currentIndex = -1;

      }catch(Exception e){
         throw new StackAllocationException(e.getMessage());
      }
   }

   @Override
   public int capacity() {
      // TODO: Implement this
      
      return capacity;
   }

   @Override
   public void push(E element) throws StackAllocationException, NullPointerException {
      // TODO: Implement this

      if(element==null){
         throw new NullPointerException("Cannot push null elements to stack");
      }

      if(currentIndex >= capacity - 1){
         reallocateArray();
      }
      currentIndex++;
      itemArray[currentIndex] = element;

   }

   @SuppressWarnings("unchecked")
   @Override
   public E pop() throws StackIsEmptyException {
      // TODO: Implement this
      if(isEmpty()){
         throw new StackIsEmptyException("Cannot pop from empty Stack.");
      }

      E element = (E) itemArray[currentIndex];
      itemArray[currentIndex] = null;
      currentIndex--;
      return element;
   }

   @SuppressWarnings("unchecked")
   @Override
   public E peek() throws StackIsEmptyException {
      // TODO: Implement this

      if(isEmpty()){
         throw new StackIsEmptyException("Cannot peek from empty Stack.");
      }else{
         return (E) itemArray[currentIndex];
      }
   }

   @Override
   public int size() {
      // TODO: Implement this
      
      int size = currentIndex + 1;
      return size;
   }

   @Override
   public void clear() {
      // TODO: Implement this

      currentIndex = -1;
      itemArray = new Object[capacity];
   }

   @Override
   public boolean isEmpty() {
      // TODO: Implement this
      return currentIndex < 0;
   }

   @Override
   public String toString() {
      // TODO: Implement this

      StringBuilder builder = new StringBuilder();
      builder.append("[");

      for(int index = 0; index <= currentIndex; index++){
         builder.append(itemArray[index]);
         if(index < currentIndex){
            builder.append(", ");
         }
      }

      builder.append("]");
      return builder.toString();
   }

   private void reallocateArray(){

      try{

      int newCapacity = capacity * 2; 
      Object [] newArray = new Object[newCapacity]; 

      for(int index = 0; index <= currentIndex; index++){
         newArray[index] = itemArray[index];
      }
      itemArray = newArray;
      capacity = newCapacity;
      
      }catch(Exception e){
         throw new StackAllocationException("No room for bigger array");
}
}

}