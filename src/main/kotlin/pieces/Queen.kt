package pieces

class Queen(startX:Int, startY:Int, override val player: Int) : Piece() {

    override val type = "queen"
    override var position: MutableList<Int> = mutableListOf(startX,startY)
    override fun moves(piece: Piece): MutableList<List<Int>> {
        TODO("Not yet implemented")
    }
}