package package_Main;

public class Main {

	public static void main(String[] args) {
		Symul sim = new Symul(3, 10, 3, 100, 100); // ilosc symulacji, ilosc procesow, ilosc realtime, max czas, max dysk
		sim.run();
	}

}
