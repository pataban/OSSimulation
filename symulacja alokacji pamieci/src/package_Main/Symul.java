package package_Main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import package_Alg.Alg;
import package_Alg.Equal;
import package_Alg.FaultRateControl;
import package_Alg.Proportional;
import package_Alg.WorkingSet;
import package_Struct.Pab;
import package_Struct.Piit;

public class Symul {
	private final int TTSiz;
	private final int TSiz;
	private final int MaxFrame;
	private final int MaxPage;
	private Piit[] data;
	private final Alg[] algt;

	public Symul(int ttSiz, int tSiz, int maxLocalSiz, int maxFrame, int maxPage) {
		TTSiz = ttSiz;
		TSiz = tSiz;
		MaxFrame = maxFrame;
		MaxPage = maxPage;

		mkData(maxLocalSiz);

		algt = new Alg[4];
		algt[0] = new Proportional(MaxFrame);
		algt[1] = new Equal(MaxFrame);
		algt[2] = new WorkingSet(MaxFrame);
		algt[3] = new FaultRateControl(MaxFrame);
	}

	/***
	 * maxlocality = MaxPage>>2 dla srednio jednokrotnego odwolania do kazdego page
	 * z lokalnosci
	 */
	private void mkData(int maxLocalSiz) {
		Random rgen = new Random();

		data = new Piit[TTSiz];
		for (int i = 0; i < data.length; i++)
			data[i] = new Piit(rgen.nextInt(MaxPage - 1) + 2, new int[TSiz]);

		for (int i = 0; i < data.length; i++) {

			int procSiz = rgen.nextInt(data[i].b.length - 1) + 1;
			int procBegin = rgen.nextInt(data[i].b.length - procSiz);
//			int procBegin = 0;

			int j = 0;
			for (; j < procBegin; j++)
				data[i].b[j] = -1;

			while (j < procBegin + procSiz) {
				int localSiz = Math.min(rgen.nextInt(maxLocalSiz - 1) + 1, procBegin + procSiz - j);
				int range = rgen.nextInt(data[i].a >> 1) + 1;
				int base = rgen.nextInt(data[i].a - range);
				for (; localSiz > 0; j++, localSiz--)
					data[i].b[j] = rgen.nextInt(range) + base;
			}

			for (; j < data[i].b.length; j++)
				data[i].b[j] = -1;
		}

	}

	public void run() {
		//prtData();
		@SuppressWarnings("unchecked")
		Pab<String, Double>[] algAvg = (Pab<String, Double>[]) (new Pab[algt.length]);
		int j = 0;
		for (Alg a : algt) {
			a.run(data);
			algAvg[j++] = new Pab<String, Double>(a.getName(), a.prtEnd());
		}
		prtAlgCompare(algAvg);
	}

	public void prtData() {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].b.length; j++)
				System.out.print(data[i].b[j] + "\t");
			System.out.print("\n");
		}
		System.out.println();
	}

	public void prtAlgCompare(Pab<String, Double>[] sdt) {

		class CompPab implements Comparator<Pab<String, Double>> {

			@Override
			public int compare(Pab<String, Double> o1, Pab<String, Double> o2) {
				return o1.getB().compareTo(o2.getB());
			}

		}
		Arrays.sort(sdt, new CompPab());

		System.out.println("\nRanking:");
		for (Pab<String, Double> p : sdt)
			System.out.printf("%.2f\t\t%d%%\t%s\n", p.getB(), (int) (p.getB() / sdt[0].getB() * 100), p.getA());

	}
}
