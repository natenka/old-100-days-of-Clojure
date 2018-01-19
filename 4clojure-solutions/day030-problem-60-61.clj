;#################################################################
;60 Sequence Reductions
;(= (take 5 (__ + (range))) [0 1 3 6 10])
;(= (__ conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]])
;(= (last (__ * 2 [3 4 5])) (reduce * 2 [3 4 5]) 120)

;my answer
(fn my-red
  ([f coll] (my-red f (first coll) (rest coll)))
  ([f result coll]
   (if (empty? coll)
     (list result)
     (lazy-seq (cons result (my-red f
                                    (f result (first coll))
                                    (rest coll)))))))

; solution 1

(fn myReductions
  ([f preVal coll]
   (lazy-seq
    (if-let [firstVal (first coll)];if there is still some items left
      (cons preVal (myReductions f (f preVal firstVal) (rest coll)));lazy recipe
      (list preVal))));last item shall be containted by a seq
  ([f coll]
    (myReductions f (first coll) (rest coll))))


;solution 2
(fn [f & args]
  (let [start (if (= 2 (count args)) (first args) (ffirst args))
        s (if (= 2 (count args)) (last args) (rest (last args)))]
    (letfn [(op [s l]
      (if (empty? s)
        s
        (cons
          (f l (first s))
          (lazy-seq (op (rest s) (f l (first s)))))))]
      (cons start (op s start)))))

;#################################################################
;61 Map Construction
;(= (__ [:a :b :c] [1 2 3]) {:a 1, :b 2, :c 3})
;(= (__ [1 2 3 4] ["one" "two" "three"]) {1 "one", 2 "two", 3 "three"})
;(= (__ [:foo :bar] ["foo" "bar" "baz"]) {:foo "foo", :bar "bar"})

;my answer
#(reduce into {} (map hash-map %1 %2))


;solution
#(apply hash-map (interleave %1 %2))
