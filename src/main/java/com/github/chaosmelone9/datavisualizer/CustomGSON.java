package com.github.chaosmelone9.datavisualizer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.awt.*;
import java.io.IOException;

public class CustomGSON {
    private static TypeAdapter<Color> colorTypeAdapter = new TypeAdapter<Color>() {
        @Override
        public void write(JsonWriter jsonWriter, Color color) throws IOException {
            jsonWriter.value(String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
        }

        @Override
        public Color read(JsonReader jsonReader) throws IOException {
            return Color.decode(jsonReader.nextString());
        }
    };

    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Color.class, colorTypeAdapter)
            .setPrettyPrinting().create();
}
