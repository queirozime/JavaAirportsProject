import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Airport {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<String> airports = new ArrayList<String>();
        Database db = new Database();
        GraphHandler graphHandler = new GraphHandler();

        while (airports.size() < 2) {
            System.out.println("Escolha um aeroporto (pressione ENTER para o menu)");
            String check1 = input.nextLine();
            System.out.println("Escolha entre os estados abaixo: ");
            db.printStates();
            System.out.println("Digite o estado do aeroporto: ");
            String state = input.nextLine();
            db.printStateCities(state);
            System.out.println("Digite a cidade do aeroporto: ");
            String city = input.nextLine();
            db.printCityAirports(city, state);
            System.out.println("Digite o aeroporto desejado: ");
            String airport = input.nextLine();
            airports.add(airport);
            System.out.println("Pressione ENTER para continuar ou X para reiniciar");
            String check2 = input.nextLine();
            if(check2.equals("X") || check2.equals("x")) {
                airports.clear();
                System.out.println("Busca reiniciada");
            }
        }

        System.out.println("Origem: " + airports.get(0));
        System.out.println("Destino: " + airports.get(1));
        System.out.println();
        
        if(airports.get(0).equals(airports.get(1))) {
            System.out.println("Entrada inválida");
        }else{
            graphHandler.calculateShortestPath(airports.get(0), airports.get(1));
        }        
    }
}
