package package_Main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import package_Alg.Strat;
import package_Alg.Strat1;
import package_Alg.Strat2;
import package_Alg.Strat3;
import package_Struct.Pab;
import package_Struct.Zad;

public class Symul {
	private int N, p, r, z;
	private Strat[] strat;
	private Zad[] data;

	public Symul(int n, int p, int r, int z) {
		N = n;
		this.p = p;
		this.r = r;
		this.z = z;
	}

	@SuppressWarnings("unchecked")
	public void run(int siz) {
		mkData(siz);
		prtData();
		strat = new Strat[] { new Strat1(new int[N], p, z), new Strat2(new int[N], p),
				new Strat3(new int[N], p, r, z) };

		for (Strat s : strat)
			for (Zad zad : data)
				s.add(zad);

//		for(Strat s:strat)
//			s.prtProc();

		for (Strat s : strat)
			s.prt();

		
		Pab<String, Double>[] psdt=(Pab<String,Double>[])(new Pab[strat.length]);
		for(int i=0;i<strat.length;i++)
			psdt[i]=new Pab<String,Double>(strat[i].getName(),strat[i].avgOdch());
		System.out.println("\n\nRanking Avg Odchylenie:");
		prtAlgCompare(psdt);
		
		
		psdt=(Pab<String,Double>[])(new Pab[strat.length]);
		for(int i=0;i<strat.length;i++)
			psdt[i]=new Pab<String,Double>(strat[i].getName(),(double)strat[i].zap());
		System.out.println("\n\nRanking Ilosc zapytan:");
		prtAlgCompare(psdt);
		
		psdt=(Pab<String,Double>[])(new Pab[strat.length]);
		for(int i=0;i<strat.length;i++)
			psdt[i]=new Pab<String,Double>(strat[i].getName(),(double)strat[i].mig());
		System.out.println("\n\nRanking Ilosc migracji:");
		prtAlgCompare(psdt);
		
	}

	private void mkData(int siz) {
		Random rgen = new Random();
		data = new Zad[siz];
		for (int i = 0; i < data.length; i++)
			data[i] = new Zad(rgen.nextInt(N), rgen.nextInt(p << 1));
//		int s = 0;
//		for (Zad zad : data)
//			s += zad.o;
//		System.out.print(p+"\t");
//		p=s/N+1;
//		System.out.println(p);
	}

	public void prtData() {
		System.out.println(Arrays.toString(data));
	}

	public void prtAlgCompare(Pab<String, Double>[] sdt) {

		class CompPab implements Comparator<Pab<String, Double>> {

			@Override
			public int compare(Pab<String, Double> o1, Pab<String, Double> o2) {
				return o1.getB().compareTo(o2.getB());
			}

		}
		Arrays.sort(sdt, new CompPab());

		for (Pab<String, Double> p : sdt)
			System.out.println((double)Math.round(p.getB()*100)/100+"\t"+(int) (p.getB() / sdt[0].getB() * 100)+"%"+"\t"+p.getA());
			//System.out.printf("%.2f\t%d%%\t%s\n", p.getB(), (int) (p.getB() / sdt[0].getB() * 100), p.getA());

	}

	
}
