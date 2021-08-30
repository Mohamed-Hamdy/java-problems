/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vector_quantizer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VectorQuantizer {

    public VectorQuantizer() {
    }

    // ************************************************************************************************************************ //
    //Public Methods
    public void Compression(String FileName, int codeBookLength, int vectRows) throws IOException {
        Read_write_Image newReader = new Read_write_Image();
        Files x = new Files();

        ArrayList<Integer> Input = new ArrayList<>();
        ArrayList<Vector> codeBook = new ArrayList<>();
        ArrayList<Vector> InputVectors = new ArrayList<>();
        ArrayList<Integer> Output = new ArrayList<>();

        Input = newReader.readImage(FileName);

        InputVectors = DivedeIntoVectors(Input, vectRows); // devide image into blocks

        codeBook = Create_Code_Book(InputVectors, codeBookLength, vectRows);

        Output = Make_Level(InputVectors, codeBook, vectRows);

        x.open_File("compressionOutput.txt");
        x.writeArrayList(Output);
        x.close_File();
        // write codebook into file
        x.open_File("CodeBook.txt");
        for (int i = 0; i < codeBook.size(); i++) {
            x.writeArrayList(codeBook.get(i).getList());
        }
        x.close_File();
        x.open_File("OriginalPexils.txt");
        x.writeArrayList(Input);
        x.close_File();
    }

    public void Decompression(String fileName, int vectRows) throws FileNotFoundException, IOException {
        Read_write_Image newWriter = new Read_write_Image(512 , 512);
                Files x = new Files();

        ArrayList<Integer> levels = new ArrayList<>();
        ArrayList<Integer> codeBookInt = new ArrayList<>();
        ArrayList<Vector> codeBookVectors = new ArrayList<>();
        ArrayList<Vector> decompOutVectors = new ArrayList<>();
        ArrayList<Integer> decompOutInt = new ArrayList<>();

        x.openFile(fileName);
        levels = x.readArrayList();
        x.closeFile();
        // Read Code Book
        x.openFile("CodeBook.txt");
        codeBookInt = x.readArrayList();
        x.closeFile();
        // convert code book Integers into vectors 
        codeBookVectors = DivedeIntoVectors(codeBookInt, vectRows);
        // System.out.println(codeBookVectors.get(0).getList() + " " + codeBookVectors.get(1).getList() );
        // decompress
        for (int i = 0; i < levels.size(); i++) {
            decompOutVectors.add(codeBookVectors.get(levels.get(i)));
        }
        // convert decompressed Output Vectors into integers
        for (int i = 0; i < decompOutVectors.size(); i++) {
            for (int j = 0; j < vectRows; j++) {
                decompOutInt.add(decompOutVectors.get(i).getList().get(j));
            }
        }
        // write Img
        newWriter.writeImage(decompOutInt, "Decompression.jpg");
        // write decompressed pixels
        x.open_File("DecompressedPixels.txt");
        x.writeArrayList(decompOutInt);
        x.close_File();
    }
// ***********************************************************************************************************    

    public ArrayList<Vector> DivedeIntoVectors(ArrayList<Integer> input, int vectorRows) {
        ArrayList<Vector> result = new ArrayList<>();
        for (int i = 0; i < input.size(); i += vectorRows) {
            List tempList = input.subList(i, i + vectorRows);
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < vectorRows; j++) {
                temp.add((Integer) tempList.get(j));
            }
            result.add(new Vector(temp));

        }
        return result;
    }

    public Vector Calculate_Avarage_Function(ArrayList<Vector> input, int vectorRows) {
        if (input.isEmpty()) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < vectorRows; i++) {
                temp.add(0);
            }
            return new Vector(temp);
        }

        ArrayList<Integer> temp = new ArrayList<>();
        int sum = 0;
        int ctr = vectorRows;
        int index = 0;
        while (ctr > 0) {
            for (int i = 0; i < input.size(); i++) {
                sum += input.get(i).getList().get(index);
            }
            temp.add(sum / input.size());
            sum = 0;
            index++;
            ctr--;
        }

        return new Vector(temp);
    }

    public ArrayList<ArrayList<Vector>> Compare_Vectors_Function(ArrayList<Vector> originalInput, ArrayList<Vector> comparingVectors, int VectRows) {
        ArrayList<ArrayList<Vector>> result = new ArrayList<ArrayList<Vector>>(comparingVectors.size());

        for (int i = 0; i < comparingVectors.size(); i++) {
            result.add(new ArrayList<Vector>());
        }
        ArrayList<Integer> temp = new ArrayList<>();
        ArrayList<Integer> tempSorted = new ArrayList<>();
        for (int i = 0; i < originalInput.size(); i++) {
            for (int j = 0; j < comparingVectors.size(); j++) {
                int subsum = 0;
                for (int k = 0; k < VectRows; k++) {
                    // equations of nearest vectors.
                    subsum += (int) Math.pow(originalInput.get(i).getList().get(k) - comparingVectors.get(j).getList().get(k), 2);
                }
                temp.add(subsum);
                tempSorted.add(subsum);
            }
            // determine which group the vector will be added 
            Collections.sort(tempSorted);
            int index = temp.indexOf(tempSorted.get(0));
            result.get(index).add(originalInput.get(i));
            temp.clear();
            tempSorted.clear();

        }
        return result;

    }

    public ArrayList<Vector> Split_Function(ArrayList<Vector> initialCodeBook, int vectRow) {
        ArrayList<Vector> initialCodeBookResult = new ArrayList<>();
        for (int i = 0; i < initialCodeBook.size(); i++) {

            ArrayList<Integer> subVect1 = new ArrayList<>();
            ArrayList<Integer> subVect2 = new ArrayList<>();
            
            for (int j = 0; j < vectRow; j++) {
                subVect1.add(initialCodeBook.get(i).getList().get(j) - 1);
                subVect2.add(initialCodeBook.get(i).getList().get(j) + 1);
            }
            initialCodeBookResult.add(new Vector(subVect1));
            initialCodeBookResult.add(new Vector(subVect2));
        }
        return initialCodeBookResult;

    }
