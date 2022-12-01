(ns tools
  (:require [clojure.string :refer [split]]))


(defn file->lines [dir]
  (-> (str dir "/" "input.txt")
       slurp
       (split #"\n")))
