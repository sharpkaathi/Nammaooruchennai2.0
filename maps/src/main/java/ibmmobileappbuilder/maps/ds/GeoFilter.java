package ibmmobileappbuilder.maps.ds;

import java.util.Locale;

import ibmmobileappbuilder.ds.filter.Filter;
import ibmmobileappbuilder.ds.restds.GeoPoint;

public class GeoFilter implements Filter<GeoPoint> {

    static final String DISTANCE = ",\"$maxDistance\":%d";
    static final String GEOQUERY =
            "\"%s\":{\"$near\":{" +
                    "\"$geometry\":"+
                    "{\"type\":\"Point\",\"coordinates\":[%f,%f]}" +
                    "%s" +
                    "}}";
    static final String BOXQUERY =
            "\"%s\":{\"$geoWithin\":{\"$box\":[[%f,%f],[%f,%f]]}}";

    private final String mField;
    private GeoPoint mCenter = null;
    private long mRadius = 0;
    private GeoPoint mSw;
    private GeoPoint mNe;

    /**
     * Constructor for distance query
     * @param field
     * @param center
     * @param radiusInMeters
     */
    public GeoFilter(String field, GeoPoint center, long radiusInMeters){
        this.mField = field;
        this.mCenter = center;
        this.mRadius = radiusInMeters;
    }

    /**
     * Constructor for box query
     * @param field
     * @param sw Southwest point
     * @param ne Northeast point
     */
    public GeoFilter(String field, GeoPoint sw, GeoPoint ne){
        this.mField = field;
        mSw = sw;
        mNe = ne;
    }

    @Override
    public String getField() {
        return mField;
    }

    @Override
    public String getQueryString() {
        if(mCenter != null) {
            String distQuery = mRadius > 0 ? String.format(Locale.ENGLISH, DISTANCE, mRadius) : "";
            return String.format(Locale.ENGLISH, GEOQUERY,
                    mField, mCenter.coordinates[0], mCenter.coordinates[1], distQuery);
        }
        else if(mSw != null && mNe != null){
            return String.format(Locale.ENGLISH, BOXQUERY,
                    mField, mSw.coordinates[GeoPoint.LONGITUDE_INDEX], mSw.coordinates[GeoPoint.LATITUDE_INDEX],
                    mNe.coordinates[GeoPoint.LONGITUDE_INDEX], mNe.coordinates[GeoPoint.LATITUDE_INDEX]);
        }

        return null;
    }

    @Override
    public boolean applyFilter(GeoPoint value) {
        // in memory search for local datasources
        if(mCenter != null) {
            return (Math.pow(value.coordinates[0] - mCenter.coordinates[0], 2) +
                    Math.pow(value.coordinates[1] - mCenter.coordinates[1], 2)) <= Math.pow(mRadius, 2);
        }
        return true;
    }
}
