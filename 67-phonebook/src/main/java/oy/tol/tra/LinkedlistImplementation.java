package oy.tol.tra;

public class LinkedlistImplementation<E> {

   private class Node<T> {
      Node(T element) {
         this.element = element;
         next = null;
      }

      T element;
      Node<T> next;

      @Override
      public String toString() {
         return element.toString();
      }
   }
   private Node<E> head = null;
   private int size = 0;

   public void add(E element) throws NullPointerException, LinkedListAllocationException {
      if (element.equals(null)) {
         throw new IllegalArgumentException("Null can not be added to the list");
      }
      if (head == null) {
         head = new Node<E>(element);
         size++;
      } else {
         Node<E> current = head;
         while (current.next != null) {
            current = current.next;
         }
         try {
            current.next = new Node<E>(element);
         } catch (Exception e) {
            throw new LinkedListAllocationException("Can not allocate the new list element");
         }
         size++;
      }
   }

   public void add(int index, E element)
         throws NullPointerException, LinkedListAllocationException, IndexOutOfBoundsException {
      if (element.equals(null)) {
         throw new IllegalArgumentException("Null can not be added to the list");
      }
      if (index > size || index < 0) {
         throw new IndexOutOfBoundsException("Index is outside of the linked list");
      }
      if (index == 0) {
         Node<E> newNode;
         try {
            newNode = new Node<>(element);
         } catch (Exception e) {
            throw new LinkedListAllocationException("Can not allocate the new list element");
         }

         newNode.next = head;
         head = newNode;
         size++;
      } else {
         int counter = 0;
         Node<E> current = head;
         Node<E> previous = null;

         while (counter < index) {
            previous = current;
            current = current.next;
            counter++;
         }
         Node<E> newNode;
         try {
            newNode = new Node<>(element);
         } catch (Exception e) {
            throw new LinkedListAllocationException("Can not allocate the new list element");
         }
         previous.next = newNode;
         newNode.next = current;
         size++;
      }
   }

   public boolean remove(E element) throws NullPointerException {

      if (element.equals(null)) {
         throw new IllegalArgumentException("Wont remove element that equals Null from array");
      }
      if (size == 0) {
         return false;
      }
      Node<E> current = head;
      Node<E> previous = null;
      int counter = 0;
      while (counter < size) {
         if (current.element.equals(element)) {
            previous.next = current.next;
            size--;
            return true;
         }
         previous = current;
         current = current.next;
         counter++;
      }
      return false;
   }

   public E remove(int index) throws IndexOutOfBoundsException {

      if (index >= size || index < 0) {
         throw new IndexOutOfBoundsException("Index is out of bound for this linked list");
      }
      Node<E> removed = null;
      if (index == 0) {
         removed = head;
         head = head.next;
         size--;
         return removed.element;
      }

      int counter = 1;
      Node<E> current = head.next;
      Node<E> previous = head;
      while (current != null) {
         if (counter == index) {

            removed = current;
            previous.next = current.next;
            size--;
            break;
         }
         counter++;
         previous = current;
         current = current.next;
      }

      return removed.element;
   }

   public E get(int index) throws IndexOutOfBoundsException {

      if (index < 0 || index >= size || size == 0) {
         throw new IndexOutOfBoundsException("Index is outside of the linked list");
      }
      int counter = 0;
      Node<E> anode = head;
      while (counter < index) {
         anode = anode.next;
         counter++;
      }
      return anode.element;
   }

   public int indexOf(E element) throws NullPointerException {

      if (element.equals(null)) {
         throw new IllegalArgumentException("Null can not be searched");

      }
      if (size == 0) {
         return -1;
      }
      Node<E> current = head;
      int counter = 0;

      while (counter < size) {

         if (current.element.equals(element)) {
            return counter;
         }

         current = current.next;
         counter++;
      }
      return -1;

   }

   public int size() {
      return size;
   }

   public void clear() {
      head = null;
      size = 0;
   }

   public void reverse() {
      if (size == 0 || size == 1) {
         return;
      }
      if (size == 2) {
         Node<E> current = head;
         current = head.next;
         current.next = head;
         head = current;
         return;
      }
      Node<E> current = head;
      Node<E> next = null;
      Node<E> previous = null;

      while (current != null) {
         next = current.next;
         current.next = previous;
         previous = current;
         current = next;
      }
      head = previous;
   }

   public String toString() {

      StringBuilder builder = new StringBuilder();
      builder.append("[");
      if (head != null) {

         Node<E> addnode = head;
         int counter = 0;
         while (counter < size) {
            builder.append(addnode.element);
            if (counter < size - 1) {
               builder.append(", ");
            }
            addnode = addnode.next;
            counter++;
         }

      }
      builder.append("]");
      return builder.toString();
   }

}
