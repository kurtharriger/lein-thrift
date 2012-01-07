(ns leiningen.thrift
  (:use [clojure.java.shell :only [sh]]
        [fs.core :as fs]
        [leiningen.javac :only [javac]]))

(defn thrift [project & args]
  "Generate java source for thrift models"
  (let [src-dir (:thrift-source-path project "src/thrift")
        java-dir (:thrift-java-path project "src/thrift-java")]
    (when (and (fs/exists? src-dir))
      (when-let [src-files (seq (fs/list-dir src-dir))]
        (when-not (fs/exists? java-dir) (fs/mkdir java-dir))
        (doseq [src src-files]
          (sh "thrift" "-r" "--gen" "java:beans,hashcode" "-out" java-dir (str src-dir "/" src))
          (println (str "Generated source for " src)))
        (javac project java-dir)))))
