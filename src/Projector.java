import Jama.*;

import java.util.ArrayList;

public class Projector {
    // środek rzutowania w początku układu współrzędnych

    public static final int VIEWPORT_WIDTH = 1400;
    public static final int VIEWPORT_HEIGHT = 900;
    private static final float VPD = 400; //odległość środka rzutowania do płaszczyzny rzutni

    private static double[][] projectionArray = {{1.,0.,0.,0.},{0.,1.,0.,0.},{0.,0.,1.,0.},{0.,0.,1/VPD, 0.}};
    private static Matrix projectionMatrix = new Matrix(projectionArray);


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
        double[][] coordinatesArray = {{p.getX(),p.getY(),p.getZ(),1.}};
        Matrix vertexVector = new Matrix(coordinatesArray);
        Matrix projectedVertex = vertexVector.times(projectionMatrix).times(VPD/vertexVector.get(0,2));
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
}
