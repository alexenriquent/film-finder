/**
 * 
 * This file is part of the FilmFinder Project, written as 
 * part of the assessment for CAB302, Semester 1, 2015. 
 *
 * FilmFinder
 * asgn1Tests 
 * 04/04/2015
 * 
 */
package asgn1Tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import asgn1Collection.Collection;
import asgn1Collection.ListingException;
import asgn1Collection.MovieCollection;
import asgn1Index.Index;
import asgn1Index.IndexException;
import asgn1Index.Record;

/**
 * @author hogan
 *
 */
public class IndexTests {
	private Index index; 
	private Collection mc; 
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mc = new MovieCollection(); 
		mc.getCollectionFromFile("testdata//indexTest.txt");
		mc.generateKeyVectors();
		index = new Index(mc);
		index.createIndex();
	}

	/**
	 * Test method for {@link asgn1Index.Index#Index(asgn1Collection.Collection)}.
	 * No other constructor test as no accessors to get state. 
	 */
	@SuppressWarnings("unused")
	@Test(expected=IndexException.class)
	public void constructorNullCollection() throws IndexException {
		Index nullIndex = new Index(null);
	}

	/**
	 * Test method for {@link asgn1Index.Index#createIndex()}.
	 * Don't test IndexException - propagated from asgn1Index.Record so tested there
	 * Other testing via tests of queryListing()
	 */
	@Test(expected=ListingException.class)
	public void createIndexNoKeyVectors() throws ListingException, IndexException, FileNotFoundException, IOException {
		MovieCollection failMC = new MovieCollection(); 
		failMC.getCollectionFromFile("testdata//keyTest.txt");
		Index failIndex = new Index(failMC); 
		failIndex.createIndex();
	}
	
	/**
	 * Test method for {@link asgn1Index.Index#queryListing(java.lang.String, int)}.
	 */
	@Test(expected=ListingException.class)
	public void queryListingNoIndex() throws IndexException, FileNotFoundException, IOException, ListingException {
		MovieCollection failMC = new MovieCollection(); 
		failMC.getCollectionFromFile("testdata//keyTest.txt");
		Index failIndex = new Index(failMC); 
		failIndex.createIndex();
	}
	
	//Tests are based on a modified version of the keyTest file. In addition to the common 
	//words introduced there, we have here added two fake movies - The Other Chariots of Fire - 
	//which has exactly the same keywords as Chariots of Fire - and Zorro and the Chariots of 
	//Fire - which shares many. So, 8 films in total. 
	
	/**
	 * Test method for {@link asgn1Index.Index#queryListing(java.lang.String, int)}.
	 */
	@Test
	public void queryListingNoResultsRequested() throws IndexException {
		List<Record> res = index.queryListing("CHARIOTS OF FIRE",0);
		assertTrue(res.size()==0);
	}
	
	/**
	 * Test method for {@link asgn1Index.Index#queryListing(java.lang.String, int)}.
	 * @throws IndexException 
	 */
	@Test
	public void queryListingAllResultsRequested() throws IndexException {
		List<Record> res = index.queryListing("CHARIOTS OF FIRE",8);
		assertTrue(res.size()==8);
	}
	
	/**
	 * Test method for {@link asgn1Index.Index#queryListing(java.lang.String, int)}.
	 * Top two entries should have the same similarity score
	 */
	@Test
	public void queryListingChariotsTopTwoEqual() throws IndexException {
		List<Record> res = index.queryListing("CHARIOTS OF FIRE",2);
		assertTrue(res.get(0).getSimilarity()==res.get(1).getSimilarity());
	}
	
	/**
	 * Test method for {@link asgn1Index.Index#queryListing(java.lang.String, int)}.
	 * The Court Jester shares no keywords
	 */
	@Test
	public void queryListingCourtJesterAllOthersZero() throws IndexException {
		List<Record> res = index.queryListing("THE COURT JESTER",8);
		assertTrue(res.get(0).getSimilarity()==2);
		int count=0; 
		for (Record rec : res) {
			if (count >0) {
			   assertTrue(rec.getSimilarity()==0);
			}
			count++;
		}	
	}
	
	/**
	 * Test method for {@link asgn1Index.Index#queryListing(java.lang.String, int)}.
	 * Full test of The King's Speech
	 */
	@Test
	public void queryListingKingsSpeechFullList() throws IndexException {
		List<Record> res = index.queryListing("THE KING'S SPEECH",8);
		Record rec = res.get(0); 
		assertTrue(rec.getTitle().equalsIgnoreCase("The King's Speech"));
		assertTrue(rec.getSimilarity()==4);
		//SoM - nazi; 1930s
		rec = res.get(1); 
		assertTrue(rec.getTitle().equalsIgnoreCase("The Sound of Music"));
		assertTrue(rec.getSimilarity()==2);
		//Title order not specified, probably alphabetic: nazi in common
		rec = res.get(2); 
		assertTrue(rec.getSimilarity()==1);
		rec = res.get(3); 
		assertTrue(rec.getSimilarity()==1);
		rec = res.get(4); 
		assertTrue(rec.getSimilarity()==1);
		rec = res.get(5); 
		assertTrue(rec.getSimilarity()==1);
		rec = res.get(6); 
		assertTrue(rec.getSimilarity()==1);
		//The Court Jester - None in common
		rec = res.get(7); 
		assertTrue(rec.getTitle().equalsIgnoreCase("The Court Jester"));
		assertTrue(rec.getSimilarity()==0);
	}
}
