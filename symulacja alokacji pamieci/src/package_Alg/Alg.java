package package_Alg;

import package_Struct.Piit;

public abstract class Alg {
	private final String name;
	protected LRU[] paging;
	protected final int maxFrame;
	protected int[] faults;

	public Alg(String name, int maxFrame) {
		this.name = name;
		this.maxFrame = maxFrame;
	}

	public int[] run(Piit[] proct) {
		load(proct);
		runAlg();
		return faults;
	}

	public abstract void runAlg();

	public abstract void load(Piit[] proct);

	protected void clearFaults(int n) {
		faults = new int[n];
		for (int i = 0; i < faults.length; i++)
			faults[i] = 0;
	}

	public double prtEnd() {
		this.prt();
		int s = 0;
		for (int i : faults) {
			System.out.print(i + "\t");
			s += i;
		}
		System.out.println();
		System.out.printf("AVG= %.2f\tAll= %d\n\n", (double) s / faults.length, s);
		return (double) s / faults.length;
	}

	public void prt() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

}
