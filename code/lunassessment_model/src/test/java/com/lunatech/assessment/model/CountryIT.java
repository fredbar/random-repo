/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lunatech.assessment.model;

import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Fred
 */
public class CountryIT
{

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    public CountryIT()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of add method, of class Country.
     */
    @Test
    public void testAdd()
    {
        System.out.println( "add" );
        Airport newcomer = null;
        Country instance = null;
        instance.add( newcomer );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of contains method, of class Country.
     */
    @Test
    public void testContains()
    {
        System.out.println( "contains" );
        Airport target = null;
        Country instance = null;
        boolean expResult = false;
        boolean result = instance.contains( target );
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of equals method, of class Country.
     */
    @Test
    public void testEquals()
    {
        System.out.println( "equals" );
        Object obj = null;
        Country instance = null;
        boolean expResult = false;
        boolean result = instance.equals( obj );
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of getAirport method, of class Country.
     */
    @Test
    public void testGetAirport()
    {
        System.out.println( "getAirport" );
        int index = 0;
        Country instance = null;
        Airport expResult = null;
        Airport result = instance.getAirport( index );
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of getCode method, of class Country.
     */
    @Test
    public void testGetCode()
    {
        System.out.println( "getCode" );
        Country instance = null;
        String expResult = "";
        String result = instance.getCode();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of getName method, of class Country.
     */
    @Test
    public void testGetName()
    {
        System.out.println( "getName" );
        Country instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of hashCode method, of class Country.
     */
    @Test
    public void testHashCode()
    {
        System.out.println( "hashCode" );
        Country instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of iterator method, of class Country.
     */
    @Test
    public void testIterator()
    {
        System.out.println( "iterator" );
        Country instance = null;
        Iterator<Airport> expResult = null;
        Iterator<Airport> result = instance.iterator();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of toString method, of class Country.
     */
    @Test
    public void testToString()
    {
        System.out.println( "toString" );
        Country instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

}
