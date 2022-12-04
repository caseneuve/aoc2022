(ns day04.solution
  (:require [tools :refer [file->lines]]
            [clojure.string :refer [split-lines]]))

(defn parse [line]
  (->> line (re-seq #"\d+") (map parse-long)))

(defn fully? [[A B X Y]]
  (or (<= A X Y B) (<= X A B Y)))

(defn partially? [[A B X Y]]
  (or (<= X A Y) (<= A X B)))

(defn -main [day]
  (let [input (map parse (file->lines day))]
    (zipmap [:part1 :part2]
            (map #(count (filter % input)) [fully? partially?]))))

(comment
  (let [test-input "2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8"
        input (map parse (split-lines test-input))]
    (zipmap [:part1 :part2]
            (map #(count (filter % input)) [fully? partially?])))
  )
