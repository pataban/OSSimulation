package package_Main;

import java.util.Arrays;
import java.util.Random;

import package_Alg.CSCAN;
import package_Alg.FCFS;
import package_Alg.SCAN;
import package_Alg.SSTF;

public class Symul {
	private final int TTSiz;
	private final int TSiz;
	private final int MaxStart;
	private final int MaxDisk;
	private final Proc[][] data;

	public Symul(int ttsiz, int tsiz, int rt, int maxstart, int maxdisk) {
		TTSiz = ttsiz;
		TSiz = tsiz;
		MaxStart = maxstart;
		MaxDisk = maxdisk;
		Random rgen = new Random();

		data = new Proc[TTSiz][TSiz];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++)
				data[i][j] = new Proc(j, rgen.nextInt(MaxDisk), rgen.nextInt(MaxStart));

			for (int j = 0; j < rt; j++)
				data[i][j].end = data[i][j].start + rgen.nextInt(MaxStart);

			Arrays.sort(data[i], new Proc.ProcStartComp());
			for (int j = 0; j < data[i].length; j++)
				data[i][j].id = j;
		}
	}

	public void run() {
		prtData();
		new FCFS(copyData()).doAll();
		new SSTF(copyData()).doAll();
		new SCAN(copyData(), MaxDisk).doAll();
		new CSCAN(copyData(), MaxDisk).doAll();
	}

	public void prtData() {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++)
				System.out.print(data[i][j] + "\t\t");
			System.out.print("\n");
		}
		System.out.println();
	}

	public Proc[][] copyData() {
		Proc[][] rtt = new Proc[data.length][];
		for (int i = 0; i < data.length; i++) {
			rtt[i] = new Proc[data[i].length];
			for (int j = 0; j < data[i].length; j++)
				rtt[i][j] = new Proc(data[i][j].id, data[i][j].place, data[i][j].start, data[i][j].end);
		}
		return rtt;
	}
}