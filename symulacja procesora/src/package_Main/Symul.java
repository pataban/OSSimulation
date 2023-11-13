package package_Main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Symul {
	public static final int TTSiz = 3;
	public static final int TSiz = 10;
	public static final int MaxStart = 100;
	public static final int MaxWork = 100;
	public static final int MinWork = 30;
	public static final int ROTQ = 50;
	Proc[][] data;

	public Symul() {
		Random rgen = new Random();

		data = new Proc[TTSiz][TSiz];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = new Proc(j, rgen.nextInt(MaxWork - MinWork) + MinWork, rgen.nextInt(MaxStart));
			}
			Arrays.sort(data[i], new ProcStartComp());
			for (int j = 0; j < data[i].length; j++)
				data[i][j].id = j;
		}
	}

	public static void main(String[] args) {
		Symul sim = new Symul();
		Proc.prt(sim.data);
		System.out.println();
		fcfs(Proc.copyTT(sim.data));
		sjf(Proc.copyTT(sim.data));
		sjfw(Proc.copyTT(sim.data));
		rot(Proc.copyTT(sim.data));
	}

	public static void fcfs(Proc[][] dtt) {
		for (int i = 0; i < dtt.length; i++) {
			int t = dtt[i][0].start + dtt[i][0].work;
			for (int j = 1; j < dtt[i].length; j++) {
				dtt[i][j].wait = Math.max(0, t - dtt[i][j].start);
				t = dtt[i][j].start + dtt[i][j].wait + dtt[i][j].work;
			}
		}

		System.out.println("FCFS:");
		Proc.prtEnd(dtt);
		System.out.println();
	}

	public static void sjf(Proc[][] dtt) {
		for (int i = 0; i < dtt.length; i++) {
			BinTree<Proc> btp = new BinTree<Proc>(new ProcWorkComp());
			int t = 0;
			int j = 0;
			while (((j < dtt[i].length) || (!btp.isEmpty()))) {
				if (btp.isEmpty()) {
					btp.add(dtt[i][j]);
					t = dtt[i][j].start;
					j++;
				} else if (j >= dtt[i].length) {
					while (!btp.isEmpty()) {
						Proc wyk = btp.remmin();
						wyk.wait = Math.max(0, t - wyk.start);
						t = wyk.start + wyk.wait + wyk.work;
					}
				} else {
					Proc wyk = btp.remmin();
					wyk.wait = Math.max(0, t - wyk.start);
					t = wyk.start + wyk.wait + wyk.work;
					while ((j < dtt[i].length) && (t >= dtt[i][j].start))
						btp.add(dtt[i][j++]);
				}
			}
		}
		System.out.println("sjf:");
		Proc.prtEnd(dtt);
		System.out.println();
	}

	public static void sjfw(Proc[][] dtt) {

		for (int i = 0; i < dtt.length; i++) {
			BinTree<Proc> btp = new BinTree<Proc>(new ProcWorkComp());
			int t = 0;
			int j = 0;
			while (((j < dtt[i].length) || (!btp.isEmpty()))) {
				if (btp.isEmpty()) {
					btp.add(dtt[i][j]);
					t = dtt[i][j].start;
					j++;
				} else if (j >= dtt[i].length) {
					while (!btp.isEmpty()) {
						Proc wyk = btp.remmin();
						wyk.wait = Math.max(0, t - wyk.start);
						t = wyk.start + wyk.wait + wyk.work;
					}
				} else {
					Proc wyk = btp.getmin();
					wyk.wait += t - wyk.start;
					if (t + wyk.work < dtt[i][j].start) {
						t += wyk.work;
						btp.del(wyk);
					} else if (t + wyk.work == dtt[i][j].start) {
						t += wyk.work;
						btp.del(wyk);
						btp.add(dtt[i][j++]);
					} else {
						int dt = dtt[i][j].start - t;
						wyk.work -= dt;
						t += dt;
						wyk.start = t;
						btp.add(dtt[i][j++]);
					}
				}
			}
		}

		System.out.println("sjfw:");
		Proc.prtEnd(dtt);
		System.out.println();
	}

	public static void rot(Proc[][] dtt) {

		for (int i = 0; i < dtt.length; i++) {
			LinkedList<Proc> lp = new LinkedList<Proc>();
			for (int j = 0; j < dtt[i].length; j++)
				lp.add(dtt[i][j]);
			Iterator<Proc> ilp = lp.iterator();
			int t = dtt[i][0].start;
			while (!lp.isEmpty()) {
				Proc wyk = ilp.next();
				wyk.wait += t - wyk.start;
				if (wyk.work <= ROTQ) {
					t += wyk.work;
					ilp.remove();
				} else {
					wyk.work -= ROTQ;
					t += ROTQ;
					wyk.start = t;
				}
				if (!ilp.hasNext())
					ilp = lp.iterator();
			}
		}

		System.out.println("rot:");
		Proc.prtEnd(dtt);
		System.out.println();
	}
}

class ProcStartComp implements Comparator<Proc> {

	@Override
	public int compare(Proc o1, Proc o2) {
		if (o1.start < o2.start)
			return -1;
		if (o1.start > o2.start)
			return 1;
		return 0;
	}

}
