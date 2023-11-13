package package_Main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import package_Alg.Alg;
import package_Alg.AproxLRU;
import package_Alg.FIFO;
import package_Alg.LRU;
import package_Alg.OPT;
import package_Alg.RAND;

public class Symul {
	private final int TTSiz;
	private final int TSiz;
	private final int MaxFrame;
	private final int MaxPage;
	private final int[][] data;
	private final Alg[] algt;

	public Symul(int ttSiz, int tSiz, int maxproc, int sizproc, int maxFrame, int maxPage) {
		TTSiz = ttSiz;
		TSiz = tSiz;
		MaxFrame = maxFrame;
		MaxPage = maxPage;

		Random rgen = new Random();
		data = new int[TTSiz][TSiz];
		for (int i = 0; i < data.length; i++) {
			int siz = rgen.nextInt(sizproc) + 1;
			int base = rgen.nextInt(maxPage - siz);
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = rgen.nextInt(siz) + base;
				if ((rgen.nextInt(tSiz) % maxproc) == 0)
					siz = rgen.nextInt(sizproc) + 1;
				base = rgen.nextInt(maxPage - siz);
			}

		}

		algt = new Alg[5];
		algt[0] = new FIFO(MaxFrame, MaxPage);
		algt[1] = new OPT(MaxFrame, MaxPage);
		algt[2] = new LRU(MaxFrame, MaxPage);
		algt[3] = new AproxLRU(MaxFrame, MaxPage);
		algt[4] = new RAND(MaxFrame, MaxPage);
	}

	public void run() {
//		 prtData();
		int[] out;
		@SuppressWarnings("unchecked")
		Pab<String, Double>[] algAvg = (Pab<String, Double>[]) (new Pab[algt.length]);
		int j = 0;
		for (Alg a : algt) {
			out = new int[data.length];
			int i = 0;
			for (int[] dt : data)
				out[i++] = a.run(dt);
			algAvg[j++] = new Pab<String, Double>(a.getName(), prtAlg(a, out));
		}
		prtAlgCompare(algAvg);
	}

	public void prtData() {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++)
				System.out.print(data[i][j] + "\t");
			System.out.print("\n");
		}
		System.out.println();
	}

	public double prtAlg(Alg a, int[] out) {
		a.prt();
		int s = 0;
		for (int i : out) {
			System.out.print(i + "\t");
			s += i;
		}
		System.out.println();
		System.out.printf("AVG= %.2f\tAll= %d\n\n", (double) s / out.length, s);
		return (double) s / out.length;
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
