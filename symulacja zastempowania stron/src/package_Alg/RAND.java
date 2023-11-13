package package_Alg;

import java.util.Random;

public class RAND extends Alg {

	public RAND(int maxFrame, int maxPage) {
		super("RAND", maxFrame, maxPage);
	}

	@Override
	public int run(int[] dt) {
		int faults = 0;
		Random rgen = new Random();
		int[] ft = new int[MaxFrame];
		boolean[] pt = new boolean[MaxPage];
		for (int i = 0; i < pt.length; i++)
			pt[i] = false;

		int i = 0;
		while ((i < dt.length) && (i < MaxFrame)) {
			ft[i] = dt[i];
			pt[dt[i++]] = true;
			faults++;
		}
		while (i < dt.length) {
			if (pt[dt[i]] == false) {
				faults++;
				int irem = rgen.nextInt(MaxFrame);
				pt[ft[irem]] = false;
				ft[irem] = dt[i];
				pt[dt[i]] = true;
			}
			i++;
		}

		return faults;
	}

}
