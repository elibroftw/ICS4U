import functools


# memoize
@functools.lru_cache()
def fibo(n):
    if n == 1: return 1
    if n == 2: return 1
    return fibo(n-1) + fibo(n-2)

# def factorial(n):
#     r = 1
#     for i in range(0, n):
#         r = r * (i+1)
#     return r


def factorial(n):
    if n == 1: return 1
    return factorial(n-1) * n


def reverse(s):
    if len(s) == 1: return s
    return reverse(s[1:]) + s[0]


def isPalindrome(s):
    if len(s) == 1 or len(s) == 0: return True
    if s[0] != s[-1]: return False
    return isPalindrome(s[1:-2])


def polish_calculator(expression: str):
    if expression.startswith('('): expression = expression[1:]
    if expression.endswith(')'): expression = expression[:-1]
    tokens = expression.split(' ')
    if len(tokens) == 1: return int(expression)
    if len(tokens) == 3:
        if tokens[0] == '+':
            print(tokens)
            return int(tokens[1]) + int(tokens[2])
        if tokens[0] == '-': return int(tokens[1]) - int(tokens[2])
        if tokens[0] == '*': return int(tokens[1]) * int(tokens[2])
        return int(tokens[1]) / int(tokens[2])
    second = expression[expression.index('('):expression.index(')')+1]
    third = expression[expression.index(')') + 2:]
    if expression[0] == '+': return polish_calculator(second) + polish_calculator(third)
    if expression[0] == '-': return polish_calculator(second) - polish_calculator(third)
    if expression[0] == '*': return polish_calculator(second) * polish_calculator(third)
    return polish_calculator(second) / polish_calculator(third)


