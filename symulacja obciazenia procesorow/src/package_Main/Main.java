package package_Main;

public class Main {

	public static void main(String[] args) {
//		new Symul(4,50,12,2).run(10);
		new Symul(100, 50, 12, 10).run(100);

	}

}

//strat2 ma srednio 15% wieksze odchylenie ale 50% mniej kosztownych zapytan i migracji
//strat1 dla N male
//strat2 dla N duze bo daleki transfer = duzo czasu marnowanego
//strat3 to polepszone strat 2 - wieksza ilosc zapytan i migracji aby zmniejszyc odchylenie
//zbyt mala roznica p-r powoduje duze straty(daremne migracje, bo zaraz i tak sie zmieni sytuacja)
//strat3 najlepsze bo malo migracji i uzyskuje dobry efekt zrownowazenia