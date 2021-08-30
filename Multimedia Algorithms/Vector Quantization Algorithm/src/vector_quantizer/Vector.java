/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vector_quantizer;

import java.util.ArrayList;
import java.util.Objects;

public class Vector {

    private ArrayList<Integer> list;

    public ArrayList<Integer> getList() {
        return list;
    }
    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }

    public Vector(ArrayList<Integer> list) {
        this.list = list;
    }

    public Vector() {
        list = new ArrayList<Integer>();
    }
}
