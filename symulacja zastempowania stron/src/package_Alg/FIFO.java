package package_Alg;

import java.util.LinkedList;

public class FIFO extends Alg {

	public FIFO(int maxFrame, int maxPage) {
		super("FIFO", maxFrame, maxPage);
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
			li.add(dt[i]);
			pt[dt[i++]] = true;
			faults++;
		}
		while (i < dt.length) {
			if (pt[dt[i]] == false) {
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
