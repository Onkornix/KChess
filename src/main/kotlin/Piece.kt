import java.beans.Expression

open class Piece {

    open val type: String = "piece"
    open var position: List<Int> = listOf(0,0)
    open val moves: Array<Map<Char,Int>> = arrayOf()
    //open var nearbyPieces: List<List<String>> = listOf()

    open fun setPosition(posx: Int, posy: Int) {
        position = listOf(posx,posy)
    }
    open fun findNearbyPieces(location: List<Int>) {
    }
    open fun checkIllegal(start: List<Int>, finish: List<Int>, board: List<Piece>): Boolean {
        return false
    }


}