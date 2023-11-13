package package_Alg;

import package_Main.BinTree;
import package_Main.Proc;

public abstract class DeadlineAlg extends Alg {
	public int t, j, p, sp;
	protected BinTree<Proc> other, btp;

	public DeadlineAlg(Proc[][] dtt, String name) {
		super(dtt, name);
		// TODO Auto-generated constructor stub
	}

}
