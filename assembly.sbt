import sbtassembly.Plugin._ 
import AssemblyKeys._

assemblySettings

mainClass in assembly := Some("io.petersen.oauth.consumer.OauthConsumerApplication")

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) => 
  { 
    case x if x startsWith "META-INF/maven/org.yaml/snakeyaml/" => MergeStrategy.first 
    case x => old(x) 
  } 
} 