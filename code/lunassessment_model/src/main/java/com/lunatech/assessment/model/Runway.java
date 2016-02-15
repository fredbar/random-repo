package com.lunatech.assessment.model;

import java.util.Objects;
import org.apache.commons.validator.routines.IntegerValidator;

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

    private static final IntegerValidator VALIDATORINSTANCE = IntegerValidator.getInstance();

    /**
     * Length of the runway in feet.
     */
    private float length;
    /**
     * Latitude of the Runway. Represents orientation in 10 degrees step. Is
     * negative if it is an helicopterRunway runway.
     */
    private int orientation = -1;

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
     * Ought to be a value between 10 and 360 and a multiple of 10.
     *
     * @param orientation the orientation of the runway.
     */
    public void setOrientation( int orientation )
    {
        this.orientation = orientation;
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
     * value, 5 degrees less for intercardinal directions.( NE, thus 45° will be
     * 04)</li>
     * </ul>
     *
     * @param orientation String representing the orientation to parse, case
     * independent.
     *
     * @return <code>true</code> if the value could be parsed,
     * <code>false</code> otherwise and orientation will be set to -2.
     */
    public boolean setOrientation( final String orientation )
    {
        String filteredOrientation = orientation.toLowerCase();
        //there are multiple formats to test. I'll avoid using regexp for performance reasons, but did not profile.
        //first test, is that a simple integer?
        Integer candidate = VALIDATORINSTANCE.validate( filteredOrientation );
        if ( null != candidate )
        {
            //that was a value, let's store it and exit.
            this.orientation = candidate;
            return true;
        }
        else
        {
            //ok. a simple integer with a letter at the end?
            candidate = VALIDATORINSTANCE.validate( orientation.substring( 0, filteredOrientation.length() - 1 ) );
            if ( null != candidate )
            {
                //that was a value with a letter, let's store it and exit.
                this.orientation = candidate;
                return true;
            }
            else
            {
                //ok. now it can also be an "all direction" track.
                if ( true == filteredOrientation.equals( "all" ) )
                {
                    this.orientation = -1;
                    return true;
                }
                else
                {
                    //or, it can he an helicopter pad. If so it starts with an H.
                    if ( true == filteredOrientation.startsWith( "h" ) )
                    {
                        this.orientation = -1;
                        return true;
                    }
                    else
                    {
                        // it's not, so it can also be a cardinal direction.
                        switch ( filteredOrientation )//let's assume the program will run in a java7+ environment
                        {
                            case "n":
                                this.orientation = 360;
                                return true;
                            case "ne":
                                this.orientation = 40;
                                return true;
                            case "e":
                                this.orientation = 90;
                                return true;
                            case "se":
                                this.orientation = 130;
                                return true;
                            case "s":
                                this.orientation = 180;
                                return true;
                            case "sw":
                                this.orientation = 220;
                                return true;
                            case "w":
                                this.orientation = 270;
                                return true;
                            case "nw":
                                this.orientation = 310;
                                return true;
                            default:
                                this.orientation = -2;
                                return false;//all failed.
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString()
    {
        return "Runway{" + "orientation=" + orientation + ", length=" + length + ", surfaceType=" + surfaceType + '}';
    }

}
