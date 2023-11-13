package package_Alg;

import java.util.LinkedList;

public class LRU extends Alg {

	public LRU(int maxFrame, int maxPage) {
		super("LRU", maxFrame, maxPage);
	}

	@Override
	public int run(int[] dt) {
		int faults = 0;
		LinkedList<Integer> li = new LinkedList<Integer>();
		boolean[] pt = new boolean[MaxPage];
		for (int i = 0; i < pt.length; i++)
			pt[i] = false;

		int i = 0;
		while ((i < dt.length) && (li.size() < MaxFrame)) {
			pt[dt[i]] = true;//wielokrotne
			li.add(dt[i++]);
			faults++;
		}
		while (i < dt.length) {
			if (pt[dt[i]]) {
				li.remove((Integer) dt[i]);
				li.add(dt[i]);
			} else {
				faults++;
				pt[li.removeFirst()] = false;
				li.add(dt[i]);
				pt[dt[i]] = true;
			}
			i++;
		}

		return faults;
	}

}
