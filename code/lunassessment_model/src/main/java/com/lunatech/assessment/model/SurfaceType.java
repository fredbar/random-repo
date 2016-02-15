package com.lunatech.assessment.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a surface type.
 * Surfaces are created through the use of factory methods in order to avoid
 * creating too many identical objects. Each creation demand will be compared to
 * a dictionnary and send a new object instance only if it was not created
 * previously.
 *
 * @author Frederic Barachant.
 */
public class SurfaceType
{

    static final private List<SurfaceType> cache = new ArrayList<SurfaceType>();

    /**
     * Creates a new instance of the type or returns an already existing
     * instance.
     *
     * @param typeName the name of the type to be created.
     *
     * @return a SurfaceType instance corresponding to the given type name.
     */
    public static SurfaceType instanciate( final String typeName )
    {
        throw new UnsupportedOperationException( "not implemented" );
    }

    /**
     * type of the surface. Can eventually be a composition of two types,
     * seperated with a hyphen.
     */
    private final String typeName;

    private SurfaceType( String typeName )
    {
        this.typeName = typeName;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj == null )
        {
            return false;
        }
        if ( getClass() != obj.getClass() )
        {
            return false;
        }
        final SurfaceType other = (SurfaceType)obj;
        if ( !Objects.equals( this.typeName, other.typeName ) )
        {
            return false;
        }
        return true;
    }

    /**
     * Returns the surface name, given at construction.
     *
     * @return the surface name
     */
    public String getTypeName()
    {
        return this.typeName;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode( this.typeName );
        return hash;
    }

    @Override
    public String toString()
    {
        return "SurfaceType{" + "typeName=" + typeName + '}';
    }

}
