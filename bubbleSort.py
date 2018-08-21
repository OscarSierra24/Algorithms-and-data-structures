# -*- coding: utf-8 -*-
"""
Created on Mon Aug 20 20:38:51 2018

@author: oscar
"""

def bubble_sort(A):
    swap_counter = -1
    
    while swap_counter != 0:
        swap_counter = 0
        for i in range(len(A) -1):
            if A[i] > A[i+1]:
                tmp = A[i+1]
                A[i+1] = A[i]
                A[i] = tmp
                swap_counter = swap_counter + 1
            
    
B = [34, 54, 12, 4, 2, 3, 122, 1]
bubble_sort(B)
print(B)