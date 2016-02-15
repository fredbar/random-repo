package com.lunatech.assessment.model;

import java.util.Objects;

/**
 * Defines a runway for an {@link com.lunatech.assessment.model.Airport}.
 * Does not link back to airport, not a needed feature.
 *
 *
 * Surfacetype is not split in case of dual type runway. We will get a single
 * surfacetype, whatever the case.
 *
 * @author Frederic Barachant
 */
public class Runway
{

    /**
     * Latitude of the Runway. Represents orientation in 10 degrees step. Is
     * negative if it is an helicopterRunway runway.
     */
    private int orientation = -1;

    /**
     * Length of the runway in feet.
     */
    private float length;

    /**
     * type of the surface.
     */
    private SurfaceType surfaceType;

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
                final Runway other = (Runway)obj;
                if ( this.orientation != other.orientation )
                {
                    return false;
                }
                else
                {
                    if ( Float.floatToIntBits( this.length ) != Float.floatToIntBits( other.length ) )
                    {
                        return false;
                    }
                    else
                    {
                        if ( !Objects.equals( this.surfaceType, other.surfaceType ) )
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
    }

    /**
     * Returns orientation of the runway.
     * Orientation is in degrees. If runway is a helicopter runway, a negative
     * value is returned.
     *
     * @return a positive value between 0 and 350 degrees, or a negative value
     * if runway is a helicopter runway.
     */
    public int getOrientation()
    {
        return orientation;
    }

    /**
     * Sets orientation of the runway.
     * Ought to be a value between 0 and 350 and a multiple of 10.
     *
     * @param orientation the orientation of the runway.
     */
    public void setOrientation( int orientation )
    {
        this.orientation = orientation;
    }

    /**
     * Parses orientation from a string and sets the correct angle into the
     * instance.
     * If the orientation is for a helicopter, ice or water runway,
     * orientation will be set to a negative value and length will be 0.
     * If the orientation is for a plane runway, the object instance will have
     * its orientation correctly set.
     * Here is how values will be treated:
     * <ul>
     * <li>Numerical values will be multiplied by 10.</li>
     * <li>Numerical values with a trailing letter will also be multiplied by 10
     * and letter will be ignored.</li>
     * <li>Values starting with H will set negative value and 0 length
     * runway.</li>
     * <li>values containing ALL will set negative value and 0 length
     * runway.</li>
     * <li>values like N/NE/E/SE/S/SW/W/NW will be converted to the correct
     * value</li>
     * </ul>
     *
     * @param orientation String representing the orientation to parse
     *
     * @return <code>true</code> if the value could be parsed,
     * <code>false</code> otherwise.
     */
    public boolean setOrientation( String orientation )
    {
        throw new UnsupportedOperationException( "Not implemented" );
    }

    /**
     * Gets length of the runway.
     * Value is in feet and can be 0 if it is for helicopters.
     *
     * @return length of the runway.
     */
    public float getLength()
    {
        return length;
    }

    /**
     * Sets runway length.
     * Value is in feet.
     *
     * @param length length of the runway in feet.
     */
    public void setLength( float length )
    {
        this.length = length;
    }

    /**
     * Returns surface type for the runway.
     *
     * @return the surface type for the runway.
     */
    public SurfaceType getSurfaceType()
    {
        return surfaceType;
    }

    /**
     * Sets the surface type for the runway.
     *
     * @param surfaceType the surface to be assigned to this runway.
     */
    public void setSurfaceType( SurfaceType surfaceType )
    {
        this.surfaceType = surfaceType;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode( this.orientation );
        hash = 89 * hash + Float.floatToIntBits( this.length );
        hash = 89 * hash + Objects.hashCode( this.surfaceType );
        return hash;
    }

    @Override
    public String toString()
    {
        return "Runway{" + "orientation=" + orientation + ", length=" + length + ", surfaceType=" + surfaceType + '}';
    }

}
