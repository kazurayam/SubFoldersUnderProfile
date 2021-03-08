import java.nio.file.FileVisitor
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import com.kms.katalon.core.configuration.RunConfiguration

import my.ProfilesTreeVisitorImpl

Path projectDir = Paths.get(RunConfiguration.getProjectDir())
Path profilesTree = projectDir.resolve("MockProfilesTree")
FileVisitor visitor = new ProfilesTreeVisitorImpl(null)
Files.walkFileTree(profilesTree, visitor) 