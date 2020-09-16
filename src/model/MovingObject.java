package model;

public class MovingObject {
    private Map blockMap;
    private Vector2 mPosition;
    private Vector2 mSpeed;


    protected boolean lifeState;
    protected boolean winState;

    protected  boolean mPushesTopWall;

    protected  boolean mPushesRightWall;

    protected  boolean mPushesLeftWall;

    protected  boolean mOnGround;

    public boolean getmPushesLeftWall() {
        return mPushesLeftWall;
    }

    public boolean getmPushesRightWall() {
        return mPushesRightWall;
    }

    public boolean getmOnGround() {
        return mOnGround;
    }

    public boolean getmPushesTopWall() {
        return mPushesTopWall;
    }

    public void setmPosition(Vector2 ptr){
        this.mPosition.setX(ptr.getX());
        this.mPosition.setY(ptr.getY());

    }

    public  int getPosX(){return (int)this.mPosition.getX();}
    public  int getPosY(){return (int)this.mPosition.getY();}

    public Vector2 getSpeed(){return this.mSpeed;};

    public void setSpeedY(double y){this.mSpeed.setY(y);}
    public void setSpeedX(double x){this.mSpeed.setX(x);}
    public void addSpeedY(double a){this.mSpeed.addY(a);}
    public void setSpeedZero(){this.mSpeed.setY(0.0);this.mSpeed.setX(0.0);}


    public MovingObject(Map lvlMap){
        blockMap = lvlMap;
        Vector2 ptrZR = new Vector2(15,60);

        this.mPosition = ptrZR;

        this.lifeState = true;
        this.winState = false;
        this.mSpeed = ptrZR;

        this.mPushesRightWall = false;
        this.mPushesLeftWall = false;
        this.mOnGround = true;
        this.lifeState = true;
    }


    public void search(){
       this.searchRight();
       this.searchLeft();
       this.searchBot();
       this.searchTop();
    }

    public void UpdatePhysics(){
        search();

        mPosition = mPosition.add(mSpeed.mul(Constants.DT));
        mPosition.setY(mPosition.getY());

        if(mOnGround && (mPosition.getY() < 1))
            mPosition.setY(0.0);
    }

    public void searchTop(){
        this.searchTopFirst(mPosition);

        if(!mPushesTopWall) {
            this.searchTopSecond(mPosition);
        }

        if(!mPushesTopWall) {
            this.searchTopThird(mPosition);
        }
    }

    public void searchBot () {
        this.searchBotFirst(mPosition);

        if (!mOnGround) {
            this.searchBotSecond(mPosition);
        }

        if (!mOnGround){
            this.searchBotThird(mPosition);
        }
    }

    public void searchRight(){
        this.searchRightTopFirst(mPosition);

        if(!mPushesRightWall)
            this.searchRightTopSecond(mPosition);

        if(!mPushesRightWall)
            this.searchRightBotFirst(mPosition);

        if(!mPushesRightWall)
            this.searchRightBotSecond(mPosition);
    }

