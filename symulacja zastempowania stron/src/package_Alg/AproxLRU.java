package package_Alg;

import java.util.LinkedList;

public class AproxLRU extends Alg {

	public AproxLRU(int maxFrame, int maxPage) {
		super("AproxLRU", maxFrame, maxPage);
	}

	@Override
	public int run(int[] dt) {
		int faults = 0;
		LinkedList<Integer> li = new LinkedList<Integer>();
		int[] pt = new int[MaxPage];
		for (int i = 0; i < pt.length; i++)
			pt[i] = 0;

		int i = 0;
		while ((i < dt.length) && (li.size() < MaxFrame)) {
			li.add(dt[i]);
			pt[dt[i++]] = 1;
			faults++;
		}
		while (i < dt.length) {
			if (pt[dt[i]] == 0) {
				faults++;
				Integer cur = li.removeFirst();
				while (pt[cur] != 2) {
					li.add(cur);
					pt[cur] = 2;
					cur = li.removeFirst();
				}
				pt[cur] = 0;
				li.add(dt[i]);
				pt[dt[i]] = 1;
			}
			i++;
		}

		return faults;
	}

}
