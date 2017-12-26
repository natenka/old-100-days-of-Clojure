;#################################################################
;33 Replicate a Sequence
;(= (__ [1 2 3] 2) '(1 1 2 2 3 3))
;(= (__ [:a :b] 4) '(:a :a :a :a :b :b :b :b))
;(= (__ [4 5 6] 1) '(4 5 6))
;(= (__ [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4]))
;(= (__ [44 33] 2) [44 44 33 33])

;answer
(fn [collection reps]
   (reduce (fn [coll x] (into coll (repeat reps x))) [] collection))


;solution
#(if(= %2 1) %1
   (apply interleave (take %2 (repeat %1))))


;solution2
(fn [xs n] (reduce concat (map #(repeat n %) xs)))


;#################################################################
;34 Implement range
;(= (__ 1 4) '(1 2 3))
;(= (__ -2 2) '(-2 -1 0 1))
;(= (__ 5 8) '(5 6 7))

;answer
(fn [start end]
  (loop [result [] start start end end]
    (if (< start end)
      (recur (conj result start) (+ start 1) end)
      result)))


;solution
#(take (- %2 %1) (iterate inc %1))

;solution2
(fn [start end]
  (map-indexed + (repeat (- end start) start)))


;#################################################################
;35 Local bindings

;answer
7

;#################################################################
;36 Let it Be

;answer
[x 7 y 3 z 1]

;#################################################################
;37 Regular Expressions
;(= __ (apply str (re-seq #"[A-Z]+" "bA1B3Ce ")))

;answer
"ABC"

;#################################################################
;38 Maximum value
;(= (__ 1 8 3 4) 8)
;(= (__ 30 20) 30)
;(= (__ 45 67 11) 67)

;answer
(fn [& args] (last (sort args)))

;solution
(fn [& params] (reduce (fn [a b] (if (< a b) b a)) 0 params))


;#################################################################
;39 Interleave Two Seqs
;(= (__ [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c))
;(= (__ [1 2] [3 4 5 6]) '(1 3 2 4))
;(= (__ [1 2 3 4] [5]) [1 5])
;(= (__ [30 20] [25 15]) [30 25 20 15])

;answer
(fn [coll1 coll2]
  (loop [result [] coll1 coll1 coll2 coll2]
    (if (or (empty? coll1) (empty? coll2))
      result
      (recur (into result [(first coll1) (first coll2)]) (rest coll1) (rest coll2)))))

;вариант без срезания по самой короткой последовательности
(fn [coll1 coll2]
  (loop [result [] coll1 coll1 coll2 coll2]
    (cond (empty? coll1) (into result coll2)
          (empty? coll2) (into result coll1)
     :else (recur (into result [(first coll1) (first coll2)]) (rest coll1) (rest coll2)))))

;solution
#(flatten (map vector %1 %2))

;solution2
mapcat vector


;#################################################################
;40 Interpose a Seq
;(= (__ 0 [1 2 3]) [1 0 2 0 3])
;(= (apply str (__ ", " ["one" "two" "three"])) "one, two, three")
;(= (__ :z [:a :b :c :d]) [:a :z :b :z :c :z :d])


;answer
(fn [inter coll]
  (butlast (reduce (fn [result item] (into result [item inter])) [] coll)))

;solution
#(drop-last (interleave %2 (repeat %1)))

