package com.rikelek1.base.engine;

public class Material {
    private Texture texture;
    private Vector3f colour;

    public Material(Texture texture, Vector3f colour) {
        this.texture = texture;
        this.colour = colour;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Vector3f getColour() {
        return colour;
    }

    public void setColour(Vector3f colour) {
        this.colour = colour;
    }
}
