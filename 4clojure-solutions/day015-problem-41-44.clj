;#################################################################
;41 Drop Every Nth Item
;(= (__ [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])
;(= (__ [:a :b :c :d :e :f] 2) [:a :c :e])
;(= (__ [1 2 3 4 5 6] 4) [1 2 3 5 6])


;my answer
(fn [coll item]
  (flatten
    (map
      (fn [x] (take (- item 1) x)) (partition-all item coll))))

;solution
(fn [s x]
  (keep-indexed
    (fn [i a] (when (> (mod (inc i) x) 0) a))
    s))


;#################################################################
;42 Factorial Fun
;(= (__ 1) 1)
;(= (__ 3) 6)
;(= (__ 5) 120)
;(= (__ 8) 40320)

;my answer
#(apply * (range 1 (inc %)))


;solution
#(reduce * (range 1 (inc %)))

;#################################################################
;43 Reverse Interleave (Medium)
;(= (__ [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6)))
;(= (__ (range 9) 3) '((0 3 6) (1 4 7) (2 5 8)))
;(= (__ (range 10) 5) '((0 5) (1 6) (2 7) (3 8) (4 9)))


;my answer
(fn [coll x]
  (loop [result [] parts (partition x coll)]
    (if (empty? (first parts))
      result
      (recur (conj result (map first parts)) (map rest parts)))))
  

;solution1
#(apply map list (partition %2 %1))

;solution2
(fn [coll x]
  (partition (/ (count coll) x) (apply interleave (partition x coll))))


;#################################################################
;44 Rotate Sequence (Medium)
;(= (__ 2 [1 2 3 4 5]) '(3 4 5 1 2))
;(= (__ -2 [1 2 3 4 5]) '(4 5 1 2 3))
;(= (__ 6 [1 2 3 4 5]) '(2 3 4 5 1))
;(= (__ 1 '(:a :b :c)) '(:b :c :a))
;(= (__ -4 '(:a :b :c)) '(:c :a :b))


;my answer
(defn myfun [turns coll]
  (let [len (count coll) fun #(drop %1 (take (+ len %1) (cycle %2)))]
    (if (> turns 0)
      (fun turns coll)
      (fun (+ (rem turns len) len) coll))))


;solution1
#(let [x (rem (+ (count %2) (rem %1 (count %2))) (count %2))]
   (flatten [(drop x %2) (take x %2)]))

;solution2
(fn [n s]
  (let [[a b] (split-at (mod n (count s)) s)]
    (concat b a)))



