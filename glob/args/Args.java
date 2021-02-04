package glob.args;

import java.util.HashMap;

/**
 * Class for parsing and storing cli arguments
 * Using this requires stridency with formatting
 * Valid: java exe -opt1 yes --opt2=no
 * Invalid: java exe yes no
 * Invalid: java exe -opt1 yes yes
 */
class Args {

	/**
	 * Public store of the args
	 * @var HashMap<String, String>
	 */
	public HashMap<String, String> opts;



	/**
	 * Runtime test for seeing how this class works
	 * @param String[] a The cli arguments
	 * @return void
	 */
	public static void main(String[] a){
		try {
			HashMap<String, String> args = (new Args(a)).opts;
			for(String k : args.keySet()){
				System.out.println(k + " :: " + args.get(k));
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}



	/**
	 * Default class constructor
	 * @param String[] args The cli arguments to pass
	 * @throws Exception On bad argument formatting
	 */
	public Args(String[] args)
	throws Exception {
		opts = new HashMap<>();
		parseFromArgs(args);
	}




	/**
	 * Does the actual parsing and assigning of arguments
	 * @param String[] args The passed cli arguments
	 * @throws Exception On bad argument formatting
	 */
	private void parseFromArgs(String[] args)
	throws Exception {
		int cursor = 0;
		char dashChar = '-';

		while(cursor < args.length){
			String a = args[cursor];

			if(a.length() < 1
			|| Character.compare(a.charAt(0), dashChar) != 0)
				throw new Exception("Illegal argument");

			if(Character.compare(a.charAt(1), dashChar) == 0){

				String substr = a.substring(2);
				int idx = substr.indexOf("=");
				if(idx < 0) throw new Exception("Illegal '--' argument");
				opts.put(substr.substring(0, idx), substr.substring(idx + 1));

			} else {
				opts.put(a.substring(1), args[++cursor]);
			}

			cursor++;
		}
	}

}

