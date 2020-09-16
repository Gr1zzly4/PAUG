package io;

import model.Map;
import model.Map.TileType;


import java.io.*;

public class LvlIO {

    public LvlIO(){}

    public  void saveLvl (Map lvl){
        File f = new File(System.getProperty(".//..//PAUG"), "lvl.txt");

        try (PrintWriter pw = new PrintWriter(f)) {
           for (int i = 0; i < lvl.getySizeMap(); i++){
               for (int j = 0; j < lvl.getxSizeMap(); j++){
                   switch (lvl.getStorage(j,i)){
                       case Free:{
                           pw.print(0);

                           break;
                       }

                       case Block:{
                           pw.print(1);

                           break;
                       }

                       case Water:{
                           pw.print(2);

                           break;
                       }

                       case Finish:{
                           pw.print(3);

                           break;
                       }
                   }
               }
               pw.print(",\n");
           }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  TileType[][]  loadLvl() {
        TileType[][] lvl = new TileType[34][19];

        try (BufferedReader br = new BufferedReader (new FileReader(".//..//PAUG//lvl.txt"))){
            String s;
            int ptr;

            for (int j = 0; j < 19; j++) {
                for (int i = 0; i < 34; i++) {

                    ptr = Character.digit(br.read(),10);
                    switch (ptr){
                        case 0:{
                            lvl[i][j] = TileType.Free;

                            break;
                        }
                        case 1:{
                            lvl[i][j] = TileType.Block;

                            break;
                        }
                        case 2:{
                            lvl[i][j] = TileType.Water;

                            break;
                        }
                        case 3:{
                            lvl[i][j] = TileType.Finish;

                            break;
                        }
                    }
                }
                br.readLine();
            }

            return lvl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lvl;
    }
}
