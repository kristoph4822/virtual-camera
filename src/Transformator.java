import Jama.Matrix;

public class Transformator {

    private static final double[][] identityArray = {{1., 0., 0., 0.}, {0., 1., 0., 0.}, {0., 0., 1., 0.}, {0., 0., 0., 1.}};
    private static final Matrix identityMatrix = new Matrix(identityArray);

    private static final double TRANSLATION_VALUE = 20;
    private static final double ROTATION_DEGREE = 2;

    public static void translateCubes(Cube[] cubes, String direction) {
        for (Cube c : cubes) {
            for (Point v : c.getVertices()) {
                switch (direction) {
                    case "right" -> translate(v, -TRANSLATION_VALUE, 0, 0);
                    case "left" -> translate(v, TRANSLATION_VALUE, 0, 0);
                    case "up" -> translate(v, 0, -TRANSLATION_VALUE, 0);
                    case "down" -> translate(v, 0, TRANSLATION_VALUE, 0);
                    case "forward" -> translate(v, 0, 0, -TRANSLATION_VALUE);
                    case "backward" -> translate(v, 0, 0, TRANSLATION_VALUE);
                }
            }
        }
    }

    public static void rotateCubes(Cube[] cubes, String direction) {
        for (Cube c : cubes) {
            for (Point v : c.getVertices()) {
                switch (direction) {
                    case "right" -> rotate(v, 'Y', -ROTATION_DEGREE);
                    case "left" -> rotate(v, 'Y', ROTATION_DEGREE);
                    case "up" -> rotate(v, 'X', ROTATION_DEGREE);
                    case "down" -> rotate(v, 'X', -ROTATION_DEGREE);
                    case "sideways_right" -> rotate(v, 'Z', ROTATION_DEGREE);
                    case "sideways_left" -> rotate(v, 'Z', -ROTATION_DEGREE);
                }
            }
        }
    }

    private static void translate(Point p, double tx, double ty, double tz) {
        Matrix translationMatrix = identityMatrix.copy();

        translationMatrix.set(3, 0, tx);
        translationMatrix.set(3, 1, ty);
        translationMatrix.set(3, 2, tz);

        Matrix translatedVertex = p.toMatrix().times(translationMatrix);

        p.setX((float) translatedVertex.get(0, 0));
        p.setY((float) translatedVertex.get(0, 1));
        p.setZ((float) translatedVertex.get(0, 2));
    }

    private static void rotate(Point p, char axis, double deg) {
        Matrix rotationMatrix = identityMatrix.copy();
        double sin = Math.sin(Math.toRadians(deg));
        double cos = Math.cos(Math.toRadians(deg));

        switch (axis) {
            case 'X' -> {
                rotationMatrix.set(1, 1, cos);
                rotationMatrix.set(1, 2, sin);
                rotationMatrix.set(2, 1, -sin);
                rotationMatrix.set(2, 2, cos);
            }
            case 'Y' -> {
                rotationMatrix.set(0, 0, cos);
                rotationMatrix.set(0, 2, -sin);
                rotationMatrix.set(2, 0, sin);
                rotationMatrix.set(2, 2, cos);
            }
            case 'Z' -> {
                rotationMatrix.set(0, 0, cos);
                rotationMatrix.set(0, 1, sin);
                rotationMatrix.set(1, 0, -sin);
                rotationMatrix.set(1, 1, cos);
            }
        }

        Matrix rotatedVertex = p.toMatrix().times(rotationMatrix);

        p.setX((float) rotatedVertex.get(0, 0));
        p.setY((float) rotatedVertex.get(0, 1));
        p.setZ((float) rotatedVertex.get(0, 2));
    }
}
