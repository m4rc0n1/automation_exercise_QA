package tests.smoke;

import org.testng.annotations.Test;

public class SmokeTest {
    @Test(groups = {"smoke"})
    public  void smokeTest(){
        System.out.println("Smoke test started.");
    }
}
