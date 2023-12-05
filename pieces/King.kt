package pieces

class King(startX:Int, startY:Int, override val player: Int) : Piece() {

    override val type = "king"
    override var position: MutableList<Int> = mutableListOf(startX,startY)

    override fun moves(): MutableList<List<Int>> {
        val moves: MutableList<List<Int>> = mutableListOf()



        return moves
    }
    // true is pass, false is fail
    fun checkCheck(): Boolean {


        // creates a ghost bishop on top of the king and if it has any valid moves, that's a big problem
        // same idea for rook
        val bishopMoves = Bishop(this.position[0],this.position[1],this.player)
        when {
            (bishopMoves.moves().contains(position)) -> {
                return false
            }

        }
        return true
    }

}