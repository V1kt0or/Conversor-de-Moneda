import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modelo.Moneda;
import modelo.MonedaDto;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();


        System.out.println("********************************");
        System.out.println("********************************");
        System.out.println("Sea bienvenido/a al Conversor de Moneda :]");

        int salir = 0;
        while(salir != 7) {
            System.out.println("1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real Brasileño");
            System.out.println("4) Real Brasileño =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Salir");
            System.out.println("Elije una opcion valida");
            System.out.println("********************************");
            try {

                salir = lectura.nextInt();
                if (salir != 7){
                    System.out.println("Ingrese el valor que desea convertir");
                Double valor = lectura.nextDouble();


                String direccion = "https://v6.exchangerate-api.com/v6/07355c7b2a425aafe568d0f0/latest/USD";


                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());


                String json = response.body();

                MonedaDto monedaDto = gson.fromJson(json, MonedaDto.class);
                Moneda moneda = new Moneda(monedaDto);

                if (salir == 1) {
                    double valor4 = moneda.convDolAArg(valor);
                    System.out.println(moneda.convDolAArg(valor));

                } else if (salir == 2) {
                    System.out.println(moneda.convArgADol(valor));

                } else if (salir == 3) {
                    System.out.println(moneda.convDolABra(valor));

                } else if (salir == 4) {
                    System.out.println(moneda.convBraADol(valor));

                } else if (salir == 5) {
                    System.out.println(moneda.convDolABol(valor));

                } else if (salir == 6) {
                    System.out.println(moneda.convBolADol(valor));

                }
            }
        }
            catch (Exception exception){
                exception.printStackTrace(); // Imprimir el error para diagnosticar mejor el problema

            }
        System.out.println("***********************");
        }
    }
}