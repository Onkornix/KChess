package pieces

import getWaitingPlayerBoard
class King(startX:Int, startY:Int, override val player: Int) : Piece() {

    override val type = "king"
    override var position: MutableList<Int> = mutableListOf(startX,startY)

    override fun moves(piece: Piece): MutableList<List<Int>> {
        TODO("Do it goddam")
    }
    // true is pass, false is fail
    fun checkCheck(): Boolean {
        val bishMoves = Bishop(position[0],position[1],player)
        when {
            (bishMoves.moves(bishMoves).contains(position)) -> {
                return false
            }

        }
        return true
    }

}