public class Cube {
    // klasa wykorzystywana również dla zrzutowanych szecianów

    private Point[] vertices;

    public Cube(Point[] vertices){
        this.setVertices(vertices);
    }

    public Point[] getVertices() {
        return vertices;
    }

    public void setVertices(Point[] vertices) {
        this.vertices = vertices;
    }
}
