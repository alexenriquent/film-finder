/**
 * 
 */
package asgn1Index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Thanat Chokwijitkul 9234900
 *
 */
public class RecordCollection extends AbstractRecordCollection {

	/**
	 * Constructor for the <code>RecordCollection</code> class
	 */
	public RecordCollection() {
		records = new ArrayList<Record>();
		sorted = false;
	}

	/* (non-Javadoc)
	 * @see asgn1Index.AbstractRecordCollection#addRecord(asgn1Index.Record)
	 */
	@Override
	public void addRecord(Record r) throws IndexException {
		if (r == null) {
			throw new IndexException("Record is null");
		}
		records.add(r);
		sorted = false;
	}

	/* (non-Javadoc)
	 * @see asgn1Index.AbstractRecordCollection#findClosestRecord()
	 */
	@Override
	public AbstractRecord findClosestRecord() throws IndexException {
		int firstRecord = 0;
		if (!this.isSorted()) {
			throw new IndexException("Collection is unsorted");
		}
		if (records.size() == 0) {
			throw new IndexException("Collection is empty");
		}
		return records.get(firstRecord);
	}

	/* (non-Javadoc)
	 * @see asgn1Index.AbstractRecordCollection#findClosestRecords(int)
	 */
	@Override
	public List<Record> findClosestRecords(int n) throws IndexException {
		if (!this.isSorted()) {
			throw new IndexException("Collection is unsorted");
		}
		if (n > records.size()) {
			throw new IndexException("n is greater than the size of the collection");
		}
		if (n < 0) {
			throw new IndexException("n is less than 0");
		}
		ArrayList<Record> topNRecords = new ArrayList<Record>(n);
		for (int i = 0; i < n; i++) {
			topNRecords.add(records.get(i));
		}
		return topNRecords;
	}

	/* (non-Javadoc)
	 * @see asgn1Index.AbstractRecordCollection#isSorted()
	 */
	@Override
	public boolean isSorted() {
		return sorted;
	}

	/* (non-Javadoc)
	 * @see asgn1Index.AbstractRecordCollection#sortCollection()
	 */
	@Override
	public void sortCollection() {
		Collections.sort(records);
		Collections.reverse(records);
		if (collectionIsSorted()) {
			sorted = true;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + records + "]";
	}
	
	/* (non-Javadoc)
	 * Helper method to test if the collection is sorted is descending order
	 * @return <code>true</code> if the collection is sorted
	 * @return <code>false</code> if the collection is not sorted
	 */
	private boolean collectionIsSorted() {
		for (int i = 1; i < records.size(); i++) {
			if (records.get(i).getSimilarity() > records.get(i-1).getSimilarity()) {
				return false;
			}
		}
		return true;
	}
	
}
