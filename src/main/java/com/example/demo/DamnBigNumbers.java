package com.example.demo;

public class DamnBigNumbers {
    public static DamnBigNumber add(DamnBigNumber a, long b) {
        return add(a, new DamnBigNumber(b));
    }
    public static DamnBigNumber add(DamnBigNumber a, DamnBigNumber b) {

        if (a.getNumStr().length() < b.getNumStr().length()){
            int diff = b.getNumStr().length()-a.getNumStr().length();
            for (int i=0; i< diff; i++){
                a.setNumStr("0" + a.getNumStr());
            }
        }
        else if (a.getNumStr().length() > b.getNumStr().length()){
            int diff = a.getNumStr().length()-b.getNumStr().length();
            for (int i=0; i< diff; i++){
                b.setNumStr("0" + b.getNumStr());
            }
        }

        String s = "";
        int carry = 0;

        for (int i = a.getNumStr().length()-1; i>=0; i--){

            int digitResult = a.getNumStr().charAt(i)-48 + b.getNumStr().charAt(i)-48 + carry;
            if (digitResult >= 10){
                digitResult -= 10;
                carry = 1;
            }
            else{
                carry = 0;
            }
            s = String.format("%d", digitResult) + s;
        }
        if (carry > 0) s = "1"+s;
        DamnBigNumber c = new DamnBigNumber(s);

        return c;
    }

    public static DamnBigNumber subtract(DamnBigNumber a, long b) throws Exception {
        return subtract(a, new DamnBigNumber(b));
    }

    /**
     * TODO - subtract big "b" from big "a"
     * @param a DamnBigNumber
     * @param b DamnBigNumber
     * @return DamnBigNumber
     */
    public static DamnBigNumber subtract(DamnBigNumber a, DamnBigNumber b) throws Exception {

        if (a.getNumStr().length() < b.getNumStr().length()){
            int diff = b.getNumStr().length()-a.getNumStr().length();
            for (int i=0; i< diff; i++){
                a.setNumStr("0" + a.getNumStr());
            }
        }
        else if (a.getNumStr().length() > b.getNumStr().length()){
            int diff = a.getNumStr().length()-b.getNumStr().length();
            for (int i=0; i< diff; i++){
                b.setNumStr("0" + b.getNumStr());
            }
        }

        if (compare(a.getNumStr(), b.getNumStr()) < 0){
            throw new Exception("negative result: " + a.getNumStr() + " - " + b.getNumStr());
        }

        String s = "";
        int borrow = 0;

        for (int i = a.getNumStr().length()-1; i>=0; i--){

            int digitResult = a.getNumStr().charAt(i)-48 - (b.getNumStr().charAt(i)-48) - borrow;
            if (digitResult < 0){
                digitResult += 10;
                borrow = 1;
            }
            else{
                borrow = 0;
            }
            s = String.format("%d", digitResult) + s;
        }

        while (s.length() > 1 && s.charAt(0) == '0'){
            s = s.substring(1);
        }

        DamnBigNumber c = new DamnBigNumber(s);

        return c;
    }

    public static DamnBigNumber multiply(DamnBigNumber a, long b) {
        return multiply(a, new DamnBigNumber(b));
    }

    /**
     * TODO - multiply big "a" by big "b"
     * @param a DamnBigNumber
     * @param b DamnBigNumber
     * @return DamnBigNumber
     */
    public static DamnBigNumber multiply(DamnBigNumber a, DamnBigNumber b) {
        int lenA = a.getNumStr().length();
        int lenB = b.getNumStr().length();
        int[] digits = new int[lenA + lenB];

        for (int i = lenA-1; i>=0; i--){
            for (int j = lenB-1; j>=0; j--){
                digits[i+j+1] += (a.getNumStr().charAt(i)-48) * (b.getNumStr().charAt(j)-48);
            }
        }

        for (int i = digits.length-1; i>0; i--){
            digits[i-1] += digits[i] / 10;
            digits[i] = digits[i] % 10;
        }

        String s = "";
        int start = 0;
        while (start < digits.length-1 && digits[start] == 0){
            start++;
        }
        for (int i = start; i < digits.length; i++){
            s = s + String.format("%d", digits[i]);
        }

        DamnBigNumber c = new DamnBigNumber(s);

        return c;
    }

    public static DamnBigNumber divide(DamnBigNumber a, long b) throws Exception {
        return divide(a, new DamnBigNumber(b));
    }

    /**
     * TODO - divide big "a" by big "b"
     * @param a DamnBigNumber
     * @param b DamnBigNumber
     * @return DamnBigNumber
     */
    public static DamnBigNumber divide(DamnBigNumber a, DamnBigNumber b) throws Exception {
        String divisor = b.getNumStr();

        boolean divisorIsZero = true;
        for (int i = 0; i < divisor.length(); i++){
            if (divisor.charAt(i) != '0'){
                divisorIsZero = false;
            }
        }
        if (divisorIsZero){
            throw new Exception("divide by zero");
        }

        String s = "";
        String remainder = "0";

        for (int i = 0; i < a.getNumStr().length(); i++){

            remainder = remainder + a.getNumStr().charAt(i);
            while (remainder.length() > 1 && remainder.charAt(0) == '0'){
                remainder = remainder.substring(1);
            }

            int count = 0;
            while (compare(remainder, divisor) >= 0){
                remainder = subtract(new DamnBigNumber(remainder), new DamnBigNumber(divisor)).getNumStr();
                count++;
            }

            s = s + String.format("%d", count);
        }

        while (s.length() > 1 && s.charAt(0) == '0'){
            s = s.substring(1);
        }

        DamnBigNumber c = new DamnBigNumber(s);

        return c;
    }

    private static int compare(String x, String y) {
        if (x.length() != y.length()){
            return x.length() < y.length() ? -1 : 1;
        }
        return x.compareTo(y);
    }

    /**
     * TODO - implement areEqual method variants
     * @param a
     * @param b
     * @return boolean
     */
    public static boolean areEqual(DamnBigNumber a, DamnBigNumber b) {
        return a.equals(b);
    }

    public static boolean areEqual(DamnBigNumber a, long b) {
        return areEqual(a, new DamnBigNumber(b));
    }

    public static boolean areEqual(long a, DamnBigNumber b) {
        return areEqual(new DamnBigNumber(a), b);
    }
}
