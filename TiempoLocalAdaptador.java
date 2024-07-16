package com.alura.screenmatch.adapters;
import com.google.gson.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class TiempoLocalAdaptador implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public JsonElement serialize(LocalDateTime src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(src));
    }

    @Override
    public LocalDateTime deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDateTime.parse(json.getAsString(), formatter);
    }
}
}
