package ThreadTest;

public class Sort {
	
	public static void sort(int[] array) {
		int length = array.length;
		int[] temArray = new int[length];
		
		sort(array,temArray,0,length-1);
	}
	
	/**
	 * 把数组分成左右两个数组,先排序左边的成员,再排序右边的
	 **/
	private static void sort(int[] array, int[] tmpArray, int left, int right){
		if(left < right) {
			int center = (left + right)/2;    //取中间值
			sort(array, tmpArray, left, center);    //递归分解
			sort(array, tmpArray, center+1, right);    //递归分解
//			merge(array, tmpArray, left, center+1, right);    //合并排序
//			System.out.println("left =" + left + "right =" + right);
		}
	}
	
	private static void merge(int[] array, int[] temArray,int leftStart,int rightStart,int rightEnd) {
		int leftEnd = rightStart - 1;
		int temPos = leftStart;
		int total = rightEnd - leftStart + 1;
		
		while (leftStart <= leftEnd && rightStart <= rightEnd) {
			if (array[leftStart] <= array[rightStart]) {
				//
				temArray[temPos++] = array[leftStart++];
			}else {
				temArray[temPos++] = array[rightStart++];
			}
		}
		
		while (leftStart <= leftEnd) {
			temArray[temPos ++] = array[leftStart];
		}
		
		while (rightStart <= rightEnd) {
			temArray[temPos++] = array[rightStart++];
		}
		
		for (int i = 0; i < total; i++) {
			array[rightEnd] = temArray[rightEnd];
		}
	}
	
	
}
