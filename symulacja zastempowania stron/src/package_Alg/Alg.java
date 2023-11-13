package package_Alg;

public abstract class Alg {
	private final String name;
	protected final int MaxFrame;
	protected final int MaxPage;

	public Alg(String name, int maxFrame, int maxPage) {
		this.name = name;
		MaxFrame = maxFrame;
		MaxPage = maxPage;
	}

	public abstract int run(int[] dt);

	protected int[][] copyData(int[][] data) {
		int[][] rtt = new int[data.length][];
		for (int i = 0; i < data.length; i++) {
			rtt[i] = new int[data[i].length];
			for (int j = 0; j < data[i].length; j++)
				rtt[i][j] = data[i][j];
		}
		return rtt;
	}

	public void prt() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return name + ":";
	}

	public String getName() {
		return name;
	}

}
