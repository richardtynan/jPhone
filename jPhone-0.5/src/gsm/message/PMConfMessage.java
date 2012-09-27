package gsm.message;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.StringTokenizer;
import java.util.Vector;

public class PMConfMessage {

	private Vector<Integer> arfcn;

	private Vector<Integer> rx_lev;

	public PMConfMessage(String message) {
		this.arfcn = new Vector<Integer>();
		this.rx_lev = new Vector<Integer>();

		StringTokenizer str = new StringTokenizer(message);
		while (str.hasMoreTokens()) {
			int ahigh = Integer.parseInt(str.nextToken());
			int alow = Integer.parseInt(str.nextToken());
			int lhigh = Integer.parseInt(str.nextToken());
			Integer.parseInt(str.nextToken());
			ByteBuffer bb = ByteBuffer.allocate(2);
			bb.order(ByteOrder.LITTLE_ENDIAN);
			bb.put((byte) alow);
			bb.put((byte) ahigh);
			int ar = bb.getShort(0);
			int lev = (110 - lhigh);
			this.arfcn.addElement(ar);
			this.rx_lev.addElement(lev);
		}
	}

	public Vector<Integer> getArfcn() {
		return arfcn;
	}

	public Vector<Integer> getRx_lev() {
		return rx_lev;
	}

}
