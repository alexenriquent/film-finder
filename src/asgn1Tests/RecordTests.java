/**
 * 
 */
package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Index.IndexException;
import asgn1Index.Record;

/**
 * @author Thanat Chokwijitkul 9234900
 *
 */
public class RecordTests {

	Record record;
	final String TITLE_1 = "The Sound of Music";
	final String TITLE_2 = "Casablanca";
	final String EMPTY_STRING = "";
	final int SIMILARITY = 100;
	final int NEGATIVE_VALUE = -1;
	final int ZERO = 0;
	final int POSITIVE_VALUE = 1;
	
	/**
	 * Create a Record object before each test case 
	 * @throws IndexException
	 */
	@Before 
	public void setUp() throws IndexException {
		record = new Record(TITLE_1, SIMILARITY);
	}

	/**
	 * Test method for {@link asgn1Index.Record#Record(String, int)}.
	 * Confirm that the constructor throws an exception if given invalid inputs
	 * Null title and negative similarity value.
	 * @throws IndexException
	 */
	@Test (expected = IndexException.class)
	public void invalidRecordWithNullTitleAndNegativeSimilarity() throws IndexException {
		@SuppressWarnings("unused")
		Record invalidRecord = new Record(null, NEGATIVE_VALUE);
	}

	/**
	 * Test method for {@link asgn1Index.Record#Record(String, int)}.
	 * Confirm that the constructor throws an exception if given invalid inputs
	 * Empty title and negative similarity value.
	 * @throws IndexException
	 */
	@Test (expected = IndexException.class)
	public void invalidRecordWithEmptyTitleAndNegativeSimilarity() throws IndexException {
		@SuppressWarnings("unused")
		Record invalidRecord = new Record(EMPTY_STRING, NEGATIVE_VALUE);
	}

	/**
	 * Test method for {@link asgn1Index.Record#Record(String, int)}.
	 * Confirm that the constructor throws an exception if given null title.
	 * @throws IndexException
	 */
	@Test (expected = IndexException.class)
	public void nullTitle() throws IndexException {
		@SuppressWarnings("unused")
		Record nullTitleRecord = new Record(null, SIMILARITY);
	}

	/**
	 * Test method for {@link asgn1Index.Record#Record(String, int)}.
	 * Confirm that the constructor throws an exception if given empty title.
	 * @throws IndexException
	 */
	@Test (expected = IndexException.class)
	public void emptyTitle() throws IndexException {
		@SuppressWarnings("unused")
		Record emptyTitleRecord = new Record(EMPTY_STRING, SIMILARITY);
	}

	/**
	 * Test method for {@link asgn1Index.Record#Record(String, int)}.
	 * Confirm that the constructor throws an exception if given negative similarity.
	 * @throws IndexException
	 */
	@Test (expected = IndexException.class)
	public void negativeSimilarity() throws IndexException {
		@SuppressWarnings("unused")
		Record negSimilarityRecord = new Record(TITLE_1, NEGATIVE_VALUE);
	}
	
	/**
	 * Test method for {@link asgn1Index.Record#Record(String, int)}.
	 * Confirm that the constructor does not throws an exception if given zero similarity.
	 * @throws IndexException
	 */
	@Test
	public void zeroSimilarity() throws IndexException {
		@SuppressWarnings("unused")
		Record zeroSimilarityRecord = new Record(TITLE_1, ZERO);
	}

	/**
	 * Test method for {@link asgn1Index.Record#compareTo(Record)}.
	 * Confirm that the <code>compareTo</code> method throws an exception if 
	 * comparing similarity with a null record.
	 * @throws NullPointerException
	 */
	@Test (expected = NullPointerException.class)
	public void compareToNullRecord() throws NullPointerException {
		Record anotherRecord = null;
		record.compareTo(anotherRecord);
	}
	
	/**
	 * Test method for {@link asgn1Index.Record#compareTo(Record)}.
	 * Confirm that the <code>compareTo</code> method throws an exception if 
	 * comparing a null record with another valid record.
	 * @throws NullPointerException
	 * @throws IndexException 
	 */
	@Test (expected = NullPointerException.class)
	public void compareToARecordWithNullRecord() throws NullPointerException, IndexException {
		record = null;
		Record anotherRecord = new Record(TITLE_2, SIMILARITY);
		record.compareTo(anotherRecord);
	}
	
	/**
	 * Test method for {@link asgn1Index.Record#compareTo(Record)}.
	 * Confirm that the <code>compareTo</code> method throws an exception if 
	 * comparing a null record with another null record.
	 * @throws NullPointerException
	 * @throws IndexException 
	 */
	@Test (expected = NullPointerException.class)
	public void compareBothNullRecords() throws NullPointerException {
		record = null;
		Record anotherRecord = null;
		record.compareTo(anotherRecord);
	}

	/**
	 * Test method for {@link asgn1Index.Record#compareTo(Record)}.
	 * Confirm that the <code>compareTo</code> method returns a negative integer
	 * if the current record has lower similarity compared to another record.
	 * @throws IndexException
	 */
	@Test
	public void lowerSimilarity() throws IndexException {
		Record anotherRecord = new Record(TITLE_2, SIMILARITY+1);
		assertEquals(NEGATIVE_VALUE, record.compareTo(anotherRecord));
	}
	
