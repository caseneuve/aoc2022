(ns day07.solution
  (:require [tools :refer [file->lines]]
            [clojure.string :refer [split-lines split]]))

(defn parser [[cwd disk] it]
  (let [[a _ c] (split it #" "), name #(re-matches #"\w+|/" %)]
    (if c
      [(if-let [d (name c)] (conj cwd d) (pop cwd)), disk]
      [cwd, (loop [p cwd, d disk]
              (if (seq p) (recur (pop p) (assoc d p (+ (d p 0) (parse-long a)))) d))])))

(defn -main [day]
  (let [input (file->lines day)
        disk (->> input (remove #(re-matches #"\$ ls|dir \w+" %)) (reduce parser [[] {}]) second)
        needed (+ 30000000 -70000000 (disk ["/"]))]
    {:part1 (->> disk vals (filter #(>= 100000 %)) (apply +))
     :part2 (->> disk vals (filter #(<= needed %)) (apply min))}))


(comment
  (let [test-input "$ cd /
$ ls
dir a
14848514 b.txt
8504156 c.dat
dir d
$ cd a
$ ls
dir e
29116 f
2557 g
62596 h.lst
$ cd e
$ ls
584 i
$ cd ..
$ cd ..
$ cd d
$ ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k"
        input (split-lines test-input)
        disk (->> input
                  (remove #(re-matches #"\$ ls|dir \w+" %))
                  (reduce parser [[] {}])
                  second)
        needed (+ 30000000 -70000000 (disk ["/"]))]
    {:part1 (= 95437 (->> disk vals (filter #(>= 100000 %)) (apply +)))
     :part2 (= 24933642 (->> disk vals (filter #(<= needed %)) (apply min)))}
    )
  )
