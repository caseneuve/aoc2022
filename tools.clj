(ns tools
  (:require [clojure.string :refer [split]]))

(defn inp->lines [dir {:keys [fname splitby]}]
  (-> (str dir "/" (or fname "input.txt"))
      slurp
      (split (re-pattern splitby))))
