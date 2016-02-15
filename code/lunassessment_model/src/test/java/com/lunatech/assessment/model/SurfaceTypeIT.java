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
public class SurfaceTypeIT
{

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    public SurfaceTypeIT()
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
     * Test of equals method, of class SurfaceType.
     */
    @Test
    public void testEquals()
    {
        System.out.println( "equals" );
        Object obj = null;
        SurfaceType instance = null;
        boolean expResult = false;
        boolean result = instance.equals( obj );
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of hashCode method, of class SurfaceType.
     */
    @Test
    public void testHashCode()
    {
        System.out.println( "hashCode" );
        SurfaceType instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of instanciate method, of class SurfaceType.
     */
    @Test
    public void testInstanciate()
    {
        System.out.println( "instanciate" );
        String typeName = "";
        SurfaceType expResult = null;
        SurfaceType result = SurfaceType.instanciate( typeName );
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of toString method, of class SurfaceType.
     */
    @Test
    public void testToString()
    {
        System.out.println( "toString" );
        SurfaceType instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

}
