package com.techelevator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

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


        String actual = product.getSlot();
        String expected = "A1";

        Assert.assertEquals(expected, actual);

    }

}


//    @Before
//    public void setup() {
//
//    }

