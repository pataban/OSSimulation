package package_Alg;

import package_Struct.Zad;

public class Strat3 extends Strat2 {
	private final int r, z;

	public Strat3(int[] proc, int p, int r, int z) {
		super(proc, "strat3", p);
		this.r = r;
		this.z = z;
	}

	@Override
	protected int finalAdd(Zad zad) {
		int ret = super.finalAdd(zad);
		if ((proc[zad.n] <= r) || (proc[zad.n] >= super.getP()))
			return ret;

		int ran;
		for (int i = 0; i < z; i++, zap++)
			if (proc[((ran = rgen.nextInt(proc.length - 1)) < zad.n) ? ran : ++ran] > super.getP()) {
				int tmp = (proc[ran] - proc[zad.n]) >> 1;
				proc[ran] -= tmp;
				proc[zad.n] += tmp;
				zap++;
				mig++;
				return ret;
			}

		return ret;
	}

}
