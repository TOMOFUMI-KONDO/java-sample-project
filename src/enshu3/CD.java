package enshu3;

public class CD {
	private String[] trackName;
	
	public CD() {
		int trackLength = 5;
		this.trackName= new String[trackLength];

		for (int i = 0; i < trackLength; i++) {
			this.trackName[i] = "song_" + String.valueOf(i);
		}
	}
	
	public void printTrack(int n) {
		String s = "track_" + String.valueOf(n) + " is " + this.trackName[n];
		System.out.println(s);
	}
	
	public void printAllTrack() {
		for (int i = 0; i < this.trackName.length; i++) {
			String s = "track_" + String.valueOf(i) + " is " + this.trackName[i];
			System.out.println(s);
		}
	}

	public static void main(String[] args) {
		CD cd = new CD();
		cd.printTrack(0);
		cd.printTrack(3);
		cd.printAllTrack();
	}

}
