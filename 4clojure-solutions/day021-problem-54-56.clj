;#################################################################
;54 Partition a Sequence
;(= (__ 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8)))
;(= (__ 2 (range 8)) '((0 1) (2 3) (4 5) (6 7)))
;(= (__ 3 (range 8)) '((0 1 2) (3 4 5)))


;my answer
(fn [size collection]
  (loop [result [] coll collection]
    (if (< (count coll) size)
      result
      (recur (conj result (take size coll)) (drop size coll)))))

;solution
#(loop[result [], remaining %2]
   (if (>= (count remaining) %1)
     (recur (conj result (take %1 remaining)) (drop %1 remaining))
     result))

;#################################################################
;55 Count Occurrences
;(= (__ [1 1 2 3 2 1 1]) {1 4, 2 2, 3 1})
;(= (__ [:b :a :b :a :b]) {:a 2, :b 3})
;(= (__ '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2})
;;;;;(frequencies [1 1 1 2 2 2 3 3 3 33 4])


;my answer
(fn [coll]
  (reduce (fn [init item]
            (if (get init item)
              (conj init {item (inc (get init item))})
              (conj init {item 1}))) {} coll))


;solution1
(fn [s]
  (into {} 
    (for [[k v] (group-by identity s)] [k (count v)])))

;solution2
(fn [col]
  (reduce #(update-in %1 [%2] (fn [v] (inc (or v 0)))) {} col))


;#################################################################
;56 Find Distinct Items
;(= (__ [1 2 1 3 1 2 4]) [1 2 3 4])
;(= (__ [:a :a :b :b :c :c]) [:a :b :c])
;(= (__ '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3]))
;(= (__ (range 50)) (range 50))

;first version of answer
(map first (group-by identity [10 10 10 200 20 20 40 40]))
(10 200 20 40)

;but with range:
(map first (group-by identity (range 50)))
(0 7 20 27 1 24 39 46 4 15 48 21 31 32 40 33 13 22 36 41 43 29 44 6 28 25 34 17 3 12 2 23 47 35 19 11 9 5 14 45 26 16 38 30 10 18 42 37 8 49)

;my final answer
(fn [coll]
  (reduce (fn [init item]
            (if (some #(= item %1) init)
              init
              (conj init item))) [] coll))

;solution
(fn [s]
  (reduce #(if ((set %1) %2) %1 (conj %1 %2)) [] s))

;explanation:
(#{1 2 3} 4)
;nil
(#{1 2 3} 2)
;2


