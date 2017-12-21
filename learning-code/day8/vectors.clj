;Vectors

(vec (range 10))

(let [my-vector [:a :b :c]]
  (into my-vector (range 10)))

(into (vector-of :int) [Math/PI 2 1.3])
;[3 2 1]

;;;length
(count [1 2 3 4 5])


(def avec [1 2 3 4 5])

;;; get item by index
(nth avec 2)
(get avec 2)
(avec 2)

;;;not found arg:
(nth avec 9 :sorry)
(get avec 9 :sorry)

;seq, rseq
(seq avec)
(rseq avec)


; assoc 
(assoc avec 2 33)


;
(def matrix
     [[1 2 3]

      [4 5 6]
      [7 8 9]])


;like python matrix[1][2]
(get-in matrix [1 2])

;like matrix[1][2] = 'x'
(assoc-in matrix [1 2] 'x)

; like matrix[1][2] = matrix[1][2]*100
(update-in matrix [1 2] * 100)


;;;;;;
(defn neighbors
  ([size yx] (neighbors [[-1 0] [1 0] [0 -1] [0 1]]
               size
               yx))
  ([deltas size yx]
   (filter (fn [new-yx]
             (every? #(< -1 % size) new-yx))
     (map #(vec (map + yx %))
       deltas))))


; (map + [1 2] [5 7])
; => (6 9)

(defn strict-map2 [f coll]
  (loop [coll coll, acc []]
    (if (empty? coll)
      acc
      (recur (next coll)
             (conj acc (f (first coll)))))))

(strict-map2 - (range 5))
;[0 -1 -2 -3 -4]


(doseq [[dimension amount] {:width 10, :height 20, :depth 15}]
  (println (str (name dimension) ":") amount "inches"))


