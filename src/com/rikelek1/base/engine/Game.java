package com.rikelek1.base.engine;

public class Game {
    private Mesh mesh;
    private Shader shader;
    private Material material;
    private Transform transform;
    private Camera camera;

    public Game() {
        mesh = ResourceLoader.loadMesh("Dante.obj"); //new Mesh();
        material = new Material(ResourceLoader.loadTexture("no_texture.png"), new Vector3f(1, 1, 1));
        shader = BasicShader.getInstance();
        camera = new Camera();

//        Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-1, -1, 0), new Vector2f(0,0)),
//                                      new Vertex(new Vector3f(0, 1, 0), new Vector2f(0.5f,0)),
//                                      new Vertex(new Vector3f(1, -1, 0), new Vector2f(1,0)),
//                                      new Vertex(new Vector3f(0, -1, 1), new Vector2f(0,0.5f)),
//                                      new Vertex(new Vector3f(0, -1, -1))};
//
//        int[] indices = new int[] {3, 1, 0,
//                                   2, 1, 3,
//                                   0, 2, 3,
//                                   0, 1, 4,
//                                   4, 1, 2,
//                                   4, 2, 0};
//
//        mesh.addVertices(vertices, indices);

        Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
        Transform.setCamera(camera);
        transform = new Transform();
    }

    public void input() {
        camera.input();

//        if(Input.getKeyDown(Input.KEY_UP)) {
//            System.out.println("We've just pressed up!");
//        }
//        if(Input.getKeyUp(Input.KEY_UP)) {
//            System.out.println("We've just released up!");
//        }
//
//        if(Input.getMouseDown(1)) {
//            System.out.println("We've just right clicked at " + Input.getMousePosition().toString());
//        }
//        if(Input.getMouseUp(1)) {
//            System.out.println("We've just released right mouse button!");
//        }
    }

    float temp = 0.0f;

    public void update() {
        temp += Time.getDelta();

        float sinTemp = (float)Math.sin(temp);

        transform.setTranslation(0.0f, -3.3f, 7.0f);
        transform.setRotation(0.0f, sinTemp * 180.0f, 0.0f);
//        transform.setScale(0.5f, 0.5f, 0.5f);
    }


    public void render() {
        RenderUtil.setClearColour(Transform.getCamera().getPos().div(2048f).abs());
        shader.bind();
        shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
        mesh.draw();
    }
}
