package pieces

class Knight(startX:Int, startY:Int, override val player: Int) : Piece() {

    override val type = "knight"
    override var position: MutableList<Int> = mutableListOf(startX,startY)

    override fun moves(): MutableList<List<Int>> {
        val moves: MutableList<List<Int>> = mutableListOf()
        return moves
    }
}