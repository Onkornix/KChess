class Board {
    private var board: List<List<List<Int>>> = listOf()

    val pawnJerry = Pawn()
    val pawnRick = Pawn()
    val pawnSeymour = Pawn()
    fun setupBoard(){
        board = listOf(
            listOf(pawnJerry.at(0,8), pawnRick.at(1,8), pawnSeymour.at(2,8))
        )
    }

    fun printBoard(){
        for (i in board){
            println(i)
        }
    }

}