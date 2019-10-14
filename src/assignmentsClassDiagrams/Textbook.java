package assignmentsClassDiagrams;

public class Textbook {
	private String name;
	private int version;
	private String id;

	public Textbook(String name, int version, String id) {
		this.name = name;
		this.version = version;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getVersion() {
		return version;
	}

	public String getId() {
		return id;
	}
}
