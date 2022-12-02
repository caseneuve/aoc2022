(ns day02.solution
  (:require [tools :refer [file->lines str->lines]]
            [clojure.string :refer [index-of]]))

(def rules
  [[2 0 1]    ;; R = 0 [who-loses who-draws who-wins]
   [0 1 2]    ;; P = 1
   [1 2 0]])  ;; S = 2

(defn parse [line]
  (let [[a b] (re-seq #"\w" line)]
    [(index-of "ABC" a) (index-of "XYZ" b)]))

(defn result [[a b] part]
  (if (= part 1)
    (+ (inc b) (* 3 (.indexOf (nth rules a) b)))
    (+ (inc (nth (nth rules a) b)) (* 3 b))))

(defn -main [day]
  (let [input (map parse (file->lines day))
        game (fn [p] (apply + (map #(result % p) input)))]
    {:part1 (game 1) :part2 (game 2)}))


(comment
  ;; A for Rock, B for Paper, and C for Scissors
  ;; X for Rock, Y for Paper, and Z for Scissors
  ;; 1 for Rock, 2 for Paper, and 3 for Scissors

  ;; part I
  ;; 0 if you lost, 3 if the round was a draw, and 6 if you won
  ;; Your total score is the sum of your scores for each round. The
  ;; score for a single round is the score for the shape you
  ;; selected (1 for Rock, 2 for Paper, and 3 for Scissors) plus the
  ;; score for the outcome of the round (0 if you lost, 3 if the round
  ;; was a draw, and 6 if you won)

  ;; part II
  ;; X means you need to lose, Y means you need to end the round in a draw

  (let [test-input "A Y
B X
C Z"
        input (map parse (str->lines test-input))
        game (fn [p] (apply + (map #(result % p) input)))]
    {:part1 (= 15 (game 1)) :part2 (= 12 (game 2))})
  (re-seq #"\w" "asdf")
  )
