
package com.example.aaron.weatherapp.WeatherData;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Clouds {

    private int all;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Clouds() {
    }

    /**
     * 
     * @param all
     */
    public Clouds(int all) {
        this.all = all;
    }

    /**
     * 
     * @return
     *     The all
     */
    public int getAll() {
        return all;
    }

    /**
     * 
     * @param all
     *     The all
     */
    public void setAll(int all) {
        this.all = all;
    }

    public Clouds withAll(int all) {
        this.all = all;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Clouds withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
