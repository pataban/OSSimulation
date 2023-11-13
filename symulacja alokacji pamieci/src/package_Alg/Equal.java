package package_Alg;

import package_Struct.Piit;

public class Equal extends Alg {
	private boolean[] active;
	private int activeCount;

	public Equal(int maxFrame) {
		super("Equal", maxFrame);
	}

	@Override
	public void runAlg() {

		while (!paging[0].isDone()) {
			chkRunning();
			for (int i = 0; i < paging.length; i++) {
				paging[i].setMaxFrame(Math.max(maxFrame / ((activeCount == 0) ? 1 : activeCount), 1));
				faults[i] += paging[i].next();
			}
		}

	}

	@Override
	public void load(Piit[] proct) {
		clearFaults(proct.length);
		active = new boolean[proct.length];
		for (int i = 0; i < proct.length; i++)
			active[i] = false;
		activeCount = 0;

		paging = new LRU[proct.length];
		for (int i = 0; i < paging.length; i++)
			paging[i] = new LRU(0, proct[i].a, proct[i].b);

	}

	private void chkRunning() {
		for (int i = 0; i < paging.length; i++) {
			if (paging[i].hasNext())
				activate(i);
			else
				deActivate(i);
		}
	}

	private void activate(int i) {
		if (active[i] == true)
			return;
		active[i] = true;
		activeCount++;
	}

	private void deActivate(int i) {
		if (active[i] == false)
			return;
		active[i] = false;
		activeCount--;
	}

}
