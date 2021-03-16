public class Cube {
    // klasa wykorzystywana również dla zrzutowanych szecianów

    private Point[] vertices;
    private int[] color = {255,255,255};

    public Cube(Point[] vertices){
        this.setVertices(vertices);
    }

    public Point[] getVertices() {
        return vertices;
    }

    public void setVertices(Point[] vertices) {
        this.vertices = vertices;
    }

    public int[] getColor() {
        return color;
    }

    public void setColor(int[] color) {
        this.color = color;
    }
}
