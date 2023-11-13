package package_Alg;

import package_Main.BinTree;
import package_Main.Proc;

public class EDSCAN extends DeadlineAlg {
	private final int MaxDisk;

	public EDSCAN(int time, int jstart, int point, int sp, BinTree<Proc> oth, Proc[] data, int maxdisk) {
		super(new Proc[][] { data }, "EDSCAN");
		this.t = time;
		this.j = jstart;
		this.p = point;
		this.sp = sp;
		this.other = oth;
		MaxDisk = maxdisk;
		btp = new BinTree<Proc>(new Proc.ProcPlaceComp());
	}

	@Override
	public int run(Proc[] dt) {
		while ((j < dt.length) && ((dt[j].end != 0) || (!btp.isEmpty()))) {
			while ((j < dt.length) && (dt[j].start <= t)) {
				if (dt[j].end != 0)
					btp.add(dt[j++]);
				else
					other.add(dt[j++]);
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
		while (!btp.isEmpty()) {
			Proc wyk = btp.get(new Proc(0, p, 0));
			while (wyk != null) {
				wyk.wait = t - wyk.start;
				btp.del(wyk);
				wyk = btp.get(wyk);
			}

			t++;
			p++;
			if (p == MaxDisk)
				p = 0;
		}
		return sp;
	}

}
