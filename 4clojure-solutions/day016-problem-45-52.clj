;#################################################################
;45 Intro to Iterate
;(= __ (take 5 (iterate #(+ 3 %) 1)))

'(1 4 7 10 13)


;#################################################################
;46 Flipping out
;(= 3 ((__ nth) 2 [1 2 3 4 5]))
;(= true ((__ >) 7 8))

;my answer
#(fn [arg1 arg2]
   (% arg2 arg1))

;solution
(fn [f]
 (fn [x y]
    (f y x)))

;solution
(fn [f] #(f %2 %1))


;#################################################################
;47 Contain Yourself
;Contain Yourself
;(contains? #{4 5 6} __)
;(contains? [1 1 1 1 1] __) => index
;(contains? {4 :a 2 :b} __)
;(not (contains? [1 2 4] __))

;Returns true if key is present in the given collection, otherwise
;returns false.  Note that for numerically indexed collections like
;vectors and Java arrays, this tests if the numeric key is within the
;range of indexes. 'contains?' operates constant or logarithmic time;
;it will not perform a linear search for a value.  See also 'some'.

4

;#################################################################
;48 Intro to some
;The some function takes a predicate function and a collection. It returns the first logical true value of (predicate x) where x is an item in the collection.

;(= __ (some #{2 7 6} [5 6 7 8]))
;(= __ (some #(when (even? %) %) [5 6 7 8]))

6

;#################################################################
;49 Split a sequence
;(= (__ 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]])
;(= (__ 1 [:a :b :c :d]) [[:a] [:b :c :d]])
;(= (__ 2 [[1 2] [3 4] [5 6]]) [[[1 2] [3 4]] [[5 6]]])

;my answer
(fn [num coll]
  [(take num coll) (drop num coll)])

;#################################################################
;50 Split by Type
;(= (set (__ [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]})
;(= (set (__ [:a "foo"  "bar" :b])) #{[:a :b] ["foo" "bar"]})
;(= (set (__ [[1 2] :a [3 4] 5 6 :b])) #{[[1 2] [3 4]] [:a :b] [5 6]})

;my-answer
(fn [coll]
  (vals (group-by type coll)))


;solution
#(map last (group-by type %))


;#################################################################
;51 Advanced Destructuring
;(= [1 2 [3 4 5] [1 2 3 4 5]] (let [[a b & c :as d] __] [a b c d]))

;my answer
[1 2 3 4 5]

;#################################################################
;52 Intro to Destructuring
;(= [2 4] (let [[a b c d e] [0 1 2 3 4]] __))

;my answer
[c e]

