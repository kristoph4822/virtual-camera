import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class FileReader {

    private String fileName;

    public FileReader(String fileName){
        this.fileName = fileName;
    }

    public ArrayList<Cube> readCubes() {

        ArrayList<Cube> cubes = new ArrayList<Cube>();

        try {
            Scanner coordinatesReader = new Scanner(new File("src/" + this.fileName));

            while (coordinatesReader.hasNextLine()){
                int verticesRead = 0;
                Point[] cubeVertices = new Point[8];

                while (coordinatesReader.hasNextLine() && verticesRead < 8) {
                    cubeVertices[verticesRead] = addVertex(coordinatesReader.nextLine());
                    verticesRead += 1;
                }

                cubes.add(new Cube(cubeVertices));
            }

            coordinatesReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading coordinates file.");
            e.printStackTrace();
        }

        return cubes;
    }

    public Point addVertex(String line){
        String[] coordinates  = line.split(",");
        Point vertex = new Point();

        vertex.setX(Float.parseFloat(coordinates[0].strip()));
        vertex.setY(Float.parseFloat(coordinates[1].strip()));
        vertex.setZ(Float.parseFloat(coordinates[2].strip()));

        return vertex;
    }

}
