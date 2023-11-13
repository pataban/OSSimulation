package package_Main;

import java.util.Comparator;

public class BinTree<T> {
	private Comparator<T> c;
	private DUB w;

	public BinTree(Comparator<T> com) {
		this.c = com;
		w = null;
	}

	public boolean isEmpty() {
		if (w == null)
			return true;
		else
			return false;
	}

	// _______________________________________________________________________
	// inner class
	private class DUB {
		T v;
		DUB wl, wp;

		DUB(T a) {
			v = a;
			wl = null;
			wp = null;
		}
	}

	// _________________________________________________________________________
	// operations

	public void add(T a) {
		if (w == null)
			w = new DUB(a);
		else
			add(w, new DUB(a));
	}

	public void del(T a) {
		if (w == null)
			return;
		if (c.compare(w.v, a) == 0) {
			DUB buf1 = w.wl, buf2 = w.wp;
			if (buf1 == null) {
				buf1 = buf2;
				buf2 = null;
			}
			w = buf1;
			if (buf2 != null)
				add(w, buf2);
		} else
			del(w, a);
	}

	public T getmin() {
		if (w == null)
			return null;
		return getmin(w);
	}

	public T getmax() {
		if (w == null)
			return null;
		return getmax(w);
	}

	public T remmin() {
		if (w == null)
			return null;
		if (w.wl == null) {
			T tmp = w.v;
			w = w.wp;
			return tmp;
		} else
			return remmin(w);
	}

	public T remmax() {
		if (w == null)
			return null;
		if (w.wp == null) {
			T tmp = w.v;
			w = w.wl;
			return tmp;
		} else
			return remmax(w);
	}

	// ______________________________________________________________________________
	// private operations

	private void add(DUB w, DUB n) {
		if (c.compare(n.v, w.v) == -1) {
			if (w.wl != null)
				add(w.wl, n);
			else
				w.wl = n;
		} else {
			if (w.wp != null)
				add(w.wp, n);
			else
				w.wp = n;
		}
	}

	private void del(DUB w, T a) {
		if (c.compare(a, w.v) == -1) {
			if (c.compare(w.wl.v, a) != 0)
				del(w.wl, a);
			else {
				DUB buf1 = w.wl.wl, buf2 = w.wl.wp;
				if (buf1 == null) {
					buf1 = buf2;
					buf2 = null;
				}
				w.wl = buf1;
				if (buf2 != null)
					add(w.wl, buf2);
			}
		} else if (c.compare(a, w.v) == 1) {
			if (c.compare(w.wp.v, a) != 0)
				del(w.wp, a);
			else {
				DUB buf1 = w.wp.wl, buf2 = w.wp.wp;
				if (buf1 == null) {
					buf1 = buf2;
					buf2 = null;
				}
				w.wp = buf1;
				if (buf2 != null)
					add(w.wp, buf2);
			}
		}
	}

	private T getmin(DUB w) {
		while (w.wl != null)
			w = w.wl;
		return w.v;
	}

	private T getmax(DUB w) {
		while (w.wp != null)
			w = w.wp;
		return w.v;
	}

	private T remmin(DUB w) {
		while (w.wl.wl != null)
			w = w.wl;
		T buf1 = w.wl.v;
		DUB buf2 = w.wl.wp;
		w.wl = buf2;
		return buf1;
	}

	private T remmax(DUB w) {
		while (w.wp.wp != null)
			w = w.wp;
		T buf1 = w.wp.v;
		DUB buf2 = w.wp.wl;
		w.wp = buf2;
		return buf1;
	}

}
