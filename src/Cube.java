public class Cube implements Comparable<Cube> {
    // klasa wykorzystywana również dla zrzutowanych szecianów

    private Point[] vertices;
    private int[] color = {255, 255, 255};
    private Polygon[] walls = new Polygon[6];

    public Cube(Point[] vertices) {
        this.setVertices(vertices);
        this.setWalls();
    }

    public Cube(Polygon[] walls) {
        this.walls = walls;
    }

    public Point[] getVertices() {
        return vertices;
    }

    public void setVertices(Point[] vertices) {
        this.vertices = vertices;
    }

    private void setWalls() {
        this.walls[0] = new Polygon(getWallVertices(0, 1, 5, 4));
        this.walls[1] = new Polygon(getWallVertices(1, 2, 6, 5));
        this.walls[2] = new Polygon(getWallVertices(2, 6, 7, 3));
        this.walls[3] = new Polygon(getWallVertices(3, 7, 4, 0));
        this.walls[4] = new Polygon(getWallVertices(0, 1, 2, 3));
        this.walls[5] = new Polygon(getWallVertices(4, 5, 6, 7));
    }

    private Point[] getWallVertices(int v0, int v1, int v2, int v3) {
        Point[] wallVertices = new Point[4];
        wallVertices[0] = this.vertices[v0];
        wallVertices[1] = this.vertices[v1];
        wallVertices[2] = this.vertices[v2];
        wallVertices[3] = this.vertices[v3];
        return wallVertices;
    }

    public float getCenterZ() {
        return (vertices[0].getZ() + vertices[1].getZ() + vertices[2].getZ() + vertices[3].getZ() + vertices[4].getZ() + vertices[5].getZ() + vertices[6].getZ() + vertices[7].getZ()) / 8;
    }

    public int[] getColor() {
        return color;
    }

    public void setColor(int[] color) {
        this.color = color;
    }

    public Polygon[] getWalls() {
        return this.walls;
    }

    @Override
    public int compareTo(Cube c) {
        float z1 = c.getCenterZ();
        float z2 = this.getCenterZ();
        if (z1 < z2)
            return -1;
        else
            return 1;
    }
}
