
public class Member {
	
	private String name;	// this member's name
	private Member big;		// reference to big
	private Member little;	// reference to little
	
	public Member(String name, Member big, Member little) {
		this.name = name;
		this.big = big;
		this.little = little;
	}
	
	public Member(String name) {
		this(name, null, null);
	}
	
	public void setBig(Member big) {
		this.big = big;
	}
	
	public void setLittle(Member little) {
		this.little = little;
	}
	
	public String toString() {
		String result = name + ".";
		if(big != null) {
			result += " Big: " + big.name + ".";
		}
		if(little != null) {
			result += " Little: " + little.name + ".";
		}
		return result;
	}
	
	public String getName() {
		return name;
	}
	
	public Member getBig() {
		return big;
	}
	
	public Member getLittle() {
		return little;
	}

}
