# -*- coding: utf-8 -*-
"""
Created on Sun Sep  2 12:34:37 2018

@author: oscar
"""

def selectionSort(arr):
    minimum = 0
    for i in range(len(arr)):
        for j in range(i, len(arr)):
            if(arr[minimum] > arr[j]):
                minimum = j
        arr[i], arr[minimum] = arr[minimum], arr[i]
        minimum = -1
    return arr
