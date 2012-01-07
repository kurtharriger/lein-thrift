(ns leiningen.thrift
  (:use [clojure.java.shell :only [sh]]
        [fs.core :as fs]))

(defn thrift [project & args]
  "Generate java source for thrift models"
  (let [src-dir (:thrift-source-path project "src/thrift")
        out-dir (:java-source-path project)]
    (when (and (fs/exists? src-dir))
      (if-not out-dir
        (println ":java-source-path must be specified in project.clj")
        (when-let [src-files (seq (fs/list-dir src-dir))]
          (when-not (fs/exists? out-dir) (fs/mkdir out-dir))
          (doseq [src src-files]
            (sh "thrift" "-r" "--gen" "java:beans,hashcode" "-out" out-dir (str src-dir "/" src))
            (println (str "Generated source for " src))))))))
