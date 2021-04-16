import processing.core.PApplet;

import java.util.Arrays;

public class ProcessingMain extends PApplet {

    private Cube[] cubes;
    private boolean edgesViewMode = true;

    public void settings() {
        size(Projector.VIEWPORT_WIDTH, Projector.VIEWPORT_HEIGHT);
        FileReader fr = new FileReader("coordinates.txt");
        this.cubes = fr.readCubes();

        Arrays.sort(cubes[0].getWalls());
        Transformator.translateCubes(cubes, "right");
        Arrays.sort(cubes[0].getWalls());
        Arrays.sort(cubes[0].getWalls());

        setCubeColors(cubes);
        noLoop();
    }

    public void keyPressed() {

        switch (key) {
            case 'd' -> Transformator.translateCubes(cubes, "right");
            case 'a' -> Transformator.translateCubes(cubes, "left");
            case 'w' -> Transformator.translateCubes(cubes, "forward");
            case 's' -> Transformator.translateCubes(cubes, "backward");
            case 'q' -> Transformator.translateCubes(cubes, "up");
            case 'e' -> Transformator.translateCubes(cubes, "down");

            case 'i' -> Transformator.rotateCubes(cubes, "up");
            case 'k' -> Transformator.rotateCubes(cubes, "down");
            case 'l' -> Transformator.rotateCubes(cubes, "right");
            case 'j' -> Transformator.rotateCubes(cubes, "left");
            case 'o' -> Transformator.rotateCubes(cubes, "sideways_right");
            case 'u' -> Transformator.rotateCubes(cubes, "sideways_left");

            case 'z' -> Projector.zoomIn();
            case 'x' -> Projector.zoomOut();

            case 'm' -> changeViewToEdges();
            case 'n' -> changeViewToWalls();
        }
        redraw();
    }

    public void draw() {
        background(20);

        if (!edgesViewMode) {
            background(240);

            for (Cube c : cubes) {
                Arrays.sort(c.getWalls());
            }
        }


        Arrays.sort(cubes);

        Cube[] projectedCubes = Projector.projectCubes(cubes);
        int[] color;
        int i = 0;

        for (Cube c : projectedCubes) {
            color = this.cubes[i].getColor();
            if (edgesViewMode) {
                stroke(color[0], color[1], color[2]);
                drawCubeEdges(c);
            } else {
                stroke(0);
                fill(color[0], color[1], color[2]);
                drawCubeWalls(c);
            }
            i++;
        }

        textSize(22);

        if (!edgesViewMode) {
            fill(20);
            text("Visible Walls Mode", 10, 30);
        } else {
            fill(240);
            text("Transparent Walls Mode", 10, 30);
        }
    }

    public void drawCubeWalls(Cube c) {
        Polygon[] p = c.getWalls();
        Point[] p0 = p[0].getVertices();
        Point[] p1 = p[1].getVertices();
        Point[] p2 = p[2].getVertices();
        Point[] p3 = p[3].getVertices();
        Point[] p4 = p[4].getVertices();
        Point[] p5 = p[5].getVertices();

        quad(p0[0].getX(), p0[0].getY(), p0[1].getX(), p0[1].getY(), p0[2].getX(), p0[2].getY(), p0[3].getX(), p0[3].getY());
        quad(p1[0].getX(), p1[0].getY(), p1[1].getX(), p1[1].getY(), p1[2].getX(), p1[2].getY(), p1[3].getX(), p1[3].getY());
        quad(p2[0].getX(), p2[0].getY(), p2[1].getX(), p2[1].getY(), p2[2].getX(), p2[2].getY(), p2[3].getX(), p2[3].getY());
        quad(p3[0].getX(), p3[0].getY(), p3[1].getX(), p3[1].getY(), p3[2].getX(), p3[2].getY(), p3[3].getX(), p3[3].getY());
        quad(p4[0].getX(), p4[0].getY(), p4[1].getX(), p4[1].getY(), p4[2].getX(), p4[2].getY(), p4[3].getX(), p4[3].getY());
        quad(p5[0].getX(), p5[0].getY(), p5[1].getX(), p5[1].getY(), p5[2].getX(), p5[2].getY(), p5[3].getX(), p5[3].getY());
    }

    public void drawCubeEdges(Cube c) {
        for (Polygon poly : c.getWalls()) {
            Point[] v = poly.getVertices();
            line(v[0].getX(), v[0].getY(), v[1].getX(), v[1].getY());
            line(v[1].getX(), v[1].getY(), v[2].getX(), v[2].getY());
            line(v[2].getX(), v[2].getY(), v[3].getX(), v[3].getY());
            line(v[3].getX(), v[3].getY(), v[0].getX(), v[0].getY());
        }
    }

    public void setCubeColors(Cube[] cubes) {
        int[] red = {255, 50, 0};
        int[] green = {0, 255, 60};
        int[] blue = {0, 150, 255};
        int[] yellow = {255, 255, 80};

        if (cubes.length >= 4) {
            cubes[0].setColor(blue);
            cubes[1].setColor(green);
            cubes[2].setColor(yellow);
            cubes[3].setColor(red);
        }
    }

    public void changeViewToEdges() {
        this.edgesViewMode = true;
    }

    public void changeViewToWalls() {
        this.edgesViewMode = false;
    }

    public static void main(String... args) {
        PApplet.main("ProcessingMain");
    }

}
