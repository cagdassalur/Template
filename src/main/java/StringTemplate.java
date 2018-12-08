import java.util.HashMap;

public class StringTemplate {
    private static final String defaultTemplateTokenStart = "{{";
    private static final String defaultTemplateTokenEnd = "}}";
    private static final String defaultKeyValueSeperator = ":";


    private String templateTokenStart = defaultTemplateTokenStart;
    private String templateTokenEnd = defaultTemplateTokenEnd;
    private String keyValueSeperator = defaultKeyValueSeperator;

    private HashMap<String, String> map;
    private String template;

    public StringTemplate() {
    }

    public StringTemplate(String template) {
        this.template = template;
    }

    public StringTemplate(String template, HashMap<String, String> valueMap) {
        this.template = template;
        this.map = valueMap;
    }

    public void setValueMap(HashMap<String,String> map){
        this.map = map;
    }

    public void setKeyValueSeperator(String seperator){
        this.keyValueSeperator = seperator;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public void setValueMap(String... keyValues){
        this.map = parseKeyValues(keyValueSeperator, keyValues);
    }

    public void setTemplateToken(String start, String end){
        this.templateTokenStart = start;
        this.templateTokenEnd = end;
    }

    public String getContent(){
        return getContent(template, map, templateTokenStart, templateTokenEnd);
    }

    private static String getContent(String template, HashMap<String, String> map, String templateTokenStart, String templateTokenEnd){
        String keyWithToken;
        String out = template;

        for (String key:map.keySet()){
            keyWithToken = templateTokenStart + key + templateTokenEnd;
            out = out.replace(keyWithToken, map.get(key));
        }

        return out;
    }

    public static String getContent(String template, HashMap<String, String> map){
        return getContent(template, map, defaultTemplateTokenStart, defaultTemplateTokenEnd);
    }

    public static String getContent(String template, String... keyValues){
        return getContent(template, defaultKeyValueSeperator, keyValues);
    }

    public static String getContent(String template, String keyValueSeperator, String[] keyvalues){
        HashMap<String, String> map = parseKeyValues(keyValueSeperator, keyvalues);
        return getContent(template, map);
    }

    private static HashMap<String, String> parseKeyValues(String seperator, String[] keyvalues){
        HashMap<String, String> out = new HashMap<String, String>();
        for (String keyvalue : keyvalues){
            String[] keyvalueAr = keyvalue.split(seperator, 2);
            out.put(keyvalueAr[0], keyvalueAr[1]);
        }

        return out;
    }

}
