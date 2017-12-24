;#################################################################
;29 Get the Caps
;(= (__ "HeLlO, WoRlD!") "HLOWRD")
;(empty? (__ "nothing"))
;(= (__ "$#A(*&987Zf") "AZ")

;answer
#(apply str (filter (set (map char (range 65 91))) %))

;solution
#(apply str (re-seq #"[A-Z]" %))

(re-seq #"[A-Z]" "$#A(*&987Zf")
;("A" "Z")


;#################################################################
;30 Compress a Sequence
;(= (apply str (__ "Leeeeeerrroyyy")) "Leroy")
;(= (__ [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))
;(= (__ [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))


;my answer named func
(defn del-dupl [collection]
  (loop [result [] coll collection]
    (if (empty? coll)
      result
      (if (= (first coll) (second coll))
        (recur result (rest coll))
        (recur (conj result (first coll)) (rest coll))))))


;my answer anonymous function
#(loop [result [] coll %1]
   (if (empty? coll)
     result
     (if (= (first coll) (second coll))
       (recur result (rest coll))
       (recur (conj result (first coll)) (rest coll)))))


;в этот раз три решения были довольно большими и похоже на мое
#(loop[result [], input %]
   (cond
    (nil? input) result
    (= (last result) (first input)) (recur result (next input))
    :else (recur (conj result (first input)) (next input))))

; а магия была только в одном
(fn [a]
  (map first (partition-by identity a)))


;refactor my answer to use cond
#(loop [result [] coll %1]
   (cond
     (empty? coll) result
     (= (first coll) (second coll)) (recur result (rest coll))
     :else  (recur (conj result (first coll)) (rest coll))))


;#################################################################
;31 Pack a Sequence
;(= (__ [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))
;(= (__ [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))
;(= (__ [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4])))

;answer
partition-by identity

;это было легко решить, так как в прошлом задании я узнала про функцию partition-by


;#################################################################
;32 Duplicate a Sequence
;(= (__ [1 2 3]) '(1 1 2 2 3 3))
;(= (__ [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))
;(= (__ [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))
;(= (__ [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))

;my answer
reduce (fn [coll x] (into coll [x x])) []

;example
(reduce (fn [coll x] (into coll [x x])) [] [1 2 3])
; [1 1 2 2 3 3]

;Первый раз поняла как применять reduce в более сложной задаче


;но и тут есть магическое решение короче моего :)
;solution
#(interleave % %)

;interleave может использоваться так
(interleave [1 2 3] [10 20 30])
;(1 10 2 20 3 30)

;а с функцией в решении мы просто передает ту же последовательность как аргументы
(#(interleave % %) [1 2 3])
;(1 1 2 2 3 3)



