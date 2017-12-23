; 

(defn index [coll]
  (cond
    (map? coll) (seq coll)
    (set? coll) (map vector coll coll)
    :else (map vector (iterate inc 0) coll)))


(defn pos [e coll]
  (for [[i v] (index coll) :when (= e v)] i))

(pos 3 [:a 1 :b 2 :c 3 :d 4])
;=> (5)
(pos 3 {:a 1, :b 2, :c 3, :d 4})
;=> (:c)
(pos 3 [:a 3 :b 3 :c 3 :d 4])
;=> (1 3 5)
(pos 3 {:a 3, :b 3, :c 3, :d 4})
;=> (:a :c :b)



;Typically in Clojure it’s more useful to pass a predicate function
;in cases such as these, so that instead of pos determining raw equality,
;it can build its result along any dimension

(defn pos [pred coll]
  (for [[i v] (index coll) :when (pred v)] i))


(pos #{3 4} {:a 1 :b 2 :c 3 :d 4})
;=> (:c :d)

(pos even? [2 3 6 7])
;=> (0 2)

