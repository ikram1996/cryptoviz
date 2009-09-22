//Frank Rowe


import java.util.*;
import java.util.Vector;

public class DESfunctions {

    static Random generator = new Random();
    static Vector<Integer> key = new Vector<Integer>(64);
    static Vector<Integer> v1 = new Vector<Integer>(7); //bit 1
    static Vector<Integer> v2 = new Vector<Integer>(7); //bit 8
    static Vector<Integer> v3 = new Vector<Integer>(7); //bit 15
    static Vector<Integer> v4 = new Vector<Integer>(7); //bit 22
    static Vector<Integer> v5 = new Vector<Integer>(7); //bit 29
    static Vector<Integer> v6 = new Vector<Integer>(7); //bit 36
    static Vector<Integer> v7 = new Vector<Integer>(7); //bit 43
    static Vector<Integer> v8 = new Vector<Integer>(7); //bit 50


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
    
    public static Vector<Integer> createByteVector(int bit_start){
    
        Vector<Integer> v = new Vector<Integer>(7); //bit 1
        
        for(int i=0; i<=6; i++){  
            int index = bit_start + i -1;    
            Integer x = key.get(index);
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
    public static Vector<Integer> generateKey(){
    
        //int x;
        Vector<Integer> v = new Vector<Integer>(64);
    
        for(int i = 1; i <= 64; i++){
            //x = generator.nextInt();
            v.add(i);
        }       
        return v;       
    }



	public static void initialPermutation(){

		Vector<Integer> IPvector = new Vector<Integer>(64);
			

	}
 
    
    
    public static void main(String args[]) {
    
        key = generateKey();
        splitKey();
        outputByteVectors();
        System.out.println(key);
        
    }
}





