package scalag

import org.apache.commons.io.FileUtils
import java.io.File

/**
 * Text file
 * @param path file path
 */
case class FilePath(path: String) {

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
    FileUtils.writeStringToFile(file, content, encoding)
    println("'" + path + "' created.")
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

}
