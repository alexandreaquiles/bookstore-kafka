package br.com.alura.ebooks.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Serializer;

public class JsonSerializer implements Serializer {

    private static final Gson gson = new GsonBuilder().create();

    @Override
    public byte[] serialize(String topic, Object data) {
        return gson.toJson(data).getBytes();
    }

}
