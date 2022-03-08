/**
 * Yei Thek Wang
 */

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BasicDoubleLinkedList_STUDENT_Test {
	BasicDoubleLinkedList<Double> linkedDouble;
	DoubleComparator dComp;

	@Before
	public void setUp() throws Exception {
		linkedDouble = new BasicDoubleLinkedList<Double>();
		linkedDouble.addToEnd(99.99);
		linkedDouble.addToEnd(345.6);
		linkedDouble.addToEnd(1.1);
		dComp = new DoubleComparator();
	}

	@After
	public void tearDown() throws Exception {
		linkedDouble = null;
		dComp = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(3,linkedDouble.getSize());
	}
	
	@Test
	public void testAddToEnd() {
		assertEquals(1.1, linkedDouble.getLast(), 0.0001);
		linkedDouble.addToEnd(44.654);
		assertEquals(44.654, linkedDouble.getLast(), 0.001);
	}
	
	@Test
	public void testAddToFront() {
		assertEquals(99.99, linkedDouble.getFirst(), 0.01);
		linkedDouble.addToFront(293.567);
		assertEquals(293.567, linkedDouble.getFirst(), 0.001);
	}
	
	@Test
	public void testGetFirst() {
		assertEquals(99.99, linkedDouble.getFirst(), 0.01);
		linkedDouble.addToFront(293.567);
		assertEquals(293.567, linkedDouble.getFirst(), 0.001);
	}

	@Test
	public void testGetLast() {
		assertEquals(1.1, linkedDouble.getLast(), 0.0001);
		linkedDouble.addToEnd(44.654);
		assertEquals(44.654, linkedDouble.getLast(), 0.001);
	}
	
	@Test
	public void testToArrayList()
	{
		linkedDouble.addToEnd(11.11);
		linkedDouble.addToEnd(800.4);
		linkedDouble.addToFront(1212.12);
		ArrayList<Double> dArr = linkedDouble.toArrayList();
		assertEquals(1212.12, dArr.get(0), 0.01);
		assertEquals(99.99, dArr.get(1), 0.01);
		assertEquals(345.6, dArr.get(2), 0.0001);
		assertEquals(1.1, dArr.get(3), 0.0001);
		assertEquals(11.11, dArr.get(4), 0.01);
		assertEquals(800.4, dArr.get(5), 0.1);
	}
	
	@Test
	public void testIteratorSuccessfulNext() {
		linkedDouble.addToFront(66.823);
		linkedDouble.addToEnd(33.88);
		ListIterator<Double> iterator = linkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(66.823, iterator.next(), 0.001);
		assertEquals(99.99, iterator.next(), 0.01);
		assertEquals(345.6, iterator.next(), 0.0001);
		assertEquals(true, iterator.hasNext());
		assertEquals(1.1, iterator.next(), 0.0001);
		assertEquals(33.8, iterator.next(), 0.1);
	}
	
	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedDouble.addToFront(12.12);
		linkedDouble.addToEnd(98.98);
		ListIterator<Double> iterator = linkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(12.12, iterator.next(), 0.01);
		assertEquals(99.99, iterator.next(), 0.01);
		assertEquals(345.6, iterator.next(), 0.0001);
		assertEquals(1.1, iterator.next(), 0.0001);
		assertEquals(98.98, iterator.next(), 0.01);
		assertEquals(true, iterator.hasPrevious());
		assertEquals(98.98, iterator.previous(), 0.01);
		assertEquals(1.1, iterator.previous(), 0.0001);
		assertEquals(345.6, iterator.previous(), 0.0001);
		assertEquals(99.99, iterator.previous(), 0.01);
		assertEquals(12.12, iterator.previous(), 0.01);
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedDouble.addToFront(66.823);
		linkedDouble.addToEnd(33.88);
		ListIterator<Double> iterator = linkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(66.823, iterator.next(), 0.001);
		assertEquals(99.99, iterator.next(), 0.01);
		assertEquals(345.6, iterator.next(), 0.0001);
		assertEquals(true, iterator.hasNext());
		assertEquals(1.1, iterator.next(), 0.0001);
		assertEquals(33.8, iterator.next(), 0.1);
		
		try{
			//no more elements in list
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionPrevious() {
		linkedDouble.addToFront(12.12);
		linkedDouble.addToEnd(98.98);
		ListIterator<Double> iterator = linkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(12.12, iterator.next(), 0.01);
		assertEquals(99.99, iterator.next(), 0.01);
		assertEquals(345.6, iterator.next(), 0.0001);
		assertEquals(1.1, iterator.next(), 0.0001);
		assertEquals(98.98, iterator.next(), 0.01);
		assertEquals(true, iterator.hasPrevious());
		assertEquals(98.98, iterator.previous(), 0.01);
		assertEquals(1.1, iterator.previous(), 0.0001);
		assertEquals(345.6, iterator.previous(), 0.0001);
		assertEquals(99.99, iterator.previous(), 0.01);
		assertEquals(12.12, iterator.previous(), 0.01);
		
		try{
			//no more elements in list
			iterator.previous();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorUnsupportedOperationException() {
		linkedDouble.addToFront(66.823);
		linkedDouble.addToEnd(33.88);
		ListIterator<Double> iterator = linkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(66.823, iterator.next(), 0.001);
		assertEquals(99.99, iterator.next(), 0.01);
		assertEquals(345.6, iterator.next(), 0.0001);
		assertEquals(true, iterator.hasNext());
		assertEquals(1.1, iterator.next(), 0.0001);
		assertEquals(33.8, iterator.next(), 0.1);
		
		try{
			//remove is not supported for the iterator
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
		
	}
	
	@Test
	public void testRemove() {
		// remove the first
		linkedDouble.addToFront(66.823);
		linkedDouble.addToEnd(33.88);
		assertEquals(66.823, linkedDouble.getFirst(), 0.001);
		linkedDouble.remove(66.823, dComp);
		assertEquals(99.99, linkedDouble.getFirst(), 0.01);
		//remove from the end of the list
		assertEquals(33.88, linkedDouble.getLast(), 0.01);
		linkedDouble.remove(33.88, dComp);
		assertEquals(1.1, linkedDouble.getLast(), 0.0001);
		//remove from middle of list
		assertEquals(99.99, linkedDouble.getFirst(), 0.01);
		assertEquals(1.1, linkedDouble.getLast(), 0.01);
		assertEquals(linkedDouble.getSize(), 3);
		linkedDouble.remove(345.6, dComp);
		assertEquals(99.99, linkedDouble.getFirst(), 0.0001);
		assertEquals(1.1, linkedDouble.getLast(), 0.0001);
		assertEquals(linkedDouble.getSize(), 2);
	}

	@Test
	public void testRetrieveFirstElement() {
		assertEquals(99.99, linkedDouble.retrieveFirstElement(), 0.01);
		linkedDouble.addToFront(44.654);
		assertEquals(44.654, linkedDouble.retrieveFirstElement(), 0.001);
		assertEquals(345.6, linkedDouble.retrieveFirstElement(), 0.0001);
	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals(1.1, linkedDouble.retrieveLastElement(), 0.0001);
		linkedDouble.addToEnd(44.654);
		assertEquals(44.654, linkedDouble.retrieveLastElement(), 0.001);
		assertEquals(345.6, linkedDouble.retrieveLastElement(), 0.0001);
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
}