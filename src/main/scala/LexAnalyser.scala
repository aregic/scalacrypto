/**
  * Created by regic on 11/20/16.
  */

import java.io.File

import HexHelper.HexString
import Xor.xor

object LexAnalyser {
    val charValues = Map(
        'e' -> 14,
        't' -> 13,
        'a' -> 12,
        'o' -> 11,
        'i' -> 10,
        'n' ->  9,
        ' ' ->  8,
        's' ->  7,
        'h' ->  6,
        'r' ->  5,
        'd' ->  4,
        'l' ->  3,
        'u' ->  2
    )

    def evalChar( c : Char ) : Int =
        if( charValues.contains(c) )
            charValues(c)
        else
            1

    def evalString( str : String ) : Int =
        str.foldLeft(0)( _ + evalChar(_) )

    // looks ugly... could use some cleanup
    def decodeXoredString( str : HexString ) : ( Int, String ) =
        { for {
                i <- (0 to 255);
                candidate = new String( xor( str.toHexArray(), List(i) ).map(_.toByte).toArray )
            } yield (evalString(candidate), candidate)
        }.foldLeft( (0,new String()) )(
            ( a : (Int, String), p : (Int,String) ) =>
                if ( a._1 < p._1 )
                    p
                else
                    a
        )

    def decodeXoredStringFromFile( fileLoc : String, expectedStrLength : Int ) : String = {
        val source = scala.io.Source.fromFile(fileLoc)
        val lines: String = (try source.mkString finally source.close()).replace("\n", "")

        { for( oneString <- lines.grouped(expectedStrLength) ) yield decodeXoredString(oneString)._2 }.
            foldLeft("")(_ + _.toString)
    }

    def replaceWhiteSpaces( str : String ) : String =
        str.replace(" ", "").
            replace("\n", "").
            replace("\t", "")

    def hammingDistance( a : Array[Byte], b : Array[Byte] ) : Int =
        {
            for( i <- 0 to a.length-1 ) yield countBits( a(i) ^ b(i) )
        }.sum

    def countBits( b : Int ) : Int = {
        def countBitsInner( b : Int, res : Int ) : Int =
            if ( b == 0 )
                res
            else {
                countBitsInner(b / 2, res + (b % 2)) // using the implicit cast from bool to int... old trick
            }

        countBitsInner( b, 0 )
    }

    def hammingDistance( a : String, b : String ) : Int =
        hammingDistance(a.getBytes(), b.getBytes())

    def findKeyLength( s : String, minKeyLength : Int, maxKeyLength : Int ) : Int =
        { for ( i <- minKeyLength to ( maxKeyLength min s.length ) )
            yield ( hammingDistance(s.slice(0, i), s.slice( i+1, 2*(i+1) ))/i, i )
        }.foldLeft(0,0)( (a,c) =>
            if ( a._2 > c._2 )
                a
            else
                c
        )._2
}
