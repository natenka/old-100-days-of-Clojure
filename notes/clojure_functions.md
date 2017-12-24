##

### for

```clojure
(for [i [1 2 3 4 5]] i)
;(1 2 3 4 5)

(for [i [1 2 3 4 5] :when (> i 2)] i)
;(3 4 5)

(for [i [1 2 3 4 5] :when (> i 2)] [i (* i i)])
;([3 9] [4 16] [5 25])

(for [i (range 10) :when (even? i)] i)
;(0 2 4 6 8)
```

## iterate

```clojure
(map vector (iterate inc 0) (range 10 20))
; ([0 10] [1 11] [2 12] [3 13] [4 14] [5 15] [6 16] [7 17] [8 18] [9 19])
```

## partition-by

```clojure
(partition-by identity [1 1 1 1 2 1 1 1 3 3 3])
; ((1 1 1 1) (2) (1 1 1) (3 3 3))

(partition-by odd? [1 3 5 1 2 4 1 9 15 39 8 3 3])
;((1 3 5 1) (2 4) (1 9 15 39) (8) (3 3))
```



## interleave

interleave работает как zip в Python:
```clojure
(interleave [1 2 3] [10 20 30])
;(1 10 2 20 3 30)

(apply assoc {} (interleave [1 2 3] [10 20 30]))
{1 10, 2 20, 3 30}
```

Для получения map есть zipmap
```clojure
(zipmap [1 2 3] [10 20 30])
;{1 10, 2 20, 3 30}
```

