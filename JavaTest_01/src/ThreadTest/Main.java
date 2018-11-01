package ThreadTest;

public class Main {
	
	private static int[] array = new MakeArrays().makeArray();

	
	public static void main(String[] args) {
			
		/**
         * 串行测试
		 */
		long startTime = System.currentTimeMillis();
		new SerialDemo().mergeSort(array);
		long endTime = System.currentTimeMillis();
		System.out.println("串行使用时间:" + (endTime - startTime) + "ms");
		
		/**
		 * 
		 */
//		long startTime2 = System.currentTimeMillis();
//		try {
//			new ThreadDemo().mergeSort(array);
//		} catch (InterruptedException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		long endTime2 = System.currentTimeMillis();
//		System.out.println("线程使用时间:" + (endTime2 - startTime2) + "ms");
	}

}
