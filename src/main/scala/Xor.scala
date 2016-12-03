import HexHelper.HexString
import HexHelper.parseIntList

/**
  * Created by regic on 11/19/16.
  */
object Xor
{
    def xor( a : Boolean, b : Boolean ) =
        a ^ b

    // stupid thing in scala: byte ^ byte = int
    // Why?!?!
    def xor( a : List[Int], b : List[Int] ) : List[Int] = {
        for {i <- 0 to a.length - 1}
            yield ( a(i % a.length) ^ b(i % b.length) )
    }.toList

    def xor( a : Array[Int], b : Array[Int] ) : List[Int] =
        xor( a.toList, b.toList )

    def xor( a : Array[Byte], b : Array[Byte] ) : List[Int] =
        xor( a.map(_.toInt), b.map(_.toInt) )

    def xorHex( hex1 : HexString, hex2 : HexString ) : HexString =
        parseIntList( xor( hex1.toHexArray(), hex2.toHexArray() ) )

    def xorText( text : String, key : String ) : String =
        new String (
            xor( text.getBytes(), key.getBytes() ).map(_.toByte).toArray
        )
}
