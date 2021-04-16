public class Polygon implements Comparable<Polygon> {

    private Point[] vertices;

    public Polygon(Point[] vertices) {
        this.vertices = vertices;
    }

    public Point[] getVertices() {
        return vertices;
    }

    public Point getCenter() {
        Point center = new Point();
        float midX = (vertices[0].getX() + vertices[1].getX() + vertices[2].getX() + vertices[3].getX()) / 4;
        float midY = (vertices[0].getY() + vertices[1].getY() + vertices[2].getY() + vertices[3].getY()) / 4;
        float midZ = (vertices[0].getZ() + vertices[1].getZ() + vertices[2].getZ() + vertices[3].getZ()) / 4;
        center.setX(midX);
        center.setY(midY);
        center.setZ(midZ);
        return center;
    }

    @Override
    public int compareTo(Polygon p) {
        return ObstructionDetector.isPointOnObserverSide(this, p.getCenter());
    }
}
