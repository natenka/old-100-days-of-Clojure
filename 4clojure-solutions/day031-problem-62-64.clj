;#################################################################
;62 Re-implement Iterate
;(= (take 5 (__ #(* 2 %) 1)) [1 2 4 8 16])
;(= (take 100 (__ inc 0)) (take 100 (range)))

;my answer

(fn my-iter [f init]
  (lazy-seq (cons init (my-iter f (f init)))))

;#################################################################
;63 Group a Sequence
;(= (__ #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]})

;(= (__ #(apply / %) [[1 2] [2 4] [4 6] [3 6]])
;   {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]})

;(= (__ count [[1] [1 2] [3] [1 2 3] [2 3]])
;   {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]})

;my answer
(defn my-group [f coll]
  (let [s (map vec (partition-by f (sort-by f coll)))]
    (zipmap (map #(f (first %)) s) s)))

;solution 1
#(apply merge-with concat (map (fn [x] {(%1 x) [x]}) %2))

;solution 2
(fn [f col]
  (reduce 
    #(update-in %1 [(f %2)] 
                (fn [v] 
                  (conj (or v []) %2)))
          {}
          col))

;solution 3
(defn [f coll]
  (reduce (fn [m a]
    (let [x (f a)]
      (assoc m x (conj (get m x []) a)))) {} coll))


;#################################################################
;64 Intro to Reduce
;(= 15 (reduce __ [1 2 3 4 5]))

;answer
+