    public void searchLeft(){
        this.searchLeftTopFirst(mPosition);

        if(!mPushesLeftWall)
            this.searchLeftTopSecond(mPosition);

        if(!mPushesLeftWall)
            this.searchLeftBotFirst(mPosition);

        if(!mPushesLeftWall)
            this.searchLeftBotSecond(mPosition);
    }
    //Done
    public void searchTopFirst(Vector2 position){
        Vector2 pointSearch = new Vector2((int)position.getX() - 58,(int)position.getY() + 121);
        Vector2 pointZero = new Vector2();
        Map.TileType typeTop = Map.TileType.Free;

        for(int i =  (int)pointSearch.getX() - 30; i <= ((int)pointSearch.getX()); i++){
            for(int j = ((int)pointSearch.getY()); j <= ((int)pointSearch.getY()) + 30; j++){
                if( ( Math.abs(i % 60) == 30) && Math.abs(j % 60) == 30) {
                    pointZero.setVector(i, j);

                    if((pointZero.getX() != 0) && (pointZero.getY() != 0 )){
                        pointZero.setX( (int)pointZero.getX()/30 - (int)pointZero.getX()/60 );
                        pointZero.setY( (int)pointZero.getY()/30 - (int)pointZero.getY()/60 );

                        if(pointZero.getX() < 0)
                            pointZero.setX(pointZero.getX() + 1);

                        typeTop = blockMap.getType((int)pointZero.getX(),(int)pointZero.getY());
                    }
                    pointZero = pointZero.zero();
                }
            }
        }

        if(typeTop == Map.TileType.Block && !mPushesLeftWall){
            this.mPushesTopWall = true;
        }
        else{
            this.mPushesTopWall = false;
        }
    }
    //Done
    public void searchTopSecond(Vector2 position){
        Vector2 pointSearch = new Vector2((int)position.getX() - 30,(int)position.getY() + 121);
        Vector2 pointZero = new Vector2();
        Map.TileType typeTop = Map.TileType.Free;

        for(int i =  (int)pointSearch.getX() - 29; i <= ((int)pointSearch.getX() + 29); i++){
            for(int j = ((int)pointSearch.getY()); j <= ((int)pointSearch.getY()) + 30; j++){
                if( ( Math.abs(i % 60) == 30) && Math.abs(j % 60) == 30) {
                    pointZero.setVector(i, j);

                    if((pointZero.getX() != 0) && (pointZero.getY() != 0 )){
                        pointZero.setX( (int)pointZero.getX()/30 - (int)pointZero.getX()/60 );
                        pointZero.setY( (int)pointZero.getY()/30 - (int)pointZero.getY()/60 );

                        if(pointZero.getX() < 0)
                            pointZero.setX(pointZero.getX() + 1);

                        typeTop = blockMap.getType((int)pointZero.getX(),(int)pointZero.getY());
                    }
                    pointZero = pointZero.zero();
                }
            }
        }

        if(typeTop == Map.TileType.Block){
            this.mPushesTopWall = true;
        }
        else{
            this.mPushesTopWall = false;
        }
    }
    //Done
    public void searchTopThird (Vector2 position){
        Vector2 pointSearch = new Vector2((int)position.getX() - 2,(int)position.getY() + 121);
        Vector2 pointZero = new Vector2();
        Map.TileType typeTop = Map.TileType.Free;

        for(int i =  (int)pointSearch.getX(); i <= ((int)pointSearch.getX() + 29); i++){
            for(int j = ((int)pointSearch.getY()); j <= ((int)pointSearch.getY()) + 30; j++){
                if( ( Math.abs(i % 60) == 30) && Math.abs(j % 60) == 30) {
                    pointZero.setVector(i, j);

                    if((pointZero.getX() != 0) && (pointZero.getY() != 0 )){
                        pointZero.setX( (int)pointZero.getX()/30 - (int)pointZero.getX()/60 );
                        pointZero.setY( (int)pointZero.getY()/30 - (int)pointZero.getY()/60 );

                        if(pointZero.getX() < 0)
                            pointZero.setX(pointZero.getX() + 1);

                        typeTop = blockMap.getType((int)pointZero.getX(),(int)pointZero.getY());
                    }
                    pointZero = pointZero.zero();
                }
            }
        }

        if(typeTop == Map.TileType.Block &&  !mPushesRightWall){
            this.mPushesTopWall = true;
        }
        else{
            this.mPushesTopWall = false;
        }
    }

