package com.alura.screenmatch.conversionMoneda;
import com.google.gson.annotations.SerializedName;

public class ConvertirRespuesta {
    /** El código de la moneda base en la conversión. Índices de la API de ExchangeRate-API:
     * "base_code"."target_code" "conversion_rate" "conversion_result"*/

    /** El código de la moneda origen en la conversión. */
    @SerializedName("base_code")
    private String monedaOrigen;

    /** El código de la moneda destino en la conversión. */
    @SerializedName("target_code")
    private String monedaDestino;

    /** La tasa de conversión entre la moneda base y la moneda destino. */
    @SerializedName("conversion_rate")
    private double conversionRate;

    /** El resultado de la conversión. */
    @SerializedName("conversion_result")
    private double resultado;

    /** Obtiene el código de la moneda base. */
    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    /** Establece el código de la moneda base. */
    public void setMonedaOrigen(String monedaOrigen) {
        this.monedaOrigen = monedaOrigen;
    }

    /** Obtiene el código de la moneda destino. */
    public String getMonedaDestino() {
        return monedaDestino;
    }

    /** Establece el código de la moneda destino. */
    public void setMonedaDestino(String monedaDestino) {
        this.monedaDestino = monedaDestino;
    }

    /** Obtiene la tasa de conversión. */
    public double getConversionRate() {
        return conversionRate;
    }

    /** Establece la tasa de conversión. */
    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    /** Obtiene el resultado de la conversión. */
    public double getResultado() {
        return resultado;
    }

    /** Establece el resultado de la conversión. */
    public void setResultado(double resultado) {
        this.resultado = resultado;
    }
}
