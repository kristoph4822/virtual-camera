import Jama.Matrix;

public class ObstructionDetector {

    /*  FUNCTIONS FOR SORTING CUBE WALLS BASED ON PLANE SET BY OTHER WALL.
        IF A WALL AND OBSERVER ARE ON THE SAME SIDE OF THE PLANE SET BY OTHER WALL,
        IT MEANS THIS WALL COVERS THE OTHER.
    */

    public static Matrix getPlaneFromPolygon(Polygon polygon) {

        Point[] polygonVertices = polygon.getVertices();

        float x1 = polygonVertices[0].getX();
        float y1 = polygonVertices[0].getY();
        float z1 = polygonVertices[0].getZ();

        float x2 = polygonVertices[1].getX();
        float y2 = polygonVertices[1].getY();
        float z2 = polygonVertices[1].getZ();

        float x3 = polygonVertices[2].getX();
        float y3 = polygonVertices[2].getY();
        float z3 = polygonVertices[2].getZ();

        float ux = x2 - x1;
        float uy = y2 - y1;
        float uz = z2 - z1;
        float vx = x3 - x1;
        float vy = y3 - y1;
        float vz = z3 - z1;

        float a = uy * vz - uz * vy;
        float b = uz * vx - ux * vz;
        float c = ux * vy - uy * vx;
        float d = (-a * x1 - b * y1 - c * z1);

        return new Matrix(new double[]{a, b, c, d}, 1);
    }

    public static double checkPointSideAgainstPlane(Matrix plane, Point point) {
        return pointMatrixMultiplication(plane, point) * pointMatrixMultiplication(plane, new Point(0, 0, 0));
    }

    public static int isPointOnObserverSide(Polygon polygon2, Point point1) {
        double result = checkPointSideAgainstPlane(getPlaneFromPolygon(polygon2), point1);
        if (result > 0)
            return -1;
        else
            return 1;
    }

    private static double pointMatrixMultiplication(Matrix m, Point p) {
        return m.get(0, 0) * p.getX() + m.get(0, 1) * p.getY() + m.get(0, 2) * p.getZ() + m.get(0, 3);
    }

}

