package package_Alg;

import java.util.LinkedList;

public class LRU {
	protected final String name = "LRU";
	protected final int maxPage;
	protected int maxFrame;
	protected LinkedList<Integer> li;
	protected int faults = 0;
	protected boolean[] pt;
	protected int[] dt;
	protected int id = 0;

	public LRU(int maxFrame, int maxPage, int[] dt) {
		this.maxPage = maxPage;
		this.maxFrame = maxFrame;
		this.dt = dt;
		li = new LinkedList<Integer>();
		pt = new boolean[this.maxPage];
		for (int i = 0; i < pt.length; i++)
			pt[i] = false;
	}

	public void addFrame() {
		maxFrame++;
	}

	public void addFrames(int n) {
		maxFrame += n;
	}

	public void releaseFrame() {
		if (li.size() == maxFrame)
			pt[li.removeFirst()] = false;
		maxFrame--;
	}

	public void releaseFrames(int n) {
		for (; n > 0; n--)
			releaseFrame();
	}

	public int releaseAllFrames() {
		int tmp = maxFrame;
		for (; maxFrame > 0;)
			releaseFrame();
		return tmp;
	}

	public void setMaxFrame(int n) {
		if (n == maxFrame)
			return;
		if (n > maxFrame)
			addFrames(n - maxFrame);
		releaseFrames(maxFrame - n);
	}

	/** returns 1 on page fault */
	public int next() {
//		System.out.println(id+"\t"+dt[id]);
		if (dt[id] == -1) {
			id++;
			return 0;
		}
		if (maxFrame == 0)
			throw new IllegalStateException();
		if (pt[dt[id]]) {
			li.remove((Integer) dt[id]);
			li.add(dt[id]);
			id++;
			return 0;
		}
		if (li.size() < maxFrame) {
			pt[dt[id]] = true;
			li.add(dt[id]);
		} else {
			pt[li.removeFirst()] = false;
			li.add(dt[id]);
			pt[dt[id]] = true;
		}
		faults++;
		id++;
		return 1;
	}

	/** run next n times. returns number of faults coused */
	public int next(int n) {
		if (id + n > dt.length)
			return -1;
		int r = 0;
		for (; n > 0; n--)
			r += next();
		return r;
	}

	/** run all remaining */
	public int run() {
		return next(dt.length - id);
	}

	public boolean hasNext() {
		if (id >= dt.length)
			return false;
		if (dt[id] == -1)
			return false;
		return true;
	}

	public boolean isDone() {
		return id >= dt.length;
	}

	public boolean hasFrames() {
		return maxFrame > 0;
	}

	@Override
	public String toString() {
		return name + ":";
	}

	public void prt() {
		System.out.println(this);
	}

	public String getName() {
		return name;
	}

	public int getFaults() {
		return faults;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public int getMaxFrame() {
		return maxFrame;
	}

	public int getId() {
		return id;
	}

}
