package package_Alg;

import java.util.Arrays;
import java.util.Random;

import package_Struct.Zad;

public abstract class Strat {
	Random rgen;
	protected int[] proc; // tablica procesorow
	private String name;
	protected int zap = 0, mig = 0;
	protected double avgObc = Double.NaN, avgOdch = Double.NaN;

	public Strat(int[] proc, String name) {
		rgen = new Random();
		this.proc = proc;
		this.name = name;
	}

	/**
	 * Dodaje do procesora wybranego zgodnie z strategia. Zwraca id do tego
	 * procesora.
	 */
	protected abstract int finalAdd(Zad zad);

	public String getName() {
		return name;
	}

	public boolean add(Zad zad) {
		int id = finalAdd(zad);

		return (id == -1) ? false : true;
	}

	public double avgObc() {
		if (!((Double) avgObc).isNaN())
			return avgObc;
		double s = 0;
		for (int i : proc)
			s += i;
		avgObc = s / proc.length;
		return avgObc;
	}

	public double avgOdch() {
		if (!((Double) avgOdch).isNaN())
			return avgOdch;
		if (!((Double) avgObc).isNaN())
			avgObc();
		double s = 0;
		for (int i : proc)
			s += Math.abs(i - avgObc());
		avgOdch = s / proc.length;
		return avgOdch;
	}

	public int zap() {
		return zap;
	}

	public int mig() {
		return mig;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(name).append(":\t");
		buf.append("avgObc=").append((double) Math.round(avgObc() * 100) / 100).append('\t');
		buf.append("avgOdch=").append((double) Math.round(avgOdch() * 100) / 100).append('\t');
		buf.append("zap=").append(zap()).append('\t');
		buf.append("mig=").append(mig()).append('\t');
		return buf.toString();
	}

	public void prt() {
		System.out.println(this);
	}

	public void prtProc() {
		System.out.println(Arrays.toString(proc));
	}

}
