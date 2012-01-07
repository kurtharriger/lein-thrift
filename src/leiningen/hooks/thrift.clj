(ns leiningen.hooks.thrift
  (:use [leiningen.thrift :only [thrift]])
  (:use [robert.hooke :only [add-hook]])
  (:require [leiningen.javac]))

(add-hook #'leiningen.javac/javac
          (fn [f & args]
            (apply thrift args)
            (apply f args)))
