package package_Alg;

import package_Struct.Zad;

public class Strat2 extends Strat {
	private final int p;

	public Strat2(int[] proc, int p) {
		super(proc, "strat2");
		this.p = p;
	}

	public Strat2(int[] proc, String s, int p) {
		super(proc, s);
		this.p = p;
	}

	@Override
	protected int finalAdd(Zad zad) {
		int ran;
		if (zad.o > p) {
			for (; zap < (1e+9); zap++) {
				if (proc[((ran = rgen.nextInt(proc.length - 1)) < zad.n) ? ran : ++ran] < p) {
					proc[ran] += zad.o;
					zap++;
					mig++;
					return ran;
				}
			}
		}
		proc[zad.n] += zad.o;
		return zad.n;
	}

	public int getP() {
		return p;
	}

}
