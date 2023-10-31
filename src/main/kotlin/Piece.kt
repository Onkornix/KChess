import java.beans.Expression

open class Piece {

    open val type: String = "piece"
    open var position: MutableList<Int> = mutableListOf(0,0)
    open var firstMoveUsed: Boolean = false

}