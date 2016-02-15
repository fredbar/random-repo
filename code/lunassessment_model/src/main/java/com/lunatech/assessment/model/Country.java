package com.lunatech.assessment.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * Regroups information related to a country .
 * It lists all {@link com.lunatech.assessment.model.Airport} that it holds.
 *
 * @author Frederic Barachant
 */
public class Country
{

    private final ArrayList<Airport> airports = new ArrayList<>();
    private final String code;

    private final String name;

    /**
     * Default constructor.
     *
     * @param code Code of the country, as defined in iso-3166
     * @param name Name of the country, ad defined in iso-3166
     *
     * @see
     * <a href="http://data.okfn.org/data/core/country-list#resource-data">ISO
     * country codes</a>
     */
    public Country( String code, String name )
    {
        this.code = code;
        this.name = name;
    }

    /**
     * Adds an airport to this country. Duplicates are not tested, so watch out.
     * Tests of this airport also being used in an other Country is not done.
     *
     * @param newcomer the airport to be added.
     */
    public void add( Airport newcomer )
    {
        this.airports.add( newcomer );//Note: does not follow the Collections specification due to ArrayList being used and its comportment is known.
    }

    /**
     * Returns <code>true</code> if this list contains the specified instance.
     * More formally,
     * returns <code>true</code> if and only if this list contains at least one
     * element e
     * such that (o==null ? e==null : o.equals(e)).
     *
     * @param target element whose presence in this list is to be tested
     *
     * @return <code>true</code> if this list contains the specified element,
     * <code>false</code> otherwise.
     */
    public boolean contains( Airport target )
    {
        return airports.contains( target );
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj == null )
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
                final Country other = (Country)obj;
                if ( !Objects.equals( this.code, other.code ) )
                {
                    return false;
                }
                else
                {
                    if ( !Objects.equals( this.name, other.name ) )
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
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     *
     * @return the element at the specified position in this list
     *
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public Airport getAirport( int index )
    {
        return airports.get( index );
    }

    /**
     * Returns code of the country, as defined in iso-3166
     *
     * @return code of the country, as defined in iso-3166
     */
    public String getCode()
    {
        return code;
    }

    /**
     * Returns name of the country, as defined in iso-3166
     *
     * @return name of the country, as defined in iso-3166
     */
    public String getName()
    {
        return name;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode( this.code );
        hash = 37 * hash + Objects.hashCode( this.name );
        return hash;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * The returned iterator is fail-fast.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    public Iterator<Airport> iterator()
    {
        return airports.iterator();
    }

    @Override
    public String toString()
    {
        return "Country{" + "airports=" + airports + ", code=" + code + ", name=" + name + '}';
    }

}
