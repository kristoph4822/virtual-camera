import processing.core.PApplet;
import java.util.ArrayList;

public class ProcessingMain extends PApplet{

    private ArrayList<Cube> cubes;

    public void settings(){
        size(Projector.VIEWPORT_WIDTH, Projector.VIEWPORT_HEIGHT);
        FileReader fr = new FileReader("coordinates.txt");
        this.cubes = fr.readCubes();
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
        }
        redraw();
    }

    public void draw(){
        background(20);
        ArrayList<Cube> projectedCubes = Projector.projectCubes(cubes);
        int color[];
        int i = 0;

        for (Cube c : projectedCubes) {
            color = this.cubes.get(i).getColor();
            stroke(color[0], color[1], color[2]);
            drawCube(c);
            i++;
        }
    }

    public void drawCube(Cube c){
        Point[] v = c.getVertices();
        line(v[0].getX(), v[0].getY(), v[1].getX(), v[1].getY());
        line(v[1].getX(), v[1].getY(), v[2].getX(), v[2].getY());
        line(v[2].getX(), v[2].getY(), v[3].getX(), v[3].getY());
        line(v[3].getX(), v[3].getY(), v[0].getX(), v[0].getY());
        line(v[0].getX(), v[0].getY(), v[4].getX(), v[4].getY());
        line(v[1].getX(), v[1].getY(), v[5].getX(), v[5].getY());
        line(v[2].getX(), v[2].getY(), v[6].getX(), v[6].getY());
        line(v[3].getX(), v[3].getY(), v[7].getX(), v[7].getY());
        line(v[4].getX(), v[4].getY(), v[5].getX(), v[5].getY());
        line(v[5].getX(), v[5].getY(), v[6].getX(), v[6].getY());
        line(v[6].getX(), v[6].getY(), v[7].getX(), v[7].getY());
        line(v[7].getX(), v[7].getY(), v[4].getX(), v[4].getY());
    }

    public void setCubeColors(ArrayList<Cube> cubes){
        int[] red = {255,50,0};
        int[] green = {0,255,60};
        int[] blue = {0,150,255};
        int[] yellow = {255,255,80};

        if (cubes.size() >= 4){
            cubes.get(0).setColor(blue);
            cubes.get(1).setColor(green);
            cubes.get(2).setColor(yellow);
            cubes.get(3).setColor(red);
        }
    }

    public static void main(String... args){
        PApplet.main("ProcessingMain");
    }

}
