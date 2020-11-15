package com.infinity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader {
    private StringBuilder fileContent = new StringBuilder();
    private final String regexToGetCost = "=\\s*(\\d+)";
    private final String regexToGetAdjacentVertex = "->(.)";

    public FileReader(String fileName) {
        loadFileContent(fileName);
    }

    public Vertex getSource() {
        return getPropertyAsVertex("Source", 2);
    }

    public Vertex getDestination() {
        return getPropertyAsVertex("Destination", 2);
    }

    public Graph initGraphFromFile() {
        String[] connections = getAllConnections();
        List<String> connectionList = new LinkedList<>(Arrays.asList(connections));
        Map<Vertex, Map<Vertex, Integer>> graph = new HashMap<>();

        while (!connectionList.isEmpty()) {
            String currentConnection = connectionList.remove(0);
            Character vertexName = currentConnection.charAt(0);
            Vertex vertex = new Vertex(vertexName);
            Map<Vertex, Integer> currentVertexMap = getAllConnectionsAndCostsOfCurrentVertex
                                             (connectionList, currentConnection, vertexName);
            graph.put(vertex, currentVertexMap);
        }
        return new Graph(graph);
    }

    private Map<Vertex, Integer> getAllConnectionsAndCostsOfCurrentVertex(List<String> connectionList, String currentConnection, Character vertexName) {
        Map<Vertex, Integer> currentVertexMap = new HashMap<>();

        while (currentConnection != null) {
            String adjacentVertexName = getPropertyAccordingToRegex(currentConnection, regexToGetAdjacentVertex, 1);
            Vertex adjacentVertex = new Vertex(adjacentVertexName.charAt(0));
            String cost = getPropertyAccordingToRegex(currentConnection, regexToGetCost, 1);
            Integer costInt = Integer.parseInt(cost);
            currentVertexMap.put(adjacentVertex, costInt);
            currentConnection = findConnectionStartsWithSpecificChar(connectionList, vertexName);
        }
        return currentVertexMap;
    }

    private String[] getAllConnections() {
        String regex = "(?<=Connection:\\n)[\\s\\S]*";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(fileContent);
        if (!matcher.find()) {
            throw new IllegalArgumentException();
        }
        String connections[] = matcher.group(0).split("\\n");
        return connections;
    }

    private String findConnectionStartsWithSpecificChar(List<String> connectionList, Character c) {
        for(String connection : connectionList){
            if (connection.startsWith(c.toString())){
                connectionList.remove(connection);
                return connection;
            }
        }
        return null;
    }

    private String getPropertyAccordingToRegex(String s, String regex, int group) {
        String connection = getCharacterByRegex(s, regex, group);
        return connection;
    }

    private Vertex getPropertyAsVertex(String property, int group) {
        String regex = "(" + property + "):\\s*(.)";
        String source = getCharacterByRegex(fileContent.toString(), regex, group);
        return new Vertex(source.charAt(0));
    }

    private String getCharacterByRegex(String s, String regex, int group) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(s);
        boolean isFound = matcher.find();
        String connection = matcher.group(group);
        if (!isFound) {
            throw new IllegalArgumentException();
        }
        return connection;
    }

    private void loadFileContent(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        try (InputStreamReader streamReader =
                     new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
