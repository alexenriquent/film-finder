/**
 * 
 */
package asgn1Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn1Index.IndexException;
import asgn1Index.Record;
import asgn1Index.RecordCollection;

/**
 * @author Thanat Chokwijitkul 9234900
 *
 */
public class RecordCollectionTests {

	RecordCollection collection;
	final String TITLE_1 = "The Sound of Music";
	final String TITLE_2 = "Casablanca";
	final String TITLE_3 = "The King's Speech";
 	final String EMPTY_STRING = "";
	final int SIMILARITY = 100;
	final int NEGATIVE_VALUE = -1;
	final int ZERO = 0;
	final int POSITIVE_VALUE = 1;
	
	/**
	 * Create a RecordCollection object before each test case. 
	 * @throws IndexException
	 */
	@Before 
	public void setUp() throws IndexException {
		collection = new RecordCollection();
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#addRecord(Record)}.
	 * Confirm that <code>addRecord</code> throws an exception when adding
	 * a null record to the collection.
	 * @throws IndexException
	 */
	@Test (expected = IndexException.class) 
	public void addNullRecord() throws IndexException {
		Record record = null;
		collection.addRecord(record);
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#addRecord(Record)}.
	 * Confirm that <code>addRecord</code> throws an exception when adding
	 * a null value instead of a Record object.
	 * @throws IndexException
	 */
	@Test (expected = IndexException.class) 
	public void addNullValueInsteadOfRecordObject() throws IndexException {
		collection.addRecord(null);
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#addRecord(Record)}.
	 * Confirm that the <code>NullPointerException</code> exception is thrown
	 * if trying to add a record to a null collection.
	 * @throws IndexException
	 * @throws NullPointerException
	 */
	@SuppressWarnings("null")
	@Test (expected = NullPointerException.class)
	public void addRecordToNullCollection() throws IndexException, NullPointerException {
		RecordCollection anotherCollection = null;
		Record record1 = new Record(TITLE_1, SIMILARITY);
		anotherCollection.addRecord(record1);
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#addRecord(Record)}.
	 * Confirm that the <code>NullPointerException</code> exception is thrown
	 * if trying to add a null record to a null collection.
	 * @throws IndexException
	 * @throws NullPointerException
	 */
	@SuppressWarnings("null")
	@Test (expected = NullPointerException.class)
	public void addNullRecordToNullCollection() throws IndexException, NullPointerException {
		RecordCollection anotherCollection = null;
		Record record1 = null;
		anotherCollection.addRecord(record1);
	}
	
    /**
	 * Test method for {@link asgn1Index.RecordCollection#addRecord(Record)}.
	 * Confirm that the collection is not sorted (sorted flag is unset) after adding a record 
	 * by calling to <code>addRecord</code> and without calling <code>sortCollection</code>.
	 * @throws IndexException
	 */
	@Test 
	public void collectionIsNotsortedAfterAddingARecord() throws IndexException {
		Record record1 = new Record(TITLE_1, SIMILARITY);
		collection.addRecord(record1);
		assertFalse(collection.isSorted());
	}
	
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#addRecord(Record)}.
	 * Confirm that the collection is not sorted after adding multiple records in
	 * ascending order (unsorted in this case) by calling to <code>addRecord</code>
	 * but without calling <code>sortCollection</code>.
	 * @throws IndexException
	 */
	@Test 
	public void collectionIsNotsortedAfterAddingMultipleRecordsInUnortedOrder() throws IndexException {
		Record record1 = new Record(TITLE_1, SIMILARITY);
		Record record2 = new Record(TITLE_2, SIMILARITY*2);
		Record record3 = new Record(TITLE_3, SIMILARITY*3);
		collection.addRecord(record1);
		collection.addRecord(record2);
		collection.addRecord(record3);
		assertFalse(collection.isSorted());
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#addRecord(Record)}.
	 * Confirm that the collection is not sorted after adding multiple records
	 * even in descending order (sorted in this case )by calling to <code>addRecord</code>
	 * but without calling <code>sortCollection</code>.
	 * @throws IndexException
	 */
	@Test 
	public void collectionIsNotsortedAfterAddingMultipleRecordsInSortedOrder() throws IndexException {
		Record record1 = new Record(TITLE_1, SIMILARITY*3);
		Record record2 = new Record(TITLE_2, SIMILARITY*2);
		Record record3 = new Record(TITLE_3, SIMILARITY);
		collection.addRecord(record1);
		collection.addRecord(record2);
		collection.addRecord(record3);
		assertFalse(collection.isSorted());
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#addRecord(Record)}.
	 * Confirm that <code>addRecord</code> can accept the same records with the same values 
	 * without throwing an exception.
	 * @throws IndexException
	 */
	@Test
	public void addDuplicateSameRecord() throws IndexException {
		Record record1 = new Record(TITLE_1, SIMILARITY);
		collection.addRecord(record1);
		collection.addRecord(record1);
		collection.addRecord(record1);
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#addRecord(Record)}.
	 * Confirm that <code>addRecord</code> can accept different records with the same values 
	 * without throwing an exception.
	 * @throws IndexException
	 */
	@Test
	public void addDifferentRecordsWithSameValues() throws IndexException {
		Record record1 = new Record(TITLE_1, SIMILARITY);
		Record record2 = new Record(TITLE_1, SIMILARITY);
		Record record3 = new Record(TITLE_1, SIMILARITY);
		collection.addRecord(record1);
		collection.addRecord(record2);
		collection.addRecord(record3);
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#findClosestRecord()}.
	 * Confirm that <code>findClosestRecord</code> throws an exception 
	 * if the collection is not sorted.
	 * @throws IndexException
	 */
	@Test (expected = IndexException.class) 
	public void findClosestRecordOfUnsortedCollection() throws IndexException {
		Record record1 = new Record(TITLE_1, SIMILARITY);
		Record record2 = new Record(TITLE_2, SIMILARITY*2);
		Record record3 = new Record(TITLE_3, SIMILARITY*3);
		collection.addRecord(record1);
		collection.addRecord(record2);
		collection.addRecord(record3);
		collection.findClosestRecord();
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#findClosestRecord()}.
	 * Confirm that <code>findClosestRecord</code> throws an exception 
	 * if the collection empty but sorted.
	 * @throws IndexException
	 */
	@Test (expected = IndexException.class)
	public void findClosestRecordOfEmptyButSortedCollection() throws IndexException {
		collection.sortCollection();
		collection.findClosestRecord();
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#findClosestRecord()}.
	 * Confirm that <code>findClosestRecord</code> throws an exception 
	 * if the collection empty and not sorted.
	 * @throws IndexException
	 */
	@Test (expected = IndexException.class)
	public void findClosestRecordOfEmptyAndUnsortedCollection() throws IndexException {
		collection.findClosestRecord();
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#findClosestRecord()}.
	 * Confirm that <code>findClosestRecord</code> returns the top record 
	 * if the collection is sorted.
	 * @throws IndexException
	 */
	@Test
	public void findClosestRecordOfSortedCollection() throws IndexException {
		Record record1 = new Record(TITLE_1, SIMILARITY);
		Record record2 = new Record(TITLE_2, SIMILARITY*2);
		Record record3 = new Record(TITLE_3, SIMILARITY*3);
		collection.addRecord(record1);
		collection.addRecord(record2);
		collection.addRecord(record3);
		collection.sortCollection();
		assertEquals(record3, collection.findClosestRecord());
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#findClosestRecord()}.
	 * Confirm that <code>findClosestRecord</code> returns the correct top record 
	 * after adding new records.
	 * @throws IndexException
	 */
	@Test
	public void findCorrectClosestRecord() throws IndexException {
		Record record1 = new Record(TITLE_1, SIMILARITY);
		collection.addRecord(record1);
		collection.sortCollection();
		assertEquals(record1, collection.findClosestRecord());
		Record record2 = new Record(TITLE_2, SIMILARITY*2);
		collection.addRecord(record2);
		collection.sortCollection();
		assertEquals(record2, collection.findClosestRecord());
		Record record3 = new Record(TITLE_3, SIMILARITY*3);
		collection.addRecord(record3);
		collection.sortCollection();
		assertEquals(record3, collection.findClosestRecord());
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#findClosestRecord()}.
	 * Confirm that <code>findClosestRecord</code> returns the top record 
	 * if the collection contains duplicate records and the collection is sorted.
	 * @throws IndexException
	 */
	@Test
	public void findClosestRecordWithDuplicateRecordsInCollection() throws IndexException {
		Record record1 = new Record(TITLE_1, SIMILARITY);
		collection.addRecord(record1);
		collection.addRecord(record1);
		collection.addRecord(record1);
		collection.sortCollection();
		assertEquals(record1, collection.findClosestRecord());
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#findClosestRecord()}.
	 * Confirm that <code>findClosestRecord</code> returns the top record, which
	 * should be the latest record added to the collection in the case that top n 
	 * (three in this case) records have the same values. 
	 * @throws IndexException
	 */
	@Test
	public void findClosestRecordWithSameValuesInCollection() throws IndexException {
		Record record1 = new Record(TITLE_1, SIMILARITY);
		Record record2 = new Record(TITLE_1, SIMILARITY);
		Record record3 = new Record(TITLE_1, SIMILARITY);
		collection.addRecord(record1);
		collection.addRecord(record2);
		collection.addRecord(record3);
		collection.sortCollection();
		assertEquals(record3, collection.findClosestRecord());
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#findClosestRecords(int)}.
	 * Confirm that <code>findClosestRecords</code> throws an exception 
	 * if the collection is not sorted.
	 * @throws IndexException
	 */
	@Test (expected = IndexException.class) 
	public void findClosestRecordsOfUnsortedCollection() throws IndexException {
		int numberOfRecords = 2;
		Record record1 = new Record(TITLE_1, SIMILARITY);
		Record record2 = new Record(TITLE_2, SIMILARITY*2);
		Record record3 = new Record(TITLE_3, SIMILARITY*3);
		collection.addRecord(record1);
		collection.addRecord(record2);
		collection.addRecord(record3);
		collection.findClosestRecords(numberOfRecords);
	}
	
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#findClosestRecords(int)}.
	 * Confirm that <code>findClosestRecords</code> throws an exception 
	 * if the integer n is negative.
	 * @throws IndexException
	 */
	@Test (expected = IndexException.class) 
	public void findClosestRecordsWithNegativeNumber() throws IndexException {
		Record record1 = new Record(TITLE_1, SIMILARITY);
		Record record2 = new Record(TITLE_2, SIMILARITY*2);
		Record record3 = new Record(TITLE_3, SIMILARITY*3);
		collection.addRecord(record1);
		collection.addRecord(record2);
		collection.addRecord(record3);
		collection.sortCollection();
		collection.findClosestRecords(NEGATIVE_VALUE);
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#findClosestRecords(int)}.
	 * Confirm that <code>findClosestRecords</code> throws an exception 
	 * if the collection is not sorted and n is negative.
	 * @throws IndexException
	 */
	@Test (expected = IndexException.class) 
	public void findClosestRecordsOfUnsortedCollectionWithNegativeNumber() throws IndexException {
		Record record1 = new Record(TITLE_1, SIMILARITY);
		Record record2 = new Record(TITLE_2, SIMILARITY*2);
		Record record3 = new Record(TITLE_3, SIMILARITY*3);
		collection.addRecord(record1);
		collection.addRecord(record2);
		collection.addRecord(record3);
		collection.findClosestRecords(NEGATIVE_VALUE);
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#findClosestRecords(int)}.
	 * Confirm that <code>findClosestRecords</code> throws an exception 
	 * if the integer n is greater than the number of records in the collection.
	 * @throws IndexException
	 */
	@Test (expected = IndexException.class) 
	public void findClosestRecordsWithExcessiveNumber() throws IndexException {
		int excessiveNumberOfRecords = 5;
		Record record1 = new Record(TITLE_1, SIMILARITY);
		Record record2 = new Record(TITLE_2, SIMILARITY*2);
		Record record3 = new Record(TITLE_3, SIMILARITY*3);
		collection.addRecord(record1);
		collection.addRecord(record2);
		collection.addRecord(record3);
		collection.sortCollection();
		collection.findClosestRecords(excessiveNumberOfRecords);
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#findClosestRecords(int)}.
	 * Confirm that <code>findClosestRecords</code> throws an exception 
	 * if the collection is not sorted and n is excessive.
	 * @throws IndexException
	 */
	@Test (expected = IndexException.class) 
	public void findClosestRecordsOfUnsortedCollectionWithExcessiveNumber() throws IndexException {
		int excessiveNumberOfRecords = 5;
		Record record1 = new Record(TITLE_1, SIMILARITY);
		Record record2 = new Record(TITLE_2, SIMILARITY*2);
		Record record3 = new Record(TITLE_3, SIMILARITY*3);
		collection.addRecord(record1);
		collection.addRecord(record2);
		collection.addRecord(record3);
		collection.findClosestRecords(excessiveNumberOfRecords);
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#findClosestRecords(int)}.
	 * Confirm that <code>findClosestRecords</code> returns an empty ArrayList
	 * when the collection contains no record.
	 * @throws IndexException
	 */
	@Test (expected = IndexException.class)
	public void findOneClosestRecordOfEmptyCollection() throws IndexException {
		int numberOfRecord = 1;
		ArrayList<Record> emptyCollection = new ArrayList<Record>();
		collection.sortCollection();
		assertEquals(emptyCollection, collection.findClosestRecords(numberOfRecord));
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#findClosestRecords(int)}.
	 * Confirm that <code>findClosestRecords</code> returns an empty ArrayList
	 * when the collection contains no record.
	 * @throws IndexException
	 */
	@Test
	public void findClosestRecordsOfEmptyCollection() throws IndexException {
		int empty = 0;
		ArrayList<Record> emptyCollection = new ArrayList<Record>();
		collection.sortCollection();
		assertEquals(emptyCollection, collection.findClosestRecords(empty));
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#findClosestRecords(int)}.
	 * Confirm that <code>findClosestRecords</code> returns the n top record 
	 * if the collection is sorted.
	 * @throws IndexException
	 */
	@Test
	public void findClosestRecordsOfsortedCollection() throws IndexException {
		int numberOfRecords = 2;
		Record record1 = new Record(TITLE_1, SIMILARITY);
		Record record2 = new Record(TITLE_2, SIMILARITY*2);
		Record record3 = new Record(TITLE_3, SIMILARITY*3);
		collection.addRecord(record1);
		collection.addRecord(record2);
		collection.addRecord(record3);
		collection.sortCollection();
		ArrayList<Record> topRecords = new ArrayList<Record>();
		topRecords.add(record3);
		topRecords.add(record2);
		assertEquals(topRecords, collection.findClosestRecords(numberOfRecords));
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#findClosestRecords(int)}.
	 * Confirm that <code>findClosestRecords</code> returns the correct top n record 
	 * after adding new records.
	 * @throws IndexException
	 */
	@Test
	public void findCorrectClosestRecords() throws IndexException {
		int numberOfRecords = 1;
		Record record1 = new Record(TITLE_1, SIMILARITY);
		collection.addRecord(record1);
		collection.sortCollection();
		ArrayList<Record> topOneRecord = new ArrayList<Record>();
		topOneRecord.add(record1);
		assertEquals(topOneRecord, collection.findClosestRecords(numberOfRecords));
		Record record2 = new Record(TITLE_2, SIMILARITY*2);
		collection.addRecord(record2);
		collection.sortCollection();
		ArrayList<Record> topTwoRecord = new ArrayList<Record>();
		topTwoRecord.add(record2);
		topTwoRecord.add(record1);
		assertEquals(topTwoRecord, collection.findClosestRecords(numberOfRecords+1));
		Record record3 = new Record(TITLE_3, SIMILARITY*3);
		collection.addRecord(record3);
		collection.sortCollection();
		ArrayList<Record> topThreeRecord = new ArrayList<Record>();
		topThreeRecord.add(record3);
		topThreeRecord.add(record2);
		topThreeRecord.add(record1);
		assertEquals(topThreeRecord, collection.findClosestRecords(numberOfRecords+2));
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#findClosestRecords(int)}.
	 * Confirm that <code>findClosestRecords</code> returns the top n record 
	 * if the collection contains duplicate records and the collection is sorted.
	 * @throws IndexException
	 */
	@Test
	public void findClosestRecordsWithDuplicateRecordsInCollection() throws IndexException {
		int numberOfRecords = 3;
		Record record1 = new Record(TITLE_1, SIMILARITY);
		collection.addRecord(record1);
		collection.addRecord(record1);
		collection.addRecord(record1);
		collection.sortCollection();
		ArrayList<Record> topThreeRecord = new ArrayList<Record>();
		topThreeRecord.add(record1);
		topThreeRecord.add(record1);
		topThreeRecord.add(record1);
		assertEquals(topThreeRecord, collection.findClosestRecords(numberOfRecords));
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#findClosestRecords(int)}.
	 * Confirm that <code>findClosestRecords</code> returns the top n record 
	 * if the collection contains records with the same values and the collection is sorted.
	 * @throws IndexException
	 */
	@Test
	public void findClosestRecordsWithSameValuesInCollection() throws IndexException {
		int numberOfRecords = 3;
		Record record1 = new Record(TITLE_1, SIMILARITY);
		Record record2 = new Record(TITLE_1, SIMILARITY);
		Record record3 = new Record(TITLE_1, SIMILARITY);
		collection.addRecord(record1);
		collection.addRecord(record2);
		collection.addRecord(record3);
		collection.sortCollection();
		ArrayList<Record> topThreeRecord = new ArrayList<Record>();
		topThreeRecord.add(record3);
		topThreeRecord.add(record2);
		topThreeRecord.add(record1);
		assertEquals(topThreeRecord, collection.findClosestRecords(numberOfRecords));
	}
	
	/**
     * Test method for {@link asgn1Index.RecordCollection#isSorted()}.
     * Check that sorted flag is set after calling <code>sortCollection</code>.
     * @throws IndexException 
     */
    @Test
    public void sortedFlagIsSetAfterSortingCollection() throws IndexException {
        collection.sortCollection();
    	assertTrue(collection.isSorted());
    }
	
	/**
     * Test method for {@link asgn1Index.RecordCollection#isSorted()}.
     * Check that sorted flag is unset before one or more records are added to the collection
     * without calling <code>sortCollection</code>.
     * @throws IndexException 
     */
    @Test
    public void sortedFlagIsUnsetWhenCollectionIsEmpty() throws IndexException {
        assertFalse(collection.isSorted());
    }
	
	/** @author samuelbr
     * Test method for {@link asgn1Index.RecordCollection#isSorted()}.
     * Check that sorted flag is unset after more records are added after call to 
     * <code>sortCollection</code>.
     * @throws IndexException 
     */
    @Test
    public void collectionNotSortedAfterSortingThenAdding() throws IndexException {
    	Record record1 = new Record(TITLE_1, SIMILARITY);
    	collection.sortCollection();
        assertTrue(collection.isSorted());
        collection.addRecord(record1);
        assertFalse(collection.isSorted());
    } 
    
	/**
	 * Test method for {@link asgn1Index.RecordCollection#isSorted()}.
	 * Confirm that the <code>isSorted</code> method returns TRUE if the collection
	 * contains only one record.
	 * @throws IndexException
	 */
	@Test
	public void sortCollectionWithOneRecord() throws IndexException {
		Record record1 = new Record(TITLE_1, SIMILARITY);
		collection.addRecord(record1);
		collection.sortCollection();
		assertTrue(collection.isSorted());
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#isSorted()}.
	 * Confirm the the <code>isSorted</code> method returns FALSE if the collection 
	 * is not sorted in descending order after adding new records.
	 * @throws IndexException
	 */
    @Test
    public void collectionNotSorted() throws IndexException {
    	Record record1 = new Record(TITLE_1, SIMILARITY);
    	Record record2 = new Record(TITLE_2, SIMILARITY*2);
    	Record record3 = new Record(TITLE_3, SIMILARITY*3);
    	collection.sortCollection();
        assertTrue(collection.isSorted());
        collection.addRecord(record1);
        assertFalse(collection.isSorted());
        collection.addRecord(record2);
        assertFalse(collection.isSorted());
        collection.addRecord(record3);
        assertFalse(collection.isSorted());
    }
    
    /**
     * Test method for {@link asgn1Index.RecordCollection#isSorted()}.
	 * Confirm the the <code>isSorted</code> method returns TRUE if the collection 
	 * is sorted in descending order after adding new records
	 * @throws IndexException
	 */
    @Test
    public void collectionIsCorrectlySorted() throws IndexException {
    	Record record1 = new Record(TITLE_1, SIMILARITY);
    	Record record2 = new Record(TITLE_2, SIMILARITY*2);
    	Record record3 = new Record(TITLE_3, SIMILARITY*3);
    	collection.sortCollection();
        assertTrue(collection.isSorted());
        collection.addRecord(record1);
        collection.sortCollection();
        assertTrue(collection.isSorted());
        collection.addRecord(record2);
        collection.sortCollection();
        assertTrue(collection.isSorted());
        collection.addRecord(record3);
        collection.sortCollection();
        assertTrue(collection.isSorted());
    }
	
    /**
     * Test method for {@link asgn1Index.RecordCollection#isSorted()}.
     * Confirm that the <code>isSorted</code> method returns TRUE if all the records
     * in the collection have the same similarity value.
     * @throws IndexException
     */
    @Test
    public void sortCollectionWithSameSimilarityRecords() throws IndexException {
    	Record record1 = new Record(TITLE_1, SIMILARITY);
    	Record record2 = new Record(TITLE_2, SIMILARITY);
    	Record record3 = new Record(TITLE_3, SIMILARITY);
    	collection.addRecord(record1);
    	collection.addRecord(record2);
    	collection.addRecord(record3);
    	collection.sortCollection();
    	assertTrue(collection.isSorted());
    }
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#sortCollection()}.
	 * Confirm that the <code>sortCollection</code> method correctly sorts 
	 * the collection in descending order.
	 * @throws IndexException
	 */
	@Test
	public void collectionIsSorted() throws IndexException {
		Record record1 = new Record(TITLE_1, SIMILARITY);
		Record record2 = new Record(TITLE_2, SIMILARITY*2);
		Record record3 = new Record(TITLE_3, SIMILARITY*3);
		collection.addRecord(record1);
		collection.addRecord(record2);
		collection.addRecord(record3);
		collection.sortCollection();
		assertTrue(collection.isSorted());
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#sortCollection()}.
	 * Confirm that the collection is not sorted if the <code>sortCollection</code>
	 * method is not called.
	 * @throws IndexException
	 */
	@Test
	public void collectionIsNotSorted() throws IndexException {
		Record record1 = new Record(TITLE_1, SIMILARITY);
		Record record2 = new Record(TITLE_2, SIMILARITY*2);
		Record record3 = new Record(TITLE_3, SIMILARITY*3);
		collection.addRecord(record1);
		collection.addRecord(record2);
		collection.addRecord(record3);
		assertFalse(collection.isSorted());
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#sortCollection()}.
	 * Confirm that the <code>sortCollection</code> method returns an exception 
	 * if the collection is null.
	 * @throws IndexException
	 * @throws NullPointerException
	 */
	@SuppressWarnings("null")
	@Test (expected = NullPointerException.class)
	public void sortEmptyCollection() throws IndexException, NullPointerException {
		RecordCollection anotherCollection = null;
		anotherCollection.sortCollection();
	}
    
	/**
	 * Test method for {@link asgn1Index.RecordCollection#toString()}.
	 * Confirm that the <code>sortCollection</code> method correctly sorts 
	 * the collection in descending order, and the <code>toString</code> method 
	 * outputs correctly formatted string.
	 * @throws IndexException
	 */
	@Test
	public void SortedCollectionToString() throws IndexException {
		String expectedString = "[[[" + TITLE_3 + ":" + SIMILARITY*3 + "], "
								+ "[" + TITLE_2 + ":" + SIMILARITY*2 + "], "
								+ "[" + TITLE_1 + ":" + SIMILARITY + "]]]";
		Record record1 = new Record(TITLE_1, SIMILARITY);
		Record record2 = new Record(TITLE_2, SIMILARITY*2);
		Record record3 = new Record(TITLE_3, SIMILARITY*3);
		collection.addRecord(record1);
		collection.addRecord(record2);
		collection.addRecord(record3);
		collection.sortCollection();
		assertEquals(expectedString, collection.toString());
	}

	/**
	 * Test method for {@link asgn1Index.RecordCollection#toString()}.
	 * Confirm that the <code>toString</code> method correctly output correctly 
	 * formatted string.
	 * @throws IndexException
	 */
	@Test
	public void UnsortedCollectionToString() throws IndexException {
		String expectedString = "[[[" + TITLE_1 + ":" + SIMILARITY + "], "
				 				+ "[" + TITLE_2 + ":" + SIMILARITY*2 + "], "
				 				+ "[" + TITLE_3 + ":" + SIMILARITY*3 + "]]]";
		Record record1 = new Record(TITLE_1, SIMILARITY);
		Record record2 = new Record(TITLE_2, SIMILARITY*2);
		Record record3 = new Record(TITLE_3, SIMILARITY*3);
		collection.addRecord(record1);
		collection.addRecord(record2);
		collection.addRecord(record3);
		assertEquals(expectedString, collection.toString());
	}
	
	/**
	 * Test method for {@link asgn1Index.RecordCollection#toString()}.
	 * Confirm that the <code>toString</code> method returns correctly formatted string.
	 */
	@Test
	public void correctlyFormattedString() {
		assertEquals(EMPTY_STRING + collection, collection.toString());
	}

}
