(ns day05.solution
  (:require [tools :refer [file->str]]
            [clojure.string :refer [join split]]))

(defn get-stacks [s]
  (let [chars (re-seq #"\w|    " s)
        len (parse-long (last chars))]
    (->> chars
         (map #(re-matches #"\w" %))
         (partition len)
         butlast
         (apply mapv vector)
         (mapv #(remove nil? %)))))

(defn get-movements [m]
  (->> m
       (re-seq #"\d+")
       (map parse-long)
       (partition 3)))

(defn parse [s]
  (-> s
      (split #"\n\n")
      ((juxt (comp get-stacks first)
             (comp get-movements second)))))

(defn reducer [[coll f] [amount from to]]
  [(-> coll
       (update (dec from) #(drop amount %))
       (update (dec to) #(into % (f (take amount (coll (dec from)))))))
   f])

(defn -main [day]
  (let [[stacks movements] (parse (file->str day))]
    (zipmap [:part1 :part2]
            (->> [identity reverse]
                 (map #(reduce reducer [stacks %] movements))
                 (map #(->> % first (map first) join))))))


(comment
  (let [test-input
"    [D]    
[N] [C]    
[Z] [M] [P]
    1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2"
        [s m] (parse test-input)]
    (zipmap [:part1 :part2]
            (->> [identity reverse]
                 (map #(reduce reducer [s %] m))
                 (map #(->> % first (map first) join)))))
  )
