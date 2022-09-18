import javax.xml.crypto.Data;
import java.util.*;

public class GraphHandler {
    public HashMap<String, Double> distance;
    public HashMap<String, String> parent;
    private PriorityQueue<Pair> pQueue;
    private List<String> visitedAirports;
    private List<String> airports;

    public GraphHandler() {
        Database db = new Database();
        this.airports = db.getAirports();
        this.distance = new HashMap<>();
        this.parent = new HashMap<>();
        airports.forEach(a -> {
            this.distance.put(a,Double.MAX_VALUE);
        });
        this.visitedAirports = new ArrayList<String>();
        this.pQueue = new PriorityQueue<Pair>(airports.size()*airports.size(), new PairComparator());
    }

    public void calculateShortestPath(String originAirport, String destinationAirport) {
        if(!airports.contains(originAirport) || !airports.contains(destinationAirport)) {
            System.out.println("Aeroportos inexistentes");
            return;
        }

        AirportsHandler handler = new AirportsHandler();

        airports.forEach(a -> {
            if(!a.equals(destinationAirport) && !a.equals(originAirport)) {
                pQueue.add(new Pair(a, handler.getDistance(originAirport, a)));
            }
        });
        pQueue.add(new Pair(originAirport,0.0));
        distance.put(originAirport, 0.0);
        System.out.println("Aguarde um momento enquanto calculamos as rotas possiveis...");

        while (visitedAirports.size() != airports.size() - 2) {
            if(pQueue.isEmpty()) return;

            Pair node = pQueue.remove();
            if(visitedAirports.contains(node.first)) continue;
            visitedAirports.add(node.first);
            System.out.println(".");


            if(distance.get(node.first) < node.second) continue;

            airports.forEach(a -> {

                if(!a.equals(originAirport)) {

                    Double distanceToCheck =  distance.get(node.first) + handler.getDistance(a, node.first);
                    if(distance.get(a) > distanceToCheck) {
                        if(!(a.equals(destinationAirport) && node.first.equals(originAirport))) {

                        parent.put(a,node.first);
                        distance.put(a, Double.valueOf(distanceToCheck));
                        pQueue.add(new Pair(a,distance.get(a)));
                        }
                    }
                }
            });
        }

        String aux = destinationAirport;
        System.out.println("Menor rota: ");
        System.out.print(originAirport + " -> ");
        while (!parent.get(aux).equals(destinationAirport)) {
            aux = parent.get(aux);
            if(aux.equals(originAirport)) {
                System.out.println(destinationAirport);
                break;
            }
            System.out.print(aux + " -> ");
        }

        System.out.println("Distancia em quilometros: " + distance.get(destinationAirport));
        System.out.println();

        new Database().registerQuery(originAirport,destinationAirport);
    }

}
