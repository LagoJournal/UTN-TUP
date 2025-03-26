import math 

# Actividad 1
def actividad1():
    print("Hola Mundo!")

# Actividad 2
def actividad2(name: str):
    print(f"Hola {name}!")

# Actividad 3
def actividad3(name: str, age: int, residence: str):
    print(f"Soy {name}, tengo {age} y vivo en {residence}")

# Actividad 4
def actividad4(radius: int):
    area = math.pi * radius ** 2
    perimeter = 2 * math.pi * radius
    print(f"Area del circulo: {area}")
    print(f"Perimetro del circulo: {perimeter}")

# Actividad 5
def actividad5(seconds: int):
    hours = seconds / 3600
    print(f"Tiempo en horas: {hours}")

# Actividad 6
def actividad6(number:int):
    print(f"Tabla de multiplicar de {number}:")
    for i in range(1, 11):
        print(f"{i} x {number} = {i * number}")

# Actividad 7
def actividad7(number1: int, number2: int):
    if number1 == 0 or number2 == 0:
        print("Ingrese un numero distinto de cero")
    else:
        print(f"El resultado de {number1} + {number2} es {number1 + number2}")
        print(f"El resultado de {number1} x {number2} es {number1 * number2}")
        print(f"El resultado de {number1} ÷ {number2} es {number1 / number2}")
        print(f"El resultado de {number1} - {number2} es {number1 - number2}")

# Actividad 8
def actividad8(height: int, weight: int):
    imc = weight / (height ^ 2)
    print(f"El IMC es {imc}")

# Actividad 9
def actividad9(celsius: int):
    fahrenheit = celsius * 9 / 5 + 32
    print(f"El valor en Fahrenheit es {fahrenheit}")

# Actividad 10
def actividad10(number1: int, number2: int, number3: int):
    print(f"El promedio es {(number1 + number2 + number3) / 3}")


print("Programacion I TP1")
print("====================================")
print("Actividad 1")
actividad1()
print("====================================")
print("Actividad 2")
actividad2("Agustin")
print("====================================")
print("Actividad 3")
actividad3("Agustin", 25, "Argentina")
print("====================================")
print("Actividad 4")
actividad4(5)
print("====================================")
print("Actividad 5")
actividad5(3600)
print("====================================")
print("Actividad 6")
actividad6(5)
print("====================================")
print("Actividad 7")
actividad7(5, 2)
print("====================================")
print("Actividad 8")
actividad8(170, 70)
print("====================================")
print("Actividad 9")
actividad9(25)
print("====================================")
print("Actividad 10")
actividad10(5, 7, 14)