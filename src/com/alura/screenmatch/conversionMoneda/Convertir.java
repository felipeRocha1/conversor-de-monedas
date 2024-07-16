package com.alura.screenmatch.conversionMoneda;
import adapters.TiempoLocalAdaptador;
import com.alura.screenmatch.adapters.TiempoLocalAdaptador;
import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import java.time.LocalDateTime;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Convertir {
     @SerializedName("Moneda_Origen")
    private String monedaOrigen;

    @SerializedName("Moneda_Destino")
    private String monedaDestino;

     @SerializedName("Monto_a_Convertir")
    private double monto;

     @SerializedName("Resultado")
    private double resultado;

    @SerializedName("Tasa_Conversion")
    private double conversionRate;

    public Convertir() {}

    public Convertir(String monedaOrigen, String monedaDestino, double monto) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.monto = monto;
    }

    // Getters y setters

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public void setMonedaOrigen(String monedaOrigen) {
        this.monedaOrigen = monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public void setMonedaDestino(String monedaDestino) {
        this.monedaDestino = monedaDestino;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

      public RegistroConvertir convertir(String codMonOrigen, String codMonDestino, int monto) {
        // Construir la URI para la solicitud a la API
        URI direccion_API = URI.create("https://v6.exchangerate-api.com/v6/d50362c2646d99e082d99a42/pair/"
                + codMonOrigen + "/" + codMonDestino + "/" + monto);

        // Crear cliente y solicitud HTTP
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion_API)
                .build();
        HttpResponse<String> response = null;

        try {
            // Realizar la solicitud HTTP
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Leer el JSON de la respuesta
        try (JsonReader reader = new JsonReader(new StringReader(response.body()))) {
            reader.setLenient(true);

            // Configurar Gson para deserializar la respuesta
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new TiempoLocalAdaptador())
                    .create();

            // Deserializar la respuesta en un objeto ConversionResponse
            ConvertirRespuesta conversionResponse = gson.fromJson(reader, ConvertirRespuesta.class);

            // Calcular el resultado y la tasa de conversión
            this.conversionRate = conversionResponse.getConversionRate();
            this.resultado = monto * conversionRate;

            // Crear un objeto Conversion con los datos de la respuesta
            Convertir convertir = new Convertir(conversionResponse.getMonedaOrigen(), conversionResponse.getMonedaDestino(), monto);
            convertir.setResultado(resultado);
            convertir.setConversionRate(conversionResponse.getConversionRate());

            // Crear un registro de la conversión
            RegistroConvertir registroConvertir = new RegistroConvertir(convertir);

            // Leer el historial de conversiones desde el archivo JSON
            RegistroConvertir[] historial;
            try (Reader fileReader = new FileReader("registros_data_time.json")) {
                historial = gson.fromJson(fileReader, RegistroConvertir[].class);
            } catch (FileNotFoundException e) {
                historial = new RegistroConvertir[0];
            }

            // Agregar el nuevo registro al historial
            RegistroConvertir[] nuevoHistorial = new RegistroConvertir[historial.length + 1];
            System.arraycopy(historial, 0, nuevoHistorial, 0, historial.length);
            nuevoHistorial[historial.length] = registroConvertir;

            // Escribir el historial actualizado en el archivo JSON
            try (Writer fileWriter = new FileWriter("registros_data_time.json")) {
                gson.toJson(nuevoHistorial, fileWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return registroConvertir; // Devolver el registro de la conversión
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
