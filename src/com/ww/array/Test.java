package com.ww.array;

public class Test {

    public static void main(String[] args) {

        int page = getTotal()/50;
        if(getTotal()%50 > 0){
            page ++;
        }

        int[] returnArray = new int[getTotal()];

        for(int j=0;j<page;j++){
             int index = j * 50;

             int[] array = query(index,50);

            for(int k =0;k<array.length; k++){
                returnArray[index+k] = array[k];
            }
        }

        for( int l =0;l<returnArray.length;l++){
            System.out.print(returnArray[l]);
        }

    }

    public static int[] query(int skip, int limit){

        int[] array = new int[50];
        for(int i=0;i<array.length;i++){
            array[i] = i;
        }
        return array;
    }

    public static int getTotal(){
        return 500;
    }
}
