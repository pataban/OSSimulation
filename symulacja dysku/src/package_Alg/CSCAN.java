package package_Alg;

import package_Main.BinTree;
import package_Main.Proc;

public class CSCAN extends Alg {
	private final int MaxDisk;

	public CSCAN(Proc[][] dtt, int maxdisk) {
		super(dtt, "CSCAN");
		MaxDisk = maxdisk;
		btp = new BinTree<Proc>(new Proc.ProcPlaceComp());
	}

	@Override
	public int run(Proc[] dt) {
		j = 0;
		t = 0;
		p = 0;
		sp = 0;

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
			p++;
			if (p == MaxDisk)
				p = 0;
		}
		return sp;
	}

	protected void runPriority(Proc[] dt) {
		edAlg = new EDSCAN(t, j, p, sp, btp, dt, MaxDisk);
		super.runPriority(dt);
	}
}
