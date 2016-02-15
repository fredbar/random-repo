package com.lunatech.assessment.model;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
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
     * Test of equals method, of class Runway.
     */
    @Test
    public void testEquals_001()
    {
        System.out.println( "equals_001" );
        SurfaceType t1 = SurfaceType.instanciate( "ASPH" );
        SurfaceType t2 = SurfaceType.instanciate( "ASPH" );//we should have the same instance on second call
        assertSame( t1, t2 );
    }

    @Test
    public void testEquals_002()
    {
        System.out.println( "equals_002" );
        SurfaceType t1 = SurfaceType.instanciate( "ASPH" );
        SurfaceType t2 = SurfaceType.instanciate( "GRAV" );//we should get different instances.
        assertNotSame( t1, t2 );
    }

    /**
     * Test of instanciate method, of class SurfaceType.
     */
    @Test
    public void testInstanciate()
    {
        System.out.println( "instanciate" );
        final String type = "ASPH";
        SurfaceType t1 = SurfaceType.instanciate( type );
        assertEquals( t1.getTypeName(), type );
    }
}
