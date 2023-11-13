package package_Struct;

public class Rate extends StosTonacy {
	int rate = 0;

	public Rate(int siz) {
		super(siz);
	}

	@Override
	public void add(int a) {
		if (this.isFull()) {
			rate -= data[id];
		}
		rate += a;
		super.add(a);
	}

	public int getRate() {
		if (this.isEmpty())
			return this.maxSize() - 1;
		return rate;
	}

}
