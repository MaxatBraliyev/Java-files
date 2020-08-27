public class TestSort {
    public static void main(String[] args) {
        int a = 12345;
        String b = Integer.toString(a);
        char[] c = b.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (i >= 0 && i < c.length-1) {
                System.out.print(c[i]+"+");
            }else if(i<=c.length){
                System.out.print(c[i]);
            }
        }
    }
}