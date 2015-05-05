/**
 * 
 */
package asgn1Tests;

import static org.junit.Assert.*;

import java.util.BitSet;

import org.junit.Before;
import org.junit.Test;

import asgn1Collection.ListingException;
import asgn1Collection.MovieListing;

/**
 * @author Thanat Chokwijitkul 9234900
 *
 */
public class MovieListingTests {

	MovieListing movie;
	final String TITLE_1 = "The Sound of Music";
	final String TITLE_2 = "Casablanca";
	final String EMPTY_STRING = "";
	final String KEYWORD_1 = "love";
	final String KEYWORD_2 = "austria";
	final int YEAR_1 = 1965;
	final int YEAR_2 = 1942;
	final int ZERO = 0;
	final int NEGATIVE_VALUE = -1;
	
	/**
	 * Create a MovieListing object before each test case 
	 * @throws ListingException
	 */
	@Before 
	public void setUp() throws ListingException {
		movie = new MovieListing(TITLE_1, YEAR_1);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#MovieListing(String, int)}.
	 * Confirm that the constructor throws an exception if given null
	 * for the title and 0 for the year.
	 * @throws ListingException
	 */
	@Test (expected = ListingException.class)
	public void noInput() throws ListingException {
		@SuppressWarnings("unused")
		MovieListing noInputMovie = new MovieListing(null, ZERO);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#MovieListing(String, int)}.
	 * Confirm that the constructor throws an exception if given no input data
	 * for the title and 0 for the year.
	 * @throws ListingException
	 */
	@Test (expected = ListingException.class)
	public void emptyTitleAndZeroYear() throws ListingException {
		@SuppressWarnings("unused")
		MovieListing noInputMovie = new MovieListing(EMPTY_STRING, ZERO);
	}

	/**
	 * Test method for {@link asgn1Collection.MovieListing#MovieListing(String, int)}.
	 * Confirm that the constructor throws an exception if given empty title.
	 * @throws ListingException
	 */
	@Test (expected = ListingException.class) 
	public void emptyTitle() throws ListingException {
		@SuppressWarnings("unused")
		MovieListing emptyTitleMovie = new MovieListing(EMPTY_STRING, YEAR_1);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#MovieListing(String, int)}.
	 * Confirm that the constructor throws an exception if given null title.
	 * @throws ListingException
	 */
	@Test (expected = ListingException.class) 
	public void nullTitle() throws ListingException {
		@SuppressWarnings("unused")
		MovieListing nullTitleMovie = new MovieListing(null, YEAR_1);
	}

	/**
	 * Test method for {@link asgn1Collection.MovieListing#MovieListing(String, int)}.
	 * Confirm that the constructor throws an exception if given 0 for the year.
	 * @throws ListingException
	 */
	@Test (expected = ListingException.class) 
	public void zeroYear() throws ListingException {
		@SuppressWarnings("unused")
		MovieListing zeroYearMovie = new MovieListing("TITLE_2", ZERO);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#MovieListing(String, int)}.
	 * Confirm that the constructor throws an exception if given negative integer 
	 * for the year.
	 * @throws ListingException
	 */
	@Test (expected = ListingException.class) 
	public void negativeYear() throws ListingException {
		@SuppressWarnings("unused")
		MovieListing negativeYearMovie = new MovieListing("TITLE_2", NEGATIVE_VALUE);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#addKeyword(String)}.
	 * Confirm that the <code>addKeyword</code> method throws an exception if given 
	 * null keyword.
	 * @throws ListingException
	 */
	@Test (expected = ListingException.class)
	public void addNullKeyword() throws ListingException {
		movie.addKeyword(null);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#addKeyword(String)}.
	 * Confirm that the <code>addKeyword</code> method throws an exception if given 
	 * empty keyword.
	 * @throws ListingException
	 */
	@Test (expected = ListingException.class)
	public void addEmptyKeyword() throws ListingException {
		movie.addKeyword(EMPTY_STRING);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#addKeyword(String)}.
	 * Confirm that valid keywords are added to the list after calling to
	 * <code>addKeyword</code>.
	 * @throws ListingException
	 */
	@Test
	public void addValidKeyword() throws ListingException {
		movie.addKeyword(KEYWORD_1);
		assertTrue(movie.getKeywords().contains(KEYWORD_1));
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#addKeyword(String)}.
	 * Confirm that the <code>addKeyword</code> method can add multiple identical 
	 * keywords to the list without throwing an exception.
	 * @throws ListingException
	 */
	@Test
	public void addMultipleSameKeyword() throws ListingException {
		movie.addKeyword(KEYWORD_1);
		movie.addKeyword(KEYWORD_1);
		movie.addKeyword(KEYWORD_1);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#findSimilarity(asgn1Collection.Listing)}.
	 * Confirm that the <code>findSimilarity</code> method throws an exception 
	 * if this keyVectors is null.
	 * @throws ListingException
	 */
	@Test (expected = ListingException.class) 
	public void thisKeyVectorsIsNullInFindSimilarity() throws ListingException {
		MovieListing anotherMovie = new MovieListing("TITLE_2" , YEAR_2);
		BitSet bs = new BitSet();
		bs.set(0);
		anotherMovie.setKeyVector(bs);
		movie.findSimilarity(anotherMovie);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#findSimilarity(asgn1Collection.Listing)}.
	 * Confirm that the <code>findSimilarity</code> method throws an exception 
	 * if another keyVectors is null.
	 * @throws ListingException
	 */
	@Test (expected = ListingException.class) 
	public void anotherKeyVectorsIsNullInFindSimilarity() throws ListingException {
		MovieListing anotherMovie = new MovieListing("TITLE_2" , YEAR_2);
		BitSet bs = new BitSet();
		bs.set(0);
		movie.setKeyVector(bs);
		movie.findSimilarity(anotherMovie);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#findSimilarity(asgn1Collection.Listing)}.
	 * Confirm that the <code>findSimilarity</code> method throws an exception 
	 * if keyVectors is null.
	 * @throws ListingException
	 */
	@Test (expected = ListingException.class) 
	public void bothNullKeyVectorsInFindSimilarity() throws ListingException {
		MovieListing anotherMovie = new MovieListing("TITLE_2" , YEAR_2);
		movie.findSimilarity(anotherMovie);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#findSimilarity(asgn1Collection.Listing)}.
	 * Confirm that the <code>findSimilarity</code> method throws an exception 
	 * if keyVectors is null.
	 * @throws ListingException
	 */
	@Test (expected = ListingException.class) 
	public void nullObjectInFindSimilarity() throws ListingException {
		MovieListing anotherMovie = null;
		movie.findSimilarity(anotherMovie);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#findSimilarity(asgn1Collection.Listing)}.
	 * Confirm that the <code>findSimilarity</code> method throws an exception 
	 * if both keyVectors are null.
	 * @throws ListingException
	 */
	@SuppressWarnings("null")
	@Test (expected = NullPointerException.class) 
	public void bothNullObjectsInFindSimilarity() throws NullPointerException, ListingException {
		MovieListing thisMovie = null;
		MovieListing anotherMovie = null;
		thisMovie.findSimilarity(anotherMovie);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#findSimilarity(asgn1Collection.Listing)}.
	 * Confirm that the <code>findSimilarity</code> method finds the correct number 
	 * of common keywords between movies based on keyVectors in case of 0 similarity.
	 * @throws ListingException
	 */
	@Test
	public void findNoSimilarity() throws ListingException {
		int similarity = 0;
		MovieListing anotherMovie = new MovieListing("TITLE_2" , YEAR_2);
		BitSet bs = new BitSet();
		bs.set(0);
		movie.setKeyVector(bs);
		bs.clear(0);
		bs.set(1);
		anotherMovie.setKeyVector(bs);
		assertEquals(similarity, movie.findSimilarity(anotherMovie));
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#findSimilarity(asgn1Collection.Listing)}.
	 * Confirm that the <code>findSimilarity</code> method finds the correct number 
	 * of common keywords between movies based on keyVectors.
	 * @throws ListingException
	 */
	@Test
	public void findCorrectSimilarity() throws ListingException {
		int similarity = 1;
		MovieListing anotherMovie = new MovieListing("TITLE_2" , YEAR_2);
		BitSet bs = new BitSet();
		bs.set(0);
		bs.set(1);
		bs.set(2);
		movie.setKeyVector(bs);
		bs.clear(1);
		bs.clear(2);
		anotherMovie.setKeyVector(bs);
		assertEquals(similarity, movie.findSimilarity(anotherMovie));
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#findSimilarity(asgn1Collection.Listing)}.
	 * Confirm that the value of this keyVectors remains the same after comparing it
	 * with another keyVector from another object. 
	 * @throws ListingException
	 */
	@Test
	public void thisKeyVectorUnchangedAfterFindingSimilarity() throws ListingException {
		MovieListing anotherMovie = new MovieListing("TITLE_2" , YEAR_2);
		BitSet bs = new BitSet();
		bs.set(0);
		bs.set(1);
		bs.set(2);
		movie.setKeyVector(bs);
		BitSet thisKeyVector = (BitSet) movie.getKeyVector().clone();
		bs.clear(2);
		anotherMovie.setKeyVector(bs);
		@SuppressWarnings("unused")
		int similarity = movie.findSimilarity(anotherMovie);
		assertEquals(thisKeyVector, movie.getKeyVector());
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#findSimilarity(asgn1Collection.Listing)}.
	 * Confirm that the value of another keyVectors remains the same after comparing it
	 * with this keyVector from another object. 
	 * @throws ListingException
	 */
	@Test
	public void anotherKeyVectorUnchangedAfterFindingSimilarity() throws ListingException {
		MovieListing anotherMovie = new MovieListing("TITLE_2" , YEAR_2);
		BitSet bs = new BitSet();
		bs.set(0);
		bs.set(1);
		bs.set(2);
		movie.setKeyVector(bs);
		bs.clear(2);
		anotherMovie.setKeyVector(bs);
		BitSet anotherKeyVector = (BitSet) anotherMovie.getKeyVector().clone();
		@SuppressWarnings("unused")
		int similarity = movie.findSimilarity(anotherMovie);
		assertEquals(anotherKeyVector, anotherMovie.getKeyVector());
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#findSimilarity(asgn1Collection.Listing)}.
	 * Confirm that the value of another keyVectors remains the same after comparing it
	 * with this keyVector from another object. 
	 * @throws ListingException
	 */
	@Test
	public void keyVectorsUnchangedAfterFindingSimilarity() throws ListingException {
		MovieListing anotherMovie1 = new MovieListing("TITLE_2" , YEAR_2);
		MovieListing anotherMovie2 = new MovieListing("TITLE_2" , YEAR_2);
		BitSet bs = new BitSet();
		bs.set(0);
		bs.set(1);
		bs.set(2);
		movie.setKeyVector(bs);
		BitSet thisKeyVector = (BitSet) movie.getKeyVector().clone();
		bs.clear(2);
		anotherMovie1.setKeyVector(bs);
		BitSet anotherKeyVector1 = (BitSet) anotherMovie1.getKeyVector().clone();
		bs.clear(1);
		anotherMovie2.setKeyVector(bs);
		BitSet anotherKeyVector2 = (BitSet) anotherMovie2.getKeyVector().clone();
		@SuppressWarnings("unused")
		int similarity1 = movie.findSimilarity(anotherMovie1);
		@SuppressWarnings("unused")
		int similarity2 = anotherMovie1.findSimilarity(anotherMovie2);
		@SuppressWarnings("unused")
		int similarity3 = anotherMovie2.findSimilarity(movie);
		assertEquals(thisKeyVector, movie.getKeyVector());
		assertEquals(anotherKeyVector1, anotherMovie1.getKeyVector());
		assertEquals(anotherKeyVector2, anotherMovie2.getKeyVector());
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#setKeyVector(BitSet)}.
	 * Confirm that the <code>setKeyVector</code> method throws an exception
	 * if given a null BitSet.
	 * @throws NullPointerException
	 */
	@Test (expected = NullPointerException.class)
	public void setNullKeyVectors() throws NullPointerException {
		BitSet bs = null;
		movie.setKeyVector(bs);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#setKeyVector(BitSet)}.
	 * Confirm that the <code>setKeyVector</code> method throws an exception
	 * if given a null value.
	 * @throws NullPointerException
	 */
	@Test (expected = NullPointerException.class)
	public void setNullValueAsKeyVectors() throws NullPointerException {
		movie.setKeyVector(null);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#setKeyVector(BitSet)}.
	 * Confirm that the <code>setKeyVector</code> method can accept an empty BitSet 
	 * @throws ListingException
	 */
	@Test
	public void setEmptyKeyVectors() throws ListingException {
		BitSet bs = new BitSet();
		movie.setKeyVector(bs);
		assertTrue(movie.getKeyVector().isEmpty());
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#setKeyVector(BitSet)}.
	 * Confirm that the <code>getKeyVector</code> method sets a valid BitSet (not empty) to
	 * this keyVectors.
	 * @throws ListingException
	 */
	@Test
	public void setValidKeyVectors() throws ListingException {
		BitSet bs = new BitSet();
		bs.set(0);
		bs.set(1);
		bs.set(3);
		movie.setKeyVector(bs);
		assertFalse(movie.getKeyVector().isEmpty());
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#getKeyVector()}.
	 * Confirm that the <code>getKeyVector</code> method throws an exception 
	 * if keyVectors is null.
	 * @throws ListingException
	 */
	@Test (expected = ListingException.class) 
	public void getNullKeyVectors() throws ListingException {
		movie.getKeyVector();
	}

	/**
	 * Test method for {@link asgn1Collection.MovieListing#getKeyVector()}.
	 * Confirm that the <code>getKeywords</code> method gets the correct set of keywords.
	 * @throws ListingException
	 */
	@Test
	public void getCorrectSetOfKeywords() throws ListingException {
		movie.addKeyword(KEYWORD_1);
		movie.addKeyword(KEYWORD_2);
		assertTrue(movie.getKeywords().contains(KEYWORD_1));
		assertTrue(movie.getKeywords().contains(KEYWORD_2));
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#getTitle()}.
	 * Confirm that the <code>NullPointerException</code> is thrown 
	 * if trying to get title from a null <code>MovieListing</code>.
	 * @throws NullPointerException
	 */
	@SuppressWarnings("null")
	@Test (expected = NullPointerException.class)
	public void getTitleFromNullListing() throws NullPointerException {
		MovieListing nullListing = null;
		nullListing.getTitle();
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#getTitle()}.
	 * Confirm that the <code>getTitle</code> method returns the correct title.
	 */
	@Test
	public void getCorrectTitle() {
		assertEquals(TITLE_1, movie.getTitle());
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#getTitle()}.
	 * Confirm that the <code>NullPointerException</code> is thrown 
	 * if trying to get year from a null <code>MovieListing</code>.
	 * @throws NullPointerException
	 */
	@SuppressWarnings("null")
	@Test (expected = NullPointerException.class)
	public void getYearFromNullListing() throws NullPointerException {
		MovieListing nullListing = null;
		nullListing.getYear();
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#getYear()}.
	 * Confirm that the <code>getYear</code> method returns the correct year.
	 */
	@Test
	public void getCorrectYear() {
		assertEquals(YEAR_1, movie.getYear());
	}

	/**
	 * Test method for {@link asgn1Collection.MovieListing#numKeywords()}.
	 * Confirm that the <code>numKeywords</code> method returns the correct 
	 * number of keywords. in this case, the number of keywords is 0.
	 * @throws ListingException
	 */
	@Test
	public void zeroNumKeywords() throws ListingException {
		assertEquals(ZERO, movie.numKeywords());
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#numKeywords()}.
	 * Confirm that the <code>numKeywords</code> method returns the correct 
	 * number of keywords.
	 * @throws ListingException
	 */
	@Test
	public void correctNumKeywords() throws ListingException {
		int numKeywords = 2;
		movie.addKeyword(KEYWORD_1);
		movie.addKeyword(KEYWORD_2);
		assertEquals(numKeywords, movie.numKeywords());
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#toString()}.
	 * Confirm that the <code>toString</code> method returns correctly formatted string
	 * if the movie has no keyword.
	 * @throws ListingException
	 */
	@Test
	public void stringWithNoKeyword() throws ListingException {
		String expectedString = TITLE_1 + ":" + YEAR_1 + ":" + 
				"Active keywords:"  + movie.numKeywords();
		assertEquals(expectedString, movie.toString());
	}

	/**
	 * Test method for {@link asgn1Collection.MovieListing#toString()}.
	 * Confirm that the <code>toString</code> method returns correctly formatted string.
	 * @throws ListingException
	 */
	@Test
	public void correctlyFormattedString() throws ListingException {
		String expectedString = "";
		movie.addKeyword(KEYWORD_1);
		movie.addKeyword(KEYWORD_2);
		expectedString = TITLE_1 + ":" + YEAR_1 + ":" + 
				"Active keywords:"  + movie.numKeywords();
		assertEquals(expectedString, movie.toString());
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#writeKeyVector()}.
	 * Confirm that the <code>writeKeyVector</code> method returns correctly formatted 
	 * string of BitSet.
	 * @throws ListingException
	 */
	@Test
	public void correctlyFormattedKeyVector() throws ListingException {
		String expectedString = "{0, 1, 2}";
		BitSet bs = new BitSet();
		bs.set(0);
		bs.set(1);
		bs.set(2);
		movie.setKeyVector(bs);
		assertEquals(expectedString , movie.writeKeyVector());
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#writeKeyVector()}.
	 * Confirm that the <code>writeKeyVector</code> method returns correctly formatted 
	 * string of BitSet.
	 * @throws ListingException
	 */
	@Test
	public void writeEmptyKeyVector() throws ListingException {
		String expectedString = "{}";
		BitSet bs = new BitSet();
		movie.setKeyVector(bs);
		assertEquals(expectedString , movie.writeKeyVector());
	}

	/**
	 * Test method for {@link asgn1Collection.MovieListing#writeKeyVector()}.
	 * Confirm that the <code>writeKeyVector</code> method throws an exception if
	 * keyVectors is null.
	 * @throws ListingException
	 */
	@Test (expected = ListingException.class)
	public void writeNullKeyVector() throws ListingException {
		movie.writeKeyVector();
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#writeKeywords()}.
	 * Confirm that the <code>writeKeywords</code> method returns correctly formatted
	 * string of keywords if the movie contains no keyword.
	 * @throws ListingException
	 */
	@Test
	public void writeEmptyKeywords() throws ListingException {
		String expectedString = "";
		assertEquals(expectedString, movie.writeKeywords());
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieListing#writeKeywords()}.
	 * Confirm that the <code>writeKeywords</code> method returns correctly formatted
	 * string of keywords.
	 * @throws ListingException
	 */
	@Test
	public void correctlyFormattedKeywords() throws ListingException {
		String expectedString = KEYWORD_2 + ":" + KEYWORD_1 + ":";
		movie.addKeyword(KEYWORD_1);
		movie.addKeyword(KEYWORD_2);
		assertEquals(expectedString, movie.writeKeywords());
	}
}
