package test;

import org.testng.annotations.Test;

public class HomeTests {
    @Test
    public void home(){
        System.out.println("HomePage");
    }

    @Test
    public void homeTestSeven() {
        System.out.println("this is Erz test");
    }

    @Test
    public void homeTest4(){
        int a = 10;
        int b = 10;
        System.out.println(a+b);
    }
}

