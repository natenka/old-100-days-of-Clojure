-- my answer
fibonacci :: Integer -> Integer
fibonacci 0 = 0
fibonacci n | n > 0 = poshelper n 0 1
            | n < 0 = neghelper n 0 1

poshelper 0 n1 n2 = n1
poshelper n n1 n2 = poshelper (n-1) n2 (n1 + n2)

neghelper 0 n1 n2 = n1
neghelper n n1 n2 = neghelper (n+1) n2 (n1 - n2)


-- solution
fibonacci :: Int -> Int
fibonacci n = fib_helper n (0,1)

fib_helper n (a, b) | n == 0 = a
                    | n < 0 = fib_helper (n + 1) (b, a - b)
                    | otherwise = fib_helper (n - 1) (b, a + b)
