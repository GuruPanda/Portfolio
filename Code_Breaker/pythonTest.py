import unittest
class TestFactorial(unittest.TestCase):
    def test_1(self):
        self.assertEqual(factorial(5), 120)
    def test_2(self):
        self.assertEqual(factorial(0), 1)
    def test_3(self):
        self.assertEqual(factorial(-1), 0)
    def test_4(self):
        with self.assertRaises(TypeError):
            factorial()
    def test_5(self):
        with self.assertRaises(NameError):
            factorial(X)
    def test_6(self):
        with self.assertRaises(TypeError):
            factorial("x")
    def test_7(self):
        with self.assertRaises(TypeError):
            factorial(None)
    def test_8(self):
        with self.assertRaises(TypeError):
            factorial(2,3)
    def test_9(self):
        self.assertEqual(factorial(2.3), 0.0)
    def test_10(self):
        with self.assertRaises(RuntimeError):
            factorial(994)
    def test_11(self):
        self.assertEqual(isPrime(7), True)
    def test_12(self):
        self.assertEqual(isPrime(-1),False)
    def test_13(self):
        self.assertEqual(isPrime(6), False)
    def test_14(self):
        with self.assertRaises(TypeError):
            isPrime()
    def test_15(self):
        with self.assertRaises(NameError):
            isPrime(X)
    def test_16(self):
        with self.assertRaises(TypeError):
            isPrime("x")
    def test_17(self):
        with self.assertRaises(TypeError):
            isPrime(None)
    def test_18(self):
        with self.assertRaises(TypeError):
            isPrime(2,3)
    def test_19(self):
        self.assertEqual(largest(1,2,3),3)
    def test_20(self):
        self.assertEqual(largest(-1,-2,-3),-1)
    def test_21(self):
        self.assertEqual(largest(3,3,3),3)
    def test_22(self):
        with self.assertRaises(TypeError):
            largest()
    def test_23(self):
        with self.assertRaises(NameError):
            largest(X)
    def test_24(self):
        with self.assertRaises(TypeError):
            largest("x")
    def test_25(self):
        with self.assertRaises(TypeError):
            largest(None)
    def test_26(self):
        with self.assertRaises(TypeError):
            largest(2,3,5,6,7)
    def test_27(self):
        with self.assertRaises(TypeError):
            largest(2,3)
if __name__ == '__main__':
    unittest.main()
