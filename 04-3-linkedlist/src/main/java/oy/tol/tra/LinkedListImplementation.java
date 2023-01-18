package oy.tol.tra;


public class LinkedListImplementation<E> implements LinkedListInterface<E> {

   private class Node<T> {
      Node(T data) {
         element = data;
         next = null;
      }
      T element;
      Node<T> next;
      @Override
      public String toString() {
         return element.toString();
      }
   }

   private Node<E> head;
   private Node<E> tail;
   private int count;

   public LinkedListImplementation(){
      this.head = null;
      this.tail = null;
      this.count = 0;
   }

   @Override
   public void add(E element) throws NullPointerException, LinkedListAllocationException {

      if(null == head){
         head = new Node<E>(element);
         count++;
      }else{
         Node<E> current = head;
         while(current.next != null){
            current = current.next;
         }
         current.next = new Node<E>(element);
         count++;
      }
   }

   @Override
   public void add(int index, E element) throws NullPointerException, LinkedListAllocationException, IndexOutOfBoundsException {

      if(index < 0 || index > count){
         throw new IndexOutOfBoundsException("Index is wrong for this linked list");
      }

      if(index == 0){
         Node<E> newNode = new Node<E>(element);
         newNode.next = head;
         head = newNode;
         count++;
      }else{
         int counter = 0;
         Node<E> current = head;
         Node<E> previous = null;

         while(counter < index){
            previous = current;
            current = current.next;
            counter++;
         }
         Node<E> newNode = new Node<E>(element);
         previous.next = newNode;
         newNode.next = current;
         count++;
      }
   }

   @Override
   public boolean remove(E element) throws NullPointerException {

      Node<E> current = head;

      if(element == null){
         throw new NullPointerException("Element is null");
      }

      if(current == null){
         return false;
      }

      if(current.element.equals(element)){
         current = current.next;
         return true;
      }
      
      while(current.next != null){
         if(current.next.element.equals(element)){
            current = current.next;
            current = current.next;
            return true;
         }
         current = current.next;
      }
      return false;
   }

   @Override
   public E remove(int index) throws IndexOutOfBoundsException {

      if(index < 0 || index >= count){
         throw new IndexOutOfBoundsException("Index is wrong for this linked list");
      }

      E removed = null;
      if(index == 0){
         removed = head.element;
         head = head.next;
         count--;
      }else{
         int counter = 1;
         Node<E> current = head.next;
         Node<E> previous = head;
         while(current != null){
            if(counter == index){
               removed = current.element;
               previous.next = current.next;
               count--;
               break;
            }
            counter++;
            previous = current;
            current = current.next;
         }
      }
      return removed;
   }

   @Override
   public E get(int index) throws IndexOutOfBoundsException {

      if(index < 0 || index >= count){
         throw new IndexOutOfBoundsException("Index is wrong for this linked list");
      }
      Node<E> current = head;

      for(int i = 0; i < index; i++){
         current = current.next;
      }
      return current.element;
   }

   @Override
   public int indexOf(E element) throws NullPointerException {

      if(element == null){
         throw new NullPointerException("Element is null");
      }
      int index = 0;
      Node<E> temp = head;

      while(temp != null){
         if(temp.element.equals(element)){
            return index;
         }
         index++;
         temp = temp.next;
      }
      return -1;
   }

   @Override
   public int size() {

      return count;
   }

   @Override
   public void clear() {

      head = null;
      count = 0;
   }

   @Override
   public void reverse() {
      
      Node<E> current = head;
      Node<E> previous = null;
      Node<E> next = null;

      while(current != null){
         next = current.next;
         current.next = previous;
         previous = current;
         current = next;
      }
      tail = head;
      head = previous;
   }

   @Override
   public String toString() {

      StringBuilder builder = new StringBuilder();
      builder.append("[");

      Node<E> current = head;

      while(current != null){
         builder.append(current.element);

         if(current.next != null){
            builder.append(", ");
         }
         current = current.next;
      }

      builder.append("]");
      return builder.toString();
   }
}
