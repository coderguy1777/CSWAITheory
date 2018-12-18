# All the imports used for this project, in this case numpy
import numpy as np


# Training data list
x = [
      [1, 0],
      [0, 1],
      [1, 0],
      [0, 0],
 ]

idealout = [1, 0, 1, 0]

# Used for storing data for inputs
l = []

# loops it all into a 2d list
for iy in x:
    for iz in iy:
        l.append(iz)

# Sigmoid function
def sigmoid(x):
    return 1.0 / (1.0 + np.exp(-x))

# Sigmoid derivative
def sigderivative(x):
    return sigmoid(x)/(1.0 + sigmoid(x))


# Defines a Class for the Hidden Values to be stored in, along with inputs, outputs, etc.
# Using this class, these values are mainly stored as tuples in this case.
class Inputvals:
    def __init__(self, values, name):
        self.name = name
        self.values = values

    def valreturn(self):
        return self.values

    def nameval(self):
        return self.name

# Neuron class of the Neural Network for Backpropagation.
class Neuron(object):
    def __init__(self):
        self.learningrate = 0.7
        self.learningmomentum = 0.3
        self.weights = np.random.uniform(size=len(x) * 2) * 20 - 10
        self.bias = 1
        self.predic = 1
        self.weight2 = np.random.uniform(size=len(x) * 2) * 20 - 10
        self.fowardprop = {}
        self.h1arr = []
        self.error = 1.0
        self.h2arr = []
        self.h2vals = []
        self.outvals = []
        self.errors = []
        self.value = "Hidden Values"

    # Initial Calculations for hidden layer
    def calc11(self, x):
        z1 = np.dot(x, self.weights) + self.bias
        z2 = np.dot(x, self.weight2) + self.bias
        h1val = z1 + z2
        self.h1arr.append(self.sighvals(h1val))
        return self.sighvals(h1val)

    # For finding the sigmoid values of the hidden layer, aka activation function
    def sighvals(self, x1):
        return sigmoid(x1)

    # Sum for the output value
    def sumout(self, x2):
        for i in self.h1arr:
            self.h1sig(i)
        return sum(x2 * self.weight2) + sum(x2 * self.weight2)

    # Stores the foward prop values
    def fowardpropvals(self):
        for i in self.h1arr:
            self.fowardprop[self.value] = i
        return self.fowardprop


    # Used for finding the sig derivative for certain parts
    def sigderive(self, x4):
        return sigderivative(x4)


    # Sig derivative for the hidden layer values
    def h1sig(self, x11):
        return sigderivative(x11)


# Does the basic object for the neural network
hava = []
o = Neuron()
u = 0
# Does Not find the output sum right.
for i in l:
    u = o.calc11(l[i])
xx = o.sumout(u)

# Function that does the error function for the neural network
sigoutval = sigmoid(xx)
errors = []
error = 0 - sigoutval
errors.append(error)

# Does the delta sum for the neural
delsum = 0
delsumvar = []
for i in errors:
    delsum = sigderivative(sigoutval) * i
    delsumvar.append(delsum)

# Does the Gradient function for a neural network
gradient = []
gradfunc = 0
for i in o.h1arr:
    gradfunc = delsum * i
    gradient.append(gradfunc)
    o.weights += gradfunc

print(gradfunc)

# For Calculating the change in weights for the NN
Cn = 0
for i in gradfunc:
    Cn = (o.learningrate * i) + (o.learningmomentum * o.weights - o.weight2)
print(Cn)

iuxx = 0
for i in o.h1arr:
    iuxx = sigderivative(i)

print(iuxx)

