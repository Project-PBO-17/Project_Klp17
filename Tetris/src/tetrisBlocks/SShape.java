package tetrisBlocks;

import api.TetrisBlock;

public class SShape extends TetrisBlock {
    public SShape(){
        super(new int[][]{{0,1,1},
                          {1,1,0}});
    }
}
