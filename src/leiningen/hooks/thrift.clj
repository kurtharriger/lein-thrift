(ns leiningen.hooks.thrift
  (:use [leiningen.thrift :only [thrift]])
  (:use [robert.hooke :only [add-hook]])
  (:require [leiningen.compile]))

(add-hook #'leiningen.compile/compile
          (fn [f & args]
            (apply thrift args)
            (apply f args)))
