package package_Main;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class BinTree<T> implements Collection<T>, Iterable<T>, Serializable {
	private static final long serialVersionUID = 1L;
	private Comparator<T> c;
	private Dub w;
	private int siz;

// 	_________________________________________________________________________
//	BinTree(Comparator<T> com)
//	BinTree(int type)
//	BinTree(double type)
//	BinTree(T[] a, Comparator<T> com)
//	BinTree(T[] a, int type)
//	BinTree(T[] a, double type)
//	BinTree(Iterable<T> a, Comparator<T> com)
//	BinTree(Iterable<T> a, int type)
//	BinTree(Iterable<T> a, double type)
// 	_________________________________________________________________________
// 	void addAll(T[] ct)
// 	void addAll(Iterable<T> ct)
// 	boolean isEmpty()
// 	void clear()
//	int size()
// 	void prt()
// 	Iterator<T> iterator()
// 	LinkedList<T> mkList()
// 	void add(T a)
// 	void del(T a)
// 	T getmin()
// 	T getmax()
// 	T remmin()
// 	T remmax()
// 	boolean contains(T a)
// 	T get(T a)
// 	_________________________________________________________________________

	//
	// ______________________________________________________________________
	// constructor

	public BinTree(Comparator<T> com) {
		this.c = com;
		w = null;
	}

	public BinTree(int type) {
		class Cmp implements Comparator<T> {
			@Override
			public int compare(T o1, T o2) {
				if ((Integer) o1 < (Integer) o2)
					return -1;
				if ((Integer) o1 > (Integer) o2)
					return 1;
				return 0;
			}
		}
		this.c = new Cmp();
		w = null;
	}

	public BinTree(double type) {
		class Cmp implements Comparator<T> {
			@Override
			public int compare(T o1, T o2) {
				if ((Double) o1 < (Double) o2)
					return -1;
				if ((Double) o1 > (Double) o2)
					return 1;
				return 0;
			}
		}
		this.c = new Cmp();
		w = null;
	}

	public BinTree(T[] a, Comparator<T> com) {
		this(com);
		addAll(a);
	}

	public BinTree(T[] a, int type) {
		this(type);
		addAll(a);
	}

	public BinTree(T[] a, double type) {
		this(type);
		addAll(a);
	}

	public BinTree(Collection<T> a, Comparator<T> com) {
		this(com);
		addAll(a);
	}

	public BinTree(Collection<T> a, int type) {
		this(type);
		addAll(a);
	}

	public BinTree(Collection<T> a, double type) {
		this(type);
		addAll(a);
	}

	//
	// ___________________________________________________________________________
	// PUBLIC method / function

	public Comparator<T> getComparator() {
		return c;
	}

	public void addAll(T[] ct) {
		for (T a : ct)
			add(a);
	}

	public void addAll(Iterable<? extends T> ct) {
		for (T a : ct)
			add(a);
	}

	@Override
	public boolean addAll(Collection<? extends T> ct) {
		boolean tmp = true;
		for (T a : ct)
			if (!add(a))
				tmp = false;
		return tmp;
	}

	public boolean isEmpty() {
		if (w == null)
			return true;
		else
			return false;
	}

	public void clear() {
		w = null;
		siz = 0;
	}

	public int size() {
		return siz;
	}

	public void prt() {
		if (w != null)
			prt(w);
		System.out.println();
	}

	@Override
	public Iterator<T> iterator() {
		return mkList().iterator();
	}

	public LinkedList<T> mkList() {
		LinkedList<T> lt = new LinkedList<T>();
		if (w != null)
			addToList(w, lt);
		return lt;
	}

	@Override
	public boolean add(T a) {
		siz++;
		if (w == null) {
			w = new Dub(a);
			return true;
		} else
			return add(w, new Dub(a));
	}

	public void del(T a) {
		siz--;
		if (w == null)
			return;
		if (c.compare(w.v, a) == 0) {
			Dub buf1 = w.wl, buf2 = w.wp;
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
		siz--;
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
		siz--;
		if (w == null)
			return null;
		if (w.wp == null) {
			T tmp = w.v;
			w = w.wl;
			return tmp;
		} else
			return remmax(w);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean contains(Object a) {
		if (w == null)
			return false;
		else
			return contains(w, (T) a);
	}

	public T get(T a) {
		if (w == null)
			return null;
		else
			return get(w, a);
	}

	public T getClosest(T a, Comparator<T> com) {
		// com.compare(T a, T b) { return a-b; }
		if (w == null)
			return null;
		else
			return getClosest(w, a, com);
	}

	//
	// _______________________________________________________________________
	// inner class

	private class Dub {
		T v;
		Dub wl, wp;

		Dub(T a) {
			v = a;
			wl = null;
			wp = null;
		}
	}

	//
	// ____________________________________________________________________________
	// inner method / function

	private void prt(Dub w) {
		if (w.wl != null)
			prt(w.wl);
		System.out.print(w.v + "\t");
		if (w.wp != null)
			prt(w.wp);
	}

	private void addToList(Dub w, LinkedList<T> lt) {
		if (w.wl != null)
			addToList(w.wl, lt);
		lt.add(w.v);
		if (w.wp != null)
			addToList(w.wp, lt);
	}

	private boolean add(Dub w, Dub n) {
		if (c.compare(n.v, w.v) == -1) {
			if (w.wl != null)
				return add(w.wl, n);
			else {
				w.wl = n;
				return true;
			}
		} else {
			if (w.wp != null)
				return add(w.wp, n);
			else {
				w.wp = n;
				return true;
			}
		}
	}

	private void del(Dub w, T a) {
		if (c.compare(a, w.v) == -1) {
			if (c.compare(w.wl.v, a) != 0)
				del(w.wl, a);
			else {
				Dub buf1 = w.wl.wl, buf2 = w.wl.wp;
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
				Dub buf1 = w.wp.wl, buf2 = w.wp.wp;
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

	private T getmin(Dub w) {
		while (w.wl != null)
			w = w.wl;
		return w.v;
	}

	private T getmax(Dub w) {
		while (w.wp != null)
			w = w.wp;
		return w.v;
	}

	private T remmin(Dub w) {
		while (w.wl.wl != null)
			w = w.wl;
		T buf1 = w.wl.v;
		Dub buf2 = w.wl.wp;
		w.wl = buf2;
		return buf1;
	}

	private T remmax(Dub w) {
		while (w.wp.wp != null)
			w = w.wp;
		T buf1 = w.wp.v;
		Dub buf2 = w.wp.wl;
		w.wp = buf2;
		return buf1;
	}

	private boolean contains(Dub w, T a) {
		if (c.compare(a, w.v) == -1) {
			if (w.wl != null)
				return contains(w.wl, a);
			else
				return false;
		} else if (c.compare(a, w.v) == 1) {
			if (w.wp != null)
				return contains(w.wp, a);
			else
				return false;
		} else
			return true;
	}

	private T get(Dub w, T a) {
		if (c.compare(a, w.v) == -1) {
			if (w.wl != null)
				return get(w.wl, a);
			else
				return null;
		} else if (c.compare(a, w.v) == 1) {
			if (w.wp != null)
				return get(w.wp, a);
			else
				return null;
		} else
			return w.v;
	}

	private T getClosest(Dub w, T a, Comparator<T> com) {
		if (com.compare(a, w.v) < 0) {
			if (w.wl != null) {
				T tmp = getClosest(w.wl, a, com);
				if (Math.abs(com.compare(a, tmp)) < Math.abs(com.compare(a, w.v)))
					return tmp;
				else
					return w.v;
			} else
				return w.v;
		} else if (com.compare(a, w.v) > 0) {
			if (w.wp != null) {
				T tmp = getClosest(w.wp, a, com);
				if (Math.abs(com.compare(a, tmp)) < Math.abs(com.compare(a, w.v)))
					return tmp;
				else
					return w.v;
			} else
				return w.v;
		} else
			return w.v;
	}

	//
	// ____________________________________________________________________________
	// Collection<T> methods

	@Override
	@SuppressWarnings("unchecked")
	public boolean remove(Object o) {
		del((T) o);
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c)
			if (!contains(o))
				return false;
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean tmp = true;
		for (Object o : c)
			if (remove(o) == false)
				tmp = false;
		return tmp;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean retainAll(Collection<?> c) {
		clear();
		return addAll((Collection<? extends T>) c);
	}

	@Override
	public Object[] toArray() {
		Object[] ot = new Object[size()];
		int i = 0;
		for (T a : this)
			ot[i++] = a;
		return ot;
	}

	@Override
	public <TYPE> TYPE[] toArray(TYPE[] a) {
		// TODO Auto-generated method stub
		return null;
	}

}