class Pawn {
    var position: List<Int> = listOf()
    var nearbyPieces: List<List<Int>> = listOf()
    var firstMoveUsed = false
    fun at(posx: Int, posy: Int): List<Int> {
        position = listOf(posx,posy)
        return position
    }
    fun move(posx: Int, posy: Int) {
        /*
        can move y+1 unless firstMovedUsed = false, then move y+2,
        or a piece is within (x+/-1,y+/-1) then move (x+/-1,y+/-1)
        and capture piece
         */



    }
    fun findNearbyPieces() {

    }
    fun checkIllegal() {

    }

}