package package_Alg;

import package_Main.BinTree;
import package_Main.Proc;

public class EDF extends DeadlineAlg {

	public EDF(int time, int jstart, int point, int sp, BinTree<Proc> oth, Proc[] data) {
		super(new Proc[][] { data }, "EDF");
		this.t = time;
		this.j = jstart;
		this.p = point;
		this.sp = sp;
		this.other = oth;
		btp = new BinTree<Proc>(new Proc.ProcEndComp());
	}

	@Override
	public int run(Proc[] dt) {
		while ((j < dt.length) && ((dt[j].end != 0) || (!btp.isEmpty()))) {
			if (btp.isEmpty())
				btp.add(dt[j++]);
			else {
				Proc wyk = btp.remmin();
				if (t < wyk.start)
					t = wyk.start;
				sp += Math.abs(wyk.place - p);
				t += Math.abs(wyk.place - p);
				p = wyk.place;
				wyk.wait = t - wyk.start;
				while ((j < dt.length) && (t >= dt[j].start)) {
					if (dt[j].end != 0)
						btp.add(dt[j++]);
					else
						other.add(dt[j++]);
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

}
