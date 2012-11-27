(ns leiningen.thrift
  (:use [clojure.java.shell :only [sh]]
        [fs.core :as fs]
        [leiningen.javac :only [javac]]))

(defn thrift [project & args]
  "Generate java source for thrift models"
  (let [src-dir (:thrift-source-path project "src/thrift")
        java-dir (:thrift-java-path project "src/thrift-java")
        opts (:thrift-opts project "beans,hashcode")]
    (when (and (fs/exists? src-dir))
      (when-let [src-files (seq (fs/list-dir src-dir))]
        (when (fs/exists? java-dir)
          (fs/delete java-dir))
        (fs/mkdir java-dir)
        (doseq [src src-files]
          (let [res (sh "thrift" "-r" "--gen" (str "java:" opts) "-out" java-dir (str src-dir "/" src))]
            (if (zero? (:exit res))
              (println (str "Generated source for " src))
              (do 
                (println (:err res))
                (System/exit 0)))))
        (javac (assoc project :java-source-path java-dir))))))
