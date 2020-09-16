package model;

public class Vector2 {

        private double x;
        private double y;

        public Vector2(){
            this.x = 0;
            this.y = 0;
        }

        public Vector2(double x, double y){
            this.x = x;
            this.y = y;
        }

        public double getX(){return this.x;}
        public double getY(){return this.y;}

        public void setX(double x){this.x = x;}
        public void setY(double y){this.y = y;}

        public void addX(double x) {
            this.x += x;
        }

        public void addY(double y) {
            this.y += y;
        }


        public void setVector(int x, int y){
            this.x = x;
            this.y = y;
        }

        public Vector2 zero(){
            return ( new Vector2(0,0));
        }

        public Vector2 add(Vector2 other){
            return( new Vector2(this.x + other.x,this.y + other.y));
        }

        public Vector2 mul(double a){
            return(new Vector2(x * a,y * a));
        }

    @Override
    public String toString(){
        StringBuilder mtxNewSb = new StringBuilder();

        mtxNewSb.append("X is ");
        mtxNewSb.append(this.getX());
        mtxNewSb.append(' ');
        mtxNewSb.append("\n");
        mtxNewSb.append("Y is ");
        mtxNewSb.append(this.getY());
        mtxNewSb.append(' ');

        return mtxNewSb.toString();
    }
}
