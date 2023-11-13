package package_Struct;

import java.io.Serializable;

public class Zad implements Serializable{		//Pii
	private static final long serialVersionUID = 1L;
	public int n,o;
	public Zad() {}
	public Zad(int n, int o) 	{this.n = n;		this.o = o;	}
	public Zad(Zad z) 			{this.n = z.getN();	this.o = z.getO();	}
	public int getN() 			{return n;}
	public int getO() 			{return o;}
	public void setN(int n) 	{this.n = n;}
	public void setO(int o) 	{this.o = o;}
	public void set(int n,int o){this.n = n;	this.o = o;	}
	public String toString()	{return n+"."+o;	}
	public void prt()			{System.out.println(this);	}
}
