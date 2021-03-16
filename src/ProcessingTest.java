import processing.core.PApplet;

import java.util.ArrayList;

public class ProcessingTest extends PApplet{

    ArrayList<Cube> cubes;

    public void settings(){
        size(Projector.VIEWPORT_WIDTH, Projector.VIEWPORT_HEIGHT);
        FileReader fr = new FileReader("coordinates.txt");
        this.cubes = Projector.projectCubes(fr.readCubes());
    }

    public void draw(){
        for (Cube c : cubes) {
            drawCube(c);
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

    public static void main(String... args){
        PApplet.main("ProcessingTest");

    }
}
