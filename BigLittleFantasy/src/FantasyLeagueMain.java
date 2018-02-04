// Handles all of the user and file input

import java.io.*;
import java.util.*;

public class FantasyLeagueMain {
	
	private static FantasyLeagueManager league;
	private static Map<Integer, List<String>> scores;

	public static void main(String[] args) throws FileNotFoundException {
		league = new FantasyLeagueManager();
		scores = new TreeMap<>();
		Scanner console = new Scanner(System.in);
		
		boolean moderator = isModerator(console);
		if (moderator) {
			boolean enter = enterOrLoad(console);
			if(enter) {
				load(console);
				promptMatches(console, "Matches file");
			} 
			loadMatches(console);
			checkScores(console);
			printScores();
		} else {
			load(console);
			promptMatches(console, "Participant");
		}
//		System.out.println();
//		league.printBigs();
//		league.printLittles();
	}
	
	public static void printScores() {
		System.out.println("Scores: ");
		System.out.println(scores);
	}
	
	public static void checkScores(Scanner console) throws FileNotFoundException {
		System.out.println("Now you will enter in the participants' guess files to check their scores.");
		String again = "y";
		do {
			oneScore(console);
			System.out.println();
			System.out.print("Do you have another score to check? (y/n) ");
			again = console.nextLine();
		} while(again.startsWith("y"));
	}
	
	public static void oneScore(Scanner console) throws FileNotFoundException {
		Scanner input = new Scanner(promptFile("checking score", console));
		String name = input.nextLine().split(" : ")[1];
		int score = 0;
		while(input.hasNextLine()) {
			String[] match = input.nextLine().split(" : ");
			String big = match[0];
			String little = match[1];
			if(league.getLittle(big).equalsIgnoreCase(little)) {
				score++;
				System.out.println("Correct match! " + big + " & " + little);
			}
		}
		if(!scores.containsKey(score)) {
			List<String> names = new ArrayList<>();
			scores.put(score, names);
		}
		scores.get(score).add(name);
	}
	
	public static void loadMatches(Scanner console) throws FileNotFoundException {
		Scanner matches = new Scanner(promptFile("matches", console));
		System.out.println();
		while(matches.hasNextLine()) {
			String[] names = matches.nextLine().split(" : ");
			String bigName = names[0];
			String littleName = names[1];
			
			league.addMatch(bigName, littleName);
		}
		matches.close();
	}
	
	public static boolean enterOrLoad(Scanner console) {
		System.out.print("Would you like to (1) enter matches, or have you (2) already created a file? ");
		boolean answer = Integer.parseInt(console.nextLine()) == 1;
		System.out.println();
		return answer;
	}
	
	public static boolean isModerator(Scanner console) {
		System.out.println("Welcome to Big Little Fantasy League!!!");
		System.out.println("This program will help manage fantasy league scores.");
		System.out.print("Are you the (1) moderator, or (2) a participant? ");
		boolean answer = Integer.parseInt(console.nextLine()) == 1;
		System.out.println();
		return answer;
	}
	
	public static void promptMatches(Scanner console, String user) throws FileNotFoundException{
		boolean isParticipant = user.equals("Participant");
		System.out.print(user + " name: ");
		String participant = console.nextLine();
		if (isParticipant) {
			participant = "guess_" + participant;
		}
		PrintStream output = new PrintStream(participant);
		if (isParticipant) {
			output.println(user + " : " + participant);
		}
		
		List<String> littles = league.getLittles();
		Set<String> bigs = league.getBigs();
		
		for (String big : bigs) {
			System.out.println();
			for (int i = 1; i <= littles.size(); i++) {
				System.out.println(i + ". " + littles.get(i - 1));
			}
			System.out.println();
			System.out.print("Who is " + big + "'s little? (enter number) ");
			output.print(big + " : ");
			int answer = Integer.parseInt(console.nextLine());
			String little = littles.get(answer - 1);
			output.println(little);
			littles.remove(answer - 1);
		}
		if (isParticipant) {
			System.out.println("Your text file containing your guesses has been created!");
			System.out.println("Please send to the moderator");
		} else {
			System.out.println("Your text file containing the correct matches has been created!");
		}
	}
	
	public static void load(Scanner console) throws FileNotFoundException {
		System.out.println("First, load the names of bigs and littles from a text file");
		System.out.println("A unique name should be on each line.");
		
		File bigsFile = promptFile("bigs", console);
		addBigs(new Scanner(bigsFile));
		
		File littlesFile = promptFile("littles", console);
		addLittles(new Scanner(littlesFile));
		
	}
	
	public static void addBigs(Scanner bigsFile) throws FileNotFoundException {
		while(bigsFile.hasNextLine()) {
			String name = bigsFile.nextLine();
			league.addBig(name);
		}
	}
	
	public static void addLittles(Scanner littlesFile) throws FileNotFoundException {
		while(littlesFile.hasNextLine()) {
			String name = littlesFile.nextLine();
			league.addLittle(name);
		}
	}
	
	public static File promptFile(String prompt, Scanner console) {
		System.out.print("File name for " + prompt + "? ");
		String fileName = console.nextLine();
		File file = new File(fileName);
		while(!file.exists()) {
			System.out.print("File not found. File name for " + prompt + "? ");
			file = new File(console.nextLine());
		}
		return file;
	}
}