    //Done
    public void searchBotFirst(Vector2 position){
        Vector2 pointSearch = new Vector2((int)position.getX() - 58,(int)position.getY() - 1 );
        Vector2 pointZero = new Vector2();
        Map.TileType typeBot = Map.TileType.Free;

        for(int i =  (int)pointSearch.getX() - 30; i <= ((int)pointSearch.getX()); i++){
            for(int j = ((int)pointSearch.getY()); j >= ((int)pointSearch.getY()) - 30; j--){
                if( ( Math.abs(i % 60) == 30) && Math.abs(j % 60) == 30) {
                    pointZero.setVector(i, j);
                    if((pointZero.getX() != 0) && (pointZero.getY() != 0 )){
                        pointZero.setX( (int)pointZero.getX()/30 - (int)pointZero.getX()/60 );
                        pointZero.setY( (int)pointZero.getY()/30 - (int)pointZero.getY()/60 );

                        if(pointZero.getX() < 0)
                            pointZero.setX(pointZero.getX() + 1);

                        typeBot = blockMap.getType((int)pointZero.getX(),(int)pointZero.getY());

                    }
                    pointZero = pointZero.zero();
                }
            }
        }
        switch (typeBot) {
            case Block: {
                if (typeBot == Map.TileType.Block && !mPushesLeftWall) {
                    this.mOnGround = true;
                } else {
                        this.mOnGround = false;
                    }

                break;
            }

            case Free: {
                this.mOnGround = false;

                break;
            }

            case Water: {
                this.lifeState = false;
                this.mOnGround = false;
            }
        }
    }
    //test class TO DO
    public void searchBotSecond(Vector2 position){
        Vector2 pointSearch = new Vector2((int)position.getX() - 30,(int)position.getY() - 1 );
        Vector2 pointZero = new Vector2();
        Map.TileType typeBot = Map.TileType.Free;

        for(int i =  (int)pointSearch.getX() - 29; i <= ((int)pointSearch.getX() + 29); i++){
            for(int j = ((int)pointSearch.getY()); j >= ((int)pointSearch.getY()) - 30; j--){
                if( ( Math.abs(i % 60) == 30) && Math.abs(j % 60) == 30) {
                    pointZero.setVector(i, j);
                    if((pointZero.getX() != 0) && (pointZero.getY() != 0 )){
                        pointZero.setX( (int)pointZero.getX()/30 - (int)pointZero.getX()/60 );
                        pointZero.setY( (int)pointZero.getY()/30 - (int)pointZero.getY()/60 );

                        if(pointZero.getX() < 0)
                            pointZero.setX(pointZero.getX() + 1);

                        typeBot = blockMap.getType((int)pointZero.getX(),(int)pointZero.getY());
                    }
                    pointZero = pointZero.zero();
                }
            }
        }

        switch (typeBot) {
            case Block: {
                if (typeBot == Map.TileType.Block) {
                    this.mOnGround = true;
                } else {
                    this.mOnGround = false;
                }

                break;
            }
            case Free: {
                this.mOnGround = false;
                break;
            }
            case Water: {
                this.lifeState = false;
                this.mOnGround = false;
            }
        }
    }
    //test class TO DO
    public void searchBotThird (Vector2 position){
        Vector2 pointSearch = new Vector2((int)position.getX() - 1,(int)position.getY() - 1 );
        Vector2 pointZero = new Vector2();
        Map.TileType typeBot = Map.TileType.Free;

        for(int i =  (int)pointSearch.getX(); i <= ((int)pointSearch.getX() + 29); i++){
            for(int j = ((int)pointSearch.getY() -  30); j <= ((int)pointSearch.getY()); j++){
                if( ( Math.abs(i % 60) == 30) && Math.abs(j % 60) == 30) {
                    pointZero.setVector(i, j);

                    if((pointZero.getX() != 0) && (pointZero.getY() != 0 )){
                        pointZero.setX( (int)pointZero.getX()/30 - (int)pointZero.getX()/60 );
                        pointZero.setY( (int)pointZero.getY()/30 - (int)pointZero.getY()/60 );

                        if(pointZero.getX() < 0)
                            pointZero.setX(pointZero.getX() + 1);

                        typeBot = blockMap.getType((int)pointZero.getX(),(int)pointZero.getY());
                    }
                    pointZero = pointZero.zero();
                }
            }
        }



        switch (typeBot) {
            case Block: {
                if (typeBot == Map.TileType.Block && !mPushesRightWall) {
                    this.mOnGround = true;
                } else {
                    this.mOnGround = false;
                }

                break;
            }

            case Free: {
                this.mOnGround = false;

                break;
            }

            case Water: {
                this.lifeState = false;
                this.mOnGround = false;
            }
        }
    }
    //test class TO DO
    public void searchRightTopFirst(Vector2 position){
        Vector2 pointSearch = new Vector2((int)position.getX() + 1,(int)position.getY() + 120 );
        Vector2 pointZero = new Vector2();
        Map.TileType rightTypeTop = Map.TileType.Free;

        for(int i =  (int)pointSearch.getX(); i <= ((int)pointSearch.getX() + 30); i++){
            for(int j = ((int)pointSearch.getY() - 59); j <= ((int)pointSearch.getY()); j++){
                if( ( Math.abs(i % 60) == 30) && Math.abs(j % 60) == 30) {
                    pointZero.setVector(i, j);
                    break;
                }
            }
        }

        if((pointZero.getX() != 0) && (pointZero.getY() != 0 )){
            pointZero.setX( (int)pointZero.getX()/30 - (int)pointZero.getX()/60 );
            pointZero.setY( (int)pointZero.getY()/30 - (int)pointZero.getY()/60 );

            if(pointZero.getX()<0)
                pointZero.setX(pointZero.getX() + 1);

            rightTypeTop = blockMap.getType((int)pointZero.getX(),(int)pointZero.getY());
        }

        switch (rightTypeTop){
            case Finish: {
                this.winState = true;

                break;
            }

            case Block:{
                this.mPushesRightWall = true;

                break;
            }

            case Free:{
                this.mPushesRightWall = false;
            }
        }
    }
    //test class TO DO
    public void searchRightTopSecond(Vector2 position){
        Vector2 pointSearch = new Vector2((int)position.getX() + 1,(int)position.getY() + 90 );
        Vector2 pointZero = new Vector2();
        Map.TileType rightTypeTop = Map.TileType.Free;

        for(int i =  (int)pointSearch.getX(); i <= ((int)pointSearch.getX() + 30); i++){
            for(int j = ((int)pointSearch.getY()); j <= ((int)pointSearch.getY() + 59); j++){
                if( ( Math.abs(i % 60) == 30) && Math.abs(j % 60) == 30) {
                    pointZero.setVector(i, j);

                    break;
                }
            }
        }

        if((pointZero.getX() != 0) && (pointZero.getX() != 0 )){
            pointZero.setX( (int)pointZero.getX()/30 - (int)pointZero.getX()/60 );
            pointZero.setY( (int)pointZero.getY()/30 - (int)pointZero.getY()/60 );

            if(pointZero.getX()<0)
                pointZero.setX(pointZero.getX() + 1);

            rightTypeTop = blockMap.getType((int)pointZero.getX(),(int)pointZero.getY());
        }

        switch (rightTypeTop){
            case Finish: {
                this.winState = true;

                break;
            }

            case Block:{
                this.mPushesRightWall = true;

                break;
            }

            case Free:{
                this.mPushesRightWall = false;
            }
        }
    }
    //test class TO DO
    public void searchRightBotFirst(Vector2 position){
        Vector2 pointSearch = new Vector2((int)position.getX() + 1,(int)position.getY() + 60);
        Vector2 pointZero = new Vector2();
        Map.TileType rightTypeBot = Map.TileType.Free;

        for(int i =  (int)pointSearch.getX(); i <= ((int)pointSearch.getX()+30); i++){
            for(int j = ((int)pointSearch.getY() - 30); j <= ((int)pointSearch.getY() + 30) ; j++){
                if( ( Math.abs(i % 60) == 30) && Math.abs(j % 60) == 30) {
                    pointZero.setVector(i, j);

                    break;
                }
            }
        }

        if((pointZero.getX() != 0) && (pointZero.getX() != 0 )){
            pointZero.setX( (int)pointZero.getX()/30 - (int)pointZero.getX()/60 );
            pointZero.setY( (int)pointZero.getY()/30 - (int)pointZero.getY()/60 );

            if(pointZero.getX()<0)
                pointZero.setX(pointZero.getX() + 1);

            rightTypeBot = blockMap.getType((int)pointZero.getX(),(int)pointZero.getY());
        }

        switch (rightTypeBot){
            case Finish: {
                this.winState = true;

                break;
            }

            case Block:{
                this.mPushesRightWall = true;

                break;
            }

            case Free:{
                this.mPushesRightWall = false;
            }
        }
    }
    //test class TO DO
    public void searchRightBotSecond(Vector2 position){
        Vector2 pointSearch = new Vector2((int)position.getX() + 1,(int)position.getY() - 29);
        Vector2 pointZero = new Vector2();
        Map.TileType rightTypeBot = Map.TileType.Free;

        for(int i =  (int)pointSearch.getX(); i <= ((int)pointSearch.getX()+30); i++){
            for(int j = ((int)pointSearch.getY()); j <= ((int)pointSearch.getY() + 60) ; j++){
                if( ( Math.abs(i % 60) == 30) && Math.abs(j % 60) == 30) {
                    pointZero.setVector(i, j);

                    break;
                }
            }
        }

        if((pointZero.getX() != 0) && (pointZero.getX() != 0 )){
            pointZero.setX( (int)pointZero.getX()/30 - (int)pointZero.getX()/60 );
            pointZero.setY( (int)pointZero.getY()/30 - (int)pointZero.getY()/60 );

            if(pointZero.getX()<0)
                pointZero.setX(pointZero.getX() + 1);

            rightTypeBot = blockMap.getType((int)pointZero.getX(),(int)pointZero.getY());
        }

        switch (rightTypeBot){
            case Finish: {
                this.winState = true;

                break;
            }

            case Block:{
                this.mPushesRightWall = true;

                break;
            }

            case Free:{
                this.mPushesRightWall = false;
            }
        }
    }
    //test class TO DO
    public void searchLeftTopFirst(Vector2 position){
        Vector2 pointSearch = new Vector2((int)position.getX() - 60,(int)position.getY() + 120 );
        Vector2 pointZero = new Vector2();
        Map.TileType leftTypeTop = Map.TileType.Free;

        for(int i =  (int)pointSearch.getX() - 30; i <= ((int)pointSearch.getX()); i++){
            for(int j = ((int)pointSearch.getY() - 59); j <= ((int)pointSearch.getY()); j++){
                if( ( Math.abs(i % 60) == 30) && Math.abs(j % 60) == 30) {
                    pointZero.setVector(i, j);

                    break;
                }
            }
        }

        if((pointZero.getX() != 0) && (pointZero.getX() != 0 )){
            pointZero.setX( (int)pointZero.getX()/30 - (int)pointZero.getX()/60 );
            pointZero.setY( (int)pointZero.getY()/30 - (int)pointZero.getY()/60 );

            if(pointZero.getX()<0)
                pointZero.setX(pointZero.getX() + 1);

            leftTypeTop = blockMap.getType((int)pointZero.getX(),(int)pointZero.getY());
        }

        switch (leftTypeTop){
            case Finish: {
                this.winState = true;

                break;
            }

            case Block:{
                this.mPushesLeftWall = true;

                break;
            }

            case Free:{
                this.mPushesLeftWall = false;
            }
        }
    }
    //test class TO DO
    public void searchLeftTopSecond(Vector2 position){
        Vector2 pointSearch = new Vector2((int)position.getX() -61,(int)position.getY() + 90 );
        Vector2 pointZero = new Vector2();
        Map.TileType leftTypeTop = Map.TileType.Free;

        for(int i =  (int)pointSearch.getX() - 30; i <= ((int)pointSearch.getX()); i++){
            for(int j = ((int)pointSearch.getY()); j <= ((int)pointSearch.getY() + 59); j++){
                if( ( Math.abs(i % 60) == 30) && Math.abs(j % 60) == 30) {
                    pointZero.setVector(i, j);

                    break;
                }
            }
        }

        if((pointZero.getX() != 0) && (pointZero.getX() != 0 )){
            pointZero.setX( (int)pointZero.getX()/30 - (int)pointZero.getX()/60 );
            pointZero.setY( (int)pointZero.getY()/30 - (int)pointZero.getY()/60 );

            if(pointZero.getX()<0)
                pointZero.setX(pointZero.getX() + 1);

            leftTypeTop = blockMap.getType((int)pointZero.getX(),(int)pointZero.getY());
        }

        switch (leftTypeTop){
            case Finish: {
                this.winState = true;

                break;
            }

            case Block:{
                this.mPushesLeftWall = true;

                break;
            }

            case Free:{
                this.mPushesLeftWall = false;
            }
        }
    }
    //test class TO DO
    public void searchLeftBotFirst(Vector2 position){
        Vector2 pointSearch = new Vector2((int)position.getX() - 61,(int)position.getY() + 60);
        Vector2 pointZero = new Vector2();
        Map.TileType leftTypeBot = Map.TileType.Free;

        for(int i =  (int)pointSearch.getX() - 30; i <= ((int)pointSearch.getX()); i++){
            for(int j = ((int)pointSearch.getY() - 30); j <= ((int)pointSearch.getY() + 30) ; j++){
                if( ( Math.abs(i % 60) == 30) && Math.abs(j % 60) == 30) {
                    pointZero.setVector(i, j);

                    break;
                }
            }
        }

        if((pointZero.getX() != 0) && (pointZero.getX() != 0 )){

            pointZero.setX( (int)pointZero.getX()/30 - (int)pointZero.getX()/60 );
            pointZero.setY( (int)pointZero.getY()/30 - (int)pointZero.getY()/60 );

            if(pointZero.getX()<0)
                pointZero.setX(pointZero.getX() + 1);

            leftTypeBot = blockMap.getType((int)pointZero.getX(),(int)pointZero.getY());
        }

        switch (leftTypeBot){
            case Finish: {
                this.winState = true;

                break;
            }

            case Block:{
                this.mPushesLeftWall = true;

                break;
            }

            case Free:{
                this.mPushesLeftWall = false;
            }
        }
    }
    //test class TO DO
    public void searchLeftBotSecond(Vector2 position){
        Vector2 pointSearch = new Vector2((int)position.getX() - 61,(int)position.getY() - 29);
        Vector2 pointZero = new Vector2();
        Map.TileType leftTypeBot = Map.TileType.Free;

        for(int i =  (int)pointSearch.getX() - 30; i <= ((int)pointSearch.getX()); i++){
            for(int j = ((int)pointSearch.getY()); j <= ((int)pointSearch.getY() + 60) ; j++){
                if( ( Math.abs(i % 60) == 30) && Math.abs(j % 60) == 30) {
                    pointZero.setVector(i, j);

                    break;
                }
            }
        }

        if((pointZero.getX() != 0) && (pointZero.getX() != 0 )){
            pointZero.setX( (int)pointZero.getX()/30 - (int)pointZero.getX()/60 );
            pointZero.setY( (int)pointZero.getY()/30 - (int)pointZero.getY()/60 );

            if(pointZero.getX()<0)
                pointZero.setX(pointZero.getX() + 1);

            leftTypeBot = blockMap.getType((int)pointZero.getX(),(int)pointZero.getY());
        }

        switch (leftTypeBot){
            case Finish: {
                this.winState = true;

                break;
            }

            case Block:{
                this.mPushesLeftWall = true;

                break;
            }

            case Free:{
                this.mPushesLeftWall = false;
            }
        }
    }
}
