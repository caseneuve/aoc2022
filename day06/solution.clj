(ns day06.solution
  (:require [tools :refer [file->str]]))

(defn solve [it len]
  (let [f (fn [ii] ((juxt #(take len %) #(drop 1 %)) ii))]
    (loop [[a b] (f it), pos len]
      (if (= len (count (set a))) pos
          (recur (f b) (inc pos))))))

(defn -main [day]
  (let [input (file->str day)
        result (partial solve input)]
    (zipmap [:part1 :part2] (map result [4 14]))))


(comment
  (let [test-inputs 
        {4
         [["bvwbjplbgvbhsrlpgdmjqwftvncz" 5]
          ["nppdvjthqldpwncqszvftbrmjlhg" 6]
          ["nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" 10]
          ["zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" 11]]
         14
         [["mjqjpqmgbljsphdztnvjfqwrcgsmlb" 19]
          ["bvwbjplbgvbhsrlpgdmjqwftvncz" 23]
          ["nppdvjthqldpwncqszvftbrmjlhg" 23]
          ["nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" 29]
          ["zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" 26]]}]
    (zipmap [:part1 :part2]
     (for [[k vs] test-inputs]
       (map #(= (second %) (solve (first %) k)) vs))))
  )
