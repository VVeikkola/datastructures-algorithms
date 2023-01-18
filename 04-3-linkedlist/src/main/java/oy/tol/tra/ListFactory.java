package oy.tol.tra;

import java.util.ArrayList;

/**
 * This class creates different types of lists implementing the {@code LinkedListInterface} interface.
 * 
 * @author Antti Juustila
 */
public class ListFactory {

   private ListFactory() {
      // Empty
   }
   
   /**
    * Creates an instance of ListImplementation for String type.
    * @return The list object.
    */
   public static LinkedListInterface<String> createStringLinkedList() {
      // - TODO: Instantiates your list implementation, 
      // - and return the stack object to the caller.
      return new LinkedListImplementation();
   }
}
