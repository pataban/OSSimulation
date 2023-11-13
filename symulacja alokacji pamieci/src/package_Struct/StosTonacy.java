package package_Struct;

public class StosTonacy {
	protected int[] data;
	protected int id; // on next empty place
	protected int siz;

	public StosTonacy(int siz) {
		data = new int[siz];
		id = 0;
		siz = 0;
	}

	public void add(int a) {
		if (this.size() < this.maxSize())
			siz++;
		data[id++] = a;
		id %= this.maxSize();
	}

	public int get() {
		if (this.isEmpty())
			return 0;
		return data[(id - 1 >= 0) ? id - 1 : data.length - 1];
	}

	public int get(int index) {
		if (index >= this.size())
			return 0;
		return data[(id - 1 - index + this.maxSize()) % this.maxSize()];
	}

	public int remove() {
		if (this.isEmpty())
			return 0;
		siz--;
		if (--id < 0)
			id += this.maxSize();
		return data[id];
	}

	public boolean set(int a) {
		if (this.isEmpty())
			return false;
		data[(id - 1 >= 0) ? id - 1 : data.length - 1] = a;
		return true;
	}

	public boolean set(int index, int a) {
		if (index >= id)
			return false;
		data[(id - 1 - index + this.maxSize()) % this.maxSize()] = a;
		return true;
	}

	public int size() {
		return siz;
	}

	public int maxSize() {
		return data.length;
	}

	public boolean isEmpty() {
		return siz == 0;
	}

	public boolean isFull() {
		return size() == maxSize();
	}

	public void clear() {
		data = new int[data.length];
		id = 0;
		siz = 0;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (int i = id - 1, j = this.size(); j > 0; i--, j--) {
			if (i < 0)
				i += this.maxSize();
			buf.append(data[i]).append("\t");

		}
		return buf.toString();
	}

	public void prt() {
		System.out.println(this);
	}

}
