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


