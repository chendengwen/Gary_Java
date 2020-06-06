package ThreadTest;

import java.util.Random;

class MakeArrays {
	
	private int length = 1024*1024;
	
	int[] makeArray() {
		int[] array = new int[length];
		
		Random random = new Random();
		for(int i=0;i<length;i++) {
			int x = random.nextInt();
			array[i] = x;
		}
		
		return array;
	}

}
