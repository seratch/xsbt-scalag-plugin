package scalag

import org.apache.commons.io.FileUtils
import java.io.File
import scala.collection.JavaConverters._

/**
 * Text file
 * @param path file path
 */
case class FilePath(path: String) {

  /**
   * Reads the file and returns the content as a string
   * @param encoding encoding
   * @return content
   */
  def readAsString(encoding: String = "UTF-8"): String = {
    FileUtils.readFileToString(new File(path), encoding)
  }

  /**
   * Reads the file and returns the content as a string array
   * @param encoding encoding
   * @return content
   */
  def readAsLines(encoding: String = "UTF-8"): Seq[String] = {
    FileUtils.readLines(new File(path), encoding).asScala
  }

  /**
   * Reads the file and returns the content as bytes
   * @return content
   */
  def readAsBytes(): Array[Byte] = {
    FileUtils.readFileToByteArray(new File(path))
  }

  /**
   * Make directories recursively
   * @param dir dir
   */
  def mkdir(dir: File): Unit = Option(dir).map(d => FileUtils.forceMkdir(d))

  /**
   * Write text content to the file
   * @param content content
   * @param encoding encoding
   */
  def forceWrite(content: String, encoding: String = "UTF-8"): Unit = {
    val file = new File(path)
    mkdir(file.getParentFile)
    val modified = file.exists()
    FileUtils.writeStringToFile(file, content, encoding)
    println("'" + path + (if (modified) "' modified." else "' created."))
  }

  /**
   * Write text content to the file unless the file already exists
   * @param content content
   * @param encoding encoding
   */
  def writeIfNotExists(content: => String, encoding: String = "UTF-8"): Unit = {
    val file = new File(path)
    mkdir(file.getParentFile)
    if (file.exists) {
      println("'" + path + "' skipped because the file already exists.")
    } else {
      FileUtils.writeStringToFile(file, content, encoding)
      println("'" + path + "' created.")
    }
  }

  /**
   * Append text content to the file
   * @param content content
   * @param encoding encoding
   */
  def appendWrite(content: String, encoding: String = "UTF-8"): Unit = {
    val file = new File(path)
    mkdir(file.getParentFile)
    FileUtils.writeStringToFile(file, content, encoding, true)
    println("'" + path + "' modified.")
  }

  /**
   * Touch the file
   */
  def touch(): Unit = {
    val file = new File(path)
    mkdir(file.getParentFile)
    if (!file.exists) {
      FileUtils.writeStringToFile(file, "", "UTF-8")
      println("'" + path + "' created.")
    }
  }

}
