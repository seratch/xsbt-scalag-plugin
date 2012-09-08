package scalag

import java.io.File

/**
 * Sbt settings
 *
 * @param srcDir usually "src/main/scala"
 * @param testDir usually "src/test/scala"
 * @param resourceDir usually "src/main/resources"
 * @param testResourceDir usually "src/test/resources"
 */
case class SbtSettings(srcDir: File, testDir: File, resourceDir: File, testResourceDir: File)
