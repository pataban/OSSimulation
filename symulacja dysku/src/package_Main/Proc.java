package package_Main;

import java.util.Comparator;

public class Proc {
	public int id, start, place, wait, end;

	public Proc(int i, int p, int s) {
		id = i;
		place = p;
		start = s;
		wait = 0;
		end = 0;
	}

	public Proc(int i, int p, int s, int e) {
		id = i;
		place = p;
		start = s;
		wait = 0;
		end = e;
	}

	// __________________________________________________________________________________________
	// override

	@Override
	public String toString() {
		return "id=" + id + "\tsta=" + start + "\tpla=" + place +"\tend="+end;
	}

	// __________________________________________________________________________________________
	// Comparator

	public static class ProcIdComp implements Comparator<Proc> {

		@Override
		public int compare(Proc o1, Proc o2) {
			if (o1.id < o2.id)
				return -1;
			if (o1.id > o2.id)
				return 1;
			return 0;
		}

	}

	public static class ProcStartComp implements Comparator<Proc> {

		@Override
		public int compare(Proc o1, Proc o2) {
			if (o1.start < o2.start)
				return -1;
			if (o1.start > o2.start)
				return 1;
			return 0;
		}

	}

	public static class ProcPlaceComp implements Comparator<Proc> {

		@Override
		public int compare(Proc o1, Proc o2) {
			if (o1.place < o2.place)
				return -1;
			if (o1.place > o2.place)
				return 1;
			return 0;
		}

	}

	public static class ProcEndComp implements Comparator<Proc> {

		@Override
		public int compare(Proc o1, Proc o2) {
			if (o1.place < o2.place)
				return -1;
			if (o1.place > o2.place)
				return 1;
			return 0;
		}

	}

}
