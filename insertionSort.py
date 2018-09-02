# -*- coding: utf-8 -*-
"""
Created on Sun Sep  2 12:33:56 2018

@author: oscar
"""

def insertionSort(arr):
    for i in range(1, len(arr)):
        for j in range(i-1,-1,-1):
            if(arr[i] < arr[j]):
                arr[i], arr[j] = arr[j], arr[i]
                i=j
            else:
                break
    return arr
