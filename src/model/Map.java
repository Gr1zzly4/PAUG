package model;

public class Map {

    private TileType[][] matrix;
    private int xSizeMap, ySizeMap;

    public Map(){
        xSizeMap = 34;
        ySizeMap = 19;
        matrix = new TileType[xSizeMap][ySizeMap];
    }

    public Map(TileType[][] lvl){
        xSizeMap = 34;
        ySizeMap = 19;

        matrix = lvl;
    }

    public int getxSizeMap() {
        return xSizeMap;
    }

    public int getySizeMap() {
        return ySizeMap;
    }

    public TileType getType(int x, int y){
        x = x + 15;
        y = 16 - y;

        return getStorage(x,y);
    }


    public TileType getStorage(int x, int y){
        return matrix[x][y];
    }

    public enum TileType {
        Free,
        Block,
        Water,
        Finish
    }
}
