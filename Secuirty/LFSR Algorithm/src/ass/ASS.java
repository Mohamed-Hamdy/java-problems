/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import java.io.FileNotFoundException;

/**
 *
 * @author AA
 */
public class ASS {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Encryption Enc_object = new Encryption();
        Decryption Dec_object = new Decryption();

        Enc_object.Read_Data_File();
        Enc_object.TextToBinary_Function();
        Enc_object.Number_of_ones();
        Enc_object.Encription_Function();
        
        Dec_object.Decrypyion_Function();
    }
    
}
/// mohamed --> 1101101 1101111 1101000 1100001 1101101 1100101 1100100
/// All Key : 1000100011101001110100111010011101001110100111010011101001110100
/// Ignore Part in Key is :10001000111010
/// Not Ignore Part in Key is :01110100111010011101001110100111010011101001110100
/// Encryption Text is : 0011001001101100111000010101001100100100010010000

/// final ans --> 0011001001101100111000010101001100100100010010000
/// mohamed --> 1101101110111111010001100001110110111001011100100
