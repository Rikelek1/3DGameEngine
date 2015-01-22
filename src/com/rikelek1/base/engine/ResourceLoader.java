package com.rikelek1.base.engine;

import org.newdawn.slick.opengl.TextureLoader;

import java.io.*;
import java.util.ArrayList;

public class ResourceLoader {
    public static Texture loadTexture(String filename) {
        String[] splitArray = filename.split("\\.");
        String extension = splitArray[splitArray.length - 1];

        try {
            int id = TextureLoader.getTexture(extension, new FileInputStream(new File("C:/Program Files/3DGameEngine/res/textures/" + filename))).getTextureID();

            return new Texture(id);
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    public static String loadShader(String filename) {
        StringBuilder shaderSource = new StringBuilder();
        BufferedReader shaderReader = null;

        try {
            shaderReader = new BufferedReader(new FileReader("C:/Program Files/3DGameEngine/res/shaders/" + filename));
            String line;

            while((line = shaderReader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }

            shaderReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return shaderSource.toString();
    }

    public static Mesh loadMesh(String filename) {
        String[] splitArray = filename.split("\\.");
        String extension = splitArray[splitArray.length - 1];

        if(!extension.equals("obj")) {
            System.err.println("Error: File format not supported for mesh data: " + extension);
            new Exception().printStackTrace();
            System.exit(1);
        }

        ArrayList<Vertex> vertices = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();

        BufferedReader meshReader;

        try {
            meshReader = new BufferedReader(new FileReader("C:/Program Files/3DGameEngine/res/models/" + filename));
            String line;

            while((line = meshReader.readLine()) != null) {
                String[] tokens = line.split(" ");
                tokens = Util.removeEmptyStrings(tokens);

                if(tokens.length == 0 || tokens[0].equals("#")) {
                    continue;
                } else if(tokens[0].equals("v")) {
                    vertices.add(new Vertex(new Vector3f(Float.valueOf(tokens[1]),
                                                         Float.valueOf(tokens[2]),
                                                         Float.valueOf(tokens[3]))));
                } else if(tokens[0].equals("f")) {
                    indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);

                    if(tokens.length > 4) {
                        indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                        indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
                        indices.add(Integer.parseInt(tokens[4].split("/")[0]) - 1);
                    }
                }
            }

            meshReader.close();

            Mesh res = new Mesh();
            Vertex[] vertexData = new Vertex[vertices.size()];
            vertices.toArray(vertexData);

            Integer[] indexData = new Integer[indices.size()];
            indices.toArray(indexData);

            res.addVertices(vertexData, Util.toIntArray(indexData));

            return res;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }
}
