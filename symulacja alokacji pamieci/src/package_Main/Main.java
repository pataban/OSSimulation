package package_Main;

public class Main {

	public static void main(String[] args) {
//		Symul sim=new Symul(15,100,20,100,150);	//SIZ = 10
		Symul sim=new Symul(15,100000,5000,1000,5000);
//		Symul sim=new Symul(15,10000,750,1000,300);
		sim.run();
	}

}
