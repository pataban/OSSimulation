package package_Alg;

import java.util.Comparator;

import package_Main.BinTree;
import package_Main.Proc;

public class SSTF extends Alg {

	public SSTF(Proc[][] dtt) {
		super(dtt, "SSTF");
		btp = new BinTree<Proc>(new Proc.ProcPlaceComp());
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
				Proc wyk = btp.getClosest(new Proc(0,p,0),new Comp());
				btp.del(wyk);
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
			Proc wyk = btp.getClosest(new Proc(0,p,0),new Comp());
			btp.del(wyk);
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
	
	class Comp implements Comparator<Proc>{

		@Override
		public int compare(Proc o1, Proc o2) {
			return o1.place-o2.place;
		}
		
	}
}
