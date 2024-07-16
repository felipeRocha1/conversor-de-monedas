package com.alura.screenmatch.conversionMoneda;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class RegistroConvertir {
    // Atributos
    private Convertir convertir; // Representa la conversión realizada
    private LocalDateTime timestamp; // Marca de tiempo de la conversión

    /**
     * Constructor de RegistroConversion.
     * @param conversion La conversión que se está registrando.
     */
    public RegistroConvertir(Convertir convertir) {
        this.convertir = convertir;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Obtiene la conversión asociada con este registro.
     * @return La conversión asociada.
     */
    // Getters y setters
    public Convertir getConversion() {
        return convertir;
    }

    /**
     * Establece la conversión asociada con este registro.
     * @param conversion La conversión a establecer.
     */
    public void setConversion(Convertir convertir) {
        this.convertir = convertir;
    }

    /**
     * Obtiene la marca de tiempo de la conversión.
     * @return La marca de tiempo.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Establece la marca de tiempo de la conversión.
     * @param timestamp La marca de tiempo a establecer.
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Genera una representación en cadena del registro de conversión.
     * @return La representación en cadena del registro de conversión.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Registro de Conversión: {" +
                "\n  Moneda de Origen: " + convertir.getMonedaOrigen() +
                "\n  Moneda de Destino: " + convertir.getMonedaDestino() +
                "\n  Monto: " + convertir.getMonto() +
                "\n  Conversion Rate: " + convertir.getConversionRate() +
                "\n  Resultado: " + convertir.getResultado() +
                "\n  Marca de Tiempo: " + timestamp.format(formatter) +
                "\n}";
    }
}
