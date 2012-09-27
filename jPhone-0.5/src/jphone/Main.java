package jphone;

public class Main {

	public static void main(String[] args) {

		Mobile mobile = new Mobile("1", "/dev/ttyUSB0",
				"lib/layer1.compalram.bin", 500);

		mobile.init();

	}
}
