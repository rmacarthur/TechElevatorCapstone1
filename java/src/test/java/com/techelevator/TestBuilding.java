package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class TestBuilding {

    private Product productTest;

    @Before
    public void setUp() {

        productTest = new Product ("A1, "Potato Crisps", new BigDecimal(3.05), 5);

    }

    @Test
    public void loadsCorrectly() {

        Assert.assertEquals("A1", productTest.getSlot());
        Assert.assertEquals("Potato Crisps", productTest.getName());
        Assert.assertEquals(new BigDecimal(3.05), productTest.getBalance());
        Assert.assertEquals(5, productTest.getQuantity());
    }

    @Test
    public void decreaseCorrectly(){

        productTest.decreaseQuantity();
        Assert.assertEquals(4, productTest.getQuantity);
    }

    @Test
    public void isSoldOut() {

        String response = productTest.();         // if (slot.getQuantity() == 0, return sold out - figure this out
        Assert.assertEquals("0", response);
    }

    @Test
    public void chipTest(){     //////// should we set up another "Before" for this? put them all in diff classes?
                                 //////// repeat this test for a Candy, a Drink, and a Gum

        Chip chip = new Chip ("Stackers", new BigDecimal("1.45"));

        Assert.assertEquals("Stackers", slot.getName());
        Assert.assertEquals("1.45", slot.getPrice().toString());
    }
}
