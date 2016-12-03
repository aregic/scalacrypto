/**
  * Created by regic on 11/20/16.
  */

object BoolListHelper {

    // bool list is represented as int list for simplicity
    implicit class BoolList(
      val value: List[Int] = List[Int]()
    ){
        def addTrailingZeros( bits : Int ) : List[Int] =
            value.reverse.addLeadingZeros(bits).value.reverse

        def addLeadingZeros( bits : Int ) : List[Int] =
            if ( value.length % bits != 0 )
                ( 0 :: value ).addLeadingZeros( bits )
            else
                value

        def toByte() : Int =
                addLeadingZeros(6).foldLeft(0)( _*2 + _)
    }
}
