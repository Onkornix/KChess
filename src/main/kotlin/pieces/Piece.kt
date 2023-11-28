package pieces

abstract class Piece {

    open val player: Int = 1
    open val type: String = ""
    open var position: MutableList<Int> = mutableListOf(0,0)
    open var firstMoveUsed: Boolean = false

    abstract fun moves(piece: Piece): MutableList<List<Int>>
}