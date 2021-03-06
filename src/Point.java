import Jama.*;

public class Point {
    // klasa wykorzystywana zarówno przy 3D, jak i 2D

    private float x;
    private float y;
    private float z;

    public Point() {
    }

    public Point(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public Matrix toMatrix() {
        double[][] coordinatesArray = {{this.x, this.y, this.z, 1.}};
        return new Matrix(coordinatesArray);
    }
}
