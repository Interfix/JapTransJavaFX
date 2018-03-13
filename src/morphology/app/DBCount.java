package morphology.app;

public class DBCount {

	
// Klassenvariablen
	
	private int vCount;
	private int aCount;
	
	
// Konstruktoren
	
	/**
	 * DBCount ist das Objekt, das die Anzahl der Datenbankeinträge trägt.
	 */
	
	public DBCount(int[] entryCounts) {
		vCount = entryCounts[0];
		aCount = entryCounts[1];
	}
	
	
// Klassenmethoden
	
	public void setVCount(int vEntries) {
		vCount = vEntries;
	}
	
	public int getVCount() {		
		return vCount;
	}
	
	public void setACount(int aEntries){
		aCount = aEntries;
	}
	
	public int getACount() {
		return aCount;
	}

}
