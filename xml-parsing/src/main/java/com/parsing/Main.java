package com.parsing;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.net.URL;

public class Main {
    public static JsonNode convertXmlToJson(String fileName) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        ClassLoader classLoader = Main.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        JsonNode jsonNode = xmlMapper.readTree(resource);
        return jsonNode;
    }

    public static String getAttributeValue(JsonNode jsonNode, String attributeName) {
        JsonNode attributeNode = jsonNode.findValue(attributeName);
        return attributeNode != null ? attributeNode.asText() : null;
    }


    public static void main(String[] args) {
        try {
            JsonNode jsonNode = convertXmlToJson("score.xml");
            String decisionFlowId = getAttributeValue(jsonNode, "DecisionFlowId");
            String score = getAttributeValue(jsonNode, "SCORE");

            System.out.println("DecisionFlowId: " + decisionFlowId);
            System.out.println("SCORE: " + score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}