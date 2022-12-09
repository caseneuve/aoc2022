(ns day09.solution
  (:require [tools :refer [file->str]]))

(defn mapf [fc ac] (mapv #(%1 %2) fc ac))

(defn follow [H T]
  (let [diff (map - H T)]
    (if (> (apply max (map abs diff)) 1)
      (let [fx (map #(cond (> % 0) inc (< % 0) dec :else *) diff)] (mapf fx T))
      T)))

(defn solve [input len]
  (-> (reduce
       (fn [{p :pos :as g} [m s]]
         (let [go (partial mapf ((zipmap ["R" "L" "U" "D"] [[inc *] [dec *] [* inc] [* dec]]) m))]
           (loop [p p, n (parse-long s), g g]
             (if (zero? n) (assoc g :pos p)
                 (let [np (reduce #(conj %1 (follow (last %1) %2)) [(go (first p))] (rest p))]
                   (recur np (dec n) (update g :vis #(conj % (last np)))))))))
       {:pos (repeat len [0 0]), :vis #{[0 0]}} input)
      :vis count))

(defn -main [day]
  (let [solution (->> (file->str day) (re-seq #"\w+") (partition 2) (partial solve))]
    {:part1 (solution 2) :part2 (solution 10)}))


(comment

  (let [test-input1 "R 4
                     U 4
                     L 3
                     D 1
                     R 4
                     D 1
                     L 5
                     R 2"
        test-input2 "R 5
                     U 8
                     L 8
                     D 3
                     R 17
                     D 10
                     L 25
                     U 20"
        get-input #(->> % (re-seq #"\w+") (partition 2))]
    {:part1  (= 13 (solve (get-input test-input1)  2))
     :part2a (=  1 (solve (get-input test-input1) 10))
     :part2b (= 36 (solve (get-input test-input2) 10))})

  )
