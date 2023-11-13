package package_Alg;

import package_Struct.Zad;

public class Strat1 extends Strat {
	private final int p, z;

	public Strat1(int[] proc, int p, int z) {
		super(proc, "strat1");
		this.p = p;
		this.z = z;
	}

	@Override
	protected int finalAdd(Zad zad) {
		int ran;
		for (int i = 0; i < z; i++, zap++)
			if (proc[((ran = rgen.nextInt(proc.length - 1)) < zad.n) ? ran : ++ran] < p) {
				proc[ran] += zad.o;
				zap++;
				mig++;
				return ran;
			}
		proc[zad.n] += zad.o;
		return zad.n;
	}

}