	/**
	 * Test method for {@link asgn1Index.Record#compareTo(Record)}.
	 * Confirm that the <code>compareTo</code> method returns zero if the 
	 * current record has equal similarity compared to another record.
	 * @throws IndexException
	 */
	@Test
	public void equalSimilarity() throws IndexException {
		Record anotherRecord = new Record(TITLE_2, SIMILARITY);
		assertEquals(ZERO, record.compareTo(anotherRecord));
	}
	
	/**
	 * Test method for {@link asgn1Index.Record#compareTo(Record)}.
	 * Confirm that the <code>compareTo</code> method returns a positive 
	 * integer if the current record has higher similarity compared to 
	 * another record.
	 * @throws IndexException
	 */
	@Test
	public void higherSimilarity() throws IndexException {
		Record anotherRecord = new Record(TITLE_2, SIMILARITY-1);
		assertEquals(POSITIVE_VALUE, record.compareTo(anotherRecord));
	}
	
	/**
	 * Test method for {@link asgn1Index.Record#compareTo(Record)}.
	 * Confirm that the <code>compareTo</code> method returns zero
	 * if the similarities of both records are zero.
	 * @throws IndexException
	 */
	@Test
	public void compareZeroSimilarity() throws IndexException {
		Record thisRecord = new Record(TITLE_1, ZERO);
		Record anotherRecord = new Record(TITLE_2, ZERO);
		assertEquals(ZERO, thisRecord.compareTo(anotherRecord));
	}
	
	/**
	 * Test method for {@link asgn1Index.Record#compareTo(Record)}.
	 * Confirm that the <code>compareTo</code> method returns a correct
	 * integer when comparing multiple records.
	 * If record1 > record2 and record2 > record3, then record1 > record3.
	 * @throws IndexException
	 */
	@Test
	public void transitiveComparisonOfSimilarities() throws IndexException {
		Record anotherRecord1 = new Record(TITLE_2, SIMILARITY-1);
		Record anotherRecord2 = new Record(TITLE_2, SIMILARITY-2);
		assertEquals(POSITIVE_VALUE, record.compareTo(anotherRecord1));
		assertEquals(POSITIVE_VALUE, anotherRecord1.compareTo(anotherRecord2));
		assertEquals(POSITIVE_VALUE, record.compareTo(anotherRecord2));
	}
	
	/**
	 * Test method for {@link asgn1Index.Record#compareTo(Record)}.
	 * Confirm that the <code>compareTo</code> method returns a correct
	 * integer when comparing multiple records.
	 * If record1 < record2 and record2 < record3, then record1 < record3.
	 * @throws IndexException
	 */
	@Test
	public void transitiveComparisonOfSimilaritiesInReverse() throws IndexException {
		Record anotherRecord1 = new Record(TITLE_2, SIMILARITY+1);
		Record anotherRecord2 = new Record(TITLE_2, SIMILARITY+2);
		assertEquals(NEGATIVE_VALUE, record.compareTo(anotherRecord1));
		assertEquals(NEGATIVE_VALUE, anotherRecord1.compareTo(anotherRecord2));
		assertEquals(NEGATIVE_VALUE, record.compareTo(anotherRecord2));
	}
	
	/**
	 * Test method for {@link asgn1Index.Record#compareTo(Record)}.
	 * Confirm that the <code>compareTo</code> method returns a correct
	 * integer when comparing multiple records.
	 * If record1 = record2 and record2 = record3, then record1 = record3.
	 * @throws IndexException
	 */
	@Test
	public void transitiveComparisonOfEqualSimilarities() throws IndexException {
		Record anotherRecord1 = new Record(TITLE_2, SIMILARITY);
		Record anotherRecord2 = new Record(TITLE_2, SIMILARITY);
		assertEquals(ZERO, record.compareTo(anotherRecord1));
		assertEquals(ZERO, anotherRecord1.compareTo(anotherRecord2));
		assertEquals(ZERO, record.compareTo(anotherRecord2));
	}
	
	/**
	 * Test method for {@link asgn1Index.Record#getSimilarity()}.
	 * Confirm that the <code>NullPointerException</code> exception is thrown
	 * if trying to get similarity value from a null record.
	 */
	@SuppressWarnings("null")
	@Test (expected = NullPointerException.class)
	public void getSimilarityFromNullRecord() {
		Record anotherRecord = null;
		anotherRecord.getSimilarity();
	}
	
	/**
	 * Test method for {@link asgn1Index.Record#getSimilarity()}.
	 * Testing the similarity getter method.
	 */
	@Test
	public void getCorrectSimilarity() {
		assertEquals(SIMILARITY, record.getSimilarity());
	}
	
	/**
	 * Test method for {@link asgn1Index.Record#getTitle()}.
	 * Confirm that the <code>NullPointerException</code> exception is thrown
	 * if trying to get title from a null record.
	 */
	@SuppressWarnings("null")
	@Test (expected = NullPointerException.class)
	public void getTitleFromNullRecord() {
		Record anotherRecord = null;
		anotherRecord.getTitle();
	}

	/**
	 * Test method for {@link asgn1Index.Record#getTitle()}.
	 * Testing the title getter method.
	 */
	@Test
	public void getCorrectTitle() {
		assertEquals(TITLE_1, record.getTitle());
	}
	
	/**
	 * Test method for {@link asgn1Index.Record#toString()}.
	 * Confirm that the <code>toString</code> method returns correctly. 
	 * formatted string
	 */
	@Test
	public void correctlyFormattedString() {
		String expectedString = "["+ TITLE_1 + ":"+ SIMILARITY + "]";
		assertEquals(expectedString, record.toString());
	}

}
