package com.lunatech.assessment.model;

import java.util.ArrayList;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author eric Barachant
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
        Runway instance_2 = new Runway();
        Airport instance = new Airport( "test" );
        instance.add( instance_2 );
        assertSame( instance_2, instance.get( 0 ) );//will not test get(), as it is checked here.
    }

    /**
     * Test of contains method, of class Airport.
     */
    @Test
    public void testContains()
    {
        System.out.println( "contains" );
        Runway instance_2 = new Runway();
        Airport instance = new Airport( "test" );
        instance.add( instance_2 );
        assertTrue( instance.contains( instance_2 ) );
    }

    /**
     * Test of equals method, of class Airport.
     */
    @Test
    public void testEquals_001()
    {
        // testing same objects, no runway
        System.out.println( "equals" );
        Object obj = new Airport( "test" );
        Airport instance = new Airport( "test" );
        assertEquals( obj, instance );
    }

    @Test
    public void testEquals_002()
    {
        // testing same objects, no runway on one of them. Runway list is not tested so it should not bother.
        System.out.println( "equals" );
        Airport obj = new Airport( "test" );
        obj.add( new Runway() );
        Airport instance = new Airport( "test" );
        assertEquals( obj, instance );
    }

    /**
     * Test of get method, of class Airport.
     */
    @Test
    public void testGet()
    {
        System.out.println( "get" );
        Runway r1 = new Runway();
        Runway r2 = new Runway();
        Airport instance = new Airport( "test" );
        instance.add( r1 );
        instance.add( r2 );
        assertSame( r1, instance.get( 0 ) );
        assertSame( r2, instance.get( 1 ) );
    }

    /**
     * Test of getName method, of class Airport.
     */
    @Test
    public void testGetName()
    {
        System.out.println( "getName" );
        String expected = "test";
        Airport instance = new Airport( expected );
        String result = instance.getName();
        assertEquals( expected, result );
    }

    /**
     * Test of iterator method, of class Airport.
     */
    @Test
    public void testIterator()
    {
        System.out.println( "iterator" );
        // testing that an iterator is returned and that the content is the same as the one i entered, in same order.
        Runway r1 = new Runway();
        Runway r2 = new Runway();
        Runway r3 = new Runway();
        Airport instance = new Airport( "test" );
        instance.add( r1 );
        instance.add( r2 );
        instance.add( r3 );
        ArrayList<Runway> expected = new ArrayList();
        expected.add( r1 );
        expected.add( r2 );
        expected.add( r3 );
        Iterator<Runway> iterator = instance.iterator();
        ArrayList<Runway> result = new ArrayList<>();
        while ( true == iterator.hasNext() )
        {
            result.add( iterator.next() );
        }
        assertEquals( expected, result );
    }

}
