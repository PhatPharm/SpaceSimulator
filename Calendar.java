package creation;

import java.util.Random;

public class Calendar {
	
	private String[] days = {"Mond", "Tuesd", "Wednesd", "Thursd", "Frid", "Saturd", "Sund"};
	private Random rDayNameVowel;
	private Random rDayNameConsonant;
	private String currentDay;
	private int dayIterator;
	private int week;
	private int year;
	private final String[] vowels = {"a", "e", "i", "o", "u"};
	private final String[] consonants = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z"};
	
	public Calendar() {
		createDayNameSchemeAndSetNames();
		currentDay = days[0];
		dayIterator = 1;
		
		week = 1;
		year = 0;
	}
	
	public void createDayNameSchemeAndSetNames() {
		rDayNameVowel = new Random();
		rDayNameConsonant = new Random();
		
		int rVowel = rDayNameVowel.nextInt(vowels.length);
		int rConsonant = rDayNameConsonant.nextInt(consonants.length);
		
		String suffix = vowels[rVowel] + consonants[rConsonant];
		
		for(int i = 0; i < days.length; i++) {
			days[i] = days[i] + suffix;
		//	System.out.println(days[i]);
		}
	}
	
	public void advanceTime() {

		if(dayIterator > 6) {
			dayIterator = 0;
			week++;
			if(week > 52) {
				year++;
				week = 1;
			}
		}
		currentDay = days[dayIterator];
		dayIterator++;
	}
	
	public void testAdvanceTime() {
		for(int i = 0; i < 10000; i++) {
			System.out.println(currentDay);
			System.out.println(week);
			System.out.println(year);
			advanceTime();
		}
	}
	
	public String getCurrentDay() {		
		return currentDay;
	}
	
	public int getCurrentWeek() {
		return week;
	}
	
	public int getCurrentYear() {
		return year;
	}

}
