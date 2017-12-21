(def seta #{:a :b :c})

(get seta :a)
;=> :a

(get seta :v)
;=> nil

; sorted-set
(sorted-set :b :c :d :a)

; sorted-set-by
(sorted-set-by > 1 2 3 4 5 7)


(defn compare-by-length [x y]
  (compare (count x) (count y)))

(sorted-set-by compare-by-length "aaaaa" "a" "aaa")
;#{"a" "aaa" "aaaaa"}

(sorted-set-by compare-by-length "aaaaa" "b" "a" "aaa")
;#{"b" "aaa" "aaaaa"}

;на основе примера из доокументации
; https://clojuredocs.org/clojure.core/sorted-set-by
(defn compare-by-length-with-tie-break [x y]
  (let [c (compare (count x) (count y))]
    (if (not= c 0)
      c
      (compare x y))))


(sorted-set-by compare-by-length-with-tie-break "aaaaa" "b" "a" "aaa")
;#{"a" "b" "aaa" "aaaaa"}

