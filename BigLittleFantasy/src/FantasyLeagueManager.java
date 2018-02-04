import java.util.*;

public class FantasyLeagueManager {
	private Map<String, String> bigToLittle;
	private List<String> littles;
	
	public FantasyLeagueManager() {
		bigToLittle = new TreeMap<>();
		littles = new ArrayList<>();
	}
	
	public void addBig(String name) {
		bigToLittle.put(name, null);
	}
	
	public void addLittle(String name) {
		littles.add(name);
		Collections.sort(littles);
	}
	
	public void addMatch(String bigName, String littleName) {
		bigToLittle.put(bigName,  littleName);
	}
	
	public Set<String> getBigs() {
		return bigToLittle.keySet();
	}
	
	public List<String> getLittles() {
		List<String> copy = new ArrayList<>();
		for(String s : littles) {
			copy.add(s);
		}
		return copy;
	}
	
	public void printBigs() {
		System.out.print(bigToLittle.size() + " bigs: ");
		System.out.println(bigToLittle.keySet());
	}
	
	public void printLittles() {
		System.out.print(littles.size() + " littles: ");
		System.out.println(littles);
	}
	
	public void printMatches() {
		System.out.println(bigToLittle);
	}
	
	public String getLittle(String big) {
		return bigToLittle.get(big);
	}
}
