package glob.generator;

import java.util.UUID;
import java.util.SplittableRandom;

final class GetJSON {

	public static void main(String a[]){
		int loops = 1;

		if(a.length > 0)
			loops = Integer.parseInt(a[0]);

		loops = Math.min(loops, 1000);

		while(loops-- > 0)
			System.out.print(getObj());
	}

	private static int propCounter = 0;

	private static SplittableRandom rng;

// // // // // Static Constructor

	static {
		rng = new SplittableRandom();
	}

// // // // // Public Methods

	public static String getObj(){
		UUID uuid = UUID.randomUUID();

		String ret = "{\"uuid\":\"" + uuid + "\",";
		ret += "\"att_1\":\"" + getAttOne() + "\",";
		ret += "\"att_2\":\"" + getAttTwo() + "\",";
		ret += "\"uuid_array\": [{\"a\":\"stuff\",\"b\":\"junk\",\"uuid\":\"" + uuid + "\"}],";

		int extraProps = rng.nextInt(50) + 1;
		while(extraProps-- > 0) ret += getRandomKeyVal();

		ret += "\"end\":true}";
		return ret;
	}

	public static String getObjWitCols(){
		String ret = UUID.randomUUID() + "\t";
		ret += getAttOne() + "\t";
		ret += getAttTwo() + "\t{";

		int extraProps = rng.nextInt(50) + 1;
		while(extraProps-- > 0) ret += getRandomKeyVal();

		ret += "\"end\":true}";
		return ret;
	}

	public static int getAttOne(){
		return rng.nextInt(5);
	}

	public static String getAttTwo(){
		return "att_2_" + rng.nextInt(1000);
	}

// // // // // Private Methods

	private static String getRandomKeyVal(){
		int toGet = rng.nextInt(5);
		String out = "\"";
		propCounter++;

		switch(toGet){
			case 0:
				out += getRandomString(rng.nextInt(11));
				out += "\":\"";
				out += getRandomString(rng.nextInt(11));
				out += "\",";
				break;
			case 1:
				out += getRandomString(rng.nextInt(11));
				out += "\":";
				out += rng.nextInt(1000001);
				out += ",";
				break;
			case 2:
				out += getRandomString(rng.nextInt(11));
				out += "bool" + propCounter;
				out += "\":true,";
				break;
			case 3:
				out += getRandomString(rng.nextInt(11));
				out += "\":[" + rng.nextInt(1000000) + "],";
				break;
			case 4:
				out += getRandomString(rng.nextInt(11));
				out += "\":{\"nested\":[" + rng.nextInt(1000000) + "],\"dNested\":{\"val\":true}},";
				break;
		}

		return out;
	}

	private static String getRandomString(int length){
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String out = "";
		while(length-- > -1)
			out += chars.charAt(rng.nextInt(chars.length()));
		return out;
	}

}

