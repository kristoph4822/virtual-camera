import Jama.*;

import java.util.ArrayList;

public class Projector {
    // środek rzutowania w początku układu współrzędnych

    public static final int VIEWPORT_WIDTH = 1400;
    public static final int VIEWPORT_HEIGHT = 900;
    private static final float ZOOM_VALUE = 25;

    private static float vpd = 1000; //odległość środka rzutowania do płaszczyzny rzutni

    private static final double[][] projectionArray = {{1.,0.,0.,0.},{0.,1.,0.,0.},{0.,0.,1.,0.},{0.,0.,1/vpd, 0.}};
    private static final Matrix projectionMatrix = new Matrix(projectionArray);


    public static ArrayList<Cube> projectCubes(ArrayList<Cube> cubes){
        ArrayList<Cube> projectedCubes = new ArrayList<>();

        for (Cube c : cubes) {
            Point[] projectedCubeVertices = new Point[8];
            int i = 0;

            for (Point p : c.getVertices()) {
                projectedCubeVertices[i] = projectPoint(p);
                i += 1;
            }

            Cube projectedCube = new Cube(projectedCubeVertices);
            projectedCubes.add(projectedCube);
        }

        return projectedCubes;
    }

    private static Point projectPoint(Point p){
        Point projectedPoint;
        Matrix vertexVector = p.toMatrix();

        /*
        jeżeli Z jest mniejsze od 0, czyli punkt jest za obserwatorem
        to ustawiamy z=0 żeby uniknąć błędów wyświetlania
        */
        if (p.getZ() < 0)
            vertexVector.set(0,2,0);

        Matrix projectedVertex = vertexVector.times(projectionMatrix).times(vpd/vertexVector.get(0,2));
        projectedPoint = matrixToPoint(projectedVertex);
        translatePointForProcessing(projectedPoint);

        return projectedPoint;
    }

    private static Point matrixToPoint(Matrix vertexMatrix){
        Point vertexPoint = new Point();
        vertexPoint.setX((float)vertexMatrix.get(0,0));
        vertexPoint.setY((float)vertexMatrix.get(0,1));

        return vertexPoint;
    }

    /*
    Punkt 0,0 jest w centrum naszej rzutni, a w Processingu w lewym górnym rog;
    W związku z tym trzeba skorygować współrzędne zrzutowanych punktów
     */
    private static void translatePointForProcessing(Point p){
        p.setX(p.getX() + VIEWPORT_WIDTH/2);
        p.setY(p.getY() * -1);
        p.setY(p.getY() + VIEWPORT_HEIGHT/2);
    }

    public static void zoomIn (){
        vpd += ZOOM_VALUE;
    }

    public static void zoomOut (){
        if (vpd > ZOOM_VALUE)
            vpd -= ZOOM_VALUE;
    }
}
