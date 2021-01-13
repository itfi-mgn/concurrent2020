package lesson14;

public class PersistenceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// There is a list of person:
		// ivanov ivan ivanovich 21 (years old)
		// petrov petr petrovich 20
		// sidorov sidor sidorovich 23
		// IMap<String,Human> ... 	"people"
		// There is a file c:/persistence.bin
	}

}


class Human {
	String	family;
	String	name;
	String	patroname;
	int		age;
}