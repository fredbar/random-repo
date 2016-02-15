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
public class AirportIT
{

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    public AirportIT()
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
     * Test of add method, of class Airport.
     */
    @Test
    public void testAdd()
    {
        System.out.println( "add" );
        Runway instance_2 = null;
        Airport instance = null;
        instance.add( instance_2 );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of contains method, of class Airport.
     */
    @Test
    public void testContains()
    {
        System.out.println( "contains" );
        Runway instance_2 = null;
        Airport instance = null;
        boolean expResult = false;
        boolean result = instance.contains( instance_2 );
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of equals method, of class Airport.
     */
    @Test
    public void testEquals()
    {
        System.out.println( "equals" );
        Object obj = null;
        Airport instance = null;
        boolean expResult = false;
        boolean result = instance.equals( obj );
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of get method, of class Airport.
     */
    @Test
    public void testGet()
    {
        System.out.println( "get" );
        int index = 0;
        Airport instance = null;
        Runway expResult = null;
        Runway result = instance.get( index );
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of getName method, of class Airport.
     */
    @Test
    public void testGetName()
    {
        System.out.println( "getName" );
        Airport instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of hashCode method, of class Airport.
     */
    @Test
    public void testHashCode()
    {
        System.out.println( "hashCode" );
        Airport instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of iterator method, of class Airport.
     */
    @Test
    public void testIterator()
    {
        System.out.println( "iterator" );
        Airport instance = null;
        Iterator<Runway> expResult = null;
        Iterator<Runway> result = instance.iterator();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of toString method, of class Airport.
     */
    @Test
    public void testToString()
    {
        System.out.println( "toString" );
        Airport instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

}
