package com.lunatech.assessment.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * An airport location.
 * It is linked to its {@link com.lunatech.assessment.model.Country} and holds a
 * list of {@link com.lunatech.assessment.model.Runway}.
 *
 * @author Frederic Barachant
 */
public class Airport
{

    /**
     * name of the airport.
     */
    private final String name;

    /**
     * List of runways of the airport.
     */
    final private ArrayList<Runway> runways = new ArrayList<>();

    public Airport( String name )
    {
        this.name = name;
    }

    /**
     * Adds a Runway to the Runway list of this airport.
     *
     * @param instance the instance to be added
     */
    public void add( Runway instance )
    {
        this.runways.add( instance );//Note: does not follow the Collections specification due to ArrayList being used and its comportment is known.
    }

    /**
     * Returns true if this list contains the specified instance. More formally,
     * returns true if and only if this list contains at least one element e
     * such that (o==null ? e==null : o.equals(e)).
     *
     * @param instance element whose presence in this list is to be tested
     *
     * @return true if this list contains the specified element
     */
    public boolean contains( Runway instance )
    {
        return this.runways.contains( instance );
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( null == obj )
        {
            return false;
        }
        else
        {
            if ( getClass() != obj.getClass() )
            {
                return false;
            }
            else
            {
                final Airport other = (Airport)obj;
                if ( false == Objects.equals( this.name, other.name ) )
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
        }
    }

    /**
     * Returns the element at the specified position in this list.
     * Parameters:
     * index -
     * Returns:
     *
     * @param index index of the element to return
     *
     * @return the element at the specified position in this list
     *
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public Runway get( int index )
    {
        return runways.get( index );
    }

    /**
     * Returns the name of this Airport
     *
     * @return the name of the Airport
     */
    public String getName()
    {
        return name;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode( this.name );
        return hash;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * The returned iterator is fail-fast.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    public Iterator<Runway> iterator()
    {
        return runways.iterator();
    }

    @Override
    public String toString()
    {
        return "Airport{" + "name=" + name + ", runways=" + runways + '}';
    }

}
