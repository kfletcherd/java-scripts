package glob.uuid;

import java.util.UUID;

class GetUUID {

	public static void main(String[] a){
		int loops = 1;

		if(a.length > 0)
			loops = Integer.parseInt(a[0]);

		loops = Math.min(loops, 1000);

		while(loops-- > 0)
			System.out.println(UUID.randomUUID());
	}

}
