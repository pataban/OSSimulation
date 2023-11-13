package package_Alg;

import package_Struct.Piit;

public class Proportional extends Alg {
	private boolean[] active;
	private int activePages;

	public Proportional(int maxFrame) {
		super("Proportional", maxFrame);
	}

	@Override
	public void runAlg() {

		while (!paging[0].isDone()) {
			chkRunning();
			for (int i = 0; i < paging.length; i++) {
				if (activePages != 0) {
					paging[i].setMaxFrame(Math.max(maxFrame * paging[i].getMaxPage() / activePages, 1));
//					if(active[i])
//						System.out.println("maxFrame="+maxFrame+"\tproces pagesiz="+paging[i].getMaxPage()+"\tall Pages="+activePages+"\tprocesFrames="+paging[i].getMaxFrame());
				}
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
		activePages = 0;

		paging = new LRU[proct.length];
		for (int i = 0; i < paging.length; i++)
			paging[i] = new LRU(0, proct[i].a, proct[i].b);

	}

	private void chkRunning() {
		for (int i = 0; i < paging.length; i++) {
			if ((!active[i])&&(paging[i].hasNext()))
				activate(i);
			else if((active[i])&&(!paging[i].hasNext()))
				deActivate(i);
		}
	}

	private void activate(int i) {
		if (active[i] == true)
			return;
		active[i] = true;
		activePages += paging[i].getMaxPage();
	}

	private void deActivate(int i) {
		if (active[i] == false)
			return;
		active[i] = false;
		activePages -= paging[i].getMaxPage();
	}

}
