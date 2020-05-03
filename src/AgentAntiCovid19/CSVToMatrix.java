package AgentAntiCovid19;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * @author Leandro
 */

public class CSVToMatrix {
    private char separator;

    public CSVToMatrix(char separator) {this.separator = separator;}

    public ArrayList<String[]> fileToMatrix(String path) {
        ArrayList<String[]> matrix = new ArrayList<String[]>();
        BufferedReader bufferLectura;

        try {
            bufferLectura = new BufferedReader(new FileReader(path));
            String linea = bufferLectura.readLine();

            while (linea != null) {
                String[] atributos = linea.split(String.valueOf(this.separator));
                matrix.add(atributos);
                linea = bufferLectura.readLine();
            }

            if (bufferLectura != null) {
                bufferLectura.close();
            }
        } catch (IOException e) {
            System.out.println("El archivo .csv de nodos no existe.");
        }
        return matrix;
    }
}
