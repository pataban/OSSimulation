package package_Alg;

import package_Struct.Piit;
import package_Struct.Rate;

public class FaultRateControl extends Alg {
	public static final int FMIN = 0;
	public static final int FMAX = 3;
	public static final int SIZ = 250;
	private boolean[] active;
	private Rate[] fRate;

	public FaultRateControl(int maxFrame) {
		super("Fault Rate Control", maxFrame);
	}

	@Override
	public void runAlg() {
		int begin = 0;
		while (begin < paging.length) {

			while ((begin < paging.length) && (paging[begin].isDone()))
				begin++;
			int i = begin;

			for (; (i < paging.length) && (!paging[i].hasNext()) && (!paging[i].isDone()); i++)
				paging[i].next();

			int framesLeft = maxFrame;// maxFrame;
			for (; (i < paging.length) && (active[i]) && (framesLeft > paging[i].getMaxFrame()); i++) {
				if (paging[i].hasNext()) {
					if (fRate[i].getRate() == FMAX)
						paging[i].setMaxFrame(paging[i].getMaxFrame() + FMAX - FMIN);
					if ((fRate[i].getRate() < FMIN) && (paging[i].getMaxFrame() > 1))
						paging[i].setMaxFrame(paging[i].getMaxFrame() - 1);
					framesLeft -= paging[i].getMaxFrame();
					fRate[i].add(paging[i].next());
					faults[i] += fRate[i].get();
				} else {
					deActivate(i);
					swapProc(begin++, i);
				}
			}

			for (; (i < paging.length) && (active[i]); i++) {
				deActivate(i);
				if (!paging[i].hasNext())
					swapProc(begin++, i);
			}

			int id = i;
			for (; (i < paging.length) && (framesLeft > SIZ); i++) {
				if (!paging[i].hasNext())
					paging[i].next();
				else {
					framesLeft -= SIZ; // opcja SIZ>>1
					swapProc(id, i);
					activate(id);
					paging[id].setMaxFrame(FMAX - FMIN);
					fRate[id].add(paging[id].next());
					faults[id] += fRate[id].get();
					id++;
				}
			}

			for (; i < paging.length; i++)
				if ((!paging[i].hasNext()) && (!paging[i].isDone()))
					paging[i].next();
		}

	}

	@Override
	public void load(Piit[] proct) {
		clearFaults(proct.length);

		active = new boolean[proct.length];
		for (int i = 0; i < proct.length; i++)
			active[i] = false;

		fRate = new Rate[proct.length];

		paging = new LRU[proct.length];
		for (int i = 0; i < paging.length; i++)
			paging[i] = new LRU(0, proct[i].a, proct[i].b);

	}

	private void swapProc(int i, int j) {
		LRU tmp = paging[i];
		paging[i] = paging[j];
		paging[j] = tmp;
		int n = faults[i];
		faults[i] = faults[j];
		faults[j] = n;
		boolean b = active[i];
		active[i] = active[j];
		active[j] = b;
		Rate r = fRate[i];
		fRate[i] = fRate[j];
		fRate[j] = r;
	}

	private void activate(int i) {
		if (active[i] == true)
			return;
		active[i] = true;
		fRate[i] = new Rate(FMAX);
	}

	private int deActivate(int i) {
		if (active[i] == false)
			return 0;
		active[i] = false;
		fRate[i] = null;
		return paging[i].releaseAllFrames();
	}

}