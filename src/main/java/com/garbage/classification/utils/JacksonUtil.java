package com.garbage.classification.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author domain
 * JacksonUtil 帮助工具类
 * 处理xml object to json 等
 */
public class JacksonUtil {

    private final static ObjectMapper objectMapper = getInstance();

    private static final XmlMapper xmlMapper = new XmlMapper();

    private static final String XML_ENCODING = "<?xml version=\"1.0\" encoding=\"GB2312\"?>";

    private JacksonUtil() {

    }

    static {
        // 将属性映射成相应节点
        xmlMapper.setPropertyNamingStrategy(new PropertyNamingStrategy() {
            private static final long serialVersionUID = 1L;

            // 反序列化时调用
            @Override
            public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
                return method.getName().substring(3);
            }

            // 序列化时调用
            @Override
            public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
                return method.getName().substring(3);
            }
        });
        // 只显示非空节点
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }

    public static ObjectMapper getInstance() {
        ObjectMapper objectMapper = new ObjectMapper();
        //空值转换-异常情况 no single-String constructor/factory method
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        // Illegal unquoted character
        objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(sdf);
        return objectMapper;
    }

    /**
     * javaBean,list,array convert to json string
     */
    public static String obj2json(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * json string convert to javaBean
     */
    public static <T> T json2pojo(String jsonStr, Class<T> clazz)
            throws Exception {
        return objectMapper.readValue(jsonStr, clazz);
    }

    /**
     * json string convert to map
     */
    public static <T> Map<String, Object> json2map(String jsonStr)
            throws Exception {
        return objectMapper.readValue(jsonStr, Map.class);
    }

    /**
     * json string convert to map with javaBean
     */
    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz)
            throws Exception {
        Map<String, Map<String, Object>> map = objectMapper.readValue(jsonStr,
                new TypeReference<Map<String, T>>() {
                });
        Map<String, T> result = new HashMap<String, T>();
        for (Entry<String, Map<String, Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
        }
        return result;
    }

    /**
     * json array string convert to list with javaBean
     */
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz)
            throws Exception {
        List<Map<String, Object>> list = objectMapper.readValue(jsonArrayStr,
                new TypeReference<List<T>>() {
                });
        List<T> result = new ArrayList<T>();
        for (Map<String, Object> map : list) {
            result.add(map2pojo(map, clazz));
        }
        return result;
    }

    /**
     * map convert to javaBean
     */
    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    /**
     * 将xml文本转换成POJO<br>
     *
     * @param xml
     * @param cls
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T xml2pojo(String xml, Class<T> cls) throws IOException {
        return xmlMapper.readValue(xml, cls);
    }

    /**
     * 将POJO转成xml<br>
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static String pojo2xml(Object obj) throws Exception {
        // 根节点为<MsgText>
        String string = xmlMapper.writer().withRootName("MsgText").writeValueAsString(obj);
        return new StringBuilder(XML_ENCODING).append(string).toString();
    }

    /**
     * 将xml转成Map,(子元素也为Map)
     *
     * @param xml
     * @return
     * @throws Exception
     */
    public static Map<String, Object> xml2map(String xml) throws Exception {
        return xml2pojo(xml, Map.class);
    }

    /**
     * 将xml转成Map,(子元素转成elementMap中定义类型,缺省Map)
     *
     * @param xml
     * @param elementMap
     * @return
     * @throws Exception
     */
    public static Map<String, Object> xml2map(String xml, Map<String, Class> elementMap) throws Exception {

        Map<String, Object> map = xml2pojo(xml, Map.class);

        for (Entry<String, Class> entry : elementMap.entrySet()) {
            Class clazz = entry.getValue();
            Object elementDate = map.get(entry.getKey());
            // elementMap中的节点在map中不存在时，提供默认值
            if (map.get(entry.getKey()) == null || elementDate instanceof Map) {
                Object obj = map2bean(clazz, (Map) elementDate);
                map.put(entry.getKey(), obj);
            }
            else {
                map.put(entry.getKey(), map.get(entry.getKey()));
            }
        }
        return map;
    }

    /**
     * 递归将map转换成POJO<br>
     *
     * @param beanClass
     * @param map
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T map2bean(Class<T> beanClass, Map<String, Object> map) throws Exception {
        if (map == null) {
            return beanClass.newInstance();
        }
        // 内部实例化对象
        T bean = beanClass.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

        if (pds != null && pds.length > 0) {
            for (PropertyDescriptor pd : pds) {
                String key = StringUtils.capitalize(pd.getName());
                Object value = map.get(key);
                if (value instanceof Map) {
                    // 递归调用，嵌套map的转换，map中存放map，map中的map表示一个对象
                    Object obj = map2bean(pd.getPropertyType(), (Map) value);
                    pd.getWriteMethod().invoke(bean, obj);
                } else {
                    pd.getWriteMethod().invoke(bean, value);
                }
            }
        }
        return bean;
    }

}