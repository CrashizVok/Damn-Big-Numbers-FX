package com.example.demo;

public class DamnBigNumber {
    private String numStr = "0";

    public String getNumStr() {
        return numStr;
    }

    public void setNumStr(String numStr) {
        this.numStr = numStr;
    }

    public DamnBigNumber() {
        setNumStr("0");
    }
    public DamnBigNumber(int x) {
        setNumStr(String.format("%d", x));
    }
    public DamnBigNumber(long x) {
        setNumStr(String.format("%d", x));
    }
    public DamnBigNumber(String xStr) {
        while (xStr.length() > 1 && xStr.charAt(0) == '0'){
            xStr = xStr.substring(1);
        }
        setNumStr(xStr);
    }

    public static final DamnBigNumber DBN_ZERO = new DamnBigNumber(0);
    public static final DamnBigNumber DBN_ONE = new DamnBigNumber(1);

    @Override
    public String toString() {
        return getNumStr();
    }

    @Override
    public boolean equals(Object obj) {
        DamnBigNumber other = (DamnBigNumber) obj;
        return this.getNumStr().equals((other).getNumStr());

    }

    public long getLong() {
        return Long.parseLong(getNumStr());
    }


    public DamnBigNumber increment() {
        String s = "";
        int carry = 1;

        for (int i = getNumStr().length()-1; i >= 0; i--){
            int digitResult = getNumStr().charAt(i)-48 + carry;
            carry = digitResult / 10;
            s = String.format("%d", digitResult % 10) + s;
        }
        if (carry > 0) s = "1" + s;

        return new DamnBigNumber(s);
    }

    public DamnBigNumber decrement() throws Exception {
        String s = "";
        int borrow = 1;

        for (int i = getNumStr().length()-1; i >= 0; i--){
            int digitResult = getNumStr().charAt(i)-48 - borrow;
            if (digitResult < 0){
                digitResult += 10;
                borrow = 1;
            }
            else{
                borrow = 0;
            }
            s = String.format("%d", digitResult) + s;
        }

        if (borrow > 0){
            throw new Exception("negative result: " + getNumStr() + " - 1");
        }

        while (s.length() > 1 && s.charAt(0) == '0'){
            s = s.substring(1);
        }

        return new DamnBigNumber(s);
    }
	
    public boolean isPrime() {
        long n = getLong();

        if (n < 2){
            return false;
        }
        if (n % 2 == 0){
            return n == 2;
        }
        for (long d = 3; d <= n / d; d += 2){
            if (n % d == 0){
                return false;
            }
        }
        return true;
    }
}


