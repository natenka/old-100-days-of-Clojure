(def m {:a 1, 1 :b, [1 2 3] "4 5 6"})

(get m :a)
(get m [1 2 3])
;=> [1 "4 5 6"]

(seq m)
;([:a 1] [1 :b] [[1 2 3] "4 5 6"])

(into {} [[:a 1] [:b 2]])
;{:a 1, :b 2}


(into {} (map vec '[(:a 1) (:b 2)]))
;{:a 1, :b 2}

(apply hash-map [:a 1 :b 2])
;{:b 2, :a 1}


(zipmap [:a :b] [1 2])
;{:a 1, :b 2}


;;;; sorted-map
(defn sort-by-length [x y]
  (let [c (compare (count x) (count y))]
    (if (not= c 0)
      c
      (compare x y))))

(def sm (sorted-map-by sort-by-length "a" 1 "aa" 2 "b" 3))

(subseq sm > "a")
;(["b" 3] ["aa" 2])

(rsubseq sm > "a")
;(["aa" 2] ["b" 3])


