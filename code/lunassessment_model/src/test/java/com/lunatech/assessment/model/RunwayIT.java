/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lunatech.assessment.model;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
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
     * Test of getLength method, of class Runway.
     */
    @Test
    public void testGetLength()
    {
        System.out.println( "getLength" );
        Runway instance = new Runway();
        float expResult = 10.0F;
        instance.setLength( expResult );
        float result = instance.getLength();
        assertEquals( expResult, result, 0.0 );
    }

    /**
     * Test of getOrientation method, of class Runway.
     */
    @Test
    public void testGetOrientation()
    {
        System.out.println( "getOrientation" );
        Runway instance = new Runway();
        int expResult = 10;
        instance.setOrientation( expResult );
        int result = instance.getOrientation();
        assertEquals( expResult, result );
    }

    /**
     * Test of getSurfaceType method, of class Runway.
     */
    @Test
    public void testGetSurfaceType()
    {
        System.out.println( "getSurfaceType" );
        Runway instance = new Runway();
        SurfaceType expResult = SurfaceType.instanciate( "ASPH" );
        instance.setSurfaceType( expResult );
        SurfaceType result = instance.getSurfaceType();
        assertSame( expResult, result );
    }

    /**
     * Test of setLength method, of class Runway.
     */
    @Test
    public void testSetLength()
    {
        System.out.println( "setLength" );
        float length = 10.0F;
        Runway instance = new Runway();
        instance.setLength( length );
        assertEquals( length, instance.getLength(), 0.0 );
    }

    /**
     * Test of setOrientation method, of class Runway.
     */
    @Test
    public void testSetOrientation_String_001()
    {
        System.out.println( "setOrientation: real angle" );
        String orientation = "10";
        Runway instance = new Runway();
        assertTrue( instance.setOrientation( orientation ) );
        assertEquals( 10, instance.getOrientation() );
    }

    /**
     * Test of setOrientation method, of class Runway.
     */
    @Test
    public void testSetOrientation_String_002()
    {
        System.out.println( "setOrientation: real angle plus character" );
        String orientation = "10W";
        Runway instance = new Runway();
        assertTrue( instance.setOrientation( orientation ) );
        assertEquals( 10, instance.getOrientation() );
    }

    /**
     * Test of setOrientation method, of class Runway.
     */
    @Test
    public void testSetOrientation_String_003()
    {
        System.out.println( "setOrientation: cardinality N" );
        String orientation = "N";
        Runway instance = new Runway();
        assertTrue( instance.setOrientation( orientation ) );
        assertEquals( 360, instance.getOrientation() );
    }

    /**
     * Test of setOrientation method, of class Runway.
     */
    @Test
    public void testSetOrientation_String_004()
    {
        System.out.println( "setOrientation: cardinality NE" );
        String orientation = "NE";
        Runway instance = new Runway();
        assertTrue( instance.setOrientation( orientation ) );
        assertEquals( 40, instance.getOrientation() );
    }

    /**
     * Test of setOrientation method, of class Runway.
     */
    @Test
    public void testSetOrientation_String_005()
    {
        System.out.println( "setOrientation: cardinality E" );
        String orientation = "E";
        Runway instance = new Runway();
        assertTrue( instance.setOrientation( orientation ) );
        assertEquals( 90, instance.getOrientation() );
    }

    /**
     * Test of setOrientation method, of class Runway.
     */
    @Test
    public void testSetOrientation_String_006()
    {
        System.out.println( "setOrientation: cardinality SE" );
        String orientation = "SE";
        Runway instance = new Runway();
        assertTrue( instance.setOrientation( orientation ) );
        assertEquals( 130, instance.getOrientation() );
    }

    /**
     * Test of setOrientation method, of class Runway.
     */
    @Test
    public void testSetOrientation_String_007()
    {
        System.out.println( "setOrientation: cardinality S" );
        String orientation = "S";
        Runway instance = new Runway();
        assertTrue( instance.setOrientation( orientation ) );
        assertEquals( 180, instance.getOrientation() );
    }

    /**
     * Test of setOrientation method, of class Runway.
     */
    @Test
    public void testSetOrientation_String_008()
    {
        System.out.println( "setOrientation: cardinality SW" );
        String orientation = "SW";
        Runway instance = new Runway();
        assertTrue( instance.setOrientation( orientation ) );
        assertEquals( 220, instance.getOrientation() );
    }

    /**
     * Test of setOrientation method, of class Runway.
     */
    @Test
    public void testSetOrientation_String_009()
    {
        System.out.println( "setOrientation: cardinality W" );
        String orientation = "W";
        Runway instance = new Runway();
        assertTrue( instance.setOrientation( orientation ) );
        assertEquals( 270, instance.getOrientation() );
    }

    /**
     * Test of setOrientation method, of class Runway.
     */
    @Test
    public void testSetOrientation_String_010()
    {
        System.out.println( "setOrientation: cardinality NW" );
        String orientation = "NW";
        Runway instance = new Runway();
        assertTrue( instance.setOrientation( orientation ) );
        assertEquals( 310, instance.getOrientation() );
    }

    /**
     * Test of setOrientation method, of class Runway.
     */
    @Test
    public void testSetOrientation_String_011()
    {
        System.out.println( "setOrientation: cardinality N" );
        String orientation = "N";
        Runway instance = new Runway();
        assertTrue( instance.setOrientation( orientation ) );
        assertEquals( 360, instance.getOrientation() );
    }

    /**
     * Test of setOrientation method, of class Runway.
     */
    @Test
    public void testSetOrientation_String_012()
    {
        System.out.println( "setOrientation: FFA" );
        String orientation = "ALL";
        Runway instance = new Runway();
        assertTrue( instance.setOrientation( orientation ) );
        assertTrue( instance.getOrientation() < 0 );
    }

    /**
     * Test of setOrientation method, of class Runway.
     */
    @Test
    public void testSetOrientation_String_013()
    {
        System.out.println( "setOrientation: helicopter pad" );
        String orientation = "H1";
        Runway instance = new Runway();
        assertTrue( instance.setOrientation( orientation ) );
        assertTrue( instance.getOrientation() < 0 );
    }

    /**
     * Test of setOrientation method, of class Runway.
     */
    @Test
    public void testSetOrientation_String_014()
    {
        System.out.println( "setOrientation: helicopter pad, second" );
        String orientation = "H2";
        Runway instance = new Runway();
        assertTrue( instance.setOrientation( orientation ) );
        assertTrue( instance.getOrientation() < 0 );
    }

    /**
     * Test of setOrientation method, of class Runway.
     */
    @Test
    public void testSetOrientation_String_015()
    {
        System.out.println( "setOrientation: erroneous entry" );
        String orientation = "Bla";
        Runway instance = new Runway();
        assertFalse( instance.setOrientation( orientation ) );
    }

    /**
     * Test of setOrientation method, of class Runway.
     */
    @Test
    public void testSetOrientation_int()
    {
        System.out.println( "setOrientation int" );
        int orientation = 10;
        Runway instance = new Runway();
        instance.setOrientation( orientation );
        assertEquals( orientation, instance.getOrientation() );
    }

    /**
     * Test of setSurfaceType method, of class Runway.
     */
    @Test
    public void testSetSurfaceType()
    {
        System.out.println( "setSurfaceType" );
        SurfaceType surfaceType = SurfaceType.instanciate( "ASPH" );
        Runway instance = new Runway();
        instance.setSurfaceType( surfaceType );
        assertSame( instance.getSurfaceType(), surfaceType );
    }

}
