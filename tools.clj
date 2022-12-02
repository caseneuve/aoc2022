(ns tools
  (:require [clojure.string :refer [split]]))

(defn str->lines [s] (split s #"\n"))

(defn file->lines [dir]
  (-> (str dir "/" "input.txt")
      slurp
      str->lines))
