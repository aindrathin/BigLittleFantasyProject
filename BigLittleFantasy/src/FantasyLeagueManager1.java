import java.util.*;
import java.io.*;

public class FantasyLeagueManager1 {
	private Map<String, Member> info;
	
	public static void main(String[] args) {
		//String[] names = "Becca : Jemimah".split(" : ");
		//System.out.println(Arrays.toString(names));
	}
	
	public void promptMatches() {
		
	}
	
	// @param 	data	file containing big : little pairings
	// creates a new FantasyLeagueManager with the given data
	public FantasyLeagueManager1(File data) throws FileNotFoundException {
		Scanner s = new Scanner(data);
		while(s.hasNextLine()) {
			String[] names = s.nextLine().split(" : ");
			String bigName = names[0];
			String littleName = names[1];
			
			Member big = new Member(bigName);
			Member little = new Member(littleName);
			
			big.setLittle(little);
			little.setBig(big);
			
			info.put(bigName, big);
			info.put(littleName,  little);
		}
		s.close();
	}
	
	
}
