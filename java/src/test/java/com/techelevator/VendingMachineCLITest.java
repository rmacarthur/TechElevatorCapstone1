package com.techelevator;

import com.techelevator.view.UsersChange;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.techelevator.Logger;
import com.techelevator.Product;
import com.techelevator.Candy;
import com.techelevator.Chip;
import com.techelevator.Drink;
import com.techelevator.Gum;
import com.techelevator.VendingMachineCLI;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class VendingMachineCLITest {


    private Product productTest;
    private Product productTest2;


    @Before
    public void setUp() {

        productTest = new Product("A1", "Potato Crisps", new BigDecimal(3.05), 5);
        productTest2 = new Product("A1", "Potato Crisps", new BigDecimal(3.05), 0);



    }

    @Test
    public void loadsCorrectly() {

        Assert.assertEquals("A1", productTest.getSlot());
        Assert.assertEquals("Potato Crisps", productTest.getName());
        Assert.assertEquals(new BigDecimal(3.05), productTest.getPrice());
        Assert.assertEquals(5, productTest.getQuantity());
    }

    @Test
    public void decreaseCorrectly(){

        productTest.decreaseQuantity();
        Assert.assertEquals(4, productTest.getQuantity());
    }

    @Test
    public void isSoldOut() {

//        int response = ;         // if (slot.getQuantity() == 0, return sold out - figure this out
        Assert.assertEquals("0", String.valueOf(productTest2.getQuantity()));
    }

//    @Test
//    public void chipTest(){     //////// should we set up another "Before" for this? put them all in diff classes?
//        //////// repeat this test for a Candy, a Drink, and a Gum
//
//        Chip chip = new Chip ("A2", "Stackers", new BigDecimal(1.45), 5);
//
//        Assert.assertEquals("Stackers", slot.getName());
//        Assert.assertEquals("1.45", slot.getPrice().toString());
//    }
}
