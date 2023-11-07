class Bishop(startX:Int,startY:Int) : Piece() {

    override val type = "bishop"
    override var position: MutableList<Int> = mutableListOf(startX,startY)

}