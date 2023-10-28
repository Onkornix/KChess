/*
create a coordinate map (0,0)..(8,8)
each piece is a class:
    contains moves, position, capture conditions?



 */



fun main() {
    val board = Board()
    board.setupBoard()
    board.printBoard()

    board.pawnJerry.move(7,0)
}