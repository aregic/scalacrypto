/**
  * Created by regic on 11/20/16.
  */

object HexHelper
{
    def parseIntList(hexList : List[Int] ) : HexString =
        { for ( hex <- hexList ) yield
            {
                val res = Integer.toHexString(hex)
                // toHexString - sadly - only returns with one digit hexa if it can
                // this is not good for us
                if (res.length == 1)
                    "0" + res
                else
                    res
            }
        }.foldLeft("")(_ + _)

    def parseByteList( hexList : List[Byte] ) : HexString =
        parseIntList( hexList.map(_.toInt) )

    def parseByteArray( hexArr : Array[Byte] ) : HexString =
        parseByteList(hexArr.toList)

    implicit class HexString(
        val value : String = new String()
    ){
        def toHexArray(): List[Int] =
            if (value.length % 2 == 0) {
                for {i <- 0 to (value.length - 1) / 2} yield
                    Integer.parseInt((value(2 * i).toString + value(2 * i + 1).toString), 16)
                }.toList // looks weird...
            else
                throw new IllegalArgumentException("input length must be even")

        def toBinaryList(): List[Int] =
            for {
                p <- this.toHexArray();
                s <- (BigInt(p).toString(2)).addLeadingZeros(8)
            } yield Integer.parseInt(s.toString, 2)

        def addLeadingZeros(expectedLength: Int): String =
            if (value.length % expectedLength != 0)
                ("0" + value).addLeadingZeros(expectedLength)
            else
                value

        override def toString() : String =
            value
    }

    implicit def hexString2String( hexString : HexString ) : String = // hexstring hexstring hexstring hexstring
        hexString.value
}
