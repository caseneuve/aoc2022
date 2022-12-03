(ns tools
  (:require [clojure.string :refer [split-lines]]))

(defn file->lines [dir]
  (-> (str dir "/" "input.txt")
      slurp
      split-lines))
