package package_Main;

import java.util.Arrays;
import java.util.Comparator;

public class Proc implements Comparable<Proc> {
	public int id, work, start, wait;

	public Proc(int i, int w, int s) {
		id = i;
		work = w;
		start = s;
		wait = 0;
	}

	// __________________________________________________________________________________________
	// override

	@Override
	public String toString() {
		return "Proc [id=" + id + ", work=" + work + ", start=" + start + "]";
	}

	@Override
	public int compareTo(Proc o) {
		if (this.start > o.start)
			return 1;
		if (this.start < o.start)
			return -1;
		return 0;
	}

	// _______________________________________________________________________________________
	// tab alg

	public static void prt(Proc[][] tt) {
		for (int i = 0; i < tt.length; i++) {
			for (int j = 0; j < tt[i].length; j++)
				System.out.print(tt[i][j] + "\t");
			System.out.print("\n");
		}
	}

	public static void prtEnd(Proc[][] tt) {
		int[] s = new int[tt.length];
		for (int i = 0; i < tt.length; i++) {
			Arrays.sort(tt[i], new ProcIdComp());
			s[i] = 0;
			for (int j = 0; j < tt[i].length; j++) {
				System.out.print(tt[i][j].wait + "\t");
				s[i] += tt[i][j].wait;
			}
			System.out.print("\n");
		}
		for (int i = 0; i < s.length; i++)
			System.out.printf("%.10f\n", (double) s[i] / tt[i].length);
	}

	public static Proc[][] copyTT(Proc[][] ptt) {
		Proc[][] rtt = new Proc[ptt.length][];
		for (int i = 0; i < ptt.length; i++) {
			rtt[i] = new Proc[ptt[i].length];
			for (int j = 0; j < ptt[i].length; j++)
				rtt[i][j] = new Proc(ptt[i][j].id, ptt[i][j].work, ptt[i][j].start);
		}
		return rtt;
	}

}

class ProcWorkComp implements Comparator<Proc> {

	@Override
	public int compare(Proc o1, Proc o2) {
		if (o1.work < o2.work)
			return -1;
		if (o1.work > o2.work)
			return 1;
		return 0;
	}

}

class ProcIdComp implements Comparator<Proc> {

	@Override
	public int compare(Proc o1, Proc o2) {
		if (o1.id < o2.id)
			return -1;
		if (o1.id > o2.id)
			return 1;
		return 0;
	}

}
