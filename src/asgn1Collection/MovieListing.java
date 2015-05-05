/**
 * 
 */
package asgn1Collection;

import java.util.BitSet;
import java.util.Set;
import java.util.TreeSet;


/**
 * @author Thanat Chokwijitkul 9234900
 *
 */
public class MovieListing implements Listing {

	private String title;
	private int year;
	private Set<String> keywords;
	private BitSet keyVectors; /* This keyVectors will be initialised (set) by the generateKeyVectors method
							      in the MovieCollection class. */
	
	/**
	 * Constructor for the <code>MovieListing</code> class
	 * 
	 * @param title <code>String</code> containing movie title
	 * @param year <code>int</code> containing year in which the movie was made
	 * @throws ListingException if <code>isNull(title) OR isEmpty(title) OR year <=0</code>
	 */
	public MovieListing(String title, int year) throws ListingException {
		if (nullOrEmpty(title)) {
			throw new ListingException("String is null or empty");
		}
		if (year <= 0) {
			throw new ListingException("Invalid year, cannot be lower than or equal to 0");
		}
		this.title = title;
		this.year = year;
		this.keywords = new TreeSet<String>();
	}

	/* (non-Javadoc)
	 * @see asgn1Collection.Listing#addKeyword(java.lang.String)
	 */
	@Override
	public void addKeyword(String kw) throws ListingException {
		if (nullOrEmpty(kw)) {
			throw new ListingException("Keyword is null or empty");
		}
		keywords.add(kw);
	}

	/* (non-Javadoc)
	 * @see asgn1Collection.Listing#findSimilarity(asgn1Collection.Listing)
	 */
	@Override
	public int findSimilarity(Listing l) throws ListingException {
		int commonKeywords = 0;
		if (this.keyVectors == null) {
			throw new ListingException("keyVectors is null");
		}
		if (l == null) {
			throw new ListingException("Object is null");
		}
		BitSet originalBitSet = (BitSet) this.keyVectors.clone();
		this.keyVectors.and(l.getKeyVector());
		commonKeywords = this.keyVectors.cardinality();
		this.keyVectors = (BitSet) originalBitSet.clone();
		return commonKeywords;
	}

	/* (non-Javadoc)
	 * @see asgn1Collection.Listing#getKeyVector()
	 */
	@Override
	public BitSet getKeyVector() throws ListingException {
		if (this.keyVectors == null) {
			throw new ListingException("keyVectors is null");
		}
		return keyVectors;
	}

	/* (non-Javadoc)
	 * @see asgn1Collection.Listing#getKeywords()
	 */
	@Override
	public Set<String> getKeywords() {
		return keywords;
	}

	/* (non-Javadoc)
	 * @see asgn1Collection.Listing#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/* (non-Javadoc)
	 * @see asgn1Collection.Listing#getYear()
	 */
	@Override
	public int getYear() {
		return year;
	}

	/* (non-Javadoc)
	 * @see asgn1Collection.Listing#numKeywords()
	 */
	@Override
	public int numKeywords() {
		return keywords.size();
	}

	/* (non-Javadoc)
	 * @see asgn1Collection.Listing#setKeyVector(java.util.BitSet)
	 */
	@Override
	public void setKeyVector(BitSet bs) {
		keyVectors = (BitSet) bs.clone(); 
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.title + ":" + this.year + ":" + "Active keywords:" + this.numKeywords();
	}

	/* (non-Javadoc)
	 * @see asgn1Collection.Listing#writeKeyVector()
	 */
	@Override
	public String writeKeyVector() throws ListingException {
		if (this.keyVectors == null) {
			throw new ListingException("keyVectors is null");
		}
		return keyVectors.toString();
	}

	/* (non-Javadoc)
	 * @see asgn1Collection.Listing#writeKeywords()
	 */
	@Override
	public String writeKeywords() {
		String str=""; 
		int index=0;
		for (String kw : this.keywords) {
			str += kw +":"; 
			index++;
			if ((index % 10)==0) {
				str += "\n";
			}
		}
		return str;
	}
	
	/*
	 * Helper method to simplify tests for null or empty strings 
	 * 
	 * @param str <code>String</code> to be tested 
	 * @return <code>true</code> if <code>isNull(str) OR isEmpty(str)</code>
	 */
	private boolean nullOrEmpty(String str) {
		return (str==null)||(str.isEmpty());
	}

}
