import java.io.{File, PrintWriter}

import org.scalatest.FunSuite
import LexAnalyser._

/**
  * Created by regic on 11/20/16.
  */
class testLexAnalyser
extends FunSuite
{
    test("score one character")
    {
        assertResult(charValues('e')){ evalChar('e') }
    }

    test("score one word")
    {
        assert( evalString("asd") > 3 )
    }

    test("decode a string that was xor'ed vs one byte")
    {
        val input = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736"

        assertResult( (215,"Cooking MC's like a pound of bacon") ){ LexAnalyser.decodeXoredString(input) }
    }

    /* TODO create slow test suite and put it there
    test("decode a string that was xor'ed vs one byte in a file")
    {
        val inputFile = "xoredStrings.txt"
        val outFile = new PrintWriter( new File("decodedStrings.txt") )
        val res = LexAnalyser.decodeXoredStringFromFile(inputFile, 60)
        outFile.write(res)
        outFile.close()
        assert( res.contains("party") )
    }
    */

    test("hamming distance")
    {
        val first   = "this is a test"
        val second  = "wokka wokka!!!"

        assertResult(37){ hammingDistance(first.getBytes(), second.getBytes()) }
    }

    test("countbits")
    {
        assertResult(3){ countBits(13) }
        assertResult(4){ countBits(15) }
    }

    /*
    test("Find keylength")
    {
        val source = scala.io.Source.fromFile("keylength_test.txt")
        val lines: String = (try source.mkString finally source.close()).replace("\n", "")
        println(s"=============== ${lines.length} ==============")

        assertResult(5){ findKeyLength(lines, 2, 40) }
    }
    */
}
