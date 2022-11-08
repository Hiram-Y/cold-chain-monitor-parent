package com.example.common.util;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Component
public class YamlUtils {
    private static final Map<String, LinkedHashMap<String, Object>> YAML = new HashMap<>();
    private static final ThreadLocal<String> NOW_FILE_NAME = new ThreadLocal<>();

    private static void loadYml(String fileName) {
        NOW_FILE_NAME.set(fileName);
        if (!YAML.containsKey(fileName)) {
            YAML.put(fileName, new Yaml().loadAs(YamlUtils.class.getResourceAsStream("/" + fileName), LinkedHashMap.class));
        }
    }

    private static Object getValueByKey(String key) throws Exception {
        // 首先将key进行拆分
        String[] keys = key.split("[.]");
        // 将配置文件进行复制
        Map ymlInfo = (Map) YAML.get(NOW_FILE_NAME.get()).clone();
        for (int i = 0; i < keys.length; i++) {
            Object value = ymlInfo.get(keys[i]);
            if (i < keys.length - 1) {
                ymlInfo = (Map) value;
            } else if (value == null) {
                throw new RuntimeException("key不存在");
            } else {
                return value;
            }
        }
        throw new RuntimeException();
    }

    public static Object getValue(String key) {
        // 首先加载默认配置文件
        loadYml("application.yml");
        Object valueByKey = null;

        try {
            valueByKey = getValueByKey(key);
        } catch (Exception e) {
            throw new RuntimeException("yaml配置读取失败");
        }

        return valueByKey;
    }

    public static Object getValue(String fileName, String key) throws Exception {
        // 首先加载配置文件
        loadYml(fileName);
        return getValueByKey(key);
    }
}
