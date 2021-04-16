import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

    private final String fileName;
    public final int N_CUBES = 4;

    public FileReader(String fileName) {
        this.fileName = fileName;
    }

    public Cube[] readCubes() {

        Cube[] cubes = new Cube[N_CUBES];

        try {
            Scanner coordinatesReader = new Scanner(new File("src/" + this.fileName));
            int i = 0;
            while (coordinatesReader.hasNextLine()) {
                int verticesRead = 0;
                Point[] cubeVertices = new Point[8];

                while (coordinatesReader.hasNextLine() && verticesRead < 8) {
                    cubeVertices[verticesRead] = addVertex(coordinatesReader.nextLine());
                    verticesRead += 1;
                }

                cubes[i] = new Cube(cubeVertices);
                i++;
            }

            coordinatesReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading coordinates file.");
            e.printStackTrace();
        }

        return cubes;
    }

    public Point addVertex(String line) {
        String[] coordinates = line.split(",");
        Point vertex = new Point();

        vertex.setX(Float.parseFloat(coordinates[0].strip()));
        vertex.setY(Float.parseFloat(coordinates[1].strip()));
        vertex.setZ(Float.parseFloat(coordinates[2].strip()));

        return vertex;
    }

}
