package model;

import view.Display;

import java.awt.event.KeyEvent;

public class Character extends MovingObject {
        protected boolean[] mInputs;
        protected boolean[] mPrevInputs;

        private CharacterState mCurrentState;
        private double mJumpSpeed;
        private double mWalkSpeed;


        public Character(Map lvlMap){
            super(lvlMap);

            this.mInputs = new boolean[(int)KeyInput.Count.ordinal()];
            this.mPrevInputs = new boolean[(int)KeyInput.Count.ordinal()];
            int count = (int)KeyInput.Count.ordinal();

            for (int i = 0; i < count; i++){
                mInputs[i] = false;
                mPrevInputs[i] = false;
            }

            this.mCurrentState = CharacterState.Stand;
            this.mJumpSpeed = Constants.C_JUMP_SPEED;
        }


        protected boolean Released(KeyInput key){
            return (!mInputs[(int)key.ordinal()] && mPrevInputs[(int)key.ordinal()]);
        }

        protected boolean KeyState(KeyInput key){
            return (this.mInputs[(int)key.ordinal()]);
        }

        protected boolean Pressed(KeyInput key){
            return (mInputs[(int)key.ordinal()] && !mPrevInputs[(int)key.ordinal()]);
        }

        public CharacterState getmCurrentState(){
            return mCurrentState;
        }

        public void setmInputs(boolean x, boolean y, boolean k, boolean j){
            mInputs[0] = x;
            mInputs[1] = y;
            mInputs[2] = k;
            mInputs[3] = j;
        }

        public void setmCurrentState(CharacterState mCurrentState) {
            this.mCurrentState = mCurrentState;
        }

        public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                if(key == KeyEvent.VK_W) {
                    mInputs[KeyInput.Jump.ordinal()]=true;
                }

                if(key == KeyEvent.VK_S) {
                    mInputs[KeyInput.GoDown.ordinal()]=true;
                }

                if(key == KeyEvent.VK_A) {
                    mInputs[KeyInput.GoLeft.ordinal()]=true;
                }

                if(key == KeyEvent.VK_D) {
                    mInputs[KeyInput.GoRight.ordinal()]=true;
                }

            }
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();

                if(key == KeyEvent.VK_W) {
                    mInputs[KeyInput.Jump.ordinal()]=false;
                }

                if(key == KeyEvent.VK_S) {
                    mInputs[KeyInput.GoDown.ordinal()]=false;
                }

                if(key == KeyEvent.VK_A) {
                    mInputs[KeyInput.GoLeft.ordinal()]=false;
                }

                if(key == KeyEvent.VK_D) {
                    mInputs[KeyInput.GoRight.ordinal()]=false;
                }

            }

            public void CharacterUpdate()
            {
                switch (mCurrentState){
                    case Stand: {

                        if (!mOnGround) {
                            mCurrentState = CharacterState.Jump;
                            break;
                        }

                        if (KeyState(KeyInput.GoRight) != KeyState(KeyInput.GoLeft)) {
                            mCurrentState = CharacterState.Walk;
                            break;
                        } else if (KeyState(KeyInput.Jump)) {
                            this.setSpeedY(mJumpSpeed);
                            mCurrentState = CharacterState.Jump;
                            break;
                        }

                        this.setSpeedZero();
                        break;
                    }

                    case Walk: {
                        if (KeyState(KeyInput.GoRight) == KeyState(KeyInput.GoLeft)) {
                            mCurrentState = CharacterState.Stand;
                            this.setSpeedZero();
                            break;

                        } else if (KeyState(KeyInput.GoRight)) {
                            if (this.mPushesRightWall)
                                this.setSpeedX(0.0);
                            else
                                this.setSpeedX(mWalkSpeed);

                        } else if (KeyState(KeyInput.GoLeft)) {
                            if (mPushesLeftWall)
                                this.setSpeedX(0.0);
                            else
                                this.setSpeedX(-mWalkSpeed);
                        }

                        if (KeyState(KeyInput.Jump) ) {
                            this.setSpeedY(mJumpSpeed);
                            mCurrentState = CharacterState.Jump;
                            break;

                        } else if (!mOnGround) {
                            mCurrentState = CharacterState.Jump;
                            break;

                        }

                        if(mOnGround)
                            this.setSpeedY(0.0);

                        break;
                    }

                    case Jump: {
                        if(this.mPushesTopWall)
                            this.setSpeedY(-1);

                        this.addSpeedY(Constants.C_GRAVITY * Constants.DT * 4);

                        if (KeyState(KeyInput.GoRight) == KeyState(KeyInput.GoLeft)) {
                            mCurrentState = CharacterState.Stand;
                            this.setSpeedX(0.0);
                            break;

                        } else if (KeyState(KeyInput.GoRight)) {
                            if (this.mPushesRightWall)
                                this.setSpeedX(0.0);
                            else
                                this.setSpeedX(mWalkSpeed);

                        } else if (KeyState(KeyInput.GoLeft)) {
                            if (mPushesLeftWall)
                                this.setSpeedX(0.0);
                            else
                                this.setSpeedX(-mWalkSpeed);

                        }

                        if (!KeyState(KeyInput.Jump) && this.getSpeed().getY() > 0.0)
                            this.setSpeedY(Math.min(this.getSpeed().getY(), Constants.C_MIN_JUMP_SPEED));

                        if (mOnGround)
                        {
                            if (mInputs[(int)KeyInput.GoRight.ordinal()] == mInputs[(int)KeyInput.GoLeft.ordinal()])
                            {
                                mCurrentState = CharacterState.Stand;
                                this.setSpeedZero();

                            }
                            else
                            {
                                mCurrentState = CharacterState.Walk;
                                this.setSpeedY(0.0);

                            }
                        }

                        break;
                    }

                    case GrabLedge: {
                        break;
                    }

                }

                UpdatePhysics();
            }

        public void CharacterInit(boolean[] inputs, boolean[] prevInputs)
        {
            this.mInputs = inputs;
            this.mPrevInputs = prevInputs;
            this.mJumpSpeed = Constants.C_JUMP_SPEED;
            this.mWalkSpeed = Constants.C_WALK_SPEED;
        }

        public enum KeyInput{
        GoLeft  /*("0")*/,
        GoRight/* ("0")*/,
        GoDown  /*("0")*/,
        Jump   /* ("0")*/,

        Count   /*("MUST BE LAST and don't touch...pls..")*/;

     }

        public enum CharacterState{
        Stand,
        Walk,
        Jump,
        GrabLedge,
        }
}


