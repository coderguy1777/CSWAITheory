# Imports used for the back propagation, that being np for calculations, and random for random weights
import numpy as np
import random

# Input training data list
neuraltraining_data = np.array([
     0, 1, 0,
     1, 0, 0,
     0, 1, 1,
     1, 0, 1
])

# List of random weights
weightlist = []

# for loop that Generates inputs for the list of random weights in between
# -10 and 10 to be used later on in the back propagation program.
for i in range(20):
    weightgeneration = random.randint(-10,  10)
    i = weightgeneration
    weightlist.append(weightgeneration)

# A List for inputs of training data.
inputlist = []
for i in neuraltraining_data:
    inputlist.append(i)

# Sigmoid Function for calculating the sigmoid
def sigmoid(x):
    return 1 / (1 + np.exp(x))

# Function for calculating sigmoid prime
def sigmoidprime(x):
    return (1. - sigmoid(x)) * sigmoid(x)

# List that stores the sigmoid outputs
sigmoids = []

# for l
for inputss in weightlist:
    functionvar = sigmoid(inputss)
    sigmoids.append(functionvar)

# List for storing the outputs of the hidden layer when it is calculated.
hiddenlayerinputss = []

# Method for calculating the hidden layer inputs.
def hiddenlayerinputs(w, inputtt, w2, inputtt2):
    y = inputtt * w + inputtt2 * w2
    return y


# Class used for writing the code for the neuron of the neural network for the project of backpropgation,
# and the used of XOR Gates within the project as well for the Backpropgation of the Neural Network.
class Neuron():
    def __init__(self):
        x = 0
        self.bias = .924
        self.weight = weightlist.pop(x)
        self.learningweight = 0.05


    def test(self, bias):
        self.bias = 1
        print(self.bias)


my_object = Neuron()
print(my_object.bias)
