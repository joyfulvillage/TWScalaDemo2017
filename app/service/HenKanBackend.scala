package service

import models.{ Converted, ConvertedContinue, Identical, Original }
import henkan.convert.Syntax._

class HenKanBackend {

  def convertIt(original: Original): ConvertedContinue = {

    val identical = original.to[Identical]()

    val converted = identical.to[Converted].set(magicWord = "Abracadabra", toBeDropped = "whatever", age = 18)

    converted.to[ConvertedContinue]()
  }

}
