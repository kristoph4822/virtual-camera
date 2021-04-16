import Jama.*;


public class Projector {
    // środek rzutowania w początku układu współrzędnych

    public static final int VIEWPORT_WIDTH = 1366;
    public static final int VIEWPORT_HEIGHT = 768;
    private static final float ZOOM_VALUE = 25;

    public static float vpd = 1000; //odległość środka rzutowania do płaszczyzny rzutni

    private static final double[][] projectionArray = {{1., 0., 0., 0.}, {0., 1., 0., 0.}, {0., 0., 1., 0.}, {0., 0., 1 / vpd, 0.}};
    private static final Matrix projectionMatrix = new Matrix(projectionArray);


    public static Cube[] projectCubes(Cube[] cubes) {
        Cube[] projectedCubes = new Cube[cubes.length];
        int i = 0;

        for (Cube c : cubes) {
            Polygon[] projectedCubeWalls = new Polygon[6];
            int j = 0;

            for (Polygon p : c.getWalls()) {
                projectedCubeWalls[j] = projectWall(p);
                j += 1;
            }

            Cube projectedCube = new Cube(projectedCubeWalls);
            projectedCubes[i] = projectedCube;
            i++;
        }

        return projectedCubes;
    }

    private static Polygon projectWall(Polygon polygon) {
        Point[] projectedPoints = new Point[4];
        int i = 0;
        for (Point p : polygon.getVertices()) {
            projectedPoints[i] = projectPoint(p);
            i++;
        }
        return new Polygon(projectedPoints);
    }

    private static Point projectPoint(Point p) {
        Point projectedPoint;
        Matrix vertexVector = p.toMatrix();

        /*
        jeżeli Z jest mniejsze od 0, czyli punkt jest za obserwatorem wsytępują błędy wyświetlania;
        jeżeli ustawimy z=0 to nie wyświetlą się krawędzie których jeden punkt jest za obserwatorem;
        */
        if (p.getZ() < 0)
            vertexVector.set(0, 2, 0);

        Matrix projectedVertex = vertexVector.times(projectionMatrix).times(vpd / vertexVector.get(0, 2));
        projectedPoint = matrixToPoint(projectedVertex);
        translatePointForProcessing(projectedPoint);

        return projectedPoint;
    }

    private static Point matrixToPoint(Matrix vertexMatrix) {
        Point vertexPoint = new Point();
        vertexPoint.setX((float) vertexMatrix.get(0, 0));
        vertexPoint.setY((float) vertexMatrix.get(0, 1));

        return vertexPoint;
    }

    /*
    Punkt 0,0 jest w centrum naszej rzutni, a w Processingu w lewym górnym rog;
    W związku z tym trzeba skorygować współrzędne zrzutowanych punktów
     */
    private static void translatePointForProcessing(Point p) {
        p.setX(p.getX() + VIEWPORT_WIDTH / 2);
        p.setY(p.getY() * -1);
        p.setY(p.getY() + VIEWPORT_HEIGHT / 2);
    }

    public static void zoomIn() {
        vpd += ZOOM_VALUE;
    }

    public static void zoomOut() {
        if (vpd > ZOOM_VALUE)
            vpd -= ZOOM_VALUE;
    }
}
