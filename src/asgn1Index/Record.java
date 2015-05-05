/**
 * 
 */
package asgn1Index;

/**
 * @author Thanat Chokwijitkul 9234900
 *
 */
public class Record extends AbstractRecord implements Comparable<Record> {
	
	/**
	 * Constructor for the <code>Record</code> class
	 * 
	 * @param title <code>String</code> containing title of listing
	 * @param similarity <code>int</code> containing similarity between listings
	 * @throws IndexException if <code>isNull(title) OR isEmpty(title) OR similarity < 0</code>
	 */
	public Record(String title, int similarity) throws IndexException {
		if (nullOrEmpty(title)) {
			throw new IndexException("Title is null or empty");
		}
		if (similarity < 0) {
			throw new IndexException("Invalid similarity value, cannot be lower than 0");
		}
		this.title = title;
		this.similarity = similarity;
	}
	
	/* (non-Javadoc)
	 * @see <code>compareTo</code> in interface <code>java.lang.Comparable<Record></code>
	 */
	@Override
	public int compareTo(Record o) {
		if (this == null ||o == null) {
			throw new NullPointerException("The specified Record object is null");
		} 
		if (o != (Record)o) {
			throw new ClassCastException("The specified Record object's type prevents itself from being compared");
		}
		return Integer.compare(this.similarity, o.similarity);
	}

	/* (non-Javadoc)
	 * @see asgn1Index.AbstractRecord#getSimilarity()
	 */
	@Override
	public int getSimilarity() {
		return similarity;
	}

	/* (non-Javadoc)
	 * @see asgn1Index.AbstractRecord#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "["+this.title+":"+this.similarity+ "]";
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
