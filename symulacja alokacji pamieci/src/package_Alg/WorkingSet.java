package package_Alg;

import package_Struct.Piit;

public class WorkingSet extends Alg {
	private static final int SIZ = 300;
	private boolean[] active;

	public WorkingSet(int maxFrame) {
		super("Working set", maxFrame);
	}

	@Override // powoduje zmiane kolejnosci
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
					faults[i] += paging[i].next();
					framesLeft -= paging[i].getMaxFrame();
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
					faults[id] += paging[id].next();
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

		paging = new LRUDynamicMaxFrame[proct.length];
		for (int i = 0; i < paging.length; i++)
			paging[i] = new LRUDynamicMaxFrame(SIZ, proct[i].a, proct[i].b);

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
	}

	private void activate(int i) {
		if (active[i] == true)
			return;
		active[i] = true;
	}

	private int deActivate(int i) {
		if (active[i] == false)
			return 0;
		active[i] = false;
		return paging[i].releaseAllFrames();
	}

}
