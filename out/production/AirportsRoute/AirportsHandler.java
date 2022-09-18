import java.util.List;

public class AirportsHandler {
    public Double getDistance(String airport1, String airport2) {
        Database db = new Database();
        double lng1 = db.getLongitude(airport1);
        double lng2 = db.getLongitude(airport2);
        double lat1 = db.getLatitude(airport1);
        double lat2 = db.getLatitude(airport2);

        lng1 = Math.toRadians(lng1);
        lng2 = Math.toRadians(lng2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double longDiference = lng2 - lng1;
        double latDiference = lat2 - lat1;
        double a = Math.pow(Math.sin(latDiference / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(longDiference / 2),2);

        double angularOpening = 2 * Math.asin(Math.sqrt(a));

        double earthRadius = 6371;

        return Double.valueOf(angularOpening * earthRadius);
    }
}
