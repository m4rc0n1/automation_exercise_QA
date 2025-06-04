package tests.sanity;

import org.testng.annotations.Test;

public class SanityTest {
    @Test(groups = {"sanity"})
    public  void sanityTest(){
        System.out.println("Sanity test started.");
    }
}
