package package_Alg;

import java.util.Arrays;

import package_Main.BinTree;
import package_Main.Proc;

public abstract class Alg {
	private Proc[][] dtt;
	protected String name;
	protected BinTree<Proc> btp;
	protected int t, j, p, sp;
	private int[] spt;
	protected DeadlineAlg edAlg;

	public Alg(Proc[][] dtt, String name) {
		this.dtt = dtt;
		this.name = name;
		this.edAlg = null;
	}

	public void run() {
		spt = new int[dtt.length];
		int i = 0;
		for (Proc[] dt : dtt)
			spt[i++] = run(dt);
	}

	public abstract int run(Proc[] dt);

	public void prt() {
		System.out.println(name + ":");
		prtEnd();
		System.out.println();
	}

	public void prtEnd() {
		int[] s = new int[dtt.length];
		for (int i = 0; i < dtt.length; i++) {
			Arrays.sort(dtt[i], new Proc.ProcIdComp());
			s[i] = 0;
			for (int j = 0; j < dtt[i].length; j++) {
				System.out.print(dtt[i][j].wait + "\t");
				s[i] += dtt[i][j].wait;
			}
			System.out.print("\n");
		}
		for (int i = 0; i < s.length; i++)
			System.out.printf("przemieszczenie = " + spt[i] + "\tczas = " + s[i] + "\tsrednia = " + "%.2f\n",
					(double) s[i] / dtt[i].length);
	}

	public void doAll() {
		run();
		prt();
	}

	protected void runPriority(Proc[] dt) {
		// System.out.printf("t=%d\tj=%d\tp=%d\n", t, j, p);
		edAlg.run();
		t = edAlg.t;
		j = edAlg.j;
		p = edAlg.p;
		sp = edAlg.sp;
		// System.out.printf("t=%d\tj=%d\tp=%d\n", t, j, p);
	}

}
