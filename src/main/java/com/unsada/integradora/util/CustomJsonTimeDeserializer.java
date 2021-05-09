package com.unsada.integradora.util;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomJsonTimeDeserializer extends JsonDeserializer<Time> {

    @Override
    public Time deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        
        String time = jsonParser.getText();
        System.out.println("time before parse : "+ time);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        long ms = 0;
            try {
                ms = sdf.parse(time).getTime();
                Time t = new Time(ms);
                return t;

            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
}

