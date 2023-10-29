import board

/*
        can move y+1 unless firstMovedUsed = false, then move y+2,
        or a piece is within (x+/-1,y+/-1) then move (x+/-1,y+/-1)
        and capture piece
         */
class Pawn : Piece() {

    override val type = "pawn"
    var firstMoveUsed = false

    override val moves: Array<Map<Char, Int>> = arrayOf(
        if (firstMoveUsed){
            mapOf('y' to position[1] + 2)
        }else mapOf('y' to position[1] + 1),
        //find out where every piece is pleassseee


    )



    override fun setPosition(posx: Int, posy: Int) {

        position = listOf(posx,posy)


    }
    override fun findNearbyPieces(location: List<Int>) {

    }
    override fun checkIllegal(start: List<Int>, finish: List<Int>, board: List<Piece>): Boolean {
        println(moves[0])

        return false
    }


}