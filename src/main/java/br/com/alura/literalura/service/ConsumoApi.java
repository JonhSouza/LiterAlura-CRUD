package br.com.alura.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {

    public String obterDados(String endereco) {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            System.out.println("Status da resposta: " + statusCode);
            if (statusCode != 200) {
                System.out.println("Erro na requisição. Código de status: " + statusCode);
                return null;
            }
        } catch (IOException e) {
            System.out.println("Erro de IO: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            System.out.println("Erro de interrupção: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        return response.body();
    }
}
