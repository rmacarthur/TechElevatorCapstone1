package com.techelevator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import java.lang.String;
import java.math.BigDecimal;

import static org.junit.Assert.*;
public class ProductTest {


    @Test
    public void getSlot_enter_stringA1_returnA1() {

    Product product = new Product("A1", "Potato Crisps", new BigDecimal(3.05), 5);


    String actual = product.getSlot();
    String expected = "A1";

        Assert.assertEquals(expected, actual);

}
    @Test
    public void getName_enter_get_PotatoCrisps() {

        Product product = new Product("A1", "Potato Crisps", new BigDecimal(3.05), 5);


        String actual = product.getName();
        String expected = "Potato Crisps";

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void getPrice_enter_get_3dec05() {

        Product product = new Product("A1", "Potato Crisps", new BigDecimal(3.05), 5);


        BigDecimal actual = product.getPrice();
        BigDecimal expected = new BigDecimal(3.05);

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void getQuantity_enter_get_5() {

        Product product = new Product("A1", "Potato Crisps", new BigDecimal(3.05), 5);


        int actual = product.getQuantity();
        int expected = 5;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void set_Sound_Test() {


        Chip chip = new Chip("A1", "Potato Crisp", new BigDecimal(3.05), 5);

        String actual = chip.getSound();
        String expected = "Crunch, crunch yum!";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void set_Slot_Test() {


        Product product = new Product("A1", "Potato Crisps", new BigDecimal(3.05), 5);

        String actual = product.getSlot();
        String expected = "A1";

        Assert.assertEquals(expected, actual);
    }
}