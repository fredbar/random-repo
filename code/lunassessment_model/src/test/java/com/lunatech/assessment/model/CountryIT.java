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
        Airport newcomer = new Airport( "test" );
        Country instance = new Country( "a", "b" );
        instance.add( newcomer );
        assertSame( newcomer, instance.getAirport( 0 ) );//will not test getAirport(), as it is checked here.
    }

    /**
     * Test of contains method, of class Country.
     */
    @Test
    public void testContains()
    {
        System.out.println( "contains" );
        Airport target = new Airport( "test" );
        Country instance = new Country( "a", "b" );
        instance.add( target );
        assertTrue( instance.contains( target ) );
    }

    /**
     * Test of equals method, of class Country.
     */
    @Test
    public void testEquals_001()
    {
        System.out.println( "equals_001" );
        Country instance1 = new Country( "a", "b" );
        Country instance2 = new Country( "a", "b" );
        assertEquals( instance1, instance2 );
    }

    /**
     * Test of equals method, of class Country.
     */
    @Test
    public void testEquals_002()
    {
        System.out.println( "equals_002" );
        //only name and code are discriminating, so other information is ignored in equals(). This should not bother.
        Country instance1 = new Country( "a", "b" );
        Country instance2 = new Country( "a", "b" );
        instance2.add( new Airport( "test" ) );
        assertEquals( instance1, instance2 );
    }

    /**
     * Test of getCode method, of class Country.
     */
    @Test
    public void testGetCode()
    {
        System.out.println( "getCode" );
        Country instance = new Country( "a", "b" );
        assertEquals( "a", instance.getCode() );
    }

    /**
     * Test of getName method, of class Country.
     */
    @Test
    public void testGetName()
    {
        System.out.println( "getName" );
        Country instance = new Country( "a", "b" );
        assertEquals( "b", instance.getName() );
    }

    /**
     * Test of iterator method, of class Country.
     */
    @Test
    public void testIterator()
    {
        System.out.println( "iterator" );
        Country instance = new Country( "a", "b" );
        Airport a1 = new Airport( "test1" );
        Airport a2 = new Airport( "test2" );
        Airport a3 = new Airport( "test3" );
        instance.add( a1 );
        instance.add( a2 );
        instance.add( a3 );

        ArrayList<Airport> expected = new ArrayList<Airport>();
        expected.add( a1 );
        expected.add( a2 );
        expected.add( a3 );
        ArrayList<Airport> result = new ArrayList<Airport>();
        Iterator<Airport> iterator = instance.iterator();
        while ( true == iterator.hasNext() )
        {
            result.add( iterator.next() );
        }
        assertEquals( expected, result );//ought to get same list and same ordering.
    }

}
