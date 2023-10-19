package Assignment3;

public class lcg{
    
    private int a = 11; // a is a constant multiplier
    private int currentX;
    private int c; // c is the added constant
    private int mod = 13; // mod is the modulus
    private int IV = 1; // IV is the initial value

    public lcg(int a, int c, int mod, int IV){
        this.a = a;
        this.currentX = IV;
        this.c = c;
        this.mod = mod;
        this.IV = IV;
    }

    public int nextX(){
        currentX = (a * currentX + c) % mod;
        return currentX;
    }

    public int getCurrentX(){
        return currentX;
    }




}
