import io.ngss.utils.StringTemplate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.HashMap;

public class StringTemplateTest {

    @Test
    public void testStaticHashMap(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("a", "0");
        map.put("b", "1");
        String content = StringTemplate.getContent("ab{{b}}aba{{a}}aa", map);
        assertEquals("ab1aba0aa", content);
    }

    @Test
    public void testHashMap(){
        StringTemplate template = new StringTemplate("ab{{b}}aba{{a}}aa");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("a", "0");
        map.put("b", "1");
        template.setValueMap(map);

        assertEquals("ab1aba0aa", template.getContent());
    }

    @Test
    public void testHashMapConstructor(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("a", "0");
        map.put("b", "1");

        StringTemplate template = new StringTemplate("ab{{b}}aba{{a}}aa", map);

        assertEquals("ab1aba0aa", template.getContent());
    }

    @Test
    public void testHashMapEmptyConstructor(){
        StringTemplate template = new StringTemplate();
        template.setTemplate("ab{{b}}aba{{a}}aa");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("a", "0");
        map.put("b", "1");
        template.setValueMap(map);

        assertEquals("ab1aba0aa", template.getContent());
    }

    @Test
    public void testHashMapTemplateToken(){
        StringTemplate template = new StringTemplate();
        template.setTemplateToken("<", ">");
        template.setTemplate("ab<b>a<tt>ba<a>aa");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("a", "0");
        map.put("b", "1");
        template.setValueMap(map);

        assertEquals("ab1a<tt>ba0aa", template.getContent());
    }

    @Test
    public void testArray(){
        StringTemplate template = new StringTemplate("ab{{b}}aba{{a}}aa");
        template.setValueMap("a:0", "b:1");

        assertEquals("ab1aba0aa", template.getContent());
    }


    @Test
    public void testArrayCustomSeperator(){
        StringTemplate template = new StringTemplate("ab{{b}}aba{{a}}aa");
        template.setKeyValueSeperator(";;");
        template.setValueMap("a;;0", "b;;1");

        assertEquals("ab1aba0aa", template.getContent());
    }

    @Test
    public void testStaticArray(){
        String content = StringTemplate.getContent("ab{{b}}aba{{a}}aa", "a:0", "b:1");
        assertEquals("ab1aba0aa", content);
    }


}
