import board as board

/*
        can move y+1 unless firstMovedUsed = false, then move y+2,
        or a piece is within (x+/-1,y+/-1) then move (x+/-1,y+/-1)
        and capture piece
         */
class Pawn : Piece() {

    override val type = "pawn"
    override var firstMoveUsed = false
    override var position: MutableList<Int> = mutableListOf(0,0)

    //scan arbitrary area for pieces that can be captured or will make move illegal


}