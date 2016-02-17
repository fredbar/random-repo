package com.lunatech.assessment.model;

/**
 * Stores a tuple, that is how many runways we have for a given orientation.
 *
 * @author Fred
 */
public class LatitudeScore implements Comparable
{

    private int count;

    private int orientation;

    LatitudeScore( int orientation, int count )
    {
        this.orientation = orientation;
        this.count = count;
    }

    LatitudeScore()
    {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo( Object o )
    {
        //no cast check, failfast.
        LatitudeScore other = (LatitudeScore)o;
        return other.getCount() - getCount();
    }

    /**
     * Get the value of count
     *
     * @return the value of count
     */
    public int getCount()
    {
        return count;
    }

    /**
     * Set the value of count
     *
     * @param count new value of count
     */
    public void setCount( int count )
    {
        this.count = count;
    }

    /**
     * Get the value of orientation
     *
     * @return the value of orientation
     */
    public int getOrientation()
    {
        return orientation;
    }

    /**
     * Set the value of orientation
     *
     * @param orientation new value of orientation
     */
    public void setOrientation( int orientation )
    {
        this.orientation = orientation;
    }

    void increase()
    {
        this.count++;
    }

}
