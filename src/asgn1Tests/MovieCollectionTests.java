/**
 * 
 * This file is part of the FilmFinder Project, written as 
 * part of the assessment for CAB302, Semester 1, 2015. 
 *
 * FilmFinder
 * asgn1Tests 
 * 03/04/2015
 * 
 */
package asgn1Tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.BitSet;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import asgn1Collection.Listing;
import asgn1Collection.ListingException;
import asgn1Collection.MovieCollection;

/**
 * @author hogan
 *
 */
public class MovieCollectionTests {
	private MovieCollection mc;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mc = new MovieCollection();
	}

	/**
	 * Test method for {@link asgn1Collection.MovieCollection#MovieCollection()}.
	 */
	@Test
	public void constructorNoKeyVectors() {
		assertFalse("KeyVectors not yet available",mc.hasKeyVectors());
	}

	/**
	 * Test method for {@link asgn1Collection.MovieCollection#MovieCollection()}.
	 */
	@Test
	public void constructorNoKeyWords() {
		assertTrue(mc.numKeywords()==0);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieCollection#MovieCollection()}.
	 */
	@Test
	public void constructorNoMovies() {
		assertTrue(mc.numListings()==0);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieCollection#generateKeyVectors()}.
	 * @throws ListingException 
	 * tests for empty and null keywords and listings - can't split test cases
	 */
	@Test(expected=ListingException.class)
	public void generateKeyVectorsNoListingsKeywords() throws ListingException {
		mc.generateKeyVectors();
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieCollection#generateKeyVectors()}.
	 * See the getCollectionFromFileKeywords test below for the keyword listing
	 */
	@Test
	public void generateKeyVectorsCommonKeywords() throws ListingException, FileNotFoundException, IOException {
		mc.getCollectionFromFile("testdata//keyTest.txt");
		mc.generateKeyVectors();
		Map<String,Listing> listing = mc.getListing();
		BitSet test = new BitSet(mc.numKeywords());
		//Casablanca: {4,6,9,10,13,16}
		test.set(4); test.set(6); test.set(9); test.set(10); test.set(13); test.set(16);
		test.and(listing.get("CASABLANCA").getKeyVector());
		assertTrue(test.cardinality()==6);
		test.clear();
		//COF: {1,2,6,12}
		test.set(1); test.set(2); test.set(6); test.set(12); 
		test.and(listing.get("CHARIOTS OF FIRE").getKeyVector());
		assertTrue(test.cardinality()==4);
		test.clear();
		//Robin Hood: {6,7,8,14}
		test.set(6); test.set(7); test.set(8); test.set(14); 
		test.and(listing.get("THE ADVENTURES OF ROBIN HOOD").getKeyVector());
		assertTrue(test.cardinality()==4);
		test.clear();
		//King's Speech: {3,5,6,15}
		test.set(3); test.set(5); test.set(6); test.set(15);
		test.and(listing.get("THE KING'S SPEECH").getKeyVector());
		assertTrue(test.cardinality()==4);
		test.clear();
		//Court Jester: {0,11}
		test.set(0); test.set(11); 
		test.and(listing.get("THE COURT JESTER").getKeyVector());
		assertTrue(test.cardinality()==2);
		test.clear();
		//Sound of Music: {3,6}
		test.set(3); test.set(6); 
		test.and(listing.get("THE SOUND OF MUSIC").getKeyVector());
		assertTrue(test.cardinality()==2);	
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieCollection#getCollectionFromFile()}.
	 */
	@Test
	public void getCollectionFromFileNoParam() {
		assertTrue("Tested using testGetCollectionFromFile(String)",true);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieCollection#getCollectionFromFile(java.lang.String)}.
	 */
	@Test(expected=ListingException.class)
	public void getCollectionFromFileNullKeywordInFile() throws FileNotFoundException, IOException, ListingException {
		mc.getCollectionFromFile("testdata//nullKeywordInLine.txt");
	}

	/**
	 * Test method for {@link asgn1Collection.MovieCollection#getCollectionFromFile(java.lang.String)}.
	 */
	@Test(expected=ListingException.class)
	public void getCollectionFromFileNullTitleInFile() throws FileNotFoundException, IOException, ListingException {
		mc.getCollectionFromFile("testdata//nullTitleInLine.txt");
	}

	/**
	 * Test method for {@link asgn1Collection.MovieCollection#getCollectionFromFile(java.lang.String)}.
	 * Checks that the number of keywords is correct for each movie 
	 */
	@Test
	public void getCollectionFromFileNumKeywords() throws FileNotFoundException, IOException, ListingException {
		mc.getCollectionFromFile("testdata//basicTest18.txt");
		Map<String,Listing> listing = mc.getListing(); 
		assertTrue(listing.get("CASABLANCA").numKeywords()==5);
		assertTrue(listing.get("CHARIOTS OF FIRE").numKeywords()==3);
		assertTrue(listing.get("THE ADVENTURES OF ROBIN HOOD").numKeywords()==4);
		assertTrue(listing.get("THE KING'S SPEECH").numKeywords()==3);
		assertTrue(listing.get("THE COURT JESTER").numKeywords()==2);
		assertTrue(listing.get("THE SOUND OF MUSIC").numKeywords()==1);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieCollection#getCollectionFromFile(java.lang.String)}.
	 */
	@Test
	public void getCollectionFromFileNumListings() throws FileNotFoundException, IOException, ListingException {
		mc.getCollectionFromFile("testdata//basicTest18.txt");
		assertTrue(mc.numListings()==6);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieCollection#getCollectionFromFile(java.lang.String)}.
	 * Test has 22 keyword occurrences; 17 unique. Multiple occurrences of nazi and 1930s
	 */
	@Test
	public void getCollectionFromFileKeyWords() throws FileNotFoundException, IOException, ListingException {
		mc.getCollectionFromFile("testdata//keyTest.txt");
		assertTrue(mc.numKeywords()==17);
		String compare ="|0:12th-century:1:1910s:2:1920s:3:1930s:4:1940s:"
				+ "5:father-daughter-relationship:6:nazi:7:tough-guy:8:trapdoor:"
				+ "9:underground:|\n|10:vichy:11:whistle:12:winning:"
				+ "13:world-war-two-in-africa:14:year-1191:15:year-1939:16:year-1941:|";
		assertTrue(compare.equalsIgnoreCase(mc.writeKeywords()));
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieCollection#getListing()}.
	 */
	@Test
	public void getListing() {
		assertTrue("Simple accessor; not tested",true);
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieCollection#getListings(boolean, boolean)}.
	 * Just test ListingException - otherwise a toString-like method 
	 */
	@Test(expected=ListingException.class)
	public void getListingsNoMovies() throws ListingException {
		mc.getListings(true, true);
	}

	/**
	 * Test method for {@link asgn1Collection.MovieCollection#hasKeyVectors()}.
	 */
	@Test
	public void hasKeyVectorsFalse() {
		assertFalse(mc.hasKeyVectors());
	}

	/**
	 * Test method for {@link asgn1Collection.MovieCollection#hasKeyVectors()}.
	 */
	@Test
	public void hasKeyVectorsFalseAfterParsingFile() throws FileNotFoundException, IOException, ListingException {
		mc.getCollectionFromFile("testdata//basicTest18.txt");
		assertFalse(mc.hasKeyVectors());
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieCollection#hasKeyVectors()}.
	 */
	@Test
	public void hasKeyVectorsTrueAfterParsingFile() throws FileNotFoundException, IOException, ListingException {
		mc.getCollectionFromFile("testdata//basicTest18.txt");
		mc.generateKeyVectors();
		assertTrue(mc.hasKeyVectors());
	}
	
	/**
	 * Test method for {@link asgn1Collection.MovieCollection#numKeywords()}.
	 * Simple count, but here used as a cross check test
	 */
	@Test
	public void numKeywordsAgreesWithMovies() throws FileNotFoundException, IOException, ListingException {
		mc.getCollectionFromFile("testdata//basicTest18.txt");
		final int IN_COMMON=1; 
		int count = 0; 
		Map<String,Listing> listing = mc.getListing(); 
		for (Map.Entry<String, Listing> entry : listing.entrySet()) {
			Listing mov = entry.getValue(); 
			count+=mov.numKeywords();
		}
		assertTrue(mc.numKeywords()==count-IN_COMMON);
	}

	/**
	 * Test method for {@link asgn1Collection.MovieCollection#numListings()}.
	 */
	@Test
	public void numListingsBasic() throws FileNotFoundException, IOException, ListingException {
		mc.getCollectionFromFile("testdata//basicTest18.txt");
		assertTrue(mc.numListings()==6);
	}

	/**
	 * Test method for {@link asgn1Collection.MovieCollection#numListings()}.
	 */
	@Test
	public void numListingsEmpty() {
		assertTrue(mc.numListings()==0);
	}

	/**
	 * Test method for {@link asgn1Collection.MovieCollection#toString()}.
	 */
	@Test
	public void testToString() {
		assertTrue("toString() method; Not tested",true);
	}

	/**
	 * Test method for {@link asgn1Collection.MovieCollection#writeKeywords()}.
	 */
	@Test
	public void writeKeywords() {
		assertTrue("toString-like method; Not tested",true);
	}

}
