package pieces

import getWaitingPlayerBoard

class King(startX:Int, startY:Int, override val player: Int) : Piece() {

    override val type = "king"
    override var position: MutableList<Int> = mutableListOf(startX,startY)

    override fun moves(): MutableList<List<Int>> {
        TODO("Do it goddam")
    }
    // true is in check, false is not in check
    override fun checkCheck(): Boolean {

        for (enemyPiece in getWaitingPlayerBoard().b) {
            if (position in enemyPiece.moves()) {
                return true
            }
        }
        return false
    }

}