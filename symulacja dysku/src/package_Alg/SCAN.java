package package_Alg;

import package_Main.BinTree;
import package_Main.Proc;

public class SCAN extends Alg {
	private final int MaxDisk;
	boolean up;

	public SCAN(Proc[][] dtt, int maxdisk) {
		super(dtt, "SCAN");
		MaxDisk = maxdisk;
		btp = new BinTree<Proc>(new Proc.ProcPlaceComp());
	}

	@Override
	public int run(Proc[] dt) {
		j = 0;
		t = 0;
		p = 0;
		sp = 0;
		up = true;

		while ((j < dt.length) || (!btp.isEmpty())) {
			while ((j < dt.length) && (dt[j].start <= t)) {
				if (dt[j].end == 0)
					btp.add(dt[j++]);
				else
					runPriority(dt);
			}

			Proc wyk = btp.get(new Proc(0, p, 0));
			while (wyk != null) {
				wyk.wait = t - wyk.start;
				btp.del(wyk);
				wyk = btp.get(wyk);
			}

			t++;
			sp++;
			if (up)
				p++;
			else
				p--;
			if ((p == -1) || (p == MaxDisk)) {
				up = !up;
				if (up)
					p++;
				else
					p--;
			}
		}
		return sp;
	}

	protected void runPriority(Proc[] dt) {
		edAlg = new EDSCAN(t, j, p, sp, btp, dt, MaxDisk);
		super.runPriority(dt);
	}
}
