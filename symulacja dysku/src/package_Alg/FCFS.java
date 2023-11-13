package package_Alg;

import package_Main.BinTree;
import package_Main.Proc;

public class FCFS extends Alg {

	public FCFS(Proc[][] dtt) {
		super(dtt, "FCFS");
		btp = new BinTree<Proc>(new Proc.ProcStartComp());
	}

	@Override
	public int run(Proc[] dt) {
		t = 0;
		j = 0;
		p = 0;
		sp = 0;

		while (j < dt.length) {
			if (btp.isEmpty()) {
				if (dt[j].end == 0)
					btp.add(dt[j++]);
				else
					runPriority(dt);
			} else {
				Proc wyk = btp.remmin();
				if (t < wyk.start)
					t = wyk.start;
				sp += Math.abs(wyk.place - p);
				t += Math.abs(wyk.place - p);
				p = wyk.place;
				wyk.wait = t - wyk.start;
				while ((j < dt.length) && (t >= dt[j].start)) {
					if (dt[j].end == 0)
						btp.add(dt[j++]);
					else
						runPriority(dt);
				}
			}
		}
		while (!btp.isEmpty()) {
			Proc wyk = btp.remmin();
			if (t < wyk.start)
				t = wyk.start;
			sp += Math.abs(wyk.place - p);
			t += Math.abs(wyk.place - p);
			p = wyk.place;
			wyk.wait = t - wyk.start;
		}
		return sp;
	}

	protected void runPriority(Proc[] dt) {
		edAlg = new EDF(t, j, p, sp, btp, dt);
		super.runPriority(dt);
	}

}
