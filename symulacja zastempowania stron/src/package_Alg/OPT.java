package package_Alg;

import package_Main.BinTree;
import package_Main.Que;

public class OPT extends Alg {

	public OPT(int maxFrame, int maxPage) {
		super("OPT", maxFrame, maxPage);
	}

	@Override
	public int run(int[] dt) {
		int faults = 0;
		BinTree<Que> loaded = new BinTree<Que>(new Que.Comp());
		BinTree<Que> waiting = new BinTree<Que>(new Que.Comp());

		Que[] qt = new Que[MaxPage];
		for (int i = 0; i < qt.length; i++)
			qt[i] = null;
		for (int i = 0; i < dt.length; i++) {
			if (qt[dt[i]] == null)
				qt[dt[i]] = new Que(dt[i]);
			qt[dt[i]].add(i);
		}
		for (Que q : qt)
			if (q != null)
				waiting.add(q);
		qt = null;

		while ((!waiting.isEmpty()) && (loaded.size() < MaxFrame)) {
			loaded.add(waiting.remmin());
			faults++;
		}
		while (!waiting.isEmpty()) {
			if (waiting.getmin().compareTo(loaded.getmin()) == -1) {
				faults++;
				Que tmp = loaded.remmax();
				if (!tmp.isEmpty())
					waiting.add(tmp);
				loaded.add(waiting.remmin());
			}

			Que tmp = loaded.remmin();
			tmp.removeFirst();
			loaded.add(tmp);
		}

		return faults;
	}

}
