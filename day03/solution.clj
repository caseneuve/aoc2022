(ns day03.solution
  (:require [tools :refer [file->lines]]
            [clojure.string :refer [index-of split-lines]]
            [clojure.set :refer [intersection]]))

(defn pts [it]
  (->> it (map set) (apply intersection) first
       (index-of ":abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")))

(defn -main [day]
  (let [input (file->lines day)]
    {:part1 (transduce (map #(pts (partition (/ (count %) 2) %))) + input)
     :part2 (transduce (map pts) + (partition 3 input))}))


(comment
  (let [test-input "vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw"
        input (split-lines test-input)]
    {:part1 (= 157 (transduce (map #(pts (partition (/ (count %) 2) %))) + input))
     :part2 (= 70 (transduce (map pts) + (partition 3 input)))}
    )
  )
