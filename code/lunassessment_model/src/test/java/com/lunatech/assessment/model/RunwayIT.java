/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lunatech.assessment.model;

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
public class RunwayIT
{

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    public RunwayIT()
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
     * Test of equals method, of class Runway.
     */
    @Test
    public void testEquals()
    {
        System.out.println( "equals" );
        Object obj = null;
        Runway instance = new Runway();
        boolean expResult = false;
        boolean result = instance.equals( obj );
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of getLength method, of class Runway.
     */
    @Test
    public void testGetLength()
    {
        System.out.println( "getLength" );
        Runway instance = new Runway();
        float expResult = 0.0F;
        float result = instance.getLength();
        assertEquals( expResult, result, 0.0 );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of getOrientation method, of class Runway.
     */
    @Test
    public void testGetOrientation()
    {
        System.out.println( "getOrientation" );
        Runway instance = new Runway();
        int expResult = 0;
        int result = instance.getOrientation();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of getSurfaceType method, of class Runway.
     */
    @Test
    public void testGetSurfaceType()
    {
        System.out.println( "getSurfaceType" );
        Runway instance = new Runway();
        SurfaceType expResult = null;
        SurfaceType result = instance.getSurfaceType();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of hashCode method, of class Runway.
     */
    @Test
    public void testHashCode()
    {
        System.out.println( "hashCode" );
        Runway instance = new Runway();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of setLength method, of class Runway.
     */
    @Test
    public void testSetLength()
    {
        System.out.println( "setLength" );
        float length = 0.0F;
        Runway instance = new Runway();
        instance.setLength( length );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of setOrientation method, of class Runway.
     */
    @Test
    public void testSetOrientation_String()
    {
        System.out.println( "setOrientation" );
        String orientation = "";
        Runway instance = new Runway();
        boolean expResult = false;
        boolean result = instance.setOrientation( orientation );
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of setOrientation method, of class Runway.
     */
    @Test
    public void testSetOrientation_int()
    {
        System.out.println( "setOrientation" );
        int orientation = 0;
        Runway instance = new Runway();
        instance.setOrientation( orientation );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of setSurfaceType method, of class Runway.
     */
    @Test
    public void testSetSurfaceType()
    {
        System.out.println( "setSurfaceType" );
        SurfaceType surfaceType = null;
        Runway instance = new Runway();
        instance.setSurfaceType( surfaceType );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of toString method, of class Runway.
     */
    @Test
    public void testToString()
    {
        System.out.println( "toString" );
        Runway instance = new Runway();
        String expResult = "";
        String result = instance.toString();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

}
