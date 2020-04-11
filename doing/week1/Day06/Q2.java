/*
分析：
1. 分成两部分，分别转置。{1, 2, 3, 4, 5, 6, 7} -> {4, 3, 2, 1}，{7, 6, 5}
2. 合并。 {4, 3, 2, 1, 7, 6, 5}
3. 转置。 {5, 6, 7, 1, 2, 3 ,4}
*/


public class Q2 {
    // 转置数组
    public static void trans(int[] arr,int a,int b) {
        for (int temp,i = 0; i <= (b - a) / 2; i++) {
            temp = arr[a + i];
            arr[a + i] = arr[b - i];
            arr[b - i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;

        System.out.print("当前数组：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        trans(arr, 0, arr.length - k -1 );    // 转置前半段
        trans(arr, arr.length - k, arr.length - 1);   // 转置后半段
        trans(arr, 0, arr.length - 1);    // 全部转置

        System.out.printf("\n向右移动 %d 位后：", k);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}