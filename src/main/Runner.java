package main;

public class Runner {
    public static void main(String[] args) {
        try{
            new Counter().start();
        } catch (Exception e ){
            e.printStackTrace();
        }
    }
}