// Find Avarage of blocks and split this avargae block into blocks and find nearest vectors 
    // and find avargae of new vectors until find bloack have a real numbers

    public ArrayList<Vector> Create_Code_Book(ArrayList<Vector> originalInput, int codeBookLength, int vectRows) {
        ArrayList<Vector> codeBook = new ArrayList<>();
        // it mean every index in array list have vector ( middleVectorsGroups is nearest vecotrs )
        ArrayList<ArrayList<Vector>> middleVectorsGroups = new ArrayList<ArrayList<Vector>>();

        // get first Code Book Vector
        codeBook.add(Calculate_Avarage_Function(originalInput, vectRows));

        // loop until you reach the number of levels needed
        int ctr = 0;
        //System.out.println("sdvf :" +codeBook.size());
        while (codeBook.size() < codeBookLength) {
            // split the code book vectors
            codeBook = Split_Function(codeBook, vectRows);

            // Grouping of original vectors
            middleVectorsGroups = Compare_Vectors_Function(originalInput, codeBook, vectRows);
            // clear initial code book
            codeBook.clear();
            // Calculate AVG for each group
            for (int i = 0; i < middleVectorsGroups.size(); i++) {
                codeBook.add(Calculate_Avarage_Function(middleVectorsGroups.get(i), vectRows));
            }
            ctr++;
        }
        return codeBook;
    }

    public ArrayList<Integer> Make_Level(ArrayList<Vector> originalInput, ArrayList<Vector> codeBook, int vectRows) {
        ArrayList<Integer> levels = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        ArrayList<Integer> tempSorted = new ArrayList<>();
        for (int i = 0; i < originalInput.size(); i++) {
            for (int j = 0; j < codeBook.size(); j++) {
                int subsum = 0;
                for (int k = 0; k < vectRows; k++) {
                    subsum += (int) Math.pow(originalInput.get(i).getList().get(k) - codeBook.get(j).getList().get(k), 2);
                }
                temp.add(subsum);
                tempSorted.add(subsum);
            }
            // determine which group the vector will be added
            Collections.sort(tempSorted);
            int index = temp.indexOf(tempSorted.get(0));
            levels.add(index);
            temp.clear();
            tempSorted.clear();
        }
        return levels;
    }
}
