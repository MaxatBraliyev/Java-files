package kz.bmt;

//Checking the data it is Palindrome or not
public class Palindrome {

    boolean isPalindrome = true;

    public boolean isPalindromes(String s) {

        char[] d = s.toCharArray();

        for(int i = 0; i < d.length; i++) {
            if (d[i] != d[d.length - 1 - i]) {
                isPalindrome = false;
                break;
            }
        } return isPalindrome;
    }

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        palindrome.isPalindromes("123321");
        System.out.println("");
        System.out.println("Check in data it is palindrome: " + palindrome.isPalindrome);
    }
}


