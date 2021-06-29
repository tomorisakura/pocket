package com.enigma.pocket.services;

import java.util.HashMap;

public interface AppConfigService {
    String getValue(String key);
    void setValue(String key, Object value);
    HashMap<String, Object> getMap(String key);
}
