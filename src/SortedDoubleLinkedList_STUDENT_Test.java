/**
 * Yei Thek Wang
 */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortedDoubleLinkedList_STUDENT_Test {
	StringComparator comparator;
	SortedDoubleLinkedList<String> sortedVegetables;
	

	@Before
	public void setUp() throws Exception {
		comparator = new StringComparator();
		sortedVegetables = new SortedDoubleLinkedList<String>(comparator);
	}

	@After
	public void tearDown() throws Exception {
		comparator = null;
		sortedVegetables = null;
	}

	@Test
	public void testAddToEndSTUDENT() {
		try {
			sortedVegetables.addToEnd("Onion");
		}
		catch (UnsupportedOperationException e)
		{
			assertEquals(e.getMessage(), "Invalid operation for sorted list.");
		}
	}

	@Test
	public void testAddToFrontSTUDENT() {
		try {
			sortedVegetables.addToFront("Broccoli");
		}
		catch (UnsupportedOperationException e)
		{
			assertEquals(e.getMessage(), "Invalid operation for sorted list.");
		}
	}

	@Test
	public void testAddSTUDENT() {
		sortedVegetables.add("Carrot"); 
		sortedVegetables.add("Daikon"); 
		sortedVegetables.add("Endive"); 
		assertEquals(new String("Carrot"), sortedVegetables.getFirst());
		assertEquals(new String("Endive"), sortedVegetables.getLast());
	}
	
	@Test
	public void testRemoveMiddleSTUDENT() {
		sortedVegetables.add("Garlic");
		sortedVegetables.add("Habanero Pepper"); 
		assertEquals(new String("Garlic"), sortedVegetables.getFirst());
		assertEquals(new String("Habanero Pepper"), sortedVegetables.getLast());
		sortedVegetables.add("Fennel"); 
		assertEquals(3,sortedVegetables.getSize());
		sortedVegetables.remove("Garlic", comparator);
		assertEquals(new String("Fennel"), sortedVegetables.getFirst());
		assertEquals(new String("Habanero Pepper"), sortedVegetables.getLast());
		assertEquals(2,sortedVegetables.getSize());
        }

	
	private class StringComparator implements Comparator<String>
	{
		@Override
		public int compare(String arg0, String arg1) {
			return arg0.compareTo(arg1);
		}
		
	}
}