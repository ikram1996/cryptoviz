//Frank Rowe
//basically this just makes a key and then splits it up into 8 vectors 
//so its easier to bit shift and calculate new indexes and such


import java.util.*;
import java.util.Vector;

public class DESfunctions {

    static Random generator = new Random();
    static Vector key = new Vector (64);
    static Vector v1 = new Vector(7); //bit 1
    static Vector v2 = new Vector(7); //bit 8
    static Vector v3 = new Vector(7); //bit 15
    static Vector v4 = new Vector(7); //bit 22
    static Vector v5 = new Vector(7); //bit 29
    static Vector v6 = new Vector(7); //bit 36
    static Vector v7 = new Vector(7); //bit 43
    static Vector v8 = new Vector(7); //bit 50


    public static void splitKey(){
        
        //takes the 64 bit key and creats 8 7bit vectors
        //every 8th bit is dropped as a parity bit
        
        //(this can probably be done a lot better)
             
        int bit_start;       
        
        bit_start = 1;
        v1 = createByteVector(bit_start);
        bit_start = 8;      
        v2 = createByteVector(bit_start);
        bit_start = 15;       
        v3 = createByteVector(bit_start);
        bit_start = 22;       
        v4 = createByteVector(bit_start);
        bit_start = 29;       
        v5 = createByteVector(bit_start);
        bit_start = 36;       
        v6 = createByteVector(bit_start);
        bit_start = 43;       
        v7 = createByteVector(bit_start);
        bit_start = 50;       
        v8 = createByteVector(bit_start);        
    
    }
    
    public static Vector createByteVector(int bit_start){
    
        Vector v = new Vector(7); //bit 1
        
        for(int i=0; i<=6; i++){  
            int index = bit_start + i -1;    
            Object x = key.get(index);
            v.add(x);
        }
        return v;
    }
    
    public static void outputByteVectors(){
        
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
        System.out.println(v4);
        System.out.println(v5);
        System.out.println(v6);
        System.out.println(v7);
        System.out.println(v8);                                                        
    
    }
 
    
    //generate a 64bit key
    //right now, just assigns each bit it's respective number 1-64
    public static Vector generateKey(){
    
        int x;
        Vector v = new Vector(64);
    
        for(int i = 1; i <= 64; i++){
            //x = generator.nextInt();
            v.add(i);
        }       
        return v;       
    }
    
    
    
    public static void main(String args[]) {
    
        key = generateKey();
        splitKey();
        outputByteVectors();
        System.out.println(key);
        
    }
}





