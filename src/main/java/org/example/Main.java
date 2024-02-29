package org.example;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Pipline{
    ArrayList<HashMap<String,Integer>>SetOfIns=new ArrayList<>();
    String[][]res=new String[50][50];
    public Pipline(ArrayList<HashMap<String,Integer>>SetOfIns){
        this.SetOfIns=SetOfIns;
    }
    int SearchEmpty(String[][]x,int r,int c){

        while (x[r][c]!=null){ c++;}
        return c;
    }
    public static boolean check_Redundnt(String [][] x,int r,int c,int lim){
        for (int i = c; i <lim ; i++) {
            if(x[r][c].equals(x[r-1][i])) return true;
        }
        return false;
    }

    public void Fetch(int i){
        if(i!=SetOfIns.size()){
        for (int j = 0; j <SetOfIns.get(i).get("IF") ; j++) {
           int Index=SearchEmpty(res,0,0);
            res[0][Index]="I"+(i+1);
        }
            Fetch(i+1);
           }

//        printArray(res);
        }


        public void Decode(int i,int s){
            if(i!=SetOfIns.size()){
                for (int j = 0; j <SetOfIns.get(i).get("ID") ; j++) {
                    int Index=SearchEmpty(res,1,s);
                    res[1][Index]="I"+(i+1);
                    while(res[1][Index].equals(res[0][Index])){
                        res[1][Index+1]=res[1][Index];
                        res[1][Index]="  ";
                    }
                }
                Decode(i+1,s);
            }
        }
    public void Excute(int i,int s){
        if(i!=SetOfIns.size()){
            for (int j = 0; j <SetOfIns.get(i).get("IE") ; j++) {
                int Index=SearchEmpty(res,2,s);
                res[2][Index]="I"+(i+1);
                while(res[2][Index].equals(res[1][Index])){
                    res[2][Index+1]=res[2][Index];
                    res[2][Index]="  ";
                }
            }
            Excute(i+1,s);
        }
    }
    public void Store(int i,int s){
        if(i!=SetOfIns.size()){
            for (int j = 0; j <SetOfIns.get(i).get("WB") ; j++) {
                int Index=SearchEmpty(res,3,s);
                res[3][Index]="I"+(i+1);
                while(res[3][Index].equals(res[2][Index])){
                    res[3][Index+1]=res[3][Index];
                    res[3][Index]="  ";
                }
            }


            Store(i+1,s);
        }

    }


    public void pipine(int i) {
       Fetch(i);
       Decode(i,SetOfIns.get(i).get("IF"));
       Excute(i,SetOfIns.get(i).get("ID")+SetOfIns.get(i).get("IF")+1);
       Store(i,SetOfIns.get(i).get("IE")+SetOfIns.get(i).get("ID")+SetOfIns.get(i).get("IF")+2);
        }

    }










public class Main {
    public static void main(String[] args) {
        System.out.println("Enter Number OF Instructions");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<HashMap<String, Integer>> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            HashMap<String, Integer> I = new HashMap<>();
            a.add(I);
            System.out.println("ENTER TIME ACC TO THIS ARRANGMENT IF ID IE WB");
            int x = sc.nextInt();
            I.put("IF", x);
            int x1 = sc.nextInt();
            I.put("ID", x1);
            int x2 = sc.nextInt();
            I.put("IE", x2);
            int x3 = sc.nextInt();
            I.put("WB", x3);
        }
//        System.out.println(a);
//        System.out.println(a.get(0).get("IF"));
//        System.out.println(a.get(0).keySet());
//        System.out.println(a.get(0).values());
        try {
            Pipline p1 = new Pipline(a);
//        p1.Fetch(0,0);
//        p1.Decode(0,0);
//        p1.Excute(0,0);
//        p1.Store(0,0);
            p1.pipine(0);
            printArray(p1.res);
            System.out.println("Speed Up"+" "+count1(a)/count(p1.res));


        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());


        }


//        System.out.println("Hello world!");
    }

    public static void printArray(String[][] x) {
        for (int i = 0; i < x[0].length; i++) {
            for (int j = 0; j < x.length; j++) {
                if (x[i][j] != null) {
                    System.out.print(x[i][j] + " ");
                } else if (x[i][j] == null) {
                    x[i][j] = " ";
                    System.out.print(x[i][j] + "  ");
                }
            }
            System.out.println();
        }
    }
    static double count(String [][] x){
        int c=0;
        for (int i = 0; i <x[0].length ; i++) {
            for (int j = 0; j <x.length ; j++) {
                if(x[i][j]!=" ") c=j;
            }
        }

        return c;
    }
    static double count1(ArrayList<HashMap<String,Integer>>a){
        int c=0;
        for (int i = 0; i <a.size() ; i++) {
            c=c+a.get(i).get("IF")+a.get(i).get("ID")+a.get(i).get("IE")+a.get(i).get("WB");
        }
        return c;
    }



}