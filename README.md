# lein-thrift

  Generates java sources for thrift files

## Usage

      Install thrift if necessary:

      > brew install thrift
      > source ~/.profile     # update your PATH

      Add the necessary dependencies to your project.clj

      ;; this plugin
      :dev-dependencies [[lein-thrift "0.1.0"]]

      ;; and thift too
      :dependencies [ ...
                    [org.apache.thrift/libthrift "0.8.0"]]

      ;; specify location of thift sources (default src/thrift)
      :thift-source-path "src/thrift"

      ;; source files will be placed here before javac is called
      :thrift-java-path "src/java"

      ;; add hook to enable sources to be regenerated before javac is called
      ;; or run manually with lein thift
      :hooks [leiningen.hooks.thrift]

      ;; you might also want to change you clojure source-path
      :source-path "src/clj"

      Instruct leiningen download plugin
      > lein deps


## License

Copyright (C) 2012 Kurt Harriger

Distributed under the Eclipse Public License, the same as Clojure.
