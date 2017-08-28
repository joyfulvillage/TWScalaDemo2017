package service

import models.{ ConvertedContinue, Identical, Original }
import henkan.convert.Syntax._

class HenKanBackend {

  def convert(original: Original): ConvertedContinue = {

    //val converted = original.to[Identical]() //.set(magicWord = "Abracadabra") //.set(toBeDropped = "whatever").set(age = 18)

    original.to[Identical]()

    //converted

    ???
  }

}
