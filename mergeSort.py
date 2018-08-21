# -*- coding: utf-8 -*-
"""
Created on Mon Aug 20 19:38:31 2018

@author: oscar
"""
import math

def mergeSort(A, p, r):
    if p < r:
        q = (p+r)//2
        mergeSort(A, p, q)
        mergeSort(A, q+1, r)
        merge(A, p, q, r)
        
def merge(A, p, q, r):
    
    n1 = q - p + 1
    n2 = r - q
    L = [None] * (n1+1)
    R = [None] * (n2+1)
    
    for i in range(0, n1):
        L[i] = A[p+i]
        
    for j in range(0, n2):
        R[j] = A[q+j + 1]
    
    L[-1] = math.inf
    R[-1] = math.inf

    i = 0
    j = 0
    for k in range(p, r+1):
        if L[i] <= R[j]:
            A[k] = L[i]
            i = i + 1
        else:
            A[k] = R[j]
            j = j + 1

A = [5, 1, 2, 4, 6, 3, 1, 8, 7, 9]
mergeSort(A, 0, 8)
print(A)