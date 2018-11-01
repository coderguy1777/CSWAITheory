import numpy as np

neuraltraining_data = [
    [0,1,0],
    [1,0,0],
    [0,1,1],
    [1,0,1]
]

def sigmoid(x):
    return 1 / (1 + np.exp(-x))

def hyperbolictangent(x):
    return (1 - np.exp(-2 * x)) / (1 + np.exp(-2 * x))

def dsigmoid(y):
    return y * (1.0 - y)

class Neuron:
    def __init__(self, name, inputs):
        self.name = name
        self.inputs = inputs

class Inputs:
    def __init__(self, name, inputs):
        self.name = name
        self.inputs = inputs



