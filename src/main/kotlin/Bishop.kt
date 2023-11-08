class Bishop(startX:Int,startY:Int,override val player:Int) : Piece() {
    override val type = "bishop"
    override var position: MutableList<Int> = mutableListOf(startX,startY)

}