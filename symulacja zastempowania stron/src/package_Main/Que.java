package package_Main;

import java.util.Comparator;
import java.util.LinkedList;

public class Que extends LinkedList<Integer> implements Comparable<Que> {
	private static final long serialVersionUID = 1L;
	int page;

	public Que(int page) {
		super();
		this.page = page;
	}

	public int getPage() {
		return page;
	}

	@Override
	public int compareTo(Que o) {
		return new Que.Comp().compare(this, o);
	}

	public static class Comp implements Comparator<Que> {

		@Override
		public int compare(Que o1, Que o2) {
			if (o1.isEmpty() && o1.isEmpty())
				return 0;
			if (o1.isEmpty())
				return 1;
			if (o2.isEmpty())
				return -1;
			if (o1.getFirst() < o2.getFirst())
				return -1;
			if (o1.getFirst() > o2.getFirst())
				return 1;
			return 0;
		}

	}

}
