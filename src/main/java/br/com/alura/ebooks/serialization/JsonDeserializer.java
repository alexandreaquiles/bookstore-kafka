package br.com.alura.ebooks.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class JsonDeserializer<T> implements Deserializer<T> {

    private static final Logger logger = LoggerFactory.getLogger(JsonDeserializer.class);

    public static final String TYPE_CONFIG = "br.com.caelum.type_config";

    private final Gson gson = new GsonBuilder().create();
    private Class<T> targetType;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        String targetTypeName = (String) configs.get(TYPE_CONFIG);
        try {
            this.targetType = (Class<T>) Class.forName(targetTypeName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Invalid target type", e);
        }
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        String dataAsString = new String(data);
        logger.info("Target Type: {} | Topic: {} | Data: {}", targetType, topic, dataAsString);
        return gson.fromJson(dataAsString, targetType);
    }
}
