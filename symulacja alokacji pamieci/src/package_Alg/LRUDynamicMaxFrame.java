package package_Alg;

public class LRUDynamicMaxFrame extends LRU {
	protected final String name = "LRUDynamicMaxFrame";
	private int hl; // history Length
	protected int[] pt;

	public LRUDynamicMaxFrame(int historyLength, int maxPage, int[] dt) {
		super(0, maxPage, dt);
		hl = historyLength;
		pt = new int[this.maxPage];
		for (int i = 0; i < pt.length; i++)
			pt[i] = 0;
	}

	public int releaseAllFrames() {
		int tmp = maxFrame;
		for (; maxFrame > 0;) {
			if (li.size() == maxFrame)
				pt[li.removeFirst()]--;
			maxFrame--;
		}
		return tmp;
	}

	/** returns 1 on page fault */
	public int next() {
		if (isDone())
			return 0;
		if (dt[id] == -1) {
			id++;
			releaseAllFrames();
			return 0;
		}
		if ((!li.isEmpty()) && (li.size() == hl)) {
			int rem = li.removeFirst();
			if (rem != dt[id]) {
				if (--pt[rem] == 0)
				maxFrame--;
			}
		}
		if (pt[dt[id]] > 0) {
			li.add(dt[id]);
			id++;
			return 0;
		}
		maxFrame++;
		pt[dt[id]]++;
		li.add(dt[id]);
		faults++;
		id++;
		return 1;
	}

	public void addFrame() {
		throw new UnsupportedOperationException();
	}

	public void addFrames(int n) {
		throw new UnsupportedOperationException();
	}

	public void releaseFrame() {
		throw new UnsupportedOperationException();
	}

	public void releaseFrames(int n) {
		throw new UnsupportedOperationException();
	}

	public void setMaxFrame(int n) {
		throw new UnsupportedOperationException();
	}

}
