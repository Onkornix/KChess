

/*
create a coordinate map (0,0)..(8,8)
each piece is a class:
    contains moves, position, capture conditions?

 */




//Pawns
val pawnJerry    =   Pawn()
val pawnRick     =   Pawn()
val pawnSeymour  =   Pawn()

//Bishops
val bishopRodger = Bishop()

val board: MutableList<Piece> = mutableListOf(
    pawnJerry,
    pawnRick,
    pawnSeymour,
    bishopRodger
)
fun setBoard(){
    pawnJerry.setPosition(1,2)
    pawnRick.setPosition(2,2)
    pawnSeymour.setPosition(3,2)
    bishopRodger.setPosition(1,1)
}

fun move(what: String, where: String){
    if (where.length > 3 || where.length < 3){
        return Unit
    }
    fun whereToInt(where: String): List<Int>{
        val letterIntMap = mapOf('a' to 1, 'b' to 2, 'c' to 3, 'd' to 4, 'e' to 5, 'f' to 6, 'g' to 7, 'h' to 8)
        val xpos: Int = letterIntMap[where[0]]!!
        val ypos: Int = where[2].toString().toInt()
        return listOf(xpos,ypos)
    }
    /*
    get piece type
    get final location

    check piece with move: if legal: do, else: move on
     */
    val whereInt = whereToInt(where)


    for (piece in board){
        if (piece.type == what){
            piece.checkIllegal(piece.position, whereInt, board)

  }

    }
}


fun main() {
    setBoard()

    move("pawn","a_3")
}