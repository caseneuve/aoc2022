(ns day01.solution
  (:require [tools :refer [file->lines]]))

(defn parse-input [lines]
  (->> lines
       (partition-by empty?)
       (take-nth 2)
       (map #(transduce (map parse-long) + %))
       (sort >)))

(defn -main [day]
  (let [input (parse-input (file->lines day))]
    {:part1 (first input)
     :part2 (transduce (take 3) + input)}))


(comment
  (require '[clojure.string :refer [split]])
  (let [test-str "1000
2000
3000

4000

5000
6000

7000
8000
9000

10000"
        input (parse-input (split test-str #"\n"))]
    {:part1 (= (first input) 24000)
     :part2 (= (transduce (take 3) + input) 45000)})
  )
